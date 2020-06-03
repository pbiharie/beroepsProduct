CREATE DATABASE  IF NOT EXISTS `bioscoop` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bioscoop`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: bioscoop
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `film`
--

DROP TABLE IF EXISTS `film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `film` (
  `film_id` int NOT NULL AUTO_INCREMENT,
  `naam` varchar(255) NOT NULL,
  `genre` varchar(255) NOT NULL,
  `dimension_type` varchar(45) NOT NULL,
  PRIMARY KEY (`film_id`),
  UNIQUE KEY `film_id_UNIQUE` (`film_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `filmvoorstelling`
--

DROP TABLE IF EXISTS `filmvoorstelling`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `filmvoorstelling` (
  `filmvoorstelling_id` int NOT NULL AUTO_INCREMENT,
  `start_datumtijd` datetime NOT NULL,
  `film_id` int NOT NULL,
  `zaal_id` int NOT NULL,
  PRIMARY KEY (`filmvoorstelling_id`),
  UNIQUE KEY `filmvoorstelling_id_UNIQUE` (`filmvoorstelling_id`),
  KEY `fk_filmvoorstelling_film_idx` (`film_id`),
  KEY `fk_filmvoorstelling_zaal_idx` (`zaal_id`),
  CONSTRAINT `fk_filmvoorstelling_film` FOREIGN KEY (`film_id`) REFERENCES `film` (`film_id`),
  CONSTRAINT `fk_filmvoorstelling_zaal` FOREIGN KEY (`zaal_id`) REFERENCES `zaal` (`zaal_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `kaart`
--

DROP TABLE IF EXISTS `kaart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kaart` (
  `kaart_id` int NOT NULL AUTO_INCREMENT,
  `kaart_nummer` int NOT NULL,
  `prijs` double NOT NULL,
  `zitplaats_nummer` int NOT NULL,
  `verkoop_datum` date NOT NULL,
  `werknemergegevens_id` int NOT NULL,
  `filmvoorstelling_id` int NOT NULL,
  PRIMARY KEY (`kaart_id`),
  UNIQUE KEY `kaart_id_UNIQUE` (`kaart_id`),
  KEY `fk_kaart_werknemersgegevens_idx` (`werknemergegevens_id`),
  KEY `fk_kaart_filmvoorstelling_idx` (`filmvoorstelling_id`),
  CONSTRAINT `fk_kaart_filmvoorstelling` FOREIGN KEY (`filmvoorstelling_id`) REFERENCES `filmvoorstelling` (`filmvoorstelling_id`),
  CONSTRAINT `fk_kaart_werknemersgegevens` FOREIGN KEY (`werknemergegevens_id`) REFERENCES `werknemergegevens` (`werknemergegevens_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `werknemergegevens`
--

DROP TABLE IF EXISTS `werknemergegevens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `werknemergegevens` (
  `werknemergegevens_id` int NOT NULL AUTO_INCREMENT,
  `voornaam` varchar(255) NOT NULL,
  `achternaam` varchar(255) NOT NULL,
  `geboortedatum` date NOT NULL,
  `adres` varchar(255) NOT NULL,
  `gebruikersnaam` varchar(255) NOT NULL,
  `wachtwoord` varchar(255) NOT NULL,
  PRIMARY KEY (`werknemergegevens_id`),
  UNIQUE KEY `credentials_id_UNIQUE` (`werknemergegevens_id`),
  UNIQUE KEY `gebruikersnaam_UNIQUE` (`gebruikersnaam`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `zaal`
--

DROP TABLE IF EXISTS `zaal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zaal` (
  `zaal_id` int NOT NULL AUTO_INCREMENT,
  `zaal_nummer` int NOT NULL,
  `aantal_zitplaatsen` int NOT NULL,
  PRIMARY KEY (`zaal_id`),
  UNIQUE KEY `zaal_id_UNIQUE` (`zaal_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'bioscoop'
--

--
-- Dumping routines for database 'bioscoop'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-02 18:58:12
