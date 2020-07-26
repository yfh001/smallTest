package com.example.demo.service;

import com.example.demo.pojo.Transactions;

import java.rmi.ServerException;

public interface TransactionService {


     void doinsert(Transactions transactions);

     void doUpdate(Transactions transactions) throws ServerException;

     void doCancel(Transactions transactions);

     void opersForTransactions(Transactions transactions) throws ServerException;
}
