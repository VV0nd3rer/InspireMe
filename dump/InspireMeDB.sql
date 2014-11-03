/*
SQLyog Community v12.01 (64 bit)
MySQL - 5.6.16 : Database - inspireme
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`inspireme` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `inspireme`;

/*Table structure for table `countries` */

DROP TABLE IF EXISTS `countries`;

CREATE TABLE `countries` (
  `country_code` varchar(4) NOT NULL,
  `country_name` varchar(45) DEFAULT NULL,
  `img_path` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`country_code`),
  UNIQUE KEY `country_code_UNIQUE` (`country_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `countries` */

insert  into `countries`(`country_code`,`country_name`,`img_path`) values ('cz','Czech Republic','CzechRepublic.png'),('it','Italy','Italy.png'),('sk','Slovakia','Slovakia.png'),('ua','Ukraine','Ukraine.png'),('uk','United Kingdom','UnitedKingdom.png');

/*Table structure for table `countries_sights` */

DROP TABLE IF EXISTS `countries_sights`;

CREATE TABLE `countries_sights` (
  `sight_id` int(11) NOT NULL AUTO_INCREMENT,
  `sight_label` varchar(100) NOT NULL,
  `country_code` varchar(2) NOT NULL,
  `img_url` varchar(100) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`sight_id`),
  KEY `country_code` (`country_code`),
  KEY `userId` (`userId`),
  CONSTRAINT `countries_sights_ibfk_1` FOREIGN KEY (`country_code`) REFERENCES `countries` (`country_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `countries_sights_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

/*Data for the table `countries_sights` */

insert  into `countries_sights`(`sight_id`,`sight_label`,`country_code`,`img_url`,`description`,`userId`) values (50,'sdsds','cz','Macro-Leaf-Orange-Autumn.jpg','dsdd',6),(51,'Test sight','cz','mountains.jpg','test',6);

/*Table structure for table `messages` */

DROP TABLE IF EXISTS `messages`;

CREATE TABLE `messages` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(150) DEFAULT NULL,
  `text` text,
  `from_id` int(11) NOT NULL,
  `to_id` int(11) NOT NULL,
  `status` smallint(6) NOT NULL,
  `date` datetime NOT NULL,
  `removed_by` int(11) DEFAULT '0',
  PRIMARY KEY (`message_id`),
  KEY `messages_ibfk_1` (`from_id`),
  KEY `messages_ibfk_2` (`to_id`),
  CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`from_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`to_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `messages` */

insert  into `messages`(`message_id`,`subject`,`text`,`from_id`,`to_id`,`status`,`date`,`removed_by`) values (4,'answer','And to you hello!',6,7,1,'2014-10-26 14:18:06',0),(7,'test','qwerty',8,6,1,'2014-10-24 14:27:08',0),(9,'RE:test','<p>REPLY 2.</p>',6,8,1,'2014-10-31 16:03:10',0),(10,'RE:test','<p>WWWW</p>',6,8,1,'2014-10-31 16:06:04',0),(12,'qqO','',6,9,0,'2014-11-02 21:56:13',0),(13,'qqP','',6,8,0,'2014-11-02 23:54:47',0),(15,'hello','<p>hello xamp</p>',6,18,0,'2014-11-03 17:29:15',0);

/*Table structure for table `posts` */

DROP TABLE IF EXISTS `posts`;

CREATE TABLE `posts` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `text` text NOT NULL,
  `sight_id` int(11) NOT NULL,
  `description` varchar(250) NOT NULL,
  `userId` int(11) NOT NULL,
  `stamp_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `stamp_updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`post_id`),
  KEY `username` (`userId`),
  KEY `sight_id` (`sight_id`),
  CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`user_id`),
  CONSTRAINT `sight_id` FOREIGN KEY (`sight_id`) REFERENCES `countries_sights` (`sight_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `posts` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`role_id`,`rolename`) values (1,'user'),(2,'admin');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  UNIQUE KEY `user_role_idx_1` (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`user_id`,`role_id`) values (6,1),(7,1),(8,1),(9,1),(18,1);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(64) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`),
  KEY `userId` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`user_id`,`username`,`password`,`enabled`) values (6,'FlyinGMind','$2a$10$oeW5cEUtxdvCWMaj0nVJ.uUh1Vjn/v0QJMH599Zfy/tQx4gB1Fn9.',1),(7,'Vasya','$2a$10$W.6EDTwrMp4n97F/pHkzRuFN8y7cKDok.lg4yiIdX6NMGWFZm6vHC',1),(8,'Petya','$2a$10$Adkflw72.4jueJqRFo9vi.bzH/yMfQ8R05tiDbxPXi5ePdV459X4m',1),(9,'Olechka','$2a$10$kdxADPcs3zMmukd.dmbXQ.hZgFr.2IVt4jJxy.DOf15h2U2CLEl7u',1),(18,'xampp','$2a$10$pHQXKCPYQeEA4.oEVFh4W.Pbx98hzbLvlIZk6Rx7Bzsqyrw49d9P2',1);

/*Table structure for table `users_data` */

DROP TABLE IF EXISTS `users_data`;

CREATE TABLE `users_data` (
  `user_id` int(11) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `e_mail` varchar(60) DEFAULT NULL,
  `avatar_url` varchar(250) DEFAULT 'noavatar.jpg',
  `about` text,
  `country_code` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `country_code` (`country_code`),
  CONSTRAINT `users_data_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `users_data_ibfk_2` FOREIGN KEY (`country_code`) REFERENCES `countries` (`country_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `users_data` */

insert  into `users_data`(`user_id`,`first_name`,`last_name`,`e_mail`,`avatar_url`,`about`,`country_code`) values (6,'Kolya','Pylypets','giperborej1992@gmail.com','Macro-Leaf-Orange-Autumn.jpg','Good guy.','cz'),(7,'&#1057;&#1072;&#1096;&#1072;','&#1043;&#1086;&#1083;&#1099;&#1081;','','GOLOSKOKOV.jpg','','ua'),(8,NULL,NULL,NULL,'noavatar.jpg',NULL,NULL),(9,NULL,NULL,NULL,'noavatar.jpg',NULL,NULL),(18,NULL,NULL,NULL,'noavatar.jpg',NULL,NULL);

/*Table structure for table `users_friends` */

DROP TABLE IF EXISTS `users_friends`;

CREATE TABLE `users_friends` (
  `friend_one_id` int(11) NOT NULL,
  `friend_two_id` int(11) NOT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`friend_one_id`,`friend_two_id`),
  KEY `friend_two_id` (`friend_two_id`),
  CONSTRAINT `users_friends_ibfk_3` FOREIGN KEY (`friend_one_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `users_friends_ibfk_4` FOREIGN KEY (`friend_two_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `users_friends` */

insert  into `users_friends`(`friend_one_id`,`friend_two_id`,`status`) values (6,7,0),(6,8,0),(9,6,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
