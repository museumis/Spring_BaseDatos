-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-02-2018 a las 04:12:30
-- Versión del servidor: 10.1.28-MariaDB
-- Versión de PHP: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `acadt`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `ejemplo` (IN `nombre` VARCHAR(15))  BEGIN
SELECT * FROM empleados WHERE apellido = nombre;
COMMIT;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departamentos`
--

CREATE TABLE `departamentos` (
  `dept_no` int(2) NOT NULL,
  `dnombre` varchar(15) DEFAULT NULL,
  `loc` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `departamentos`
--

INSERT INTO `departamentos` (`dept_no`, `dnombre`, `loc`) VALUES
(1, 'Programación', 'Cáceres'),
(2, 'Diseño', 'Plasencia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `emp_no` int(4) NOT NULL,
  `apellido` varchar(10) DEFAULT NULL,
  `oficio` varchar(10) DEFAULT NULL,
  `dir` int(2) DEFAULT NULL,
  `fecha_alta` date DEFAULT NULL,
  `salario` float(6,2) DEFAULT NULL,
  `comision` float(6,2) DEFAULT NULL,
  `dept_no` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`emp_no`, `apellido`, `oficio`, `dir`, `fecha_alta`, `salario`, `comision`, `dept_no`) VALUES
(1, 'Flora', 'developer', 1, '2000-10-01', 1011.00, 1.00, 1),
(2, 'Juan', 'testeo', 3, '2018-02-06', 2111.00, 1.00, 2),
(3, 'Fe', 'AppMovil', 1, '2010-12-01', 2111.00, 2.00, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `departamentos`
--
ALTER TABLE `departamentos`
  ADD PRIMARY KEY (`dept_no`);

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`emp_no`),
  ADD KEY `fk_emp_dptono_depart` (`dept_no`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `empleados`
--
ALTER TABLE `empleados`
  MODIFY `emp_no` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33336;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD CONSTRAINT `fk_emp_dptono_depart` FOREIGN KEY (`dept_no`) REFERENCES `departamentos` (`dept_no`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
