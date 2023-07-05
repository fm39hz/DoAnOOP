-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 05, 2023 at 06:45 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kitchen`
--

-- --------------------------------------------------------

--
-- Table structure for table `attendances`
--

CREATE TABLE `attendances` (
  `Id` int(11) NOT NULL,
  `emp_id` int(11) NOT NULL,
  `day` date NOT NULL,
  `checkin_at` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `attendances`
--

INSERT INTO `attendances` (`Id`, `emp_id`, `day`, `checkin_at`) VALUES
(100, 401, '2023-06-15', '15:07:05'),
(101, 402, '2023-06-15', '15:10:55'),
(102, 403, '2023-06-28', '15:10:55'),
(105, 406, '2023-06-15', '20:13:41'),
(106, 403, '2023-06-14', '20:13:41'),
(107, 407, '2023-06-02', '20:13:41'),
(108, 409, '2023-06-14', '20:13:41'),
(109, 407, '2023-06-13', '20:13:41'),
(110, 410, '2023-06-01', '15:13:30'),
(111, 411, '2023-06-07', '20:13:41'),
(112, 411, '2023-06-08', '20:13:41'),
(113, 410, '2023-06-02', '20:13:41'),
(114, 409, '2023-06-01', '15:13:30'),
(116, 400, '2023-06-01', '02:22:18'),
(117, 400, '2023-06-14', '02:22:54'),
(118, 400, '2023-06-14', '02:23:50');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` int(11) NOT NULL,
  `first_name` text DEFAULT NULL,
  `last_name` text DEFAULT 'NULL',
  `phones` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `first_name`, `last_name`, `phones`) VALUES
(300, 'Nguyễn Văn', 'Hiếu', '12314234'),
(301, 'Nguyễn Văn', 'Long', '12412443'),
(302, 'Nguyễn Văn', 'Nhật', '124124'),
(303, 'Nguyễn Văn', 'Bản', '345345335'),
(304, 'Nguyễn Văn', 'Bình', '12354125'),
(305, 'Nguyễn Văn', 'Minh', '3453453'),
(306, 'Nguyễn Văn', 'Kiêm', '12412344'),
(307, 'Nguyễn Văn', 'Kinh', '124124'),
(308, 'Nguyễn Văn', 'Cam', '12345345'),
(309, 'Nguyễn Văn', 'Canh', '12341234'),
(310, 'Nguyễn Văn', 'Quýt', '124124'),
(311, 'Nguyễn Văn', 'Ban', '23456345'),
(312, 'Nguyễn Văn', 'Kim', '1234512345'),
(313, 'Nguyễn Văn', 'Huy', '12341234'),
(318, 'jTextField1', 'jTextField2', 'jTextField3'),
(319, 'jTextField1', 'jTextField2', 'jTextField3'),
(320, 'jTextField1', 'jTextField2', 'jTextField3'),
(321, 'Nguyễn', 'Tuấn', '345345'),
(322, 'Nguyễn', 'Tuấn', 'weqweqwe'),
(323, 'g', 'g', '2'),
(324, 'f', 'f', '3');

-- --------------------------------------------------------

--
-- Table structure for table `dishes`
--

CREATE TABLE `dishes` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `price` int(11) NOT NULL,
  `image_path` text NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `dishes`
--

INSERT INTO `dishes` (`id`, `name`, `price`, `image_path`, `description`) VALUES
(200, 'Cơm rang dưa bò', 45000, '/resource/beef_fried_rice.png', 'Món cơm rang dưa bò của nhà hàng chúng tôi là số 1'),
(201, 'Bánh mì nướng muối ớt', 20000, '/resource/dishes/download.png', 'Đây là best seller của quán'),
(202, 'Cơm rang thập cẩm', 35000, '/resource/dishes/images.png', 'Món cơm thuyển thống của quán'),
(203, 'Sting', 15000, '/resource/dishes/download (19).png', 'Nước giải khát số 1 của quán'),
(204, 'Phở bò', 40000, '/resource/dishes/download-_2_.png', 'Phở bò obama'),
(205, 'Nem chua rán', 35000, '/resource/dishes/download (3).png', 'Món tráng miệng best seller'),
(206, 'Kem', 12000, '/resource/dishes/download (4).png', 'Kem mixue'),
(207, 'Cơm gà chiên', 40000, '/resource/dishes/download-_5_.png', ''),
(208, 'Cơm sườn sốt', 45000, '/resource/dishes/download-_6_.png', ''),
(209, 'Cơm rang gà chiên', 45000, '/resource/dishes/images-_1_.png', ''),
(210, 'com ', 100000, '/resource/dishes/images-_2_.png', '');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `Id` int(11) NOT NULL,
  `first_name` text NOT NULL,
  `last_name` text NOT NULL,
  `phone` text NOT NULL,
  `salary` int(11) NOT NULL,
  `position` text NOT NULL,
  `image_path` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`Id`, `first_name`, `last_name`, `phone`, `salary`, `position`, `image_path`) VALUES
