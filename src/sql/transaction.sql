CREATE DATABASE transaction;

drop table  if exists transactions ;

CREATE TABLE `transactions` (
  `transactionId` bigint NOT NULL AUTO_INCREMENT,
  `tradeId` varchar(20) NOT NULL,
  `version` tinyint NOT NULL COMMENT '1-insert,2-update,3-cancel',
  `securityCode` varchar(3) NOT NULL,
  `quantity` bigint NOT NULL,
  `operType` varchar(6) NOT NULL COMMENT 'insert,update,cancel',
  `tadeType` varchar(4) NOT NULL COMMENT 'buy,sell',
  PRIMARY KEY (`transactionId`),
  KEY `idx_tid_ver` (`tradeId`,`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;