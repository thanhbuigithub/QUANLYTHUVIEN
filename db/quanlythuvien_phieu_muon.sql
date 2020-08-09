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
-- Table structure for table `phieu_muon`
--

DROP TABLE IF EXISTS `phieu_muon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieu_muon` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_sach` int DEFAULT NULL,
  `id_ban_doc` int DEFAULT NULL,
  `ngay_muon` date DEFAULT NULL,
  `thoi_han_muon` int DEFAULT NULL,
  `gia_han` int DEFAULT NULL,
  `id_nhan_vien` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sach_phieu_muon_idx` (`id_sach`),
  KEY `fk_nhan_vien_phieu_muon_idx` (`id_nhan_vien`),
  KEY `fk_ban_doc_phieu_muon_idx` (`id_ban_doc`),
  CONSTRAINT `fk_ban_doc_phieu_muon` FOREIGN KEY (`id_ban_doc`) REFERENCES `ban_doc` (`id`),
  CONSTRAINT `fk_nhan_vien_phieu_muon` FOREIGN KEY (`id_nhan_vien`) REFERENCES `nhan_vien` (`id`),
  CONSTRAINT `fk_sach_phieu_muon` FOREIGN KEY (`id_sach`) REFERENCES `sach` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieu_muon`
--

LOCK TABLES `phieu_muon` WRITE;
/*!40000 ALTER TABLE `phieu_muon` DISABLE KEYS */;
INSERT INTO `phieu_muon` VALUES (5,2,1,'2020-08-08',7,1,1),(6,3,2,'2020-08-09',7,0,2),(7,4,3,'2020-08-08',14,1,3),(8,5,4,'2020-08-08',7,0,4),(9,6,5,'2020-08-08',7,0,5);
/*!40000 ALTER TABLE `phieu_muon` ENABLE KEYS */;
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
