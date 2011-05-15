-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.43-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema conference
--

CREATE DATABASE IF NOT EXISTS conference;
USE conference;

--
-- Definition of table `conference`.`admin`
--

DROP TABLE IF EXISTS `conference`.`admin`;
CREATE TABLE  `conference`.`admin` (
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

--
-- Dumping data for table `conference`.`admin`
--

/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
LOCK TABLES `admin` WRITE;
INSERT INTO `conference`.`admin` VALUES  ('2010-08-18 00:00:00','2010-08-18 00:00:0','2010-08-18 00:00:00','admin@yourdomain.com','Administrator',1,'admin','admin');
UNLOCK TABLES;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;


--
-- Definition of table `conference`.`attendee`
--

DROP TABLE IF EXISTS `conference`.`attendee`;
CREATE TABLE  `conference`.`attendee` (
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
  `idPassportInformation` int(11) DEFAULT NULL,
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


--
-- Definition of table `conference`.`attendeefunction`
--

DROP TABLE IF EXISTS `conference`.`attendeefunction`;
CREATE TABLE  `conference`.`attendeefunction` (
  `id` int(11) NOT NULL,
  `lable` text CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `conference`.`attendeefunction`
--

/*!40000 ALTER TABLE `attendeefunction` DISABLE KEYS */;
LOCK TABLES `attendeefunction` WRITE;
INSERT INTO `conference`.`attendeefunction` VALUES  (1,'Teacher'),
 (2,'Teacher Trainer'),
 (3,'IT Professional'),
 (4,'Sales Manager'),
 (5,'School Administrator');
UNLOCK TABLES;
/*!40000 ALTER TABLE `attendeefunction` ENABLE KEYS */;


--
-- Definition of table `conference`.`attendeemealpreference`
--

DROP TABLE IF EXISTS `conference`.`attendeemealpreference`;
CREATE TABLE  `conference`.`attendeemealpreference` (
  `id` int(11) NOT NULL,
  `lable` text CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


--
-- Definition of table `conference`.`attendeeselectedvevent`
--

DROP TABLE IF EXISTS `conference`.`attendeeselectedvevent`;
CREATE TABLE  `conference`.`attendeeselectedvevent` (
  `id` int(11) NOT NULL,
  `idAttendee` int(11) NOT NULL,
  `idVEvent` int(11) DEFAULT NULL,
  `idVenue` int(11) NOT NULL,
  `participants` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


--
-- Definition of table `conference`.`attendeespecialneed`
--

DROP TABLE IF EXISTS `conference`.`attendeespecialneed`;
CREATE TABLE  `conference`.`attendeespecialneed` (
  `attendeeId` int(11) NOT NULL,
  `specialNeedId` int(11) NOT NULL,
  PRIMARY KEY (`attendeeId`,`specialNeedId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


--
-- Definition of table `conference`.`attendeetype`
--

DROP TABLE IF EXISTS `conference`.`attendeetype`;
CREATE TABLE  `conference`.`attendeetype` (
  `id` int(11) NOT NULL,
  `lable` text CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


--
-- Dumping data for table `conference`.`attendeetype`
--

/*!40000 ALTER TABLE `attendeetype` DISABLE KEYS */;
LOCK TABLES `attendeetype` WRITE;
INSERT INTO `conference`.`attendeetype` VALUES  (1,'Teacher'),
 (2,'Teacher Trainer'),
 (3,'IT Staff/Manager'),
 (4,'Sales Manager');
UNLOCK TABLES;
/*!40000 ALTER TABLE `attendeetype` ENABLE KEYS */;


--
-- Definition of table `conference`.`eo_pk_table`
--

DROP TABLE IF EXISTS `conference`.`eo_pk_table`;
CREATE TABLE  `conference`.`eo_pk_table` (
  `NAME` char(40) COLLATE utf8_unicode_ci NOT NULL,
  `PK` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


--
-- Definition of table `conference`.`hotelinformation`
--

DROP TABLE IF EXISTS `conference`.`hotelinformation`;
CREATE TABLE  `conference`.`hotelinformation` (
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


--
-- Definition of table `conference`.`invitee`
--

DROP TABLE IF EXISTS `conference`.`invitee`;
CREATE TABLE  `conference`.`invitee` (
  `emailAddress` text CHARACTER SET utf8 NOT NULL,
  `freeFormName` text CHARACTER SET utf8 NOT NULL,
  `id` int(11) NOT NULL,
  `idAdmin` int(11) NOT NULL,
  `idVenue` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


--
-- Definition of table `conference`.`passportinformation`
--

DROP TABLE IF EXISTS `conference`.`passportinformation`;
CREATE TABLE  `conference`.`passportinformation` (
  `birthDate` date NOT NULL,
  `birthPlace` text COLLATE utf8_unicode_ci,
  `dateArrival` date DEFAULT NULL,
  `dateDeparture` date DEFAULT NULL,
  `daterequested` datetime NOT NULL,
  `gender` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `id` int(11) NOT NULL,
  `nameFamily` text COLLATE utf8_unicode_ci NOT NULL,
  `nameGiven` text COLLATE utf8_unicode_ci NOT NULL,
  `nameMiddle` text COLLATE utf8_unicode_ci NOT NULL,
  `nationality` text COLLATE utf8_unicode_ci NOT NULL,
  `passportDateExpire` date NOT NULL,
  `passportDateIssued` date NOT NULL,
  `passportNumber` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


--
-- Definition of table `conference`.`specialneed`
--

DROP TABLE IF EXISTS `conference`.`specialneed`;
CREATE TABLE  `conference`.`specialneed` (
  `id` int(11) NOT NULL,
  `lable` text CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `conference`.`specialneed`
--

/*!40000 ALTER TABLE `specialneed` DISABLE KEYS */;
LOCK TABLES `specialneed` WRITE;
INSERT INTO `conference`.`specialneed` VALUES  (1,'Dietary'),
 (2,'Access'),
 (3,'Mobiility'),
 (4,'Health'),
 (5,'Other, please specify');
UNLOCK TABLES;
/*!40000 ALTER TABLE `specialneed` ENABLE KEYS */;


--
-- Definition of table `conference`.`travelinformation`
--

DROP TABLE IF EXISTS `conference`.`travelinformation`;
CREATE TABLE  `conference`.`travelinformation` (
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


--
-- Definition of table `conference`.`venue`
--

DROP TABLE IF EXISTS `conference`.`venue`;
CREATE TABLE  `conference`.`venue` (
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
  `facilityName` text CHARACTER SET utf8,
  `facilityPhone` text CHARACTER SET utf8,
  `documentsURL` text COLLATE utf8_unicode_ci,
  `documentsURLUsername` text COLLATE utf8_unicode_ci,
  `documentsURLPassword` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`),
  KEY `lable_index` (`lable`(10))
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Definition of table `conference`.`venueattendee`
--

DROP TABLE IF EXISTS `conference`.`venueattendee`;
CREATE TABLE  `conference`.`venueattendee` (
  `attendeeId` int(11) NOT NULL,
  `venueId` int(11) NOT NULL,
  PRIMARY KEY (`attendeeId`,`venueId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


--
-- Definition of table `conference`.`vevent`
--

DROP TABLE IF EXISTS `conference`.`vevent`;
CREATE TABLE  `conference`.`vevent` (
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


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
