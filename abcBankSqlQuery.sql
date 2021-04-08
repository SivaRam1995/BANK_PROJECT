CREATE DATABASE `bank`;
CREATE TABLE `bank`.`banklog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bankname` varchar(45) DEFAULT NULL,
  `acctype` varchar(45) DEFAULT NULL,
  `accnum` varchar(45) DEFAULT NULL,
  `pin` int DEFAULT NULL,
  `userimage` longblob,
  `name` varchar(45) DEFAULT NULL,
  `loginid` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `dob` varchar(10) DEFAULT NULL,
  `mobile` varchar(10) DEFAULT NULL,
  `gender` varchar(6) DEFAULT NULL,
  `aadhar` varchar(45) DEFAULT NULL,
  `nationality` varchar(45) DEFAULT NULL,
  `balance` double DEFAULT '0',
  `status` varchar(45) DEFAULT 'ACTIVATE',
  PRIMARY KEY (`id`),
  UNIQUE KEY `accnum_UNIQUE` (`accnum`),
  UNIQUE KEY `loginid_UNIQUE` (`loginid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `bank`.`emplogin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `empname` varchar(45) DEFAULT NULL,
  `loginid` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `photo` longblob,
  `dob` varchar(10) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `mobile` varchar(10) DEFAULT NULL,
  `aadhar` varchar(45) DEFAULT NULL,
  `gender` varchar(6) DEFAULT NULL,
  `nationality` varchar(45) DEFAULT NULL,
  `designation` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `loginid_UNIQUE` (`loginid`),
  UNIQUE KEY `empname_UNIQUE` (`empname`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `bank`.`abc_bank_transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `TransactionDate` varchar(45) DEFAULT NULL,
  `fromAccNum` varchar(45) DEFAULT NULL,
  `toAccNum` varchar(45) DEFAULT NULL,
  `Amount` double DEFAULT NULL,
  `Cre_or_Deb` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
