-- MySQL dump 10.13  Distrib 5.1.48, for apple-darwin10.3.0 (i386)
--
-- Host: localhost    Database: conference
-- ------------------------------------------------------
-- Server version	5.1.48

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `dateCreated` datetime NOT NULL,
  `dateLastLogin` datetime DEFAULT NULL,
  `dateModified` datetime NOT NULL,
  `emailAddress` text NOT NULL,
  `fullName` text NOT NULL,
  `id` int(11) NOT NULL,
  `password` text NOT NULL,
  `username` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('2010-08-18 07:23:45',NULL,'2010-08-18 07:23:45','kberkling@dyned.com','Klaus',1,'Test','kib');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendee`
--

DROP TABLE IF EXISTS `attendee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendee` (
  `addressCity` text,
  `addressCountry` text,
  `addressPostalCode` text,
  `addressStreet1` text,
  `addressStreet2` text,
  `companyName` text,
  `dateLastEdited` datetime DEFAULT NULL,
  `dateLastVisit` datetime NOT NULL,
  `dateRegistered` datetime NOT NULL,
  `fax` text,
  `id` int(11) NOT NULL,
  `idAttendee` int(11) DEFAULT NULL,
  `idAttendeeFunction` int(11) DEFAULT NULL,
  `idAttendeeMealPreference` int(11) DEFAULT NULL,
  `idAttendeeType` int(11) DEFAULT NULL,
  `idHotelInformation` int(11) DEFAULT NULL,
  `idTravelInformation` int(11) DEFAULT NULL,
  `jobTitle` text,
  `nameFamily` text NOT NULL,
  `nameGiven` text,
  `phone` text,
  `salutation` text,
  `specialNeedsNotes` text,
  `userEmailAddress` text NOT NULL,
  `userPassword` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userEmailAddress_index` (`userEmailAddress`(100)),
  KEY `userPassword_index` (`userPassword`(50)),
  KEY `nameFamiliy_index` (`nameFamily`(100))
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendee`
--

LOCK TABLES `attendee` WRITE;
/*!40000 ALTER TABLE `attendee` DISABLE KEYS */;
INSERT INTO `attendee` VALUES ('gfdgfdgfd','hjkhjk','hjkhjkh','gfdgdf','fgfdgf','gfgdf','2010-08-15 02:32:50','2010-08-18 05:20:10','2010-08-15 02:32:19','hhjkhjk',1,NULL,NULL,NULL,NULL,2,1,'Head Hncho','Berkling','Klaus','hjhjk','Mr','hjkhj','kib@dyned.com','Test'),(NULL,NULL,NULL,NULL,NULL,NULL,'2010-08-17 02:02:55','2010-08-17 02:02:16','2010-08-17 02:02:16',NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Randall','Nick',NULL,'Mr.',NULL,'nrandall@dyned.com','Test'),('jkljkl','jkljkl','jkljkl','gjfdkl','jkljkl','gjfdkl',NULL,'2010-08-17 05:50:19','2010-08-17 05:50:19','jkljkl',4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'gjfkdl','gjfkdl','jkljkl','Mr',NULL,'kib2@dyned.com','Test'),('jkljkl','jkljkl','jkljkl','gjfdkl','jkljkl','gjfdkl',NULL,'2010-08-17 05:51:21','2010-08-17 05:51:21','jkljkl',5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'gjfkdl','gjfkdl','jkljkl','Mr',NULL,'kib3@dyned.com','Test'),('jkljkl','jkljkl','jkljkl','gjfdkl','jkljkl','gjfdkl',NULL,'2010-08-17 05:53:23','2010-08-17 05:53:23','jkljkl',6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'gjfkdl','gjfkdl','jkljkl','Mr',NULL,'kib4@dyned.com','Test'),('hjkhjk','hjkhjk','hjkhjk','hjhjk','hjkhjkh','ghjgfk',NULL,'2010-08-17 06:07:21','2010-08-17 06:04:49','hjkhjk',7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Krik','James','hjkhjk','Mr',NULL,'jkirk@starfeet.mil','Test'),('hjklhjkl','hjkhjk','hjkhjkl','hjkhjkl','hjkhjkl','jhkhjk',NULL,'2010-08-18 00:33:38','2010-08-18 00:33:38','hjkhjkl',8,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'hjkhjk','hjhjk','hjkhjkl','Mr',NULL,'kib5@dyned.com','Test'),('jkljkl','jkljkl','jkljkl','jljkl','jkljkl','jkljkl',NULL,'2010-08-18 05:28:53','2010-08-18 05:28:53','jkljlk',9,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'jkljkl','jkljkl','jkljkl','jkljkl',NULL,'kib6@dyned.com','Test'),(NULL,NULL,NULL,NULL,NULL,'hfgfh',NULL,'2010-08-18 05:32:04','2010-08-18 05:32:04',NULL,10,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'gfd','jk',NULL,'gf',NULL,'kib7@dyned.com','Test'),(NULL,NULL,NULL,'jkljkljkl','jkljkl','jjkl',NULL,'2010-08-18 05:34:21','2010-08-18 05:34:21',NULL,11,NULL,NULL,NULL,NULL,NULL,5,NULL,'jkljkl','jkljkl',NULL,'jklj',NULL,'kib8@dyned.com','Test'),(NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2010-08-18 06:07:17','2010-08-18 06:07:17',NULL,12,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'gfdgdf','gfdgdf',NULL,'fdg',NULL,'kib9@dyned.com','Test'),(NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2010-08-18 06:08:29','2010-08-18 06:08:29',NULL,13,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'jkljkl','jkljkl',NULL,'jkljl',NULL,'kib10@dyned.com','Test'),('jkljkl',NULL,NULL,'jkljkl','jkljkl','jkljkl',NULL,'2010-08-18 06:10:27','2010-08-18 06:10:27',NULL,14,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'hjkhjk','hjhjk',NULL,'hhj',NULL,'kib11@dyned.com','Test');
/*!40000 ALTER TABLE `attendee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendeefunction`
--

DROP TABLE IF EXISTS `attendeefunction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendeefunction` (
  `id` int(11) NOT NULL,
  `lable` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendeefunction`
