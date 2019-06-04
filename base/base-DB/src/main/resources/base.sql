-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.15 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for base
CREATE DATABASE IF NOT EXISTS `base` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `base`;

-- Dumping structure for table base.sec_permission
CREATE TABLE IF NOT EXISTS `sec_permission` (
  `id` bigint(20) NOT NULL,
  `permission_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table base.sec_permission: ~0 rows (approximately)
/*!40000 ALTER TABLE `sec_permission` DISABLE KEYS */;
REPLACE INTO `sec_permission` (`id`, `permission_name`) VALUES
	(1, 'test_permission');
/*!40000 ALTER TABLE `sec_permission` ENABLE KEYS */;

-- Dumping structure for table base.sec_role
CREATE TABLE IF NOT EXISTS `sec_role` (
  `id` bigint(20) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table base.sec_role: ~0 rows (approximately)
/*!40000 ALTER TABLE `sec_role` DISABLE KEYS */;
REPLACE INTO `sec_role` (`id`, `role_name`) VALUES
	(1, 'test_role');
/*!40000 ALTER TABLE `sec_role` ENABLE KEYS */;

-- Dumping structure for table base.sec_role_permission
CREATE TABLE IF NOT EXISTS `sec_role_permission` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table base.sec_role_permission: ~0 rows (approximately)
/*!40000 ALTER TABLE `sec_role_permission` DISABLE KEYS */;
REPLACE INTO `sec_role_permission` (`id`, `role_id`, `permission_id`) VALUES
	(1, 1, 1);
/*!40000 ALTER TABLE `sec_role_permission` ENABLE KEYS */;

-- Dumping structure for table base.sec_user
CREATE TABLE IF NOT EXISTS `sec_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table base.sec_user: ~0 rows (approximately)
/*!40000 ALTER TABLE `sec_user` DISABLE KEYS */;
REPLACE INTO `sec_user` (`id`, `username`, `password`) VALUES
	(1, 'test', 'test');
/*!40000 ALTER TABLE `sec_user` ENABLE KEYS */;

-- Dumping structure for table base.sec_user_role
CREATE TABLE IF NOT EXISTS `sec_user_role` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table base.sec_user_role: ~0 rows (approximately)
/*!40000 ALTER TABLE `sec_user_role` DISABLE KEYS */;
REPLACE INTO `sec_user_role` (`id`, `user_id`, `role_id`) VALUES
	(1, 1, 1);
/*!40000 ALTER TABLE `sec_user_role` ENABLE KEYS */;

-- Dumping structure for table base.user
CREATE TABLE IF NOT EXISTS `user` (
  `USER_ID` varchar(50) NOT NULL,
  `USER_NAME` varchar(50) NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table base.user: ~0 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
REPLACE INTO `user` (`USER_ID`, `USER_NAME`) VALUES
	('002', 'shiyi');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
