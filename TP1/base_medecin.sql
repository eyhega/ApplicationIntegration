-- MySQL dump 10.13  Distrib 5.5.15, for Win32 (x86)
--
-- Host: localhost    Database: base_medecin_ed
-- ------------------------------------------------------
-- Server version	5.5.16

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
-- Current Database: `base_medecin_ed`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `base_medecin_ed` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `base_medecin_ed`;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clients` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `VERSION` int(11) NOT NULL,
  `TITRE` varchar(5) NOT NULL,
  `NOM` varchar(30) NOT NULL,
  `PRENOM` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,1,'Mr','MARTIN','Jules'),(2,1,'Mme','GERMAN','Christine'),(3,1,'Mr','JACQUARD','Jules'),(4,1,'Melle','BISTROU','Brigitte');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creneaux`
--

DROP TABLE IF EXISTS `creneaux`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `creneaux` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `VERSION` int(11) NOT NULL,
  `HDEBUT` int(11) NOT NULL,
  `MDEBUT` int(11) NOT NULL,
  `HFIN` int(11) NOT NULL,
  `MFIN` int(11) NOT NULL,
  `ID_MEDECIN` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK9BD7A197FE16862` (`ID_MEDECIN`),
  CONSTRAINT `FK9BD7A197FE16862` FOREIGN KEY (`ID_MEDECIN`) REFERENCES `medecins` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creneaux`
--

LOCK TABLES `creneaux` WRITE;
/*!40000 ALTER TABLE `creneaux` DISABLE KEYS */;
INSERT INTO `creneaux` VALUES (1,1,8,0,8,20,1),(2,1,8,20,8,40,1),(3,1,8,40,9,0,1),(4,1,9,0,9,20,1),(5,1,9,20,9,40,1),(6,1,9,40,10,0,1),(7,1,10,0,10,20,1),(8,1,10,20,10,40,1),(9,1,10,40,11,0,1),(10,1,11,0,11,20,1),(11,1,11,20,11,40,1),(12,1,11,40,12,0,1),(13,1,14,0,14,20,1),(14,1,14,20,14,40,1),(15,1,14,40,15,0,1),(16,1,15,0,15,20,1),(17,1,15,20,15,40,1),(18,1,15,40,16,0,1),(19,1,16,0,16,20,1),(20,1,16,20,16,40,1),(21,1,16,40,17,0,1),(22,1,17,0,17,20,1),(23,1,17,20,17,40,1),(24,1,17,40,18,0,1),(25,1,8,0,8,20,2),(26,1,8,20,8,40,2),(27,1,8,40,9,0,2),(28,1,9,0,9,20,2),(29,1,9,20,9,40,2),(30,1,9,40,10,0,2),(31,1,10,0,10,20,2),(32,1,10,20,10,40,2),(33,1,10,40,12,0,2),(34,1,12,0,12,20,2),(35,1,12,20,12,40,2),(36,1,12,40,12,0,2),(37,1,8,0,8,20,3),(38,1,8,20,8,40,3),(39,1,8,40,9,0,3),(40,1,9,0,9,20,3),(41,1,9,20,9,40,3),(42,1,9,40,10,0,3),(43,1,10,0,10,20,3),(44,1,10,20,10,40,3),(45,1,10,40,12,0,3),(46,1,12,0,12,20,3);
/*!40000 ALTER TABLE `creneaux` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medecins`
--

DROP TABLE IF EXISTS `medecins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medecins` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `VERSION` int(11) NOT NULL,
  `TITRE` varchar(5) NOT NULL,
  `NOM` varchar(30) NOT NULL,
  `PRENOM` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medecins`
--

LOCK TABLES `medecins` WRITE;
/*!40000 ALTER TABLE `medecins` DISABLE KEYS */;
INSERT INTO `medecins` VALUES (1,1,'Mme','PELISSIER','Marie'),(2,1,'Mr','BROMARD','Jacques'),(3,1,'Mr','JANDOT','Philippe'),(4,1,'Melle','JACQUEMOT','Justine');
/*!40000 ALTER TABLE `medecins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rv`
--

DROP TABLE IF EXISTS `rv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rv` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `JOUR` date NOT NULL,
  `ID_CLIENT` bigint(20) NOT NULL,
  `ID_CRENEAU` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNQ1_RV` (`JOUR`,`ID_CRENEAU`),
  KEY `FKA4494D97AD2` (`ID_CLIENT`),
  KEY `FKA441A673246` (`ID_CRENEAU`),
  CONSTRAINT `FKA441A673246` FOREIGN KEY (`ID_CRENEAU`) REFERENCES `creneaux` (`ID`),
  CONSTRAINT `FKA4494D97AD2` FOREIGN KEY (`ID_CLIENT`) REFERENCES `clients` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rv`
--

LOCK TABLES `rv` WRITE;
/*!40000 ALTER TABLE `rv` DISABLE KEYS */;
INSERT INTO `rv` VALUES (1,'2006-08-22',2,1),(2,'2006-08-23',2,5),(3,'2006-08-23',4,20),(4,'2006-09-10',2,10),(5,'2006-08-23',4,8),(6,'2006-08-23',3,7);
/*!40000 ALTER TABLE `rv` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-10-08 16:04:21