--

LOCK TABLES `attendeefunction` WRITE;
/*!40000 ALTER TABLE `attendeefunction` DISABLE KEYS */;
INSERT INTO `attendeefunction` VALUES (1,'Teacher'),(2,'Teacher Trainer'),(3,'IT Professional'),(4,'Sales Manager'),(5,'School Administrator');
/*!40000 ALTER TABLE `attendeefunction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendeemealpreference`
--

DROP TABLE IF EXISTS `attendeemealpreference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendeemealpreference` (
  `id` int(11) NOT NULL,
  `lable` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendeemealpreference`
--

LOCK TABLES `attendeemealpreference` WRITE;
/*!40000 ALTER TABLE `attendeemealpreference` DISABLE KEYS */;
/*!40000 ALTER TABLE `attendeemealpreference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendeeselectedvevent`
--

DROP TABLE IF EXISTS `attendeeselectedvevent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendeeselectedvevent` (
  `id` int(11) NOT NULL,
  `idAttendee` int(11) NOT NULL,
  `idVEvent` int(11) DEFAULT NULL,
  `idVenue` int(11) NOT NULL,
  `participants` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendeeselectedvevent`
--

LOCK TABLES `attendeeselectedvevent` WRITE;
/*!40000 ALTER TABLE `attendeeselectedvevent` DISABLE KEYS */;
INSERT INTO `attendeeselectedvevent` VALUES (7,1,14,1,5),(8,1,13,1,1),(9,1,10,1,2),(10,1,12,1,3),(11,1,11,1,6),(18,4,10,1,0),(20,4,12,1,0),(21,4,13,1,0),(22,4,14,1,0),(23,4,11,1,0),(24,5,12,1,0),(25,5,10,1,0),(26,5,11,1,0),(28,5,13,1,0),(29,5,14,1,0),(30,6,12,1,0),(31,6,14,1,0),(33,6,13,1,0),(34,6,10,1,0),(35,6,11,1,0),(36,7,13,1,0),(37,7,14,1,0),(38,7,10,1,0),(39,7,11,1,0),(40,7,12,1,0),(42,8,10,1,0),(44,8,11,1,0),(45,8,12,1,0),(46,8,13,1,0),(47,8,14,1,0),(48,14,12,1,0),(49,14,13,1,0),(51,14,11,1,0),(52,14,14,1,0),(53,14,10,1,0);
/*!40000 ALTER TABLE `attendeeselectedvevent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendeespecialneed`
--

DROP TABLE IF EXISTS `attendeespecialneed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendeespecialneed` (
  `attendeeId` int(11) NOT NULL,
  `specialNeedId` int(11) NOT NULL,
  PRIMARY KEY (`attendeeId`,`specialNeedId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendeespecialneed`
--

LOCK TABLES `attendeespecialneed` WRITE;
/*!40000 ALTER TABLE `attendeespecialneed` DISABLE KEYS */;
/*!40000 ALTER TABLE `attendeespecialneed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendeetype`
--

DROP TABLE IF EXISTS `attendeetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendeetype` (
  `id` int(11) NOT NULL,
  `lable` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendeetype`
--

LOCK TABLES `attendeetype` WRITE;
/*!40000 ALTER TABLE `attendeetype` DISABLE KEYS */;
INSERT INTO `attendeetype` VALUES (1,'Teacher'),(2,'Teacher Trainer'),(3,'IT Staff/Manager'),(4,'Sales Manager');
/*!40000 ALTER TABLE `attendeetype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_pk_table`
--

DROP TABLE IF EXISTS `eo_pk_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_pk_table` (
  `NAME` char(40) NOT NULL,
  `PK` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_pk_table`
--

LOCK TABLES `eo_pk_table` WRITE;
/*!40000 ALTER TABLE `eo_pk_table` DISABLE KEYS */;
INSERT INTO `eo_pk_table` VALUES ('admin',1);
/*!40000 ALTER TABLE `eo_pk_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotelinformation`
--

DROP TABLE IF EXISTS `hotelinformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotelinformation` (
  `checkIn` datetime DEFAULT NULL,
  `checkOut` datetime DEFAULT NULL,
  `hotelAddress` text,
  `hotelName` text,
  `id` int(11) NOT NULL,
  `numRooms` int(11) DEFAULT NULL,
  `telephone` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotelinformation`
--

LOCK TABLES `hotelinformation` WRITE;
/*!40000 ALTER TABLE `hotelinformation` DISABLE KEYS */;
INSERT INTO `hotelinformation` VALUES ('2010-10-15 09:00:00','2010-10-25 10:00:00','61 Dongsanhuan Middle Road, Chaoyang District\r\nBeijing, Beijing 100022 China','Renaissance Beijing Capital Hotel',2,3,'555-555-1212');
/*!40000 ALTER TABLE `hotelinformation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invitationletterinformation`
--

DROP TABLE IF EXISTS `invitationletterinformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invitationletterinformation` (
  `birthDate` date DEFAULT NULL,
  `birthPlace` text,
  `dateArrival` date DEFAULT NULL,
  `dateDeparture` date DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `nameFamily` text,
  `nameGiven` text,
  `nameMiddle` text,
  `nationality` text,
  `passportDateExpire` date DEFAULT NULL,
  `passportDateIssued` date DEFAULT NULL,
  `passportNumber` text,
  `idAttendee` int(11) DEFAULT NULL,
  `daterequested` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invitationletterinformation`
--

LOCK TABLES `invitationletterinformation` WRITE;
/*!40000 ALTER TABLE `invitationletterinformation` DISABLE KEYS */;
INSERT INTO `invitationletterinformation` VALUES (NULL,NULL,'2010-10-17','2010-10-25',NULL,3,'Berkling','Klaus',NULL,NULL,NULL,NULL,NULL,1,'2010-08-18'),(NULL,NULL,'2010-10-17','2010-10-25',NULL,4,'Berkling','Klaus',NULL,NULL,NULL,NULL,NULL,1,'2010-08-18'),(NULL,NULL,'2010-10-17','2010-10-25',NULL,5,'Berkling','Klaus',NULL,NULL,NULL,NULL,NULL,1,'2010-08-18');
/*!40000 ALTER TABLE `invitationletterinformation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invitee`
--

DROP TABLE IF EXISTS `invitee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invitee` (
  `emailAddress` text NOT NULL,
  `freeFormName` text NOT NULL,
  `id` int(11) NOT NULL,
  `idAdmin` int(11) NOT NULL,
  `idVenue` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invitee`
--

LOCK TABLES `invitee` WRITE;
/*!40000 ALTER TABLE `invitee` DISABLE KEYS */;
/*!40000 ALTER TABLE `invitee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialneed`
--

DROP TABLE IF EXISTS `specialneed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specialneed` (
  `id` int(11) NOT NULL,
  `lable` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialneed`
--

LOCK TABLES `specialneed` WRITE;
/*!40000 ALTER TABLE `specialneed` DISABLE KEYS */;
INSERT INTO `specialneed` VALUES (1,'Dietary'),(2,'Access'),(3,'Mobiility'),(4,'Health'),(5,'Other, please specify');
/*!40000 ALTER TABLE `specialneed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `travelinformation`
--

DROP TABLE IF EXISTS `travelinformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `travelinformation` (
  `arrivalDate` datetime NOT NULL,
  `arrivalFlightNumber` text NOT NULL,
  `arrivalFrom` text NOT NULL,
  `departureDate` datetime NOT NULL,
  `departureFlightNumber` text NOT NULL,
  `departureTo` text NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travelinformation`
--

LOCK TABLES `travelinformation` WRITE;
/*!40000 ALTER TABLE `travelinformation` DISABLE KEYS */;
INSERT INTO `travelinformation` VALUES ('2010-10-17 06:00:00','1111','Here','2010-10-25 20:00:00','2222','There',1),('2010-10-17 06:00:00','4444','Paris','2010-10-24 22:00:00','5555','Paris',5);
/*!40000 ALTER TABLE `travelinformation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venue`
--

DROP TABLE IF EXISTS `venue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venue` (
  `dateCreated` datetime NOT NULL,
  `dateEnd` datetime NOT NULL,
  `dateModified` datetime NOT NULL,
  `dateStart` datetime NOT NULL,
  `description` text,
  `id` int(11) NOT NULL,
  `idAdmin` int(11) NOT NULL,
  `inactive` int(11) DEFAULT NULL,
  `lable` text NOT NULL,
  `mapAddress` text NOT NULL,
  `secretCode` varchar(50) DEFAULT NULL,
  `webpageURL` text,
  `facilityname` text,
  PRIMARY KEY (`id`),
  KEY `lable_index` (`lable`(10))
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venue`
--

LOCK TABLES `venue` WRITE;
/*!40000 ALTER TABLE `venue` DISABLE KEYS */;
INSERT INTO `venue` VALUES ('2010-08-15 01:58:51','2010-10-23 00:00:00','2010-08-15 16:58:46','2010-10-18 00:00:00','It\'s DynEd Conference time again! Come join the celebration of DynEd\'s continuing growth and success in changing the way English is taught around the world. And how appropriate - in China - heart of DynEd\'s greatest success.\r\n\r\nLunch is being hosted by DynEd at the Conference hotel.\r\n\r\nA Chinese Banquet dinner will be hosted by DynEd on Thursday evening, Oct. 21. Other evenings dinner will be on your own, but DynEd will provide \"guided options\" for those who wish to join a group.\r\n\r\nFunday and Optional Excursions must be signed up for in advance.\r\n\r\nElectricity in China is 220, so keep this in mind for personal electric products and gadgets.',1,1,NULL,'2010 Beijing Conference','61 Dongsanhuan Middle Road, Chaoyang District\r\nBeijing, Beijing 100022 China','2010Beijing','http://www.dyned.com/beijingconference/','Renaissance Beijing Capital Hotel'),('2010-08-15 02:03:18','2010-12-31 00:00:00','2010-08-15 16:59:08','2010-12-23 00:00:00','gfhjkfg  gfhdjk gfdhjk sd hjk hdgdfhjk gfhdjk gfhdjk gfhjkfg  gfhdjk gfdhjk sd hjk hdgdfhjk gfhdjk gfhdjk gfhjkfg  gfhdjk gfdhjk sd hjk hdgdfhjk gfhdjk gfhdjk gfhjkfg  gfhdjk gfdhjk sd hjk hdgdfhjk gfhdjk gfhdjk gfhjkfg  gfhdjk gfdhjk sd hjk hdgdfhjk gfhdjk gfhdjk \r\n\r\ngfhjkfg  gfhdjk gfdhjk sd hjk hdgdfhjk gfhdjk gfhdjk gfhjkfg  gfhdjk gfdhjk sd hjk hdgdfhjk gfhdjk gfhdjk \r\n\r\ngfhjkfg  gfhdjk gfdhjk sd hjk hdgdfhjk gfhdjk gfhdjk gfhjkfg  gfhdjk gfdhjk sd hjk hdgdfhjk gfhdjk gfhdjk gfhjkfg  gfhdjk gfdhjk sd hjk hdgdfhjk gfhdjk gfhdjk gfhjkfg  gfhdjk gfdhjk sd hjk hdgdfhjk gfhdjk gfhdjk \r\n\r\ngfhjkfg  gfhdjk gfdhjk sd hjk hdgdfhjk gfhdjk gfhdjk ',2,1,NULL,'Second Conference For Testing','fdhjkghfd ghfjdk gfdh hgjfdk\r\ngfhdjk ghfdjk ghfdjk\r\ngfdhjk gfhdjk','Test','http://www.dyned.com/','Test Place');
/*!40000 ALTER TABLE `venue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venueattendee`
--

DROP TABLE IF EXISTS `venueattendee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venueattendee` (
  `attendeeId` int(11) NOT NULL,
  `venueId` int(11) NOT NULL,
  PRIMARY KEY (`attendeeId`,`venueId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venueattendee`
--

LOCK TABLES `venueattendee` WRITE;
/*!40000 ALTER TABLE `venueattendee` DISABLE KEYS */;
INSERT INTO `venueattendee` VALUES (1,1),(2,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1);
/*!40000 ALTER TABLE `venueattendee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vevent`
--

DROP TABLE IF EXISTS `vevent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vevent` (
  `color` text,
  `dateTimeEnd` datetime NOT NULL,
  `dateTimeStart` datetime NOT NULL,
  `description` text,
  `id` int(11) NOT NULL,
  `idVenue` int(11) NOT NULL,
  `lable` text NOT NULL,
  `meetingPlace` text,
  PRIMARY KEY (`id`),
  KEY `lable_index` (`lable`(10))
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vevent`
--

LOCK TABLES `vevent` WRITE;
/*!40000 ALTER TABLE `vevent` DISABLE KEYS */;
INSERT INTO `vevent` VALUES ('#2F64DA','2010-12-23 09:00:00','2010-12-23 08:00:00',NULL,5,2,'Test A',NULL),('#37B11B','2010-12-24 10:00:00','2010-12-24 09:00:00',NULL,6,2,'Test B',NULL),('#E71E27','2010-12-25 12:00:00','2010-12-25 11:00:00',NULL,7,2,'Test C',NULL),('#F38B1E','2010-10-18 09:30:00','2010-10-18 09:00:00','Forbidden City, Tiananmen Square, Temple of Heaven',10,1,'Route #1','TBD'),('#37B11B','2010-10-22 10:00:00','2010-10-22 09:00:00','Post-Conference Excursion to Shanghai to visit Web Center(s), a great example of a DynEd-based adult language school, plus World Expo 2010.  \r\nNOTE: Two-day trip via air.',11,1,'Web International Center(s)','TBD'),('#F38B1E','2010-10-18 09:30:00','2010-10-18 09:00:00','Great Wall, Summer Palace',12,1,'Route #2','TBD'),('#F38B1E','2010-10-18 09:30:00','2010-10-18 09:00:00','Shuiquanguou Village Tour, Wild Great Wall Hiking, Barbecue',13,1,'Route #3','TBD'),('#37B11B','2010-10-22 09:30:00','2010-10-22 09:00:00','Post-Conference Excursion to Tieling in Northeast China to visit \"Jerry\'s School,\" a great example of using DynEd with young children.  Long day trip via train/bus.',14,1,'Jerry\'s School','TBD');
/*!40000 ALTER TABLE `vevent` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-08-17 16:27:22
