-- --------------------------------------------------------
-- Máy chủ:                      127.0.0.1
-- Server version:               11.0.1-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Phiên bản:           12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for kitchen
CREATE DATABASE IF NOT EXISTS `kitchen` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `kitchen`;

-- Dumping structure for table kitchen.attendances
CREATE TABLE IF NOT EXISTS `attendances` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_id` int(11) NOT NULL,
  `day` date NOT NULL,
  `checkin_at` datetime NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_emp_Id` (`emp_id`) USING BTREE,
  CONSTRAINT `FK_emp_Id` FOREIGN KEY (`emp_id`) REFERENCES `employees` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- Dumping data for table kitchen.attendances: ~13 rows (approximately)
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
	(114, 409, '2023-06-01', '2023-06-01 15:13:30');

-- Dumping structure for table kitchen.customers
CREATE TABLE IF NOT EXISTS `customers` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `phone` text DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=317 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- Dumping data for table kitchen.customers: ~14 rows (approximately)
INSERT INTO `customers` (`Id`, `name`, `phone`) VALUES
	(300, 'Hiếu', '12314234'),
	(301, 'Long', '12412443'),
	(302, 'Nhật', ' 124124'),
	(303, 'Bản', '345345335'),
	(304, 'Bình', '12354125'),
	(305, 'Minh', '3453453'),
	(306, 'Kiêm', '12412344'),
	(307, 'Kinh', '124124'),
	(308, 'Cam', '12345345'),
	(309, 'Canh', '12341234'),
	(310, 'Quýt', '124124'),
	(311, 'Ban', '23456345'),
	(312, 'Kim', '1234512345'),
	(313, 'Huy', '12341234');

-- Dumping structure for table kitchen.dishes
CREATE TABLE IF NOT EXISTS `dishes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `price` int(11) NOT NULL,
  `image_path` text NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- Dumping data for table kitchen.dishes: ~11 rows (approximately)
INSERT INTO `dishes` (`id`, `name`, `price`, `image_path`, `description`) VALUES
	(200, 'Cơm rang dưa bò', 45000, '', ''),
	(201, 'Bánh mì nướng muối ớt', 20000, '', ''),
	(202, 'Cơm rang thập cẩm', 35000, '', ''),
	(203, 'Sting', 15000, '', ''),
	(204, 'Phở bò', 40000, '', ''),
	(205, 'Nem chua rán', 35000, '', ''),
	(206, 'Kem', 12000, '', ''),
	(207, 'Cơm gà chiên', 40000, '', ''),
	(208, 'Cơm sườn sốt', 45000, '', ''),
	(209, 'Cơm rang gà chiên', 45000, '', ''),
	(210, 'Cơm rang trứng', 25000, '', '');

-- Dumping structure for table kitchen.employees
CREATE TABLE IF NOT EXISTS `employees` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `phone` text NOT NULL,
  `position` text NOT NULL,
  `salary` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=413 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- Dumping data for table kitchen.employees: ~12 rows (approximately)
INSERT INTO `employees` (`Id`, `name`, `phone`, `position`, `salary`) VALUES
	(400, 'Hiếu', '123414', 'nv', 4000),
	(401, 'Sơn', '152345', 'PB', 5000),
	(402, 'Hùng', '145123', 'NV', 4000),
	(403, 'Long', '124123', 'NV', 4000),
	(404, 'Hiển', '123515', 'BC', 6000),
	(405, 'Nhật', '145153', 'PB', 5000),
	(406, 'Tùng', '834756', 'NV', 4000),
	(407, 'Minh', '123526', 'PB', 5000),
	(408, 'Ninh', '152345', 'BT', 7000),
	(409, 'Nam', '345666', 'NV', 4000),
	(410, 'Cam', '151243124', 'NV', 4000),
	(411, 'Kinh', '12352345', 'PB', 5000);

-- Dumping structure for table kitchen.ingredients
CREATE TABLE IF NOT EXISTS `ingredients` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `date_in` date NOT NULL,
  `in_stock` int(11) NOT NULL,
  `cost` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=612 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- Dumping data for table kitchen.ingredients: ~11 rows (approximately)
INSERT INTO `ingredients` (`Id`, `name`, `date_in`, `in_stock`, `cost`) VALUES
	(600, 'thịt bò', '2023-06-14', 30000, 150000),
	(601, 'trứng', '2023-06-14', 1000, 2300),
	(602, 'dưa chua', '2023-06-22', 1000, 2000),
	(603, 'cơm', '2024-06-13', 100000, 3500),
	(604, 'cà chua', '2023-06-30', 10000, 60000),
	(605, 'đường', '2023-10-12', 10000, 18000),
	(606, 'muối', '2023-08-24', 10000, 1000),
	(607, 'dầu hào', '2023-07-21', 10000, 40000),
	(608, 'xì dầu', '2023-06-14', 10000, 20000),
	(609, 'thịt gà', '2023-06-22', 60000, 120000),
	(610, 'sữa', '2023-06-23', 70000, 20000);

-- Dumping structure for table kitchen.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `createdDay` date NOT NULL,
  `createdTime` time NOT NULL,
  `cus_Id` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `cus_Id` (`cus_Id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`cus_Id`) REFERENCES `customers` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=713 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- Dumping data for table kitchen.orders: ~0 rows (approximately)
INSERT INTO `orders` (`Id`, `createdDay`, `createdTime`, `cus_Id`) VALUES
	(701, '2023-06-08', '11:49:06', 301);

-- Dumping structure for table kitchen.order_detail
CREATE TABLE IF NOT EXISTS `order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `dishes_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK__orders` (`order_id`),
  KEY `FK__dishes` (`dishes_id`),
  CONSTRAINT `FK__dishes` FOREIGN KEY (`dishes_id`) REFERENCES `dishes` (`id`),
  CONSTRAINT `FK__orders` FOREIGN KEY (`order_id`) REFERENCES `orders` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table kitchen.order_detail: ~3 rows (approximately)
INSERT INTO `order_detail` (`id`, `order_id`, `dishes_id`, `quantity`) VALUES
	(1, 701, 201, 1),
	(2, 701, 203, 2),
	(3, 701, 206, 2);

-- Dumping structure for table kitchen.quantities
CREATE TABLE IF NOT EXISTS `quantities` (
  `dish_id` int(11) NOT NULL,
  `ingredient_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`dish_id`,`ingredient_id`) USING BTREE,
  KEY `FK_quantities_ingredients` (`ingredient_id`),
  CONSTRAINT `FK_quantities_dishes` FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`id`),
  CONSTRAINT `FK_quantities_ingredients` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredients` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- Dumping data for table kitchen.quantities: ~4 rows (approximately)
INSERT INTO `quantities` (`dish_id`, `ingredient_id`, `quantity`) VALUES
	(200, 600, 1),
	(200, 603, 3),
	(210, 601, 2),
	(210, 603, 3);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