(400, 'Nguyễn Thạc', 'Hiếu', '123414', 4000, 'Staff', '/resource/employee/download (7).png'),
(401, 'Nguyễn Bá', 'Sơn', '152345', 5000, 'Staff', '/resource/employee/download (9).png'),
(402, 'Nguyễn Phi', 'Hùng', '145123', 4000, 'Staff', '/resource/employee/download (10).png'),
(403, 'Ngô Tiến', 'Long', '124123', 4000, 'Staff', '/resource/employee/download (17).png'),
(404, 'Phạm Danh', 'Hiển', '123515', 6000, 'Staff', '/resource/employee/download (16).png'),
(405, 'Nguyễn Khắc', 'Nhật', '145153', 5000, 'Staff', '/resource/employee/images (3).png'),
(406, 'Nông Quang', 'Tùng', '834756', 4000, 'Staff', '/resource/employee/images (6).png'),
(407, 'Nguyễn Anh', 'Minh', '123526', 5000, 'Staff', '/resource/employee/download (14).png'),
(408, 'Nguyễn Văn', 'Ninh', '152345', 7000, 'Staff', '/resource/employee/images (5).png'),
(409, 'Nguyễn Văn', 'Nam', '345666', 4000, 'Staff', '/resource/employee/images (7).png'),
(410, 'Nguyễn Văn', 'Cam', '151243124', 4000, 'Staff', '/resource/employee/download (11).png'),
(411, 'Nguyễn Văn', 'Kinh', '12352345', 5000, 'Staff', '/resource/employee/download (12).png');

-- --------------------------------------------------------

--
-- Table structure for table `ingredients`
--

