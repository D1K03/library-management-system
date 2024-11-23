-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 23, 2024 at 06:43 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `librarydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `book_id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `publisher` varchar(255) NOT NULL,
  `category` varchar(100) NOT NULL,
  `isbn` varchar(20) NOT NULL,
  `available` int(11) NOT NULL,
  `borrowed` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`book_id`, `title`, `author`, `publisher`, `category`, `isbn`, `available`, `borrowed`) VALUES
(1, 'Harry Potter and the Sorcerer\'s Stone', 'J.K. Rowling', 'Scholastic', 'Fantasy', '9781338878929', 12, 1),
(3, 'David Copper Field', 'Charles Dickens', 'Ramesh Publishing House', 'Bildungsroman', '9789386063458', 4, 0),
(4, 'James and the Giant Peach', 'Roald Dahl', 'Penguin Young Readers Group', 'Fantasy', '9780142410363', 5, 1),
(5, 'Don Quixote', 'Miguel de Cervantes', 'borders', 'Chivalric Romance', '9781589770256', 2, 1),
(6, 'The Da Vinci Code (Robert Langdon)', 'Dan Brown', 'Anchor', 'Thriller', '9780274808328', 3, 1),
(7, 'A Christmas Carol', 'Charles Dickens', 'CreateSpace Independent Publishing Platform', 'Supernatural', '9781514696583', 3, 0),
(8, 'Diary of a Wimpy Kid (Diary of a Wimpy Kid #1)', 'Jeff Kinney', 'Harry N. Abrams', 'Comedy', '9781419741852', 10, 1),
(9, 'Moby Dick', 'Herman Melville', 'Wordsworth Pub.', 'Adventure', '9781853260087', 3, 0),
(10, ' Oliver Twist', 'Charles Dickens', 'Penguin Classics', 'Bildungsroman', '9780141439747', 12, 0);

-- --------------------------------------------------------

--
-- Table structure for table `rents`
--

CREATE TABLE `rents` (
  `rent_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `borrowed_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `due_date` timestamp NULL DEFAULT NULL,
  `returned_date` timestamp NULL DEFAULT NULL,
  `overdue` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rents`
--

INSERT INTO `rents` (`rent_id`, `user_id`, `book_id`, `borrowed_date`, `due_date`, `returned_date`, `overdue`) VALUES
(15, 9, 1, '2024-11-19 10:30:23', '2024-12-03 10:30:23', NULL, 0),
(20, 3, 1, '2024-11-21 02:47:56', '2024-12-05 02:47:56', '2024-11-21 02:34:27', 0),
(21, 3, 6, '2024-11-22 05:12:58', '2024-12-06 05:12:58', NULL, 0),
(22, 3, 5, '2024-11-07 14:52:19', '2024-11-16 14:52:19', NULL, 1),
(23, 3, 4, '2024-11-22 21:00:55', '2024-12-06 21:00:55', '2024-11-23 03:54:18', 0),
(24, 5, 4, '2024-11-23 07:49:28', '2024-12-07 07:49:28', NULL, 0),
(25, 4, 9, '2024-11-23 09:44:12', '2024-12-07 09:44:12', '2024-11-23 09:48:26', 0),
(26, 4, 8, '2024-11-23 09:47:59', '2024-12-07 09:47:59', NULL, 0),
(27, 4, 5, '2024-11-23 09:58:52', '2024-12-07 09:58:52', '2024-11-23 09:58:59', 0);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `forename` varchar(55) NOT NULL,
  `surname` varchar(55) NOT NULL,
  `email` varchar(255) NOT NULL,
  `role` enum('student','librarian','admin') NOT NULL DEFAULT 'student',
  `reg_date` datetime NOT NULL DEFAULT current_timestamp(),
  `password_hash` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `forename`, `surname`, `email`, `role`, `reg_date`, `password_hash`) VALUES
(1, 'Daniel', 'Olusakin', 'olusakindaniel03@gmail.com', 'admin', '2024-11-17 00:00:00', '$2a$10$wro.hsRqnrgRVS4rAlvyQuWF8/9KL584S6ZIr5/EURUfV2YV8CTYy'),
(2, 'Samantha', 'Pickett', 'sam.pickett01@gmail.com', 'librarian', '2024-11-17 00:00:00', '$2a$10$slJsvmiPCH6s9XOIG/oyReMOsoUqPZGPmpSz2NSRUT8upTsaxmvQC'),
(3, 'John', 'Sparrow', 'john.sp2000@gmail.com', 'student', '2024-11-17 00:00:00', '$2a$10$pM/3ChLahqJZ28ECbT9WeeYJa4qcR4UpbB1URzNwqHNehLk6MNesW'),
(4, 'James', 'Edwards', 'james.edwards12@gmail.com', 'student', '2024-11-17 00:00:00', '$2a$10$S1PzNKXyBjHrsqtooQogH.dYFLZVaoYWbblSmg63mb4pwn9ecPryW'),
(5, 'Lennon', 'Jones', 'lennon.j03@gmail.com', 'student', '2024-11-17 16:44:35', '$2a$10$5CCI0.6GRJGlFyNSMyyqA.i.5XBkmB1sX1W8jYp0dMGB/BCzKl3Ki'),
(9, 'Sean', 'James', 'sean@gmail.com', 'student', '2024-11-18 16:14:24', '$2a$10$P5wGKIar6x9IfsUg8BwTietUHHWUv1dh0wDOYO1YHcSh1WKW18H9C');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`book_id`),
  ADD UNIQUE KEY `ISBN` (`isbn`);

--
-- Indexes for table `rents`
--
ALTER TABLE `rents`
  ADD PRIMARY KEY (`rent_id`),
  ADD UNIQUE KEY `user_id` (`user_id`,`book_id`),
  ADD KEY `book_id` (`book_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `HASH` (`password_hash`),
  ADD UNIQUE KEY `ADDRESS` (`email`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `book_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `rents`
--
ALTER TABLE `rents`
  MODIFY `rent_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `rents`
--
ALTER TABLE `rents`
  ADD CONSTRAINT `rents_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `rents_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
