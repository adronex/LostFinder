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
-- Table structure for table `account_detail`
--

DROP TABLE IF EXISTS `account_detail`;
CREATE TABLE `account_detail` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  `DESCRIPTION` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOGIN` varchar(20) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `DETAILS_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `LOGIN_UNIQUE` (`LOGIN`),
  UNIQUE KEY `EMAIL_UNIQUE` (`EMAIL`),
  KEY `FK_ACCOUNT_DETAIL_idx` (`DETAILS_ID`),
  CONSTRAINT `FK_ACCOUNT_DETAIL` FOREIGN KEY (`DETAILS_ID`) REFERENCES `account_detail` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

--
-- Dumping data for table `account`
--

INSERT INTO `account` VALUES (2,'sosiya','12345678','pisya@sosisya.com',2),(37,'gus','123','los@polos.com',37),(41,'monbon','123','mon@bon.com',41),(50,'shalaleyla','123','shala@leyla.com',50);

--
-- Dumping data for table `account_detail`
--

INSERT INTO `account_detail` VALUES (2,'pisya sosisya','SosLand'),(37,'gustavo fring','New Mexico'),(41,'mongo bongo','jungle'),(50,'shala leyla','shalamaleyla');

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  `VALUE` varchar(45) NOT NULL,
  `DETAILS_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CONTACT_ACCOUNT_DETAIL_idx` (`DETAILS_ID`),
  CONSTRAINT `FK_CONTACT_ACCOUTN_DETAIL` FOREIGN KEY (`DETAILS_ID`) REFERENCES `account_detail` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

--
-- Dumping data for table `contact`
--

INSERT INTO `contact` VALUES (1,'phone','234234234234',50),(2,'sss','232323ddssd',2);

--
-- Table structure for table `hashtag`
--

DROP TABLE IF EXISTS `hashtag`;
CREATE TABLE `hashtag` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`)
);

--
-- Dumping data for table `hashtag`
--

INSERT INTO `hashtag` VALUES (1,'голова'),(6,'девственность'),(3,'кот'),(5,'кошелёк'),(8,'потерял'),(4,'собака'),(2,'трусы'),(7,'убежал');

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(300) NOT NULL,
  `TITLE` varchar(45) NOT NULL,
  `ACCOUNT_ID` int(11) NOT NULL,
  `DATE` date NOT NULL,
  `POST_TYPE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_POST_ACCOUNT_idx` (`ACCOUNT_ID`),
  KEY `FK_POST_TYPE_idx` (`POST_TYPE_ID`),
  CONSTRAINT `FK_POST_TYPE` FOREIGN KEY (`POST_TYPE_ID`) REFERENCES `post_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_POST_ACCOUNT` FOREIGN KEY (`ACCOUNT_ID`) REFERENCES `account` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

--
-- Dumping data for table `post`
--
INSERT INTO `post` VALUES (1,'убежал кот, черный, толстый, полосатый','убежал кот',2,'2016-05-12',2),(2,'потерял кошелёк в районе пл.Победы, черный, много денег','потерял кошелёк',2,'2016-04-29',2);

--
-- Table structure for table `post_hashtag`
--

CREATE TABLE `post_hashtag` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_POST` int(11) NOT NULL,
  `ID_HASHTAG` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_POSTHASHTAG_HASHTAG_idx` (`ID_HASHTAG`),
  KEY `FK_POSTHASHTAG_POST_idx` (`ID_POST`),
  CONSTRAINT `FK_POSTHASHTAG_HASHTAG` FOREIGN KEY (`ID_HASHTAG`) REFERENCES `hashtag` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_POSTHASHTAG_POST` FOREIGN KEY (`ID_POST`) REFERENCES `post` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

--
-- Dumping data for table `post_hashtag`
--

INSERT INTO `post_hashtag` VALUES (24,2,5),(25,2,8),(28,1,7),(29,1,3);

--
-- Table structure for table `post_type`
--

DROP TABLE IF EXISTS `post_type`;
CREATE TABLE `post_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
);

--
-- Dumping data for table `post_type`
--

INSERT INTO `post_type` VALUES (1,'нашёл'),(2,'потерял');