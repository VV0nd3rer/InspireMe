/*
SQLyog Community v12.01 (32 bit)
MySQL - 5.6.20-log : Database - inspireme
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
  PRIMARY KEY (`sight_id`),
  KEY `country_code` (`country_code`),
  CONSTRAINT `countries_sights_ibfk_1` FOREIGN KEY (`country_code`) REFERENCES `countries` (`country_code`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `countries_sights` */

insert  into `countries_sights`(`sight_id`,`sight_label`,`country_code`,`img_url`,`description`) values (2,'Vyshehrad','cz','Vysehrad.jpg',''),(3,'Karlov most','cz','Karlov_most.jpg',NULL),(4,'Petrshin','cz','Petrshin.jpg',NULL),(5,'Orloy','cz','Orloy.jpg',NULL),(6,'Karlshtejn','cz','Karlstejn.jpg',NULL),(7,'Big ban','uk',NULL,NULL);

/*Table structure for table `posts` */

DROP TABLE IF EXISTS `posts`;

CREATE TABLE `posts` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `text` text NOT NULL,
  `sight_id` int(11) NOT NULL,
  `description` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`post_id`),
  KEY `username` (`username`),
  KEY `sight_id` (`sight_id`),
  CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sight_id` FOREIGN KEY (`sight_id`) REFERENCES `countries_sights` (`sight_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `posts` */

