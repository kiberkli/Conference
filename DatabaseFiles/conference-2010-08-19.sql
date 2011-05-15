-- MySQL dump 10.13  Distrib 5.1.43, for apple-darwin10.2.0 (i386)
--
-- Host: localhost    Database: conference
-- ------------------------------------------------------
-- Server version	5.1.43-log
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
INSERT INTO `admin` VALUES ('2010-08-18 07:23:45','2010-08-20 01:44:59','2010-08-18 07:23:45','kberkling@dyned.com','Klaus',1,'Test','kib');
UNLOCK TABLES;

--
-- Table structure for table `attendee`
--

DROP TABLE IF EXISTS `attendee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendee` (
  `addressCity` text CHARACTER SET utf8,
  `addressCountry` text CHARACTER SET utf8,
  `addressPostalCode` text CHARACTER SET utf8,
  `addressStreet1` text CHARACTER SET utf8,
  `addressStreet2` text CHARACTER SET utf8,
  `companyName` text CHARACTER SET utf8,
  `dateLastEdited` datetime DEFAULT NULL,
  `dateLastVisit` datetime NOT NULL,
  `dateRegistered` datetime NOT NULL,
  `fax` text CHARACTER SET utf8,
  `id` int(11) NOT NULL,
  `idAttendee` int(11) DEFAULT NULL,
  `idAttendeeFunction` int(11) DEFAULT NULL,
  `idAttendeeMealPreference` int(11) DEFAULT NULL,
  `idAttendeeType` int(11) DEFAULT NULL,
  `idTravelInformation` int(11) DEFAULT NULL,
  `jobTitle` text CHARACTER SET utf8,
  `nameFamily` text CHARACTER SET utf8 NOT NULL,
  `nameGiven` text CHARACTER SET utf8,
  `phone` text CHARACTER SET utf8,
  `salutation` text CHARACTER SET utf8,
  `specialNeedsNotes` text CHARACTER SET utf8,
  `userEmailAddress` text CHARACTER SET utf8 NOT NULL,
  `userPassword` text CHARACTER SET utf8 NOT NULL,
  `addressState` text CHARACTER SET utf8,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userEmailAddress_index` (`userEmailAddress`(100)),
  KEY `userPassword_index` (`userPassword`(50)),
  KEY `nameFamiliy_index` (`nameFamily`(100))
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendee`
--

LOCK TABLES `attendee` WRITE;
INSERT INTO `attendee` VALUES ('gfdgfdgfd','hjkhjk','hjkhjkh','gfdgdf','fgfdgf','gfgdf','2010-08-15 02:32:50','2010-08-20 07:29:56','2010-08-15 02:32:19','hhjkhjk',1,NULL,NULL,NULL,NULL,1,'Head Hncho','Berkling','Klaus','hjhjk','Mr','hjkhj','kib@dyned.com','Test',NULL),(NULL,NULL,NULL,NULL,NULL,NULL,'2010-08-17 02:02:55','2010-08-17 02:02:16','2010-08-17 02:02:16',NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,'Randall','Nick',NULL,'Mr.',NULL,'nrandall@dyned.com','Test',NULL),(NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2010-08-20 01:00:53','2010-08-20 00:52:57',NULL,19,NULL,NULL,NULL,NULL,NULL,NULL,'gfg','gf',NULL,'gf',NULL,'kib1@dyned.com','Test',NULL),(NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2010-08-20 01:25:47','2010-08-20 01:25:47',NULL,20,NULL,NULL,NULL,NULL,NULL,NULL,'hjkhkj','hjk',NULL,'Me',NULL,'kib2@dyned.com','Test',NULL),(NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2010-08-20 01:29:14','2010-08-20 01:29:14',NULL,21,NULL,NULL,NULL,NULL,NULL,NULL,'hjkhjk','hjkhjk',NULL,'ghjk',NULL,'kib4@dyned.com','Test',NULL),(NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2010-08-20 02:29:03','2010-08-20 02:29:03',NULL,22,NULL,NULL,NULL,NULL,NULL,NULL,'gfd','gfd',NULL,'fd',NULL,'kib5@dyned.com','Test',NULL);
UNLOCK TABLES;

--
-- Table structure for table `attendeefunction`
--

DROP TABLE IF EXISTS `attendeefunction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendeefunction` (
  `id` int(11) NOT NULL,
  `lable` text CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendeefunction`
--

LOCK TABLES `attendeefunction` WRITE;
INSERT INTO `attendeefunction` VALUES (1,'Teacher'),(2,'Teacher Trainer'),(3,'IT Professional'),(4,'Sales Manager'),(5,'School Administrator');
UNLOCK TABLES;

--
-- Table structure for table `attendeemealpreference`
--

DROP TABLE IF EXISTS `attendeemealpreference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendeemealpreference` (
  `id` int(11) NOT NULL,
  `lable` text CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendeemealpreference`
--

LOCK TABLES `attendeemealpreference` WRITE;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendeeselectedvevent`
--

LOCK TABLES `attendeeselectedvevent` WRITE;
INSERT INTO `attendeeselectedvevent` VALUES (7,1,14,1,10),(8,1,13,1,7),(9,1,10,1,8),(10,1,12,1,9),(11,1,11,1,11);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendeespecialneed`
--

LOCK TABLES `attendeespecialneed` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `attendeetype`
--

DROP TABLE IF EXISTS `attendeetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendeetype` (
  `id` int(11) NOT NULL,
  `lable` text CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendeetype`
--

LOCK TABLES `attendeetype` WRITE;
INSERT INTO `attendeetype` VALUES (1,'Teacher'),(2,'Teacher Trainer'),(3,'IT Staff/Manager'),(4,'Sales Manager');
UNLOCK TABLES;

--
-- Table structure for table `eo_pk_table`
--

DROP TABLE IF EXISTS `eo_pk_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_pk_table` (
  `NAME` char(40) COLLATE utf8_unicode_ci NOT NULL,
  `PK` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_pk_table`
--

LOCK TABLES `eo_pk_table` WRITE;
INSERT INTO `eo_pk_table` VALUES ('travelinformation',1);
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
  `hotelAddress` text COLLATE utf8_unicode_ci,
  `hotelName` text COLLATE utf8_unicode_ci,
  `id` int(11) NOT NULL,
  `idAttendee` int(11) DEFAULT NULL,
  `idVenue` int(11) DEFAULT NULL,
  `numRooms` int(11) DEFAULT NULL,
  `telephone` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotelinformation`
--

LOCK TABLES `hotelinformation` WRITE;
INSERT INTO `hotelinformation` VALUES ('2010-10-17 09:00:00','2010-10-23 11:00:00','61 Dongsanhuan Middle Road, Chaoyang District\r\nBeijing, Beijing 100022 China','Renaissance Beijing Capital Hotel',2,1,1,1,'555-555-1212');
UNLOCK TABLES;

--
-- Table structure for table `invitationletterinformation`
--

DROP TABLE IF EXISTS `invitationletterinformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invitationletterinformation` (
  `birthDate` date DEFAULT NULL,
  `birthPlace` text CHARACTER SET utf8,
  `dateArrival` date DEFAULT NULL,
  `dateDeparture` date DEFAULT NULL,
  `gender` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `id` int(11) NOT NULL,
  `nameFamily` text CHARACTER SET utf8,
  `nameGiven` text CHARACTER SET utf8,
  `nameMiddle` text CHARACTER SET utf8,
  `nationality` text CHARACTER SET utf8,
  `passportDateExpire` date DEFAULT NULL,
  `passportDateIssued` date DEFAULT NULL,
  `passportNumber` text CHARACTER SET utf8,
  `idAttendee` int(11) DEFAULT NULL,
  `daterequested` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invitationletterinformation`
--

LOCK TABLES `invitationletterinformation` WRITE;
INSERT INTO `invitationletterinformation` VALUES (NULL,NULL,'2010-10-17','2010-10-25',NULL,3,'Berkling','Klaus',NULL,NULL,NULL,NULL,NULL,1,'2010-08-18'),(NULL,NULL,'2010-10-17','2010-10-25',NULL,4,'Berkling','Klaus',NULL,NULL,NULL,NULL,NULL,1,'2010-08-18'),(NULL,NULL,'2010-10-17','2010-10-25',NULL,5,'Berkling','Klaus',NULL,NULL,NULL,NULL,NULL,1,'2010-08-18');
UNLOCK TABLES;

--
-- Table structure for table `invitee`
--

DROP TABLE IF EXISTS `invitee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invitee` (
  `emailAddress` text CHARACTER SET utf8 NOT NULL,
  `freeFormName` text CHARACTER SET utf8 NOT NULL,
  `id` int(11) NOT NULL,
  `idAdmin` int(11) NOT NULL,
  `idVenue` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invitee`
--

LOCK TABLES `invitee` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `specialneed`
--

DROP TABLE IF EXISTS `specialneed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specialneed` (
  `id` int(11) NOT NULL,
  `lable` text CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialneed`
--

LOCK TABLES `specialneed` WRITE;
INSERT INTO `specialneed` VALUES (1,'Dietary'),(2,'Access'),(3,'Mobiility'),(4,'Health'),(5,'Other, please specify');
UNLOCK TABLES;

--
-- Table structure for table `travelinformation`
--

DROP TABLE IF EXISTS `travelinformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `travelinformation` (
  `arrivalDate` datetime DEFAULT NULL,
  `arrivalFlightNumber` text COLLATE utf8_unicode_ci,
  `arrivalFrom` text COLLATE utf8_unicode_ci,
  `departureDate` datetime DEFAULT NULL,
  `departureFlightNumber` text COLLATE utf8_unicode_ci,
  `departureTo` text COLLATE utf8_unicode_ci,
  `id` int(11) NOT NULL,
  `idAttendee` int(11) NOT NULL,
  `idVenue` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travelinformation`
--

LOCK TABLES `travelinformation` WRITE;
INSERT INTO `travelinformation` VALUES ('2010-10-17 09:00:00','1111','Here','2010-10-24 18:00:00','2222','There',1,1,1);
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
  `description` text CHARACTER SET utf8,
  `id` int(11) NOT NULL,
  `idAdmin` int(11) NOT NULL,
  `inactive` int(11) DEFAULT NULL,
  `lable` text CHARACTER SET utf8 NOT NULL,
  `mapAddress` text CHARACTER SET utf8 NOT NULL,
  `secretCode` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `webpageURL` text CHARACTER SET utf8,
  `facilityname` text CHARACTER SET utf8,
  PRIMARY KEY (`id`),
  KEY `lable_index` (`lable`(10))
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venue`
--

LOCK TABLES `venue` WRITE;
INSERT INTO `venue` VALUES ('2010-08-15 01:58:51','2010-10-23 00:00:00','2010-08-18 22:55:16','2010-10-18 00:00:00','Lunch each day is being hosted by DynEd at the Conference hotel.\r\n\r\nA Chinese Banquet dinner will be hosted by DynEd on Thursday evening, Oct. 21. Other evenings dinner will be on your own, but DynEd will provide \"guided options\" for those who wish to join a group.\r\n\r\nElectricity in China is 220V, so keep this in mind for personal electric products and gadgets.\r\n\r\nFunday and Optional Excursions must be signed up for in advance.\r\n',1,1,NULL,'2010 Beijing Conference','61 Dongsanhuan Middle Road, Chaoyang District\r\nBeijing, Beijing 100022 China','2010Beijing','http://www.dyned.com/beijingconference/','Renaissance Beijing Capital Hotel'),('2010-08-15 02:03:18','2010-12-31 00:00:00','2010-08-18 23:37:22','2010-12-23 00:00:00',NULL,2,1,NULL,'Second Conference For Testing','üäß fdhjkghfd ghfjdk gfdh hgjfdk\r\ngfhdjk ghfdjk ghfdjk\r\ngfdhjk gfhdjk','Test','http://www.dyned.com/','Test Place');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venueattendee`
--

LOCK TABLES `venueattendee` WRITE;
INSERT INTO `venueattendee` VALUES (1,1),(2,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(21,1),(22,1);
UNLOCK TABLES;

--
-- Table structure for table `vevent`
--

DROP TABLE IF EXISTS `vevent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vevent` (
  `color` text CHARACTER SET utf8,
  `dateTimeEnd` datetime NOT NULL,
  `dateTimeStart` datetime NOT NULL,
  `description` text CHARACTER SET utf8,
  `id` int(11) NOT NULL,
  `idVenue` int(11) NOT NULL,
  `lable` text CHARACTER SET utf8 NOT NULL,
  `meetingPlace` text CHARACTER SET utf8,
  PRIMARY KEY (`id`),
  KEY `lable_index` (`lable`(10))
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vevent`
--

LOCK TABLES `vevent` WRITE;
INSERT INTO `vevent` VALUES ('#2F64DA','2010-12-23 09:00:00','2010-12-23 08:00:00',NULL,5,2,'Test A',NULL),('#37B11B','2010-12-24 10:00:00','2010-12-24 09:00:00',NULL,6,2,'Test B',NULL),('#E71E27','2010-12-25 12:00:00','2010-12-25 11:00:00',NULL,7,2,'Test C',NULL),('#F38B1E','2010-10-18 09:30:00','2010-10-18 09:00:00','Forbidden City, Tiananmen Square, Temple of Heaven',10,1,'Route #1','TBD'),('#37B11B','2010-10-22 10:00:00','2010-10-22 09:00:00','Post-Conference Excursion to Shanghai to visit Web Center(s), a great example of a DynEd-based adult language school, plus World Expo 2010.  \r\nNOTE: Two-day trip via air.',11,1,'Web International Center(s)','TBD'),('#F38B1E','2010-10-18 09:30:00','2010-10-18 09:00:00','Great Wall, Summer Palace',12,1,'Route #2','TBD'),('#F38B1E','2010-10-18 09:30:00','2010-10-18 09:00:00','Shuiquanguou Village Tour, Wild Great Wall Hiking, Barbecue',13,1,'Route #3','TBD'),('#37B11B','2010-10-22 09:30:00','2010-10-22 09:00:00','Post-Conference Excursion to Tieling in Northeast China to visit \"Jerry\'s School,\" a great example of using DynEd with young children.  Long day trip via train/bus.',14,1,'Jerry\'s School','TBD');
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-08-19 16:41:40
