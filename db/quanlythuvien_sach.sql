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
-- Table structure for table `sach`
--

DROP TABLE IF EXISTS `sach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sach` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ten_sach` varchar(100) NOT NULL,
  `nam_xuat_ban` int(11) NOT NULL,
  `ma_nxb` int(11) NOT NULL,
  `ma_vi_tri` int(11) DEFAULT NULL,
  `mo_ta` varchar(500) DEFAULT NULL,
  `gia_bia` int(11) DEFAULT NULL,
  `so_luong` int(11) DEFAULT NULL,
  `so_trang` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_nxb_sach_idx` (`ma_nxb`),
  KEY `fk_vi_tri_sach_idx` (`ma_vi_tri`),
  CONSTRAINT `fk_nxb_sach` FOREIGN KEY (`ma_nxb`) REFERENCES `nxb` (`id`),
  CONSTRAINT `fk_vi_tri_sach` FOREIGN KEY (`ma_vi_tri`) REFERENCES `vi_tri` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sach`
--

LOCK TABLES `sach` WRITE;
/*!40000 ALTER TABLE `sach` DISABLE KEYS */;
INSERT INTO `sach` VALUES (2,'TRANH TRUYỆN DÂN GIAN VIỆT NAM - BÀ CHÚA TRẦM HƯƠNG',0,1,NULL,NULL,NULL,NULL,0),(3,'Bộ sách OpenMath',0,2,NULL,NULL,NULL,NULL,0),(4,'Giáo trình Chế Biến Dầu Và Chất Béo',0,3,NULL,NULL,NULL,NULL,0),(5,'Bản Sắc Việt Nam',2015,4,NULL,NULL,148000,NULL,523),(6,'Bộ Luật Dân Sự (Hiện Hành)',2019,5,NULL,NULL,50000,NULL,350);
/*!40000 ALTER TABLE `sach` ENABLE KEYS */;
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