insert  into `posts`(`post_id`,`title`,`text`,`sight_id`,`description`,`username`) values (2,'Second post','Hi, I am a nice cat!',3,'Second post','FlyinGMind'),(3,'Vyshegrad','<p>About this place on Chech language.</p>\r\n<p><img src=\"http://www.stopin-praha.cz/images/articles/kostel1.jpg\" alt=\"Vy&scaron;ehrad\" width=\"400\" height=\"514\" /></p>\r\n<p>Vy&scaron;ehrad je historick&eacute; opevn&igrave;n&iacute; na sk&aacute;le nad prav&yacute;m b&oslash;ehem &oslash;eky Vltavy v Praze a takt&eacute; stejnojmenn&aacute; &egrave;tvr na jihu spr&aacute;vn&iacute;ho obvodu a m&igrave;stsk&eacute; &egrave;&aacute;sti Prahy 2, zauj&iacute;maj&iacute;c&iacute; jeho bezprost&oslash;edn&iacute; okol&iacute;. V&aacute;e se k n&igrave;mu &oslash;ada pov&igrave;st&iacute; z po&egrave;&aacute;tk&ugrave; &egrave;esk&yacute;ch d&igrave;jin.</p>\r\n<p><object width=\"422\" height=\"94\"><param value=\"http://www.divshare.com/flash/audio_embed?data=YTo2OntzOjU6ImFwaUlkIjtzOjE6IjQiO3M6NjoiZmlsZUlkIjtzOjg6IjI2MTE1Nzg0IjtzOjQ6ImNvZGUiO3M6MTI6IjI2MTE1Nzg0LWFkOCI7czo2OiJ1c2VySWQiO3M6NzoiMzI4MjQxMiI7czoxMjoiZXh0ZXJuYWxDYWxsIjtpOjE7czo0OiJ0aW1lIjtpOjE0MTA4ODg5NTA7fQ==&amp;autoplay=default\" name=\"movie\" /><param name=\"allowFullScreen\" value=\"true\" /><param name=\"allowscriptaccess\" value=\"always\" /><param name=\"wmode\" value=\"transparent\" /><embed wmode=\"transparent\" height=\"94\" width=\"422\" type=\"application/x-shockwave-flash\" allowscriptaccess=\"always\" allowfullscreen=\"allowfullscreen\" src=\"http://www.divshare.com/flash/audio_embed?data=YTo2OntzOjU6ImFwaUlkIjtzOjE6IjQiO3M6NjoiZmlsZUlkIjtzOjg6IjI2MTE1Nzg0IjtzOjQ6ImNvZGUiO3M6MTI6IjI2MTE1Nzg0LWFkOCI7czo2OiJ1c2VySWQiO3M6NzoiMzI4MjQxMiI7czoxMjoiZXh0ZXJuYWxDYWxsIjtpOjE7czo0OiJ0aW1lIjtpOjE0MTA4ODg5NTA7fQ==&amp;autoplay=default\" /></object></p>',2,'It must be work!','FlyinGMind'),(5,'Java developers','<p>Two cats love each other.</p>\r\n<p><iframe src=\"//www.youtube.com/embed/uXlzBKzjlcQ\" width=\"420\" height=\"315\" frameborder=\"0\" allowfullscreen=\"allowfullscreen\"></iframe></p>',2,'Try it!','FlyinGMind'),(11,'Amazing travelling','Vyehrad je historické opevnìní na skále nad pravým bøehem øeky Vltavy v Praze a takté stejnojmenná ètvr na jihu správního obvodu a mìstské èásti Prahy 2, zaujímající jeho bezprostøední okolí. Váe se k nìmu øada povìstí z poèátkù èeských dìjin. ',2,'I want to tell you about blue sky!','FlyinGMind'),(14,'Travel in the another world','<p>In to another world. We have another atmosphere and just wont to bring you special things.</p>\r\n<p><img src=\"http://cs620028.vk.me/v620028599/15c7c/fRCfyXcj_Yg.jpg\" alt=\"\" width=\"500\" height=\"592\" /></p>',2,'Hello. In the next hour you will taken with us.','FlyinGMind'),(15,'Prokop valley','<p><strong>Prokop valley</strong> (<a title=\"Czech language\" href=\"https://en.wikipedia.org/wiki/Czech_language\">Czech</a>: <span lang=\"cs\" xml:lang=\"cs\"><em>Prokopsk&eacute; &uacute;dol&iacute;</em></span>) is a recreational area in south-western <a title=\"Prague\" href=\"https://en.wikipedia.org/wiki/Prague\">Prague</a>, located in the districts of <a title=\"Barrandov\" href=\"https://en.wikipedia.org/wiki/Barrandov\">Barrandov</a>, <a class=\"new\" title=\"Holyn&igrave; (page does not exist)\" href=\"https://en.wikipedia.org/w/index.php?title=Holyn%C4%9B&amp;action=edit&amp;redlink=1\">Holyn&igrave;</a>, <a title=\"&Oslash;eporyje\" href=\"https://en.wikipedia.org/wiki/%C5%98eporyje\">&Oslash;eporyje</a>, <a title=\"Stod&ugrave;lky\" href=\"https://en.wikipedia.org/wiki/Stod%C5%AFlky\">Stod&ugrave;lky</a> and <a class=\"new\" title=\"Hlubo&egrave;epy (page does not exist)\" href=\"https://en.wikipedia.org/w/index.php?title=Hlubo%C4%8Depy&amp;action=edit&amp;redlink=1\">Hlubo&egrave;epy</a>. It encompasses two streams, Dalejsk&yacute; potok and Prokopsk&yacute; potok, the latter of which is surrounded by a valley, despite the fact that it is much shorter. The area includes a <a class=\"mw-redirect\" title=\"Natural reserve\" href=\"https://en.wikipedia.org/wiki/Natural_reserve\">natural reserve</a> which encompasses a far wider area than the valley.</p>',2,'Prokopské údolí','FlyinGMind');

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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_role_idx_1` (`username`),
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`role_id`,`username`) values (1,1,'FlyinGMind'),(4,1,'Astrid'),(9,1,'BigBoom');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(64) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`username`,`password`,`enabled`) values ('Astrid','$2a$10$udiPCmEOwvRIUVALGyV3V.ePUwE2kJhT2uaKuRgXPjNG7pzthH9NO',1),('BigBoom','$2a$10$n0770NyWkqbjSX3Sl8T7e.vKtIzKmrK3yrwN0UQl9nPmFdSEu23wu',1),('FlyinGMind','$2a$10$D2AOK6I7MWZR0PTfykxtEus3hlT21BES0m01vQBMFbkukRGJGVUbK',1),('Kverchi','$2a$10$MPf/0ghp0jT360QZor1vNudQ/QIPhEBS.p3IC/xtMh5IyQaqEkfyW',1),('LaVon','bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
