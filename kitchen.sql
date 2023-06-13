-- MariaDB dump 10.19  Distrib 10.11.4-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: kitchen
-- ------------------------------------------------------
-- Server version	10.11.4-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attendances`
--

DROP TABLE IF EXISTS `attendances`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendances` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_id` int(11) NOT NULL,
  `day` date NOT NULL,
  `checkin_at` time NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_emp_Id` (`emp_id`) USING BTREE,
  CONSTRAINT `FK_emp_Id` FOREIGN KEY (`emp_id`) REFERENCES `employees` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendances`
--

LOCK TABLES `attendances` WRITE;
/*!40000 ALTER TABLE `attendances` DISABLE KEYS */;
INSERT INTO `attendances` VALUES
(100,401,'2023-06-15','15:07:05'),
(101,402,'2023-06-15','15:10:55'),
(102,403,'2023-06-28','15:10:55'),
(105,406,'2023-06-15','20:13:41'),
(106,403,'2023-06-14','20:13:41'),
(107,407,'2023-06-02','20:13:41'),
(108,409,'2023-06-14','20:13:41'),
(109,407,'2023-06-13','20:13:41'),
(110,410,'2023-06-01','15:13:30'),
(111,411,'2023-06-07','20:13:41'),
(112,411,'2023-06-08','20:13:41'),
(113,410,'2023-06-02','20:13:41'),
(114,409,'2023-06-01','15:13:30');
/*!40000 ALTER TABLE `attendances` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` text DEFAULT NULL,
  `last_name` text DEFAULT 'NULL',
  `phones` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=317 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES
(300,'Nguyễn Văn','Hiếu','12314234'),
(301,'Nguyễn Văn','Long','12412443'),
(302,'Nguyễn Văn','Nhật','124124'),
(303,'Nguyễn Văn','Bản','345345335'),
(304,'Nguyễn Văn','Bình','12354125'),
(305,'Nguyễn Văn','Minh','3453453'),
(306,'Nguyễn Văn','Kiêm','12412344'),
(307,'Nguyễn Văn','Kinh','124124'),
(308,'Nguyễn Văn','Cam','12345345'),
(309,'Nguyễn Văn','Canh','12341234'),
(310,'Nguyễn Văn','Quýt','124124'),
(311,'Nguyễn Văn','Ban','23456345'),
(312,'Nguyễn Văn','Kim','1234512345'),
(313,'Nguyễn Văn','Huy','12341234');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dishes`
--

DROP TABLE IF EXISTS `dishes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dishes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `price` int(11) NOT NULL,
  `image_path` text NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dishes`
--

LOCK TABLES `dishes` WRITE;
/*!40000 ALTER TABLE `dishes` DISABLE KEYS */;
INSERT INTO `dishes` VALUES
(200,'Cơm rang dưa bò',45000,'/resource/beef_fried_rice.png','Món cơm rang dưa bò của nhà hàng chúng tôi là số 1'),
(201,'Bánh mì nướng muối ớt',20000,'',''),
(202,'Cơm rang thập cẩm',35000,'',''),
(203,'Sting',15000,'',''),
(204,'Phở bò',40000,'',''),
(205,'Nem chua rán',35000,'',''),
(206,'Kem',12000,'',''),
(207,'Cơm gà chiên',40000,'',''),
(208,'Cơm sườn sốt',45000,'',''),
(209,'Cơm rang gà chiên',45000,'',''),
(210,'Cơm rang trứng',25000,'','');
/*!40000 ALTER TABLE `dishes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employees` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` text NOT NULL,
  `last_name` text NOT NULL,
  `phone` text NOT NULL,
  `salary` int(11) NOT NULL,
  `position` text NOT NULL,
  `image_path` text NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=413 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES
(400,'Nguyễn Thạc','Hiếu','123414',4000,'Staff',''),
(401,'Nguyễn Bá','Sơn','152345',5000,'Staff',''),
(402,'Nguyễn Phi','Hùng','145123',4000,'Staff',''),
(403,'Ngô Tiến','Long','124123',4000,'Staff',''),
(404,'Phạm Danh','Hiển','123515',6000,'Staff',''),
(405,'Nguyễn Khắc','Nhật','145153',5000,'Staff',''),
(406,'Nông Quang','Tùng','834756',4000,'Staff',''),
(407,'Nguyễn Anh','Minh','123526',5000,'Staff',''),
(408,'Nguyễn Văn','Ninh','152345',7000,'Staff',''),
(409,'Nguyễn Văn','Nam','345666',4000,'Staff',''),
(410,'Nguyễn Văn','Cam','151243124',4000,'Staff',''),
(411,'Nguyễn Văn','Kinh','12352345',5000,'Staff','');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredients`
--

DROP TABLE IF EXISTS `ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingredients` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `date_in` date NOT NULL,
  `in_stock` int(11) NOT NULL,
  `cost` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=612 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredients`
--

LOCK TABLES `ingredients` WRITE;
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
INSERT INTO `ingredients` VALUES
(600,'thịt bò','2023-06-14',30000,150000),
(601,'trứng','2023-06-14',1000,2300),
(602,'dưa chua','2023-06-22',1000,2000),
(603,'cơm','2024-06-13',100000,3500),
(604,'cà chua','2023-06-30',10000,60000),
(605,'đường','2023-10-12',10000,18000),
(606,'muối','2023-08-24',10000,1000),
(607,'dầu hào','2023-07-21',10000,40000),
(608,'xì dầu','2023-06-14',10000,20000),
(609,'thịt gà','2023-06-22',60000,120000),
(610,'sữa','2023-06-23',70000,20000);
/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoices`
--

DROP TABLE IF EXISTS `invoices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoices` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created_day` date NOT NULL,
  `created_time` time NOT NULL,
  `customer_id` int(11) NOT NULL,
  `customer_feedback` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `invoices_relation_1` (`customer_id`),
  CONSTRAINT `invoices_relation_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=703 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoices`
--

LOCK TABLES `invoices` WRITE;
/*!40000 ALTER TABLE `invoices` DISABLE KEYS */;
INSERT INTO `invoices` VALUES
(701,'2023-06-01','12:22:03',307,'Lần đầu đi ăn rất ok'),
(702,'2023-06-09','20:30:35',307,'Trải nghiệm lại lần này tốt hơn trước');
/*!40000 ALTER TABLE `invoices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quantities`
--

DROP TABLE IF EXISTS `quantities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quantities` (
  `dish_id` int(11) NOT NULL,
  `ingredient_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`dish_id`,`ingredient_id`) USING BTREE,
  KEY `FK_quantities_ingredients` (`ingredient_id`),
  CONSTRAINT `FK_quantities_dishes` FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`id`),
  CONSTRAINT `FK_quantities_ingredients` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredients` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quantities`
--

LOCK TABLES `quantities` WRITE;
/*!40000 ALTER TABLE `quantities` DISABLE KEYS */;
INSERT INTO `quantities` VALUES
(200,600,1),
(200,603,3),
(210,601,2),
(210,603,3);
/*!40000 ALTER TABLE `quantities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-13 21:20:17
