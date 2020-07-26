package com.example.demo.dao;

import com.example.demo.pojo.Transactions;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface TransaticonDao {

    void doinsert(Transactions transactions);

    void doUpdate(Transactions transactions);

    void doCancel(Transactions transactions);

    Transactions doSelectDesc(Transactions transactions);

    Transactions doSelectAsc(Transactions transactions);


}
