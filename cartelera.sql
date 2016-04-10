-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 10, 2016 at 10:51 PM
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

-- --------------------------------------------------------

--
-- Table structure for table `carrera`
--

CREATE TABLE IF NOT EXISTS `carrera` (
  `codigo` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(55) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `descripcion` (`descripcion`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=351 ;

--
-- Dumping data for table `carrera`
--

INSERT INTO `carrera` (`codigo`, `descripcion`) VALUES
(347, 'abogacia'),
(342, 'arquitectura'),
(317, 'enfermeria'),
(330, 'gestion_de_empresas_funebres'),
(319, 'ingenieria_en_informatica'),
(338, 'ingenieria_en_materiales'),
(335, 'lic._periodismo');

-- --------------------------------------------------------

--
-- Table structure for table `comision`
--

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
(317, 329, 'mañana', 1, 'lunes', 8, 10, 113),
(317, 330, 'mañana', 2, 'martes', 8, 12, 112),
(317, 330, 'noche', 1, 'martes', 18, 22, 207),
(317, 346, 'mañana', 3, 'martes', 8, 12, 115),
(317, 362, 'noche', 6, 'jueves', 21, 23, 203),
(319, 331, 'tarde', 1, 'miercoles', 9, 12, 201),
(319, 332, 'mañana', 2, 'jueves', 8, 12, 203),
(319, 333, 'tarde', 3, 'viernes', 15, 18, 202),
(319, 334, 'mañana', 2, 'sabado', 8, 12, 204),
(319, 335, 'tarde', 2, 'lunes', 16, 18, 203),
(319, 336, 'mañana', 1, 'martes', 8, 12, 201),
(319, 337, 'tarde', 2, 'miercoles', 14, 16, 106),
(319, 338, 'mañana', 2, 'jueves', 8, 12, 207),
(319, 339, 'tarde', 2, 'viernes', 13, 16, 206),
(319, 340, 'mañana', 3, 'sabado', 8, 10, 205),
(319, 341, 'tarde', 2, 'martes', 16, 18, 203),
(330, 342, 'noche', 5, 'miercoles', 21, 23, 666),
(330, 343, 'noche', 4, 'viernes', 21, 23, 666),
(330, 344, 'noche', 6, 'jueves', 21, 23, 666),
(335, 347, 'mañana', 1, 'lunes', 8, 10, 113),
(335, 348, 'noche', 1, 'martes', 18, 22, 207),
(335, 349, 'tarde', 1, 'miercoles', 9, 12, 201),
(338, 350, 'mañana', 2, 'jueves', 8, 12, 203),
(338, 351, 'tarde', 3, 'viernes', 15, 18, 202),
(338, 352, 'mañana', 2, 'sabado', 8, 12, 204),
(338, 353, 'tarde', 2, 'lunes', 16, 18, 203),
(342, 354, 'mañana', 1, 'martes', 8, 12, 201),
(342, 355, 'tarde', 2, 'miercoles', 14, 16, 106),
(342, 356, 'mañana', 2, 'jueves', 8, 12, 207),
(342, 357, 'tarde', 2, 'viernes', 13, 16, 206),
(342, 358, 'mañana', 3, 'sabado', 8, 10, 205),
(347, 359, 'tarde', 2, 'martes', 16, 18, 203),
(347, 360, 'noche', 5, 'miercoles', 21, 23, 204),
(347, 361, 'noche', 4, 'viernes', 21, 23, 115);

-- --------------------------------------------------------

--
-- Table structure for table `final`
--

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

CREATE TABLE IF NOT EXISTS `materia` (
  `codigo` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(55) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `descripcion` (`descripcion`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=363 ;

--
-- Dumping data for table `materia`
--

INSERT INTO `materia` (`codigo`, `descripcion`) VALUES
(341, 'algoritmos_y_estructura_de_datos'),
(351, 'analisis_matematico_i'),
(333, 'analisis_matematico_ii'),
(336, 'arquitectura_de_sistemas_de_elaboracion_de_datos_ii'),
(358, 'arquitectura_iii'),
(342, 'ataudes_ii'),
(352, 'conformado_de_materiales'),
(355, 'construcciones_i'),
(360, 'derecho_informatico'),
(359, 'derecho_penal'),
(361, 'derecho_tributario'),
(353, 'electroquimica'),
(362, 'enfermeria_en_salud_publica_iii'),
(346, 'enfermeria_medica_i'),
(343, 'entierros_iv'),
(356, 'estructuras_ii'),
(354, 'fisica_aplicada_a_la_arquitectura'),
(331, 'fisica_i'),
(334, 'gestion_de_datos'),
(357, 'historia_iii'),
(347, 'historia_latinoamericana_y_argentina'),
(339, 'ingenieria_de_software'),
(337, 'ingles_i'),
(340, 'metodos_matematicos_de_la_ingenieria'),
(338, 'probabilidad_y_estadistica'),
(335, 'programacion_orientada_a_objetos'),
(349, 'publicidad'),
(350, 'quimica'),
(332, 'redes_de_computadoras'),
(344, 'rituales_y_cultos_i'),
(329, 'salud_i'),
(348, 'sociologia'),
(330, 'vacunas_ii');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `password`) VALUES
(6, 'Eric', '$2y$10$p0ViF4BKJwd2vUtw.s2yV.XNR9jRcI6DEQkrc44bOp1sjhja/rQJO'),
(7, 'a', '$2y$10$e2heXNcZ0VmijvvknN9Sw.LLtRX0qY6LmGqWcsixQEkGZwpNaUKga');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comision`
--
ALTER TABLE `comision`
  ADD CONSTRAINT `fk_carrera` FOREIGN KEY (`codigoCarrera`) REFERENCES `carrera` (`codigo`),
  ADD CONSTRAINT `fk_materia` FOREIGN KEY (`codigoMateria`) REFERENCES `materia` (`codigo`);

--
-- Constraints for table `final`
--
ALTER TABLE `final`
  ADD CONSTRAINT `fk_car` FOREIGN KEY (`codigoCarrera`) REFERENCES `carrera` (`codigo`),
  ADD CONSTRAINT `fk_mat` FOREIGN KEY (`codigoMateria`) REFERENCES `materia` (`codigo`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
