package com.example.demo.exceptions;

public class ServiceException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        private Integer code;

        private String messages;

        private Object data;

        public ServiceException(Integer code, String message) {
            super(message);
            this.code = code;
         }

        public ServiceException() {
            super();
            this.code = 0;
        }

        public ServiceException(String message) {
            super(message);
            this.code = 0;
        }





}
