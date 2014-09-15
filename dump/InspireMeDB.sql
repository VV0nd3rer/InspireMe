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
  PRIMARY KEY (`post_id`),
  KEY `sight_id` (`sight_id`),
  CONSTRAINT `sight_id` FOREIGN KEY (`sight_id`) REFERENCES `countries_sights` (`sight_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `posts` */

insert  into `posts`(`post_id`,`title`,`text`,`sight_id`,`description`) values (1,'Testing post','Hello, this is my first post.',2,'First post'),(2,'Second post','Hi, I am a nice cat!',3,'Second post'),(3,'Vyshegrad','Wonderful place.',2,'It\'s was cool!'),(4,'Java','t...',2,'It\'s cool!'),(5,'Java developers','Two cats love each other.',2,'Try it!');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`role_id`,`rolename`) values (1,'user'),(2,'admin');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `username` varchar(50) NOT NULL,
  `password` varchar(64) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`username`,`password`,`enabled`) values ('Albert','$2a$10$1l8NSzWL5JVchAfNmnMt0usPXV8mW7zb0wsg9C8WclY0vbyRiMmxC',NULL),('Astrid','$2a$10$udiPCmEOwvRIUVALGyV3V.ePUwE2kJhT2uaKuRgXPjNG7pzthH9NO',1),('Dream','$2a$10$2R.5vpWnesBPBSLsLejMgOLz3C.uvltMtGi1GAkUY4VGnkViXcFkO',NULL),('FlyinGMind','$2a$10$D2AOK6I7MWZR0PTfykxtEus3hlT21BES0m01vQBMFbkukRGJGVUbK',1),('Kverchi','$2a$10$MPf/0ghp0jT360QZor1vNudQ/QIPhEBS.p3IC/xtMh5IyQaqEkfyW',1),('LaVon','bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a',1),('Maxim','$2a$10$./ySmnn2QoounrWQBrxvgutlJXnXUNtarqvfomKPcbnxmBJSHe/ua',NULL),('PinkFloyd','$2a$10$VO9gDORydjLaswtRHiPoHeUonrPdDrMua5U3uuywyiHoGQL5lsL.W',NULL),('Vasya','bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a',1);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_role_idx_1` (`username`),
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`role_id`,`username`) values (1,1,'FlyinGMind'),(2,1,'Vasya'),(4,1,'Astrid'),(5,1,'PinkFloyd'),(6,1,'Dream'),(7,1,'Albert'),(8,1,'Maxim');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
