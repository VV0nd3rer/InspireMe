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
  `username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`sight_id`),
  KEY `country_code` (`country_code`),
  KEY `username` (`username`),
  CONSTRAINT `countries_sights_ibfk_1` FOREIGN KEY (`country_code`) REFERENCES `countries` (`country_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `countries_sights_ibfk_2` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `countries_sights` */

insert  into `countries_sights`(`sight_id`,`sight_label`,`country_code`,`img_url`,`description`,`username`) values (2,'Vyshehrad','cz','Vysehrad.jpg','','FlyinGMind'),(3,'Karlov most','cz','Karlov_most.jpg',NULL,'FlyinGMind'),(4,'Petrshin','cz','Petrshin.jpg',NULL,'FlyinGMind'),(6,'Karlshtejn','cz','Karlstejn.jpg',NULL,'FlyinGMind'),(7,'Big ban','uk',NULL,NULL,'FlyinGMind'),(11,'Orloy','cz','Orloy.jpg','','FlyinGMind'),(13,'TestAstridSights','cz','GLHF.jpg','','Astrid');

/*Table structure for table `posts` */

DROP TABLE IF EXISTS `posts`;

CREATE TABLE `posts` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `text` text NOT NULL,
  `sight_id` int(11) NOT NULL,
  `description` varchar(250) NOT NULL,
  `username` varchar(50) NOT NULL,
  `stamp_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `stamp_updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`post_id`),
  KEY `username` (`username`),
  KEY `sight_id` (`sight_id`),
  CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sight_id` FOREIGN KEY (`sight_id`) REFERENCES `countries_sights` (`sight_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `posts` */

