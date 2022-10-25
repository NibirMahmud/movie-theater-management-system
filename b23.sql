-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 17, 2018 at 10:58 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `b23`
--

-- --------------------------------------------------------

--
-- Table structure for table `adminlogin`
--

CREATE TABLE `adminlogin` (
  `adminId` varchar(8) NOT NULL,
  `password` varchar(8) NOT NULL,
  `status` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `adminlogin`
--

INSERT INTO `adminlogin` (`adminId`, `password`, `status`) VALUES
('e101', 'e101', 0),
('e102', 'e102', 1),
('e104', 'e104', 1);

-- --------------------------------------------------------

--
-- Table structure for table `employeeinfo`
--

CREATE TABLE `employeeinfo` (
  `employeeId` varchar(8) NOT NULL,
  `eName` varchar(30) NOT NULL,
  `phoneNumber` varchar(14) NOT NULL,
  `role` varchar(15) NOT NULL,
  `salary` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employeeinfo`
--

INSERT INTO `employeeinfo` (`employeeId`, `eName`, `phoneNumber`, `role`, `salary`) VALUES
('e102', 'sakib', '+8801230000910', 'General', '12000'),
('e104', 'farhan', '+8801716045025', 'General', '10000');

-- --------------------------------------------------------

--
-- Table structure for table `managerinfo`
--

CREATE TABLE `managerinfo` (
  `adminId` varchar(8) NOT NULL,
  `eName` varchar(30) NOT NULL,
  `phoneNumber` varchar(14) NOT NULL,
  `role` varchar(15) NOT NULL,
  `salary` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `managerinfo`
--

INSERT INTO `managerinfo` (`adminId`, `eName`, `phoneNumber`, `role`, `salary`) VALUES
('e101', 'James', '+8801029238485', 'Manager', 2000);

-- --------------------------------------------------------

--
-- Table structure for table `movieinfo`
--

CREATE TABLE `movieinfo` (
  `movieId` varchar(8) NOT NULL,
  `movieName` varchar(50) NOT NULL,
  `availableSeat` int(3) NOT NULL,
  `capacity` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `movieinfo`
--

INSERT INTO `movieinfo` (`movieId`, `movieName`, `availableSeat`, `capacity`) VALUES
('m300', 'The Nun(2D)', 100, 100),
('m301', 'The Lion King (3D)', 100, 100),
('m302', 'Inception(3D)', 100, 100),
('m304', 'Avengers: Infinity War(3D)', 100, 100),
('m305', 'Venom(3D)', 100, 100);

-- --------------------------------------------------------

--
-- Table structure for table `movie_timeinfo`
--

CREATE TABLE `movie_timeinfo` (
  `movieId` varchar(8) NOT NULL,
  `date` varchar(30) NOT NULL,
  `time` varchar(8) NOT NULL,
  `availableSeat` int(3) NOT NULL,
  `capacity` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `movie_timeinfo`
--

INSERT INTO `movie_timeinfo` (`movieId`, `date`, `time`, `availableSeat`, `capacity`) VALUES
('m300', '2018-12-18', '11:00AM', 80, 100),
('m300', '2018-12-20', '2:00PM', 100, 100),
('m301', '2018-12-19', '11:00AM', 100, 100),
('m301', '2018-12-21', '3:00PM', 100, 100),
('m302', '2018-12-18', '2:00PM', 100, 100),
('m302', '2018-12-20', '4:30PM', 100, 100),
('m304', '2018-12-20', '7:30PM', 100, 100),
('m304', '2018-12-21', '7:30PM', 100, 100),
('m305', '2018-12-23', '12.30PM', 100, 100),
('m305', '2018-12-23', '6.30PM', 100, 100);

-- --------------------------------------------------------

--
-- Table structure for table `soldticket`
--

CREATE TABLE `soldticket` (
  `userid` varchar(30) NOT NULL,
  `movieName` varchar(40) NOT NULL,
  `date` varchar(40) NOT NULL,
  `time` varchar(40) NOT NULL,
  `seats` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `soldticket`
--

INSERT INTO `soldticket` (`userid`, `movieName`, `date`, `time`, `seats`) VALUES
('i0001', 'The Nun(2D)', '2018-12-18', '11:00AM', 5),
('i010', 'The Nun(2D)', '2018-12-18', '11:00AM', 5),
('i0001', 'The Nun(2D)', '2018-12-18', '11:00AM', 10);

-- --------------------------------------------------------

--
-- Table structure for table `userinfo`
--

CREATE TABLE `userinfo` (
  `userid` varchar(8) NOT NULL,
  `username` varchar(15) NOT NULL,
  `phoneNumber` varchar(14) NOT NULL,
  `emailId` varchar(30) NOT NULL,
  `password` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userinfo`
--

INSERT INTO `userinfo` (`userid`, `username`, `phoneNumber`, `emailId`, `password`) VALUES
('i010', 'muntakim', '+8801616074075', 'idrak.10225@gmail.com', 'i010');

-- --------------------------------------------------------

--
-- Table structure for table `userlogin`
--

CREATE TABLE `userlogin` (
  `userid` varchar(11) NOT NULL,
  `password` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userlogin`
--

INSERT INTO `userlogin` (`userid`, `password`) VALUES
('i010', 'i010');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `adminlogin`
--
ALTER TABLE `adminlogin`
  ADD PRIMARY KEY (`adminId`);

--
-- Indexes for table `employeeinfo`
--
ALTER TABLE `employeeinfo`
  ADD PRIMARY KEY (`employeeId`);

--
-- Indexes for table `managerinfo`
--
ALTER TABLE `managerinfo`
  ADD PRIMARY KEY (`adminId`);

--
-- Indexes for table `movieinfo`
--
ALTER TABLE `movieinfo`
  ADD PRIMARY KEY (`movieId`);

--
-- Indexes for table `userinfo`
--
ALTER TABLE `userinfo`
  ADD PRIMARY KEY (`userid`);

--
-- Indexes for table `userlogin`
--
ALTER TABLE `userlogin`
  ADD PRIMARY KEY (`userid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
