package com.example.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.ResultData;
import com.example.demo.pojo.Transactions;
import com.example.demo.service.TransactionService;
import com.example.demo.utils.ParamsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;

@RestController
@RequestMapping("/transaction")
public class TransactionController {


    @Autowired
    TransactionService TransactionService;

    @PostMapping("/opersForCUR")
    public ResultData  opersForTransactionsRecord(@RequestBody String params){
        ResultData resultData = null;
        Transactions transactions = JSONObject.parseObject(params,Transactions.class);
        try {
            ParamsValidate.validateTransactions(transactions);
            TransactionService.opersForTransactions(transactions);
            resultData = ResultData.success();
        } catch (ServerException e) {
            resultData = ResultData.failure(e.getMessage());
        }catch (Exception e){
            resultData = ResultData.failure(e.getMessage());
        }
        return  resultData;
    }



}
