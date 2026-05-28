-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: moviedb
-- ------------------------------------------------------
-- Server version	9.4.0

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
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `mid` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `pwd` varchar(256) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telno` varchar(20) DEFAULT NULL,
  `role` varchar(10) DEFAULT 'user',
  `regdate` date DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('admin123','admin','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','admin123@abc.com','010-3948-2049','admin','2025-12-22'),('Apple123','애플','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','Apple123@abc.com','010-1234-5678','user','2025-12-22'),('Lemon123','레몬','7020e57625b6a6695ffd51ed494fbfc56c699eaceca4e77bf7ea590c7ebf3879','lemon@abc.com','010-1482-1240','user','2025-12-22'),('Mango123','망고','f6f2ea8f45d8a057c9566a33f99474da2e5c6a6604d736121650e2730c6fb0a3','mango123@abc.com','010-6397-1948','user','2025-12-22'),('test1','test1','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','test@abc.com','010-8765-4321','user','2025-12-22');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `movie_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `director` varchar(50) DEFAULT NULL,
  `genre` varchar(50) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `description` text,
  `image` varchar(100) DEFAULT NULL,
  `running_time` int DEFAULT NULL,
  `regdate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`movie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'아바타 : 물의 길','제임스 카메론','SF',15000,'판도라 행성에서 제이크 설리와 네이티리가 이룬 가족이 겪는 무자비한 위협과 살아남기 위한 여정과 전투, 그리고 견뎌내야 할 상처에 대한 이야기.','movie1.jpg',192,'2025-12-22 16:46:37'),(2,'어벤져스: 엔드게임','안소니 루소','액션',15000,'인피니티 워 이후, 지구의 마지막 희망이 된 살아남은 어벤져스 조합과 빌런 타노스의 최강 전투를 그린 영화.','movie2.jpg',181,'2025-12-22 16:47:03'),(3,'이웃집 토토로','미야자키 하야오','애니메이션',15000,'도시를 떠나 시골로 이사 온 사츠키와 메이 자매가 숲을 지키는 신비로운 생명체 \'토토로\'를 만나며 벌어지는 마법 같은 모험.','movie3.jpg',87,'2025-12-22 16:47:26'),(4,'겨울왕','크리스 벅','애니메이션',15000,'아렌델 왕국의 평화를 되찾기 위해 숨겨진 과거의 비밀과 새로운 운명을 찾아 모험을 떠나는 엘사와 안나의 이야기.','movie4.jpg',103,'2025-12-22 16:47:50'),(5,'반지의 제왕','피터 잭슨','판타지',15000,'모든 힘을 지배할 악의 군주 사우론에 맞서, 절대반지를 파괴하기 위해 목숨을 건 여정을 떠나는 프로도와 원정대의 마지막 대서사시.','movie5.jpg',263,'2025-12-22 16:48:27'),(6,'8번 출구','코타케 크리에이트','스릴러',15000,'\"이변을 발견하면 즉시 뒤로 돌아가라.\" 무한히 반복되는 지하 통로에 갇힌 주인공. 8번 출구를 찾아 탈출해야만 한다.','movie6.jpg',90,'2025-12-22 16:48:57');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_reservation`
--

DROP TABLE IF EXISTS `movie_reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_reservation` (
  `reservation_id` int NOT NULL AUTO_INCREMENT,
  `mid` varchar(20) DEFAULT NULL,
  `movie_id` int DEFAULT NULL,
  `screening_date` varchar(20) DEFAULT NULL,
  `screening_time` varchar(20) DEFAULT NULL,
  `people_count` int DEFAULT NULL,
  `seat_list` varchar(100) DEFAULT NULL,
  `total_price` int DEFAULT NULL,
  `status` varchar(20) DEFAULT '예약완료',
  PRIMARY KEY (`reservation_id`),
  KEY `mid` (`mid`),
  KEY `movie_id` (`movie_id`),
  CONSTRAINT `movie_reservation_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `member` (`mid`) ON DELETE CASCADE,
  CONSTRAINT `movie_reservation_ibfk_2` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_reservation`
--

LOCK TABLES `movie_reservation` WRITE;
/*!40000 ALTER TABLE `movie_reservation` DISABLE KEYS */;
INSERT INTO `movie_reservation` VALUES (1,'Apple123',6,'2025-12-25','16:00',3,'C3,C4,C5',45000,'취소됨');
/*!40000 ALTER TABLE `movie_reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `mid` varchar(20) DEFAULT NULL,
  `movie_id` int DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `regdate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`review_id`),
  KEY `mid` (`mid`),
  KEY `movie_id` (`movie_id`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `member` (`mid`) ON DELETE CASCADE,
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,'Lemon123',6,'원작 게임을 해보셨다면 강추~',5,'2025-12-22 17:03:34'),(2,'Mango123',6,'깜짝 놀라는 장면이 많아요!',4,'2025-12-22 17:04:08');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-22 17:20:56
