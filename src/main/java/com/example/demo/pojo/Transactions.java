package com.example.demo.pojo;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Validated
public class Transactions implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long transactionId;

    private String tradeId ;

    private Byte version;

    @NotNull
    private String securityCode;

    @NotNull
    private Long quantity;
   // Insert, Update,Cancel   opertype=version
   @NotNull
    private String operType;
    // Buy 1 ,Sell -1
    @NotNull
    private String  tadeType;

}
