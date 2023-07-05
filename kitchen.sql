-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.28-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for kitchen2
DROP DATABASE IF EXISTS `kitchen2`;
CREATE DATABASE IF NOT EXISTS `kitchen2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `kitchen2`;

-- Dumping structure for table kitchen2.attendances
DROP TABLE IF EXISTS `attendances`;
CREATE TABLE IF NOT EXISTS `attendances` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_id` int(11) NOT NULL,
  `day` date NOT NULL,
  `checkin_at` datetime NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_emp_Id` (`emp_id`) USING BTREE,
  CONSTRAINT `FK_emp_Id` FOREIGN KEY (`emp_id`) REFERENCES `employees` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Dumping data for table kitchen2.attendances: ~16 rows (approximately)
INSERT INTO `attendances` (`Id`, `emp_id`, `day`, `checkin_at`) VALUES
	(100, 401, '2023-06-15', '2023-06-01 15:07:05'),
	(101, 402, '2023-06-15', '2023-06-01 15:10:55'),
	(102, 403, '2023-06-28', '2023-06-01 15:10:55'),
	(105, 406, '2023-06-15', '2023-06-15 20:13:41'),
	(106, 403, '2023-06-14', '2023-06-14 20:13:41'),
	(107, 407, '2023-06-02', '2023-06-02 20:13:41'),
	(108, 409, '2023-06-14', '2023-06-14 20:13:41'),
	(109, 407, '2023-06-13', '2023-06-13 20:13:41'),
	(110, 410, '2023-06-01', '2023-06-01 15:13:30'),
	(111, 411, '2023-06-07', '2023-06-07 20:13:41'),
	(112, 411, '2023-06-08', '2023-06-08 20:13:41'),
	(113, 410, '2023-06-02', '2023-06-02 20:13:41'),
	(114, 409, '2023-06-01', '2023-06-01 15:13:30'),
	(115, 401, '2023-07-01', '2023-07-01 04:54:41'),
	(116, 401, '2023-07-01', '2023-07-01 10:55:16'),
	(117, 401, '2023-07-01', '2023-07-01 17:55:16');

-- Dumping structure for table kitchen2.customers
DROP TABLE IF EXISTS `customers`;
CREATE TABLE IF NOT EXISTS `customers` (
  `Id` int(11) NOT NULL,
  `first_name` int(11) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `phone` text DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Dumping data for table kitchen2.customers: ~14 rows (approximately)
INSERT INTO `customers` (`Id`, `first_name`, `last_name`, `phone`) VALUES
	(300, NULL, 'Hiếu', '12314234'),
	(301, NULL, 'Long', '12412443'),
	(302, NULL, 'Nhật', ' 124124'),
	(303, NULL, 'Bản', '345345335'),
	(304, NULL, 'Bình', '12354125'),
	(305, NULL, 'Minh', '3453453'),
	(306, NULL, 'Kiêm', '12412344'),
	(307, NULL, 'Kinh', '124124'),
	(308, NULL, 'Cam', '12345345'),
	(309, NULL, 'Canh', '12341234'),
	(310, NULL, 'Quýt', '124124'),
	(311, NULL, 'Ban', '23456345'),
	(312, NULL, 'Kim', '1234512345'),
	(313, NULL, 'Huy', '12341234');

-- Dumping structure for table kitchen2.dishes
DROP TABLE IF EXISTS `dishes`;
CREATE TABLE IF NOT EXISTS `dishes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `cost` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `image_path` text DEFAULT NULL,
  `description` text DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Dumping data for table kitchen2.dishes: ~11 rows (approximately)
INSERT INTO `dishes` (`id`, `name`, `cost`, `price`, `image_path`, `description`) VALUES
	(200, 'Cơm rang dưa bò', 20000, 45000, '/resource/dishes/beef_fried_rice.png', 'Đây là best seller của quán'),
	(201, 'Bánh mì nướng muối ớt', 7500, 20000, '/resource/dishes/download.png', 'Đây là best seller của quán'),
	(202, 'Cơm rang thập cẩm', 13000, 35000, NULL, NULL),
	(203, 'Sting', 7500, 15000, NULL, 'Là thức uống nổi tiếng'),
	(204, 'Phở bò', 15000, 40000, NULL, NULL),
	(205, 'Nem chua rán', 15000, 35000, NULL, NULL),
	(206, 'Kem', 3000, 12000, NULL, NULL),
	(207, 'Cơm gà chiên', 17000, 40000, NULL, NULL),
	(208, 'Cơm sườn sốt', 20000, 45000, NULL, NULL),
	(209, 'Cơm rang gà chiên', 20000, 45000, NULL, NULL),
	(210, 'Cơm rang trứng', 10000, 25000, NULL, NULL);

-- Dumping structure for table kitchen2.employees
DROP TABLE IF EXISTS `employees`;
CREATE TABLE IF NOT EXISTS `employees` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `phone` text NOT NULL,
  `position` text NOT NULL,
  `salary` int(11) NOT NULL,
  `image_path` text DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=413 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Dumping data for table kitchen2.employees: ~12 rows (approximately)