CREATE TABLE `ingredients` (
  `Id` int(11) NOT NULL,
  `name` text NOT NULL,
  `date_in` date NOT NULL,
  `in_stock` int(11) NOT NULL,
  `cost` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `ingredients`
--

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
(610, 'test ingredient', '2023-06-01', 123123, 111);

-- --------------------------------------------------------

--
-- Table structure for table `invoices`
--

CREATE TABLE `invoices` (
  `id` varchar(12) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `customer_feedback` text DEFAULT NULL,
  `created_day` date NOT NULL,
  `created_time` time NOT NULL,
  `total_price` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `invoices`
--

INSERT INTO `invoices` (`id`, `customer_id`, `customer_feedback`, `created_day`, `created_time`, `total_price`) VALUES
('202206140001', 307, NULL, '2023-06-15', '00:01:51', NULL),
('202206140002', 307, NULL, '0000-00-00', '00:00:00', NULL),
('202206140003', 307, 'Lần đầu đi ăn rất ok', '2023-06-01', '12:22:03', NULL),
('202206140004', 307, 'Trải nghiệm lại lần này tốt hơn trước', '2023-06-09', '20:30:35', NULL),
('202206140005', 307, NULL, '2023-06-14', '22:41:54', NULL),
('202206140006', 307, NULL, '2023-06-14', '22:46:08', NULL),
('202206140007', 307, NULL, '2023-06-14', '22:46:27', NULL),
('202206140008', 307, NULL, '2023-06-14', '22:52:39', NULL),
('202306150009', 300, NULL, '2023-06-15', '01:18:11', NULL),
('202306150010', 300, NULL, '2023-06-15', '01:19:26', NULL),
('202306150011', 300, NULL, '2023-06-15', '01:20:49', NULL),
('202306150012', 300, NULL, '2023-06-15', '01:42:37', 0),
('202306150013', 300, NULL, '2023-06-15', '01:43:46', 335000);

-- --------------------------------------------------------

--
-- Table structure for table `invoices_detail`
--

CREATE TABLE `invoices_detail` (
  `invoice_id` varchar(12) DEFAULT NULL,
  `dishes_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `invoices_detail`
--

INSERT INTO `invoices_detail` (`invoice_id`, `dishes_id`, `quantity`, `price`) VALUES
('202206140001', 201, 1, NULL),
('202206140001', 203, 2, NULL),
('202206140001', 206, 2, NULL),
('202206140001', 200, 3, 135000),
('202306150010', 200, 3, 135000),
('202306150011', 201, 10, 200000),
('202306150011', 200, 3, 135000),
('202306150012', 201, 10, 200000),
('202306150012', 200, 3, 135000),
('202306150013', 201, 10, 200000),
('202306150013', 200, 3, 135000),
('202206140001', 201, 1, NULL),
('202206140001', 203, 2, NULL),
('202206140001', 206, 2, NULL),
('202206140001', 200, 3, 135000),
('202306150010', 200, 3, 135000),
('202306150011', 201, 10, 200000),
('202306150011', 200, 3, 135000),
('202306150012', 201, 10, 200000),
('202306150012', 200, 3, 135000),
('202306150013', 201, 10, 200000),
('202306150013', 200, 3, 135000),
('202206140002', 207, 2, NULL),
('202206140003', 209, 2, NULL),
('202206140004', 202, 2, NULL),
('202206140005', 204, 1, NULL),
('202206140006', 209, 2, NULL),
('202206140007', 203, 3, NULL),
('202206140008', 205, 1, NULL),
('202306150009', 206, 1, NULL),
('202206140008', 200, 1, NULL),
('202206140005', 209, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `quantities`
--

CREATE TABLE `quantities` (
  `dish_id` int(11) NOT NULL,
  `ingredient_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `quantities`
--

INSERT INTO `quantities` (`dish_id`, `ingredient_id`, `quantity`) VALUES
(200, 600, 1),
(200, 603, 3),
(210, 601, 2),
(210, 603, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attendances`
--
ALTER TABLE `attendances`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `FK_emp_Id` (`emp_id`) USING BTREE;

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dishes`
--
ALTER TABLE `dishes`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `ingredients`
--
ALTER TABLE `ingredients`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `invoices`
--
ALTER TABLE `invoices`
  ADD PRIMARY KEY (`id`),
  ADD KEY `invoices_relation_1` (`customer_id`);

--
-- Indexes for table `invoices_detail`
--
ALTER TABLE `invoices_detail`
  ADD KEY `FK__dishes` (`dishes_id`),
  ADD KEY `FK__orders` (`invoice_id`) USING BTREE;

--
-- Indexes for table `quantities`
--
ALTER TABLE `quantities`
  ADD PRIMARY KEY (`dish_id`,`ingredient_id`) USING BTREE,
  ADD KEY `FK_quantities_ingredients` (`ingredient_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `attendances`
--
ALTER TABLE `attendances`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=119;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=325;

--
-- AUTO_INCREMENT for table `dishes`
--
ALTER TABLE `dishes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=213;

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=414;

--
-- AUTO_INCREMENT for table `ingredients`
--
ALTER TABLE `ingredients`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=613;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `attendances`
--
ALTER TABLE `attendances`
  ADD CONSTRAINT `FK_emp_Id` FOREIGN KEY (`emp_id`) REFERENCES `employees` (`Id`);

--
-- Constraints for table `invoices`
--
ALTER TABLE `invoices`
  ADD CONSTRAINT `invoices_relation_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`);

--
-- Constraints for table `invoices_detail`
--
ALTER TABLE `invoices_detail`
  ADD CONSTRAINT `FK_invoice_detail_dishes` FOREIGN KEY (`dishes_id`) REFERENCES `dishes` (`id`),
  ADD CONSTRAINT `FK_invoice_detail_invoices` FOREIGN KEY (`invoice_id`) REFERENCES `invoices` (`id`);

--
-- Constraints for table `quantities`
--
ALTER TABLE `quantities`
  ADD CONSTRAINT `FK_quantities_dishes` FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`id`),
  ADD CONSTRAINT `FK_quantities_ingredients` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredients` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
