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
  CONSTRAINT `countries_sights_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`),
  CONSTRAINT `countries_sights_ibfk_1` FOREIGN KEY (`country_code`) REFERENCES `countries` (`country_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `countries_sights` */

insert  into `countries_sights`(`sight_id`,`sight_label`,`country_code`,`img_url`,`description`,`userId`) values (17,'Orloy','cz','Orloy.jpg','Orloy',6);

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
  CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`),
  CONSTRAINT `sight_id` FOREIGN KEY (`sight_id`) REFERENCES `countries_sights` (`sight_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `posts` */

insert  into `posts`(`post_id`,`title`,`text`,`sight_id`,`description`,`userId`,`stamp_created`,`stamp_updated`) values (2,'Autumn in Prague','<p><span style=\"color: #333399;\">The city begins to shed the frenetic attitude resulting from the multitude of international tourists that pack its main thoroughfare that make any visit to major sights a significant time investment. The fall-weather nip starts to be felt in the air, creating the perfect excuse to warm up with a glass of Czech beer or a hearty meal in one of the city\'s restaurants. Even if you\'ve traveled to Prague before during another time of year, consider visiting in the autumn; it may very well become your favorite season to enjoy the City of a Thousand Spires. </span></p>\r\n<p><span style=\"color: #333399;\"><img style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"http://0.tqn.com/y/goeasteurope/1/L/H/W/-/-/Rust-Colored-Leaves-Prague.jpg\" alt=\"\" width=\"300\" height=\"400\" /></span></p>\r\n<h3><span style=\"color: #ff9900;\">Fall Activities</span></h3>\r\n<p><span style=\"color: #008000;\">Travel to Prague in autumn is a relaxed affair. Fewer lines at major attractions such as St. Vitus Cathedral makes taking in the city enjoyable, so be sure to hit <span style=\"color: #ff6600;\"><a style=\"color: #ff6600;\" href=\"http://goeasteurope.about.com/od/czechrepublic/tp/praguesights.htm\" data-component=\"link\" data-source=\"inlineLink\" data-type=\"internalLink\" data-ordinal=\"3\">Prague\'s must-see sights</a></span> or any other sights that you have missed on previous trips to the Czech capital.</span></p>\r\n<p><span style=\"color: #008000;\">Tuck into some hearty Czech fare, which may be too heavy for summer weather, while you\'re in Prague during fall. Meat dishes are most often accompanied by potato or <span style=\"color: #ff6600;\"><a style=\"color: #ff6600;\" href=\"http://easteuropeanfood.about.com/od/bohemianczechdumplings/r/breaddumplings.htm\" data-component=\"link\" data-source=\"inlineLink\" data-type=\"internalLink\" data-ordinal=\"4\">bread dumplings</a></span>. You\'ll also find warming soups and stews on Czech restaurant menus. Prague\'s restaurants will keep their patios open as long as weather permits&mdash;outdoor heaters will keep you cozy and allow you to watch evening fall as you enjoy your meal.</span></p>\r\n<p><span style=\"color: #008000;\">Consider participating in one of Prague\'s seasonal events or checking museums for special exhibitions. When the weather gets too cold for outdoor sightseeing, head indoors to any one of <span style=\"color: #ff6600;\"><a style=\"color: #ff6600;\" href=\"http://goeasteurope.about.com/od/praguetravel/tp/praguemuseums.htm\" data-component=\"link\" data-source=\"inlineLink\" data-type=\"internalLink\" data-ordinal=\"5\">Prague\'s museums</a></span></span></p>\r\n<p><span style=\"color: #008000;\"><span style=\"color: #ff6600;\"><iframe style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"//www.youtube.com/embed/xTkkkgfAdO4\" width=\"425\" height=\"350\"></iframe></span></span></p>',17,'Autumn is a great time to travel to Prague',6,NULL,NULL);

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
  `userId` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`role_id`),
  UNIQUE KEY `user_role_idx_1` (`userId`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`userId`,`role_id`) values (6,1),(7,1),(8,1),(9,1);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(64) NOT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`userId`),
  KEY `userId` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`userId`,`username`,`password`,`enabled`) values (6,'FlyinGMind','$2a$10$oeW5cEUtxdvCWMaj0nVJ.uUh1Vjn/v0QJMH599Zfy/tQx4gB1Fn9.',1),(7,'Vasya','$2a$10$W.6EDTwrMp4n97F/pHkzRuFN8y7cKDok.lg4yiIdX6NMGWFZm6vHC',1),(8,'Petya','$2a$10$Adkflw72.4jueJqRFo9vi.bzH/yMfQ8R05tiDbxPXi5ePdV459X4m',1),(9,'Olechka','$2a$10$kdxADPcs3zMmukd.dmbXQ.hZgFr.2IVt4jJxy.DOf15h2U2CLEl7u',1);

/*Table structure for table `users_friends` */

DROP TABLE IF EXISTS `users_friends`;

CREATE TABLE `users_friends` (
  `friendOneId` int(11) NOT NULL,
  `friendTwoId` int(11) NOT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`friendOneId`,`friendTwoId`),
  KEY `friendTwo` (`friendTwoId`),
  CONSTRAINT `users_friends_ibfk_1` FOREIGN KEY (`friendOneId`) REFERENCES `users` (`userId`),
  CONSTRAINT `users_friends_ibfk_2` FOREIGN KEY (`friendTwoId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `users_friends` */

insert  into `users_friends`(`friendOneId`,`friendTwoId`,`status`) values (6,7,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