INSERT INTO `employees` (`Id`, `name`, `phone`, `position`, `salary`, `image_path`) VALUES
	(400, 'Hiếu', '123414', 'nv', 4000, '/resource/employee/download (8).png'),
	(401, 'Sơn', '152345', 'PB', 5000, '/resource/employee/download (9).png"'),
	(402, 'Hùng', '145123', 'NV', 4000, NULL),
	(403, 'Long', '124123', 'NV', 4000, NULL),
	(404, 'Hiển', '123515', 'BC', 6000, NULL),
	(405, 'Nhật', '145153', 'PB', 5000, NULL),
	(406, 'Tùng', '834756', 'NV', 4000, NULL),
	(407, 'Minh', '123526', 'PB', 5000, NULL),
	(408, 'Ninh', '152345', 'BT', 7000, NULL),
	(409, 'Nam', '345666', 'NV', 4000, NULL),
	(410, 'Cam', '151243124', 'NV', 4000, NULL),
	(411, 'Kinh', '12352345', 'PB', 5000, NULL);

-- Dumping structure for table kitchen2.ingredients
DROP TABLE IF EXISTS `ingredients`;
CREATE TABLE IF NOT EXISTS `ingredients` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `date_in` date NOT NULL,
  `in_stock` int(11) NOT NULL,
  `cost` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=616 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Dumping data for table kitchen2.ingredients: ~15 rows (approximately)
INSERT INTO `ingredients` (`Id`, `name`, `date_in`, `in_stock`, `cost`) VALUES
	(600, 'thịt bò', '2023-06-14', 30, 150000),
	(601, 'trứng', '2023-06-14', 100, 2300),
	(602, 'dưa chua', '2023-06-22', 1, 2000),
	(603, 'cơm', '2024-06-13', 100, 3500),
	(604, 'cà chua', '2023-06-30', 10, 60000),
	(605, 'đường', '2023-10-12', 10, 18000),
	(606, 'muối', '2023-08-24', 10, 1000),
	(607, 'dầu hào', '2023-07-21', 10, 40000),
	(608, 'xì dầu', '2023-06-14', 10, 20000),
	(609, 'thịt gà', '2023-06-22', 60, 120000),
	(610, 'sữa', '2023-06-23', 70, 20000),
	(612, 'mì chính', '2023-06-14', 10, 15000),
	(613, 'bột mì', '2023-07-01', 10, 10000),
	(614, 'tương ớt', '2023-07-10', 10, 20000),
	(615, 'ốc quê', '2023-06-09', 6, 30000);

-- Dumping structure for table kitchen2.invoices
DROP TABLE IF EXISTS `invoices`;
CREATE TABLE IF NOT EXISTS `invoices` (
  `id` varchar(12) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `customer_feedback` text DEFAULT NULL,
  `total_price` int(11) NOT NULL DEFAULT 0,
  `createdDay` date NOT NULL,
  `createdTime` time NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Dumping data for table kitchen2.invoices: ~9 rows (approximately)
INSERT INTO `invoices` (`id`, `customer_id`, `customer_feedback`, `total_price`, `createdDay`, `createdTime`) VALUES
	('202206140001', 303, 'Khá ok', 85000, '2023-06-29', '02:02:38'),
	('202206140002', 301, NULL, 74000, '2023-06-08', '11:49:06'),
	('202206140003', 300, NULL, 80000, '2023-07-20', '09:20:02'),
	('202206140004', 308, NULL, 100000, '2023-07-04', '11:34:02'),
	('202206140005', 309, NULL, 45000, '2023-07-06', '06:17:54'),
	('202206140006', 305, NULL, 75000, '2023-07-04', '00:00:00'),
	('202206140007', 312, NULL, 45000, '2023-07-02', '17:48:24'),
	('202206140008', 302, NULL, 90000, '2023-07-12', '06:48:48'),
	('202206140022', 312, NULL, 57000, '2023-07-02', '03:22:38');

-- Dumping structure for table kitchen2.invoices_detail
DROP TABLE IF EXISTS `invoices_detail`;
CREATE TABLE IF NOT EXISTS `invoices_detail` (
  `invoice_id` int(11) NOT NULL,
  `dishes_id` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  KEY `FK__dishes` (`dishes_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table kitchen2.invoices_detail: ~16 rows (approximately)
INSERT INTO `invoices_detail` (`invoice_id`, `dishes_id`, `amount`) VALUES
	(701, 201, 1),
	(701, 203, 2),
	(701, 206, 2),
	(702, 202, 1),
	(702, 200, 1),
	(703, 204, 2),
	(703, 203, 2),
	(705, 205, 1),
	(705, 201, 2),
	(704, 208, 1),
	(706, 200, 1),
	(707, 209, 2),
	(708, 202, 1),
	(708, 210, 1),
	(709, 208, 1),
	(709, 206, 1);

-- Dumping structure for table kitchen2.quantities
DROP TABLE IF EXISTS `quantities`;
CREATE TABLE IF NOT EXISTS `quantities` (
  `ingredient_id` int(11) NOT NULL,
  `dishes_id` int(11) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  KEY `FK_quantities_ingredients` (`ingredient_id`),
  KEY `FK_quantities_dishes` (`dishes_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- Dumping data for table kitchen2.quantities: ~16 rows (approximately)
INSERT INTO `quantities` (`ingredient_id`, `dishes_id`, `quantity`) VALUES
	(600, 201, 123),
	(601, 201, 6),
	(603, 207, 4),
	(614, 201, 8),
	(610, 206, 60),
	(601, 210, 3),
	(605, 204, 3),
	(612, 204, 6),
	(609, 209, 6),
	(609, 207, 6),
	(602, 200, 2),
	(615, 206, 1),
	(606, 210, 6),
	(606, 202, 6),
	(607, 200, 5),
	(613, 201, 34);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
