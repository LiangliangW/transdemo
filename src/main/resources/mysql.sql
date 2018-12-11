DROP DATABASE IF EXISTS `jta-income-slave`;

CREATE DATABASE `jta-income-slave`;

USE `jta-income-slave`;

DROP TABLE IF EXISTS `income`;

CREATE TABLE `income` (
  `id` INT(20) NOT NULL AUTO_INCREMENT,
  `userId` INT(20) NOT NULL,
  `amount` FLOAT(8,2) NOT NULL,
  `operateDate` DATETIME  NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP DATABASE IF EXISTS `jta-user-slave`;

CREATE DATABASE `jta-user-slave`;

USE `jta-user-slave`;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` INT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;