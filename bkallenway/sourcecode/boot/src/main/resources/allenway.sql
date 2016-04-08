-- MySQL dump 10.13  Distrib 5.6.26, for osx10.10 (x86_64)
--
-- Host: localhost    Database: allenway
-- ------------------------------------------------------
-- Server version	5.6.20

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
-- Table structure for table `tb_admin`
--

CREATE database allenway character set utf8;
use allenway;

DROP TABLE IF EXISTS `tb_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_admin` (
  `id` varchar(255) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `is_delete` bit(1) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `github` varchar(255) DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `wechat` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_admin`
--

LOCK TABLES `tb_admin` WRITE;
/*!40000 ALTER TABLE `tb_admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_article`
--

DROP TABLE IF EXISTS `tb_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_article` (
  `id` varchar(255) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `is_delete` bit(1) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `read_num` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `classify_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h9ijm051bfq2x8ioejbsj00cl` (`classify_id`),
  CONSTRAINT `FK_h9ijm051bfq2x8ioejbsj00cl` FOREIGN KEY (`classify_id`) REFERENCES `tb_classify` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_article`
--

LOCK TABLES `tb_article` WRITE;
/*!40000 ALTER TABLE `tb_article` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_article_tag`
--

DROP TABLE IF EXISTS `tb_article_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_article_tag` (
  `article_id` varchar(255) NOT NULL,
  `tag_id` varchar(255) NOT NULL,
  KEY `FK_iylg6owk81xfu4kkrluj0x2t7` (`tag_id`),
  KEY `FK_k7wjwm4s07ib2eijtw9q89r43` (`article_id`),
  CONSTRAINT `FK_iylg6owk81xfu4kkrluj0x2t7` FOREIGN KEY (`tag_id`) REFERENCES `tb_tag` (`id`),
  CONSTRAINT `FK_k7wjwm4s07ib2eijtw9q89r43` FOREIGN KEY (`article_id`) REFERENCES `tb_article` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_article_tag`
--

LOCK TABLES `tb_article_tag` WRITE;
/*!40000 ALTER TABLE `tb_article_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_article_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_classify`
--

DROP TABLE IF EXISTS `tb_classify`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_classify` (
  `id` varchar(255) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `is_delete` bit(1) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `article_num` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_classify_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_classify`
--

LOCK TABLES `tb_classify` WRITE;
/*!40000 ALTER TABLE `tb_classify` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_classify` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_comment`
--

DROP TABLE IF EXISTS `tb_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_comment` (
  `id` varchar(255) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `is_delete` bit(1) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `article_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h76oc9dy1bi5rf3s517vr40ay` (`article_id`),
  CONSTRAINT `FK_h76oc9dy1bi5rf3s517vr40ay` FOREIGN KEY (`article_id`) REFERENCES `tb_article` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_comment`
--

LOCK TABLES `tb_comment` WRITE;
/*!40000 ALTER TABLE `tb_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_tag`
--

DROP TABLE IF EXISTS `tb_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_tag` (
  `id` varchar(255) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `is_delete` bit(1) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_tag`
--

LOCK TABLES `tb_tag` WRITE;
/*!40000 ALTER TABLE `tb_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_tag` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-08 15:44:02
