CREATE DATABASE  IF NOT EXISTS `inspireme` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `inspireme`;
-- MySQL dump 10.13  Distrib 5.5.40, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: inspireme
-- ------------------------------------------------------
-- Server version	5.6.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(64) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `email` varchar(60) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `userId` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (6,'FlyinGMind','$2a$10$oeW5cEUtxdvCWMaj0nVJ.uUh1Vjn/v0QJMH599Zfy/tQx4gB1Fn9.',1,''),(7,'Vasya','$2a$10$W.6EDTwrMp4n97F/pHkzRuFN8y7cKDok.lg4yiIdX6NMGWFZm6vHC',1,''),(8,'Petya','$2a$10$Adkflw72.4jueJqRFo9vi.bzH/yMfQ8R05tiDbxPXi5ePdV459X4m',1,''),(9,'Olechka','$2a$10$HU64FSBTDWs0b/vL/y378uOFS3UeHXwGDIiC1Vksko.zFaYeW46mu',1,'kverchi@hotmail.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countries_sights`
--

DROP TABLE IF EXISTS `countries_sights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries_sights`
--

LOCK TABLES `countries_sights` WRITE;
/*!40000 ALTER TABLE `countries_sights` DISABLE KEYS */;
INSERT INTO `countries_sights` VALUES (50,'sdsds','cz','Macro-Leaf-Orange-Autumn.jpg','dsdd',6),(51,'Test sight','cz','mountains.jpg','test',6),(52,'Nice place','ua','IMG_5431.JPG','In Ukr',7),(53,'Super place','cz','0.jpeg','In this place...',9),(54,'Super place','cz','41827.gif.jpg','Another',9);
/*!40000 ALTER TABLE `countries_sights` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `n_emails_addresses`
--

DROP TABLE IF EXISTS `n_emails_addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `n_emails_addresses` (
  `email_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(60) NOT NULL,
  `password` varchar(64) NOT NULL,
  PRIMARY KEY (`email_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `n_emails_addresses`
--

LOCK TABLES `n_emails_addresses` WRITE;
/*!40000 ALTER TABLE `n_emails_addresses` DISABLE KEYS */;
INSERT INTO `n_emails_addresses` VALUES (1,'kverchi24@gmail.com','n1c3_w0r1d');
/*!40000 ALTER TABLE `n_emails_addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_friends`
--

DROP TABLE IF EXISTS `users_friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_friends` (
  `friend_one_id` int(11) NOT NULL,
  `friend_two_id` int(11) NOT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`friend_one_id`,`friend_two_id`),
  KEY `friend_two_id` (`friend_two_id`),
  CONSTRAINT `users_friends_ibfk_3` FOREIGN KEY (`friend_one_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `users_friends_ibfk_4` FOREIGN KEY (`friend_two_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_friends`
--

LOCK TABLES `users_friends` WRITE;
/*!40000 ALTER TABLE `users_friends` DISABLE KEYS */;
INSERT INTO `users_friends` VALUES (6,7,0),(6,8,0),(9,6,1);
/*!40000 ALTER TABLE `users_friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (4,'answer','And to you hello!',6,7,1,'2014-10-26 14:18:06',0),(7,'test','qwerty',8,6,1,'2014-10-24 14:27:08',0),(9,'RE:test','<p>REPLY 2.</p>',6,8,1,'2014-10-31 16:03:10',0),(10,'RE:test','<p>WWWW</p>',6,8,1,'2014-10-31 16:06:04',0),(12,'qqO','',6,9,1,'2014-11-02 21:56:13',0),(13,'qqP','',6,8,0,'2014-11-02 23:54:47',0),(15,'hello','<p>hello xamp</p>',6,18,0,'2014-11-03 17:29:15',0);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reset_password`
--

DROP TABLE IF EXISTS `reset_password`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reset_password` (
  `token` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL,
  `time_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`token`),
  KEY `fk_reset_password_1` (`user_id`),
  CONSTRAINT `fk_reset_password_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reset_password`
--

LOCK TABLES `reset_password` WRITE;
/*!40000 ALTER TABLE `reset_password` DISABLE KEYS */;
/*!40000 ALTER TABLE `reset_password` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  UNIQUE KEY `user_role_idx_1` (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (6,1),(7,1),(8,1),(9,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (1,'Super place','<p>About place, where was Van Helsing.</p>',50,'Great!',6,NULL,NULL);
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'user'),(2,'admin');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_data`
--

DROP TABLE IF EXISTS `users_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_data` (
  `user_id` int(11) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `avatar_url` varchar(250) DEFAULT 'noavatar.jpg',
  `about` text,
  `country_code` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `country_code` (`country_code`),
  CONSTRAINT `users_data_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `users_data_ibfk_2` FOREIGN KEY (`country_code`) REFERENCES `countries` (`country_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_data`
--

LOCK TABLES `users_data` WRITE;
/*!40000 ALTER TABLE `users_data` DISABLE KEYS */;
INSERT INTO `users_data` VALUES (6,'Kolya','Pylypets','Macro-Leaf-Orange-Autumn.jpg','Good guy.','uk'),(7,'&#1057;&#1072;&#1096;&#1072;','&#1043;&#1086;&#1083;&#1099;&#1081;','GOLOSKOKOV.jpg','&#1040; &#1089;&#1077;&#1081;&#1095;&#1072;&#1089; &#1079;&#1072;&#1087;&#1080;&#1096;&#1077;&#1084; &#1072;&#1087;&#1087;&#1088;&#1086;&#1082;&#1089;&#1080;&#1084;&#1072;&#1094;&#1080;&#1102;...','ua'),(8,NULL,NULL,'noavatar.jpg',NULL,NULL),(9,NULL,NULL,'noavatar.jpg',NULL,NULL);
/*!40000 ALTER TABLE `users_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `countries` (
  `country_code` varchar(4) NOT NULL,
  `country_name` varchar(45) DEFAULT NULL,
  `img_path` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`country_code`),
  UNIQUE KEY `country_code_UNIQUE` (`country_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES ('cz','Czech Republic','CzechRepublic.png'),('it','Italy','Italy.png'),('sk','Slovakia','Slovakia.png'),('ua','Ukraine','Ukraine.png'),('uk','United Kingdom','UnitedKingdom.png');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-05 10:21:17
