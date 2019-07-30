

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `current_balance` double DEFAULT NULL,
  `account_number` varchar(45) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `mobile_no` varchar(20) DEFAULT NULL,
  `current_location` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL,
  `created_by` bigint(20) unsigned DEFAULT NULL,
  `updated_by` bigint(20) unsigned DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`id`,`current_balance`,`account_number`,`first_name`,`last_name`,`email`,`mobile_no`,`current_location`,`created_at`,`updated_at`,`created_by`,`updated_by`,`status`) values (100,49980,'1234567890123456','Sajjad','Hussain','sajjadbabar5@gmail.com','00971506036419','UAE','2019-07-29 17:22:20','2019-07-30 08:14:05',NULL,NULL,1),(101,40020,'1234567890123457','Awais','Iqbal','awais@gmail.com','00971506036418','Pakistan','2019-07-29 17:23:10','2019-07-30 08:14:05',NULL,NULL,1);

DROP TABLE IF EXISTS `user_transaction_history`;

CREATE TABLE `user_transaction_history` (
  `transaction_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `transaction_type` varchar(45) DEFAULT NULL,
  `reference_account` varchar(45) DEFAULT NULL,
  `transaction_amount` double DEFAULT NULL,
  `current_balance` double DEFAULT NULL,
  `transaction_status` varchar(45) DEFAULT NULL,
  `user_id` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `mobile_no` varchar(20) DEFAULT NULL,
  `current_location` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL,
  `created_by` bigint(20) unsigned DEFAULT NULL,
  `updated_by` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `user_transaction_history` */

insert  into `user_transaction_history`(`transaction_id`,`transaction_type`,`reference_account`,`transaction_amount`,`current_balance`,`transaction_status`,`user_id`,`email`,`mobile_no`,`current_location`,`created_at`,`updated_at`,`created_by`,`updated_by`) values (3,'Transfer','1234567890123457',20,49980,'Success','100',NULL,NULL,NULL,'2019-07-30 08:14:05','2019-07-30 08:14:05',NULL,NULL),(4,'Deposit','1234567890123456',20,40020,'Success','101',NULL,NULL,NULL,'2019-07-30 08:14:05','2019-07-30 08:14:05',NULL,NULL);
