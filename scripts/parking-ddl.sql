DROP TABLE IF EXISTS ESTACIONAMIENTO CASCADE;
DROP TABLE IF EXISTS VEHICULO CASCADE;
DROP TABLE IF EXISTS TARIFAS CASCADE;
DROP TABLE IF EXISTS TIPO CASCADE;

CREATE TABLE TIPO(
    idTipo integer primary key,
    tipo varchar(30)
);

CREATE TABLE TARIFAS(
    idTarifa integer primary key,
    idTipo integer,
    valor integer,
    foreign key(idTipo) references TIPO (idTipo)
);

CREATE TABLE VEHICULO (
    idVehiculo integer primary key,
    placa varchar(6),
    color varchar(20),
    idTipo integer,
    foreign key(idTipo) references TIPO (idTipo)
);


CREATE TABLE ESTACIONAMIENTO(
    idEstacionamiento integer primary key,
    idVehiculo integer,
    tiempo_entrada TIMESTAMP,
    tiempo_salida TIMESTAMP,
    total integer,
    foreign key(idVehiculo) references VEHICULO (idVehiculo)
);





