DELETE FROM ESTACIONAMIENTO ;
DELETE FROM VEHICULO ;
DELETE FROM TARIFAS ;
DELETE FROM TIPO ;


INSERT INTO TIPO (idTipo, tipo) VALUES  (1, 'Carro');
INSERT INTO TIPO (idTipo, tipo) VALUES  (2, 'Moto');

INSERT INTO TARIFAS (idTarifa,idTipo,valor) VALUES (1, 1, 4000);
INSERT INTO TARIFAS (idTarifa,idTipo,valor) VALUES (2, 2, 2000);

INSERT INTO VEHICULO (idVehiculo,placa, color, idTipo) VALUES (1, 'ABC123','Gris',1);
INSERT INTO VEHICULO (idVehiculo,placa, color, idTipo) VALUES (2, 'CAB12D','Rojo',2);
INSERT INTO VEHICULO (idVehiculo,placa, color, idTipo) VALUES (3, 'DEF456','Azul',1);
INSERT INTO VEHICULO (idVehiculo,placa, color, idTipo) VALUES (4, 'FED45F','Negro',2);
INSERT INTO VEHICULO (idVehiculo,placa, color, idTipo) VALUES (5, 'JDK123','Blanco',1);
INSERT INTO VEHICULO (idVehiculo,placa, color, idTipo) VALUES (6, 'KJD12K','Dorado',2);


INSERT INTO ESTACIONAMIENTO(idEstacionamiento,idVehiculo,tiempo_entrada,tiempo_salida, total) VALUES (1, 1,'2023-06-11 11:30:00',null,null) ;
INSERT INTO ESTACIONAMIENTO(idEstacionamiento,idVehiculo,tiempo_entrada,tiempo_salida, total) VALUES (2, 2,'2023-06-11 11:30:00',null,null) ;
INSERT INTO ESTACIONAMIENTO(idEstacionamiento,idVehiculo,tiempo_entrada,tiempo_salida, total) VALUES (3, 3,'2023-06-12 08:00:00',null,null) ;
INSERT INTO ESTACIONAMIENTO(idEstacionamiento,idVehiculo,tiempo_entrada,tiempo_salida, total) VALUES (4, 4,'2023-06-10 15:00:00',null,null) ;