insert  into `posts`(`post_id`,`title`,`text`,`sight_id`,`description`,`username`,`stamp_created`,`stamp_updated`) values (2,'Second post','Hi, I am a nice cat!',3,'Second post','FlyinGMind','2014-09-21 10:00:00','2014-09-25 19:20:52'),(14,'Travel in the another world','<p>In to another world. We have another atmosphere and just wont to bring you special things.</p>\r\n<p><img src=\"http://cs620028.vk.me/v620028599/15c7c/fRCfyXcj_Yg.jpg\" alt=\"\" width=\"500\" height=\"592\" /></p>',2,'Hello. In the next hour you will taken with us.','FlyinGMind','2014-09-21 18:00:00','2014-09-25 19:20:44'),(15,'Java developers','<p><span class=\"post-b\"><span class=\"p-color\" style=\"color: #006699;\">&#1055;&#1086;&#1089;&#1083;&#1077; &#1086;&#1082;&#1086;&#1085;&#1095;&#1072;&#1085;&#1080;&#1103; &#1082;&#1086;&#1083;&#1083;&#1077;&#1076;&#1078;&#1072; &#1069;&#1084;&#1086;&#1088;&#1080; &#1086;&#1076;&#1080;&#1085; &#1080;&#1079; &#1077;&#1075;&#1086; &#1074;&#1077;&#1076;&#1091;&#1097;&#1080;&#1093; &#1089;&#1090;&#1091;&#1076;&#1077;&#1085;&#1090;&#1086;&#1074; &#1080; &#1072;&#1090;&#1083;&#1077;&#1090;&#1086;&#1074; &#1050;&#1088;&#1080;&#1089;&#1090;&#1086;&#1092;&#1077;&#1088; &#1052;&#1072;&#1082;&#1050;&#1101;&#1085;&#1076;&#1083;&#1077;&#1089;&#1089; &#1086;&#1089;&#1090;&#1072;&#1074;&#1083;&#1103;&#1077;&#1090; &#1074;&#1089;&#1077; &#1089;&#1074;&#1086;&#1077; &#1080;&#1084;&#1091;&#1097;&#1077;&#1089;&#1090;&#1074;&#1086;, &#1086;&#1090;&#1076;&#1072;&#1077;&#1090; &#1085;&#1072;&#1082;&#1086;&#1087;&#1083;&#1077;&#1085;&#1085;&#1099;&#1077; &#1079;&#1072; &#1074;&#1088;&#1077;&#1084;&#1103; &#1091;&#1095;&#1077;&#1073;&#1099; 24 &#1090;&#1099;&#1089;. &#1076;&#1086;&#1083;&#1083;&#1072;&#1088;&#1086;&#1074; &#1074; &#1073;&#1083;&#1072;&#1075;&#1086;&#1090;&#1074;&#1086;&#1088;&#1080;&#1090;&#1077;&#1083;&#1100;&#1085;&#1099;&#1081; &#1092;&#1086;&#1085;&#1076;, &#1080; &#1086;&#1090;&#1087;&#1088;&#1072;&#1074;&#1083;&#1103;&#1077;&#1090;&#1089;&#1103; &#1072;&#1074;&#1090;&#1086;&#1089;&#1090;&#1086;&#1087;&#1086;&#1084; &#1085;&#1072; &#1040;&#1083;&#1103;&#1089;&#1082;&#1091;, &#1095;&#1090;&#1086;&#1073;&#1099; &#1086;&#1082;&#1091;&#1085;&#1091;&#1090;&#1100;&#1089;&#1103; &#1074; &#1076;&#1080;&#1082;&#1091;&#1102; &#1087;&#1088;&#1080;&#1088;&#1086;&#1076;&#1091;. &#1055;&#1086; &#1076;&#1086;&#1088;&#1086;&#1075;&#1077; &#1050;&#1088;&#1080;&#1089;&#1090;&#1086;&#1092;&#1077;&#1088; &#1079;&#1085;&#1072;&#1082;&#1086;&#1084;&#1080;&#1090;&#1089;&#1103; &#1089; &#1088;&#1072;&#1079;&#1085;&#1099;&#1084;&#1080; &#1083;&#1102;&#1076;&#1100;&#1084;&#1080;, &#1090;&#1072;&#1082; &#1080;&#1083;&#1080; &#1080;&#1085;&#1072;&#1095;&#1077; &#1074;&#1083;&#1080;&#1103;&#1102;&#1097;&#1080;&#1084;&#1080; &#1085;&#1072; &#1077;&#1075;&#1086; &#1078;&#1080;&#1079;&#1085;&#1100;.</span></span></p>',2,'&#1055;&#1086;&#1089;&#1083;&#1077;','FlyinGMind','2014-09-22 17:00:00','2014-09-25 19:20:30'),(18,'Rain in the sky','Rain...',2,'Rain...','FlyinGMind','2014-09-25 19:06:39','2014-09-25 19:06:54'),(19,'Autumn in Old Town','<p><span style=\"color: #008000;\">The view of Old Town from <span style=\"color: #0000ff;\"><a style=\"color: #0000ff;\" href=\"http://www.hrad.cz/en/prague-castle/prague-castle-tourist-information/visit-of-prague-castle.shtml\">Prague Castle</a></span> is just magnificent. Past the sparkle of the River Vltava the lavish quarters begin with their many spires rising above streets gushing with tourists. Everybody admires the ancient and opulent Royal Route which many years ago served as an access way by the Czech kings to Prague Castle. There are other treasures though, the network of mysterious lanes that can lead you to some beautiful nooks where even the locals seldom wander.</span></p>\r\n<h3><span style=\"color: #0000ff;\">In heart of the city</span></h3>\r\n<p><span style=\"color: #008000;\">The Old Town is at the heart of Prague&acute;s historical heritage and we can find many historically and architectonically significant monuments. For centuries the locals used to meet at the <span style=\"color: #0000ff;\"><a style=\"color: #0000ff;\" href=\"http://www.praguewelcome.cz/srv/www/en/objects/detail.x?id=42754\">Old Town Square</a></span> where much of the social and public life occurred.</span></p>\r\n<p><span style=\"color: #008000;\">Today, it is a centre point for most visitors admiring the <span style=\"color: #0000ff;\"><a style=\"color: #0000ff;\" href=\"http://www.praguewelcome.cz/en/visit/monuments/top-monuments/58-old-town-hall-with-the-astronomical-clock.shtml\">Prague Astronomical Clock</a></span>. It is set in motion every hour and a curious observer can watch all the twelve apostles presenting themselves at the doorways. It is a home to two significant churches &ndash; the gothic <span style=\"color: #0000ff;\"><a style=\"color: #0000ff;\" href=\"http://www.praguewelcome.cz/srv/www/cs/objects/detail.x?id=45112\">Church of Our Lady before T&yacute;n</a></span>, and the baroque <span style=\"color: #0000ff;\"><a style=\"color: #0000ff;\" href=\"http://www.praguewelcome.cz/srv/www/en/objects/detail.x?id=45109\">St. Nicholas Church.</a></span></span></p>\r\n<p><span style=\"color: #008000;\"><span style=\"color: #0000ff;\"><img style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"http://www.all-free-photos.com/images/prague/PI3016-hr.jpg\" alt=\"St. Nicholas Church\" width=\"600\" height=\"450\" /></span></span></p>\r\n<p><span style=\"color: #008000;\">The smallest district is Josefov, a former Jewish quarters. It is submerged in the Old Town and remains an important part of it. In here a visitor can find eight synagogues such as the <span style=\"color: #0000ff;\"><a style=\"color: #0000ff;\" href=\"http://www.praguewelcome.cz/srv/www/en/objects/detail.x?id=53398\">Old New Synagogue</a></span> which is one of the oldest in the whole of Europe and until today continues to hold religious ceremonies.&nbsp;</span></p>\r\n<h3><span style=\"color: #0000ff;\">One of many arteries of the Old Town</span></h3>\r\n<p><span style=\"color: #008000;\">The Royal route flows from the Old Town Square through Celetn&aacute; Street which is also interlinked with the life stories of many locals, and where we can find a variety of monuments. For example one of the faculties of the Charles University is located here, and the cubist <span style=\"color: #0000ff;\"><a style=\"color: #0000ff;\" href=\"http://www.praguewelcome.cz/srv/www/en/objects/detail.x?id=49440\">House of the Black Madonna</a></span> as well. There are plenty of theatres around as well as the <span style=\"color: #0000ff;\"><a style=\"color: #0000ff;\" href=\"http://www.praguewelcome.cz/srv/www/en/objects/detail.x?id=42710\">Powder Tower</a></span> which in the medieval times functioned as an entrance gate to the city. The art nouveau pearl of architecture the <span style=\"color: #0000ff;\"><a style=\"color: #0000ff;\" href=\"http://www.praguewelcome.cz/srv/www/en/objects/detail.x?id=42782\">Municipal House</a></span> has initiated the development of the modern Czech state.</span></p>\r\n<h3><span style=\"color: #0000ff;\">Europe&acute;s cultural centre</span></h3>\r\n<p><span style=\"color: #008000;\">Prague has often been a place for artists, scientist and philosophers from all corners of Europe to gather. Therefore, here in the Old Town we can find gems such as <span style=\"color: #0000ff;\"><a style=\"color: #0000ff;\" href=\"http://www.praguewelcome.cz/srv/www/en/objects/detail.x?id=43669\">Karolina</a></span> &ndash; the oldest university hall of residence, or the glorious library Klementinum, or whole range of theatres such as the famous Stavovsk&eacute; which staged acclaimed icons including W. A. Mozart and his Don Giovanni. Furthermore, some outstanding galleries are sprinkled about such as the <span style=\"color: #0000ff;\"><a style=\"color: #0000ff;\" href=\"http://www.praguewelcome.cz/srv/www/cs/objects/detail.x?id=43824\">Stone Bell House</a></span> or the <span style=\"color: #0000ff;\"><a style=\"color: #0000ff;\" href=\"http://www.praguewelcome.cz/srv/www/en/objects/detail.x?id=65876\">Golden Ring</a></span>.</span></p>\r\n<p><span style=\"color: #008000;\"><iframe style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"//www.youtube.com/embed/E9J89vBxFP0\" width=\"420\" height=\"315\"></iframe></span></p>\r\n<p>&nbsp;</p>',2,'The Old Town is the oldest and most magical part of Prague  a city glistening with historical jewels and the hustle and bustle of tourists.','FlyinGMind',NULL,NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

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
