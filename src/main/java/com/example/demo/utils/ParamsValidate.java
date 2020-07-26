package com.example.demo.utils;

import com.example.demo.common.Constants;
import com.example.demo.pojo.Transactions;
import org.apache.commons.lang3.StringUtils;

import java.rmi.ServerException;

import static com.example.demo.common.Constants.SECURITY_CODE;
import static com.example.demo.common.Constants.TRADE_TYPE;


public class ParamsValidate {

    public static void  validateTransactions(Transactions transactions) throws ServerException {
         String SecurityCode = transactions.getSecurityCode();
         if(SECURITY_CODE.indexOf(SecurityCode)<-1){
                throw new ServerException("SecurityCode error");
         }
        String  tadeType = transactions.getTadeType();
        if(TRADE_TYPE.indexOf(tadeType)<-1)    {
            throw new ServerException("tadeType error");
        }
        Long  quantity = transactions.getQuantity();
        if(quantity <= 0){
            throw new ServerException("quantity error");
        }
    }
}
