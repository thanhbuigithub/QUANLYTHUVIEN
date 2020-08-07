-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: quanlythuvien
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `sach`
--

DROP TABLE IF EXISTS `sach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sach` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten_sach` varchar(100) NOT NULL,
  `nam_xuat_ban` int NOT NULL,
  `ma_nxb` int NOT NULL,
  `ma_vi_tri` int DEFAULT NULL,
  `mo_ta` varchar(500) DEFAULT NULL,
  `gia_bia` int DEFAULT NULL,
  `so_luong` int DEFAULT NULL,
  `so_trang` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_nxb_sach_idx` (`ma_nxb`),
  KEY `fk_vi_tri_sach_idx` (`ma_vi_tri`),
  CONSTRAINT `fk_nxb_sach` FOREIGN KEY (`ma_nxb`) REFERENCES `nxb` (`id`),
  CONSTRAINT `fk_vi_tri_sach` FOREIGN KEY (`ma_vi_tri`) REFERENCES `vi_tri` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sach`
--

LOCK TABLES `sach` WRITE;
/*!40000 ALTER TABLE `sach` DISABLE KEYS */;
INSERT INTO `sach` VALUES (1,'TRANH TRUYỆN DÂN GIAN VIỆT NAM - BÀ CHÚA TRẦM HƯƠNG',2016,1,1,NULL,14000,NULL,36),(2,'Đi Tìm Triết Lý Giáo Dục Việt Nam',2019,2,2,NULL,75000,NULL,284),(3,'Giáo Trình Phong Trào SAEMAUL Của Hàn Quốc',2020,3,3,NULL,150000,NULL,198),(4,'Phân Tích Thống Kê Và Khai Phá Dữ Liệu',2018,4,4,NULL,95000,NULL,352),(5,'Bộ Luật Dân Sự (Hiện Hành)',2019,5,5,NULL,113000,NULL,350);
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

-- Dump completed on 2020-08-06  9:17:50
