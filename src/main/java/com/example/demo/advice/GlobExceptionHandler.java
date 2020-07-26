package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobExceptionHandler {

    @ExceptionHandler(value = { Exception.class, ServiceException.class, RuntimeException.class })
    public void handleErrors(HttpServletRequest request,
                             HttpServletResponse response, Exception e) throws Exception {

        Integer code = 0;
        String messages ="";
        Object data = null;
        if (e instanceof ServiceException) {
            ServiceException ex = ((ServiceException) e);
            messages = ex.getMessage();
        }else {
            e.printStackTrace();
            messages = e.getMessage();
        }
        Map<String, Object> map = new HashMap();
        map.put("code", code);
        map.put("messages", messages);
        map.put("data", data);
        String text = JSON.toJSONString(map);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods",
                "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(text);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
