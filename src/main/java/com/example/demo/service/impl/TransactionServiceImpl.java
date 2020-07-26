package com.example.demo.service.impl;

import com.example.demo.dao.TransaticonDao;
import com.example.demo.pojo.Transactions;
import com.example.demo.service.TransactionService;
import com.example.demo.utils.GlobalUniqueId;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.ServerException;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransaticonDao transaticonDao;

    @Autowired
    GlobalUniqueId globalUniqueId;

    @Autowired
    RedissonClient redissonClient;

    @Override
    public void doinsert(Transactions transactions) {
        Transactions transc = transaticonDao.doSelectAsc(transactions);
        if(null != transc)
            return;
        transaticonDao.doinsert(transactions);
    }

    /**
     *
     * @param transactions
     * @throws ServerException
     * every oper will generate one new record
     */
    @Override
    public void doUpdate(Transactions transactions) throws ServerException {
        Transactions transc = transaticonDao.doSelectDesc(transactions);
        if(transc.getOperType().equals("CANCEL") ){
            throw new ServerException("can't repeato do CANCLE oper");
        }
        transc.setSecurityCode(transactions.getSecurityCode());
        transc.setTadeType(transactions.getTadeType());
        transc.setOperType(transactions.getOperType());
        Long quantityNew = transactions.getQuantity();
        Long quantityOld = transc.getQuantity();
        Long totl = quantityNew + quantityOld;
        if(totl < 0){
            throw new ServerException("quantity less than zero");
        }
        transc.setQuantity(totl);
        transaticonDao.doinsert(transc);
    }

    /**
     * @param transactions
     * cancel oper retrurn to insert status but operType is cancel
     */
    @Override
    public void doCancel(Transactions transactions) {
        Transactions transcfirst = transaticonDao.doSelectAsc(transactions);
        Transactions transclast = transaticonDao.doSelectDesc(transactions);
        if(transclast.getOperType().equals("CANCEL") )
            return;
        transcfirst.setOperType(transactions.getOperType());
        transaticonDao.doinsert(transcfirst);
    }

    @Override
    public  void opersForTransactions(Transactions transactions) throws ServerException {

        String tradeId = transactions.getTradeId();
        //distribute lock on tradeId
        RLock lock = redissonClient.getLock(tradeId);
        lock.lock();
        String operType = transactions.getOperType();
        switch(operType){
            case "INSERT":
                doinsert(transactions);
                break;
            case "UPDATE":
                doUpdate(transactions);
                break;
            default:
                doCancel(transactions);
                break;
        }
        lock.unlock();
    }

}
