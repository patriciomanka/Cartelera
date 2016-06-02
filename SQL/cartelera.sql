-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 02, 2016 at 08:21 AM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `cartelera`
--
CREATE DATABASE IF NOT EXISTS `cartelera` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `cartelera`;

-- --------------------------------------------------------

--
-- Table structure for table `carrera`
--

DROP TABLE IF EXISTS `carrera`;
CREATE TABLE IF NOT EXISTS `carrera` (
  `codigo` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(55) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `descripcion` (`descripcion`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=379 ;

--
-- Dumping data for table `carrera`
--

INSERT INTO `carrera` (`codigo`, `descripcion`) VALUES
(378, 'Abogacia'),
(373, 'Arquitectura'),
(348, 'Enfermeria'),
(361, 'Gestion de Empresas Funebres'),
(350, 'Ingenieria en Informatica'),
(369, 'Ingenieria en Materiales'),
(366, 'Lic. Periodismo');

-- --------------------------------------------------------

--
-- Table structure for table `comision`
--

DROP TABLE IF EXISTS `comision`;
CREATE TABLE IF NOT EXISTS `comision` (
  `codigoCarrera` smallint(5) unsigned NOT NULL,
  `codigoMateria` smallint(5) unsigned NOT NULL,
  `turno` char(6) NOT NULL,
  `comision` tinyint(3) unsigned NOT NULL,
  `dia` char(10) NOT NULL,
  `horaInicio` tinyint(3) unsigned NOT NULL,
  `horaFin` tinyint(3) unsigned NOT NULL,
  `aula` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`codigoCarrera`,`codigoMateria`,`turno`,`comision`,`dia`),
  KEY `fk_materia` (`codigoMateria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comision`
--

INSERT INTO `comision` (`codigoCarrera`, `codigoMateria`, `turno`, `comision`, `dia`, `horaInicio`, `horaFin`, `aula`) VALUES
(348, 363, 'mañana', 1, 'lunes', 8, 10, 113),
(348, 364, 'mañana', 2, 'martes', 8, 12, 112),
(348, 364, 'noche', 1, 'martes', 18, 22, 207),
(348, 380, 'mañana', 3, 'martes', 8, 12, 115),
(348, 396, 'noche', 6, 'jueves', 21, 23, 203),
(350, 365, 'tarde', 1, 'miercoles', 9, 12, 201),
(350, 366, 'mañana', 2, 'jueves', 8, 12, 203),
(350, 367, 'tarde', 3, 'viernes', 15, 18, 202),
(350, 368, 'mañana', 2, 'sabado', 8, 12, 204),
(350, 369, 'tarde', 2, 'lunes', 16, 18, 203),
(350, 370, 'mañana', 1, 'martes', 8, 12, 201),
(350, 371, 'tarde', 2, 'miercoles', 14, 16, 106),
(350, 372, 'mañana', 2, 'jueves', 8, 12, 207),
(350, 373, 'tarde', 2, 'viernes', 13, 16, 206),
(350, 374, 'mañana', 3, 'sabado', 8, 10, 205),
(350, 375, 'tarde', 2, 'martes', 16, 18, 203),
(361, 376, 'noche', 5, 'miercoles', 21, 23, 666),
(361, 377, 'noche', 4, 'viernes', 21, 23, 666),
(361, 378, 'noche', 6, 'jueves', 21, 23, 666),
(366, 381, 'mañana', 1, 'lunes', 8, 10, 113),
(366, 382, 'noche', 1, 'martes', 18, 22, 207),
(366, 383, 'tarde', 1, 'miercoles', 9, 12, 201),
(369, 384, 'mañana', 2, 'jueves', 8, 12, 203),
(369, 385, 'tarde', 3, 'viernes', 15, 18, 202),
(369, 386, 'mañana', 2, 'sabado', 8, 12, 204),
(369, 387, 'tarde', 2, 'lunes', 16, 18, 203),
(373, 388, 'mañana', 1, 'martes', 8, 12, 201),
(373, 389, 'tarde', 2, 'miercoles', 14, 16, 106),
(373, 390, 'mañana', 2, 'jueves', 8, 12, 207),
(373, 391, 'tarde', 2, 'viernes', 13, 16, 206),
(373, 392, 'mañana', 3, 'sabado', 8, 10, 205),
(378, 393, 'tarde', 2, 'martes', 16, 18, 203),
(378, 394, 'noche', 5, 'miercoles', 21, 23, 204),
(378, 395, 'noche', 4, 'viernes', 21, 23, 115);

-- --------------------------------------------------------

--
-- Table structure for table `final`
--

DROP TABLE IF EXISTS `final`;
CREATE TABLE IF NOT EXISTS `final` (
  `codigoMateria` smallint(5) unsigned NOT NULL,
  `codigoCarrera` smallint(5) unsigned NOT NULL,
  `llamado` tinyint(3) unsigned NOT NULL,
  `fecha` char(10) NOT NULL,
  `profesor` varchar(30) DEFAULT NULL,
  `hora` char(5) NOT NULL,
  PRIMARY KEY (`codigoMateria`,`codigoCarrera`,`llamado`),
  KEY `fk_car` (`codigoCarrera`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `materia`
--

DROP TABLE IF EXISTS `materia`;
CREATE TABLE IF NOT EXISTS `materia` (
  `codigo` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(55) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `descripcion` (`descripcion`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=397 ;

--
-- Dumping data for table `materia`
--

INSERT INTO `materia` (`codigo`, `descripcion`) VALUES
(375, 'Algoritmos y Estructura de Datos'),
(385, 'Analisis Matematico I'),
(367, 'Analisis Matematico II'),
(370, 'Arquitectura de Sistemas de Elaboracion de Datos II'),
(392, 'Arquitectura III'),
(376, 'Ataudes II'),
(386, 'Conformado de Materiales'),
(389, 'Construcciones I'),
(394, 'Derecho Informatico'),
(393, 'Derecho Penal'),
(395, 'Derecho Tributario'),
(387, 'Electroquimica'),
(396, 'Enfermeria en Salud Publica III'),
(380, 'Enfermeria Medica I'),
(377, 'Entierros IV'),
(390, 'Estructuras II'),
(388, 'Fisica Aplicada a la Arquitectura'),
(365, 'Fisica I'),
(368, 'Gestion de Datos'),
(391, 'Historia III'),
(381, 'Historia Latinoamericana y Argentina'),
(373, 'Ingenieria de Software'),
(371, 'Ingles I'),
(374, 'Metodos Matematicos de la Ingenieria'),
(372, 'Probabilidad y Estadistica'),
(369, 'Programacion Orientada a Objetos'),
(383, 'Publicidad'),
(384, 'Quimica'),
(366, 'Redes de Computadoras'),
(378, 'Rituales y Cultos I'),
(363, 'Salud I'),
(382, 'Sociologia'),
(364, 'Vacunas II');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `password`) VALUES
(1, 'Eric', '$2y$10$BAstPfAN5MmRQC4yhMC94Ofz.VQn39PnwkKHr3s11G.flS4vLJZKK');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comision`
--
ALTER TABLE `comision`
  ADD CONSTRAINT `fk_carrera` FOREIGN KEY (`codigoCarrera`) REFERENCES `carrera` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_materia` FOREIGN KEY (`codigoMateria`) REFERENCES `materia` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `final`
--
ALTER TABLE `final`
  ADD CONSTRAINT `fk_car` FOREIGN KEY (`codigoCarrera`) REFERENCES `carrera` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_mat` FOREIGN KEY (`codigoMateria`) REFERENCES `materia` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
