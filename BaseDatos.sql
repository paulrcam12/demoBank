CREATE TABLE `cliente` (
  `cliente_id` int NOT NULL,
  `contrasena` varchar(255) DEFAULT NULL,
  `estado` bit(1) NOT NULL,
  `id_persona` int DEFAULT NULL,
  PRIMARY KEY (`cliente_id`),
  UNIQUE KEY `UK_t1gmqf4awori7khiyj149h94y` (`id_persona`),
  CONSTRAINT `FKlbs69o9qkvv7lgn06idak3crb` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`identificacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `cuenta` (
  `numero_cuenta` int NOT NULL,
  `estado` bit(1) NOT NULL,
  `saldo_inicial` double DEFAULT NULL,
  `tipo_cuenta` varchar(255) DEFAULT NULL,
  `id_cliente` int DEFAULT NULL,
  PRIMARY KEY (`numero_cuenta`),
  KEY `FKmkmi3xf6wrp0y1mdn8nm4weim` (`id_cliente`),
  CONSTRAINT `FKmkmi3xf6wrp0y1mdn8nm4weim` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`cliente_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `movimiento` (
  `id_movimiento` int NOT NULL AUTO_INCREMENT,
  `fecha` datetime(6) DEFAULT NULL,
  `saldo` double DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `id_cuenta` int DEFAULT NULL,
  PRIMARY KEY (`id_movimiento`),
  KEY `FK8veysyanipny5mpudj13t8873` (`id_cuenta`),
  CONSTRAINT `FK8veysyanipny5mpudj13t8873` FOREIGN KEY (`id_cuenta`) REFERENCES `cuenta` (`numero_cuenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `persona` (
  `identificacion` int NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `edad` int NOT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`identificacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
