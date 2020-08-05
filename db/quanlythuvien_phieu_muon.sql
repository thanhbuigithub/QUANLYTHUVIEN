-- MariaDB dump 10.17  Distrib 10.4.13-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: quanlythuvien
-- ------------------------------------------------------
-- Server version	10.4.13-MariaDB

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
-- Table structure for table `phieu_muon`
--

DROP TABLE IF EXISTS `phieu_muon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phieu_muon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_sach` int(11) DEFAULT NULL,
  `id_the_thu_vien` int(11) DEFAULT NULL,
  `ngay_muon` date DEFAULT NULL,
  `thoi_han_muon` int(11) DEFAULT NULL,
  `gia_han` int(11) DEFAULT NULL,
  `id_nhan_vien` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sach_phieu_muon_idx` (`id_sach`),
  KEY `fk_the_thu_vien_phieu_muon_idx` (`id_the_thu_vien`),
  KEY `fk_nhan_vien_phieu_muon_idx` (`id_nhan_vien`),
  CONSTRAINT `fk_nhan_vien_phieu_muon` FOREIGN KEY (`id_nhan_vien`) REFERENCES `nhan_vien` (`id`),
  CONSTRAINT `fk_sach_phieu_muon` FOREIGN KEY (`id_sach`) REFERENCES `sach` (`id`),
  CONSTRAINT `fk_the_thu_vien_phieu_muon` FOREIGN KEY (`id_the_thu_vien`) REFERENCES `the_thu_vien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieu_muon`
--

LOCK TABLES `phieu_muon` WRITE;
/*!40000 ALTER TABLE `phieu_muon` DISABLE KEYS */;
INSERT INTO `phieu_muon` VALUES (5,2,1,'2020-08-08',2020,1,1),(6,3,2,'2020-08-09',2020,NULL,2),(7,4,3,'2020-08-08',2020,NULL,3),(8,5,4,'2020-08-08',2020,NULL,4),(9,6,5,'2020-08-08',2020,NULL,5);
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

-- Dump completed on 2020-08-05 18:40:25
