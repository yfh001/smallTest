package com.example.demo.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultData implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String messages;

    private Object data;


    public static ResultData  failure(String messages){
        ResultData resultData = new ResultData();
        resultData.setCode(0);
        resultData.setMessages(messages);
        return  resultData;
    }

    public static ResultData  failure(){
        ResultData resultData = new ResultData();
        resultData.setCode(0);
        resultData.setMessages("failuer");
        return  resultData;
    }

    public static ResultData  success(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(1);
        resultData.setMessages("success");
        resultData.setData(data);
        return  resultData;
    }

    public static ResultData  success(){
        ResultData resultData = new ResultData();
        resultData.setCode(1);
        resultData.setMessages("success");
        return  resultData;
    }
}
