CREATE DATABASE  IF NOT EXISTS `lf` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `lf`;
-- MySQL dump 10.13  Distrib 5.6.11, for Win32 (x86)
--
-- Host: localhost    Database: lf
-- ------------------------------------------------------
-- Server version	5.5.30

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOGIN` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `PASSWORD` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `EMAIL` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `DETAILS_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `LOGIN_UNIQUE` (`LOGIN`),
  UNIQUE KEY `EMAIL_UNIQUE` (`EMAIL`),
  KEY `FK_ACCOUNT_DETAIL_idx` (`DETAILS_ID`),
  CONSTRAINT `FK_ACCOUNT_DETAIL` FOREIGN KEY (`DETAILS_ID`) REFERENCES `account_detail` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `account_detail`
--

DROP TABLE IF EXISTS `account_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_detail` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `VALUE` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `DETAILS_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CONTACT_ACCOUNT_DETAIL_idx` (`DETAILS_ID`),
  CONSTRAINT `FK_CONTACT_ACCOUTN_DETAIL` FOREIGN KEY (`DETAILS_ID`) REFERENCES `account_detail` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hashtag`
--

DROP TABLE IF EXISTS `hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hashtag` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(300) COLLATE utf8_unicode_ci NOT NULL,
  `TITLE` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `ACCOUNT_ID` int(11) NOT NULL,
  `DATE` date NOT NULL,
  `POST_TYPE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_POST_ACCOUNT_idx` (`ACCOUNT_ID`),
  KEY `FK_POST_TYPE_idx` (`POST_TYPE_ID`),
  CONSTRAINT `FK_POST_TYPE` FOREIGN KEY (`POST_TYPE_ID`) REFERENCES `post_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_POST_ACCOUNT` FOREIGN KEY (`ACCOUNT_ID`) REFERENCES `account` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `post_hashtag`
--

DROP TABLE IF EXISTS `post_hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_hashtag` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_POST` int(11) NOT NULL,
  `ID_HASHTAG` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_POSTHASHTAG_HASHTAG_idx` (`ID_HASHTAG`),
  KEY `FK_POSTHASHTAG_POST_idx` (`ID_POST`),
  CONSTRAINT `FK_POSTHASHTAG_HASHTAG` FOREIGN KEY (`ID_HASHTAG`) REFERENCES `hashtag` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_POSTHASHTAG_POST` FOREIGN KEY (`ID_POST`) REFERENCES `post` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `post_type`
--

DROP TABLE IF EXISTS `post_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-04 21:52:27
