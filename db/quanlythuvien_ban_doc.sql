-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: quanlythuvien
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `ban_doc`
--

DROP TABLE IF EXISTS `ban_doc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ban_doc` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ho_va_ten` varchar(45) DEFAULT NULL,
  `ngay_sinh` date DEFAULT NULL,
  `gioi_tinh` int DEFAULT NULL,
  `cmnd` varchar(10) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `sdt` varchar(45) DEFAULT NULL,
  `thoi_han_su_dung_the` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ban_doc`
--

LOCK TABLES `ban_doc` WRITE;
/*!40000 ALTER TABLE `ban_doc` DISABLE KEYS */;
INSERT INTO `ban_doc` VALUES (1,'Nguyễn Hoàng Nam','1999-04-12',0,'215467745','hoangnam@gmail.com','0367127128','2021-08-08'),(2,'Đinh Minh Nhựt','1989-11-12',0,'214263945','minhnhut@gmail.com','0235162789','2021-08-08'),(3,'Hoàng Kim Ngân','1990-12-01',1,'231453623','kimngan@gmail.com','0123517283','2021-08-08'),(4,'Vũ Minh Luân','1995-09-09',0,'312456324','minhluan@gmail.com','0127347284','2021-08-08'),(5,'Nguyễn Thị Mai','1998-05-08',1,'312435345','thimai@gmail.com','0126346283','2021-08-08');
/*!40000 ALTER TABLE `ban_doc` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-08 21:52:54
