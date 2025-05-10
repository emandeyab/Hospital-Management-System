-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hospital_management_system
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `ID` varchar(50) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Phone_Number` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES ('04','Emergency Department','01007777888'),('05','Internal Medicine','01001111222'),('06','Surgical department','123456789'),('07','Nursing department','01001002670'),('08','Operation theater complex','01043405021'),('09','Paramedical department','01001324560');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `Age` int DEFAULT NULL,
  `Phone_Number` varchar(15) DEFAULT NULL,
  `Salary` decimal(10,2) DEFAULT NULL,
  `Gmail` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Gmail` (`Gmail`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'Ahmed Mohamed',30,'01234567890',5000.00,'ahmed.mohamed@gmail.com'),(2,'Sara Ali',25,'01123456789',4500.00,'sara.ali@gmail.com'),(3,'Mahmoud Khaled',35,'01098765432',6000.00,'mahmoud.khaled@gmail.com'),(4,'Nourhan Essam',28,'01555555555',4800.00,'nourhan.essam@gmail.com'),(5,'Omar Youssef',40,'01298765432',7000.00,'omar.youssef@gmail.com'),(6,'Fatma Hassan',27,'01111111111',4200.00,'fatma.hassan@gmail.com'),(7,'Khaled Mostafa',32,'01012345678',5500.00,'khaled.mostafa@gmail.com'),(8,'Laila Ahmed',29,'01512345678',4700.00,'laila.ahmed@gmail.com'),(9,'Hassan Ibrahim',38,'01222222222',6500.00,'hassan.ibrahim@gmail.com'),(10,'Yasmin Tarek',26,'01155555555',4300.00,'yasmin.tarek@gmail.com');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `username` varchar(22) NOT NULL,
  `ps` varchar(32) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('eman','1234');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_info`
--

DROP TABLE IF EXISTS `patient_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient_info` (
  `ID` varchar(50) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Phone_Number` varchar(20) DEFAULT NULL,
  `Gender` varchar(10) DEFAULT NULL,
  `Room_Number` varchar(10) DEFAULT NULL,
  `Deposite` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_info`
--

LOCK TABLES `patient_info` WRITE;
/*!40000 ALTER TABLE `patient_info` DISABLE KEYS */;
INSERT INTO `patient_info` VALUES ('1122','Ali Mohamed','01023453245','Male','100','200'),('1123','Sara Nader','01245678790','Female','200','300'),('1124','Maram Nezar','01123204021','Female','203','1000'),('1125','Ashraf Ahmed','01056789343','Male','302','1500'),('1126','Alaa Ali','01123454343','Female','303','2000'),('1127','Mariam Emad','01221345432','Female','102','400'),('1128','Saif Ayman','01023426789','Male','202','900');
/*!40000 ALTER TABLE `patient_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `room_num` varchar(20) NOT NULL,
  `Availability` varchar(20) DEFAULT NULL,
  `Price` varchar(20) DEFAULT NULL,
  `Room_Type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`room_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES ('100','Unavailable','500','General Room Bed 1'),('101','Available','500','General Room Bed 2'),('102','Unavailable','500','General Room Bed 3'),('103','Available','500','General Room Bed 4'),('200','Unavailable','1500','Private Room'),('201','Unavailable','1500','Private Room'),('202','Unavailable','1500','Private Room'),('203','Unavailable','1500','Private Room'),('300','Available','2500','Emergency Room 1'),('301','Available','2500','Emergency Room 2'),('302','Unavailable','2500','Emergency Room 3'),('303','Unavailable','2500','Emergency Room 4');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-10 17:42:27
