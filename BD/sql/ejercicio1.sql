drop database if exists empresaincidencias;
create database empresaincidencias;

use empresaincidencias;

create or replace table Aplicacion(
nombre varchar(20),
descripcion varchar (300)not null,

primary key (nombre)
);


create or replace table Incidencia(
codigo int auto_increment,
fecha_apertura date not null,
fecha_cierre date,
aplicacion varchar(20),

primary key (codigo),
foreign key (aplicacion) references Aplicacion(nombre)
on delete restrict
on update cascade
);


create or replace table version_aplicacion(
num_version varchar(10),
aplicacion varchar(20),

primary key (num_version, aplicacion),

foreign key(aplicacion) references Aplicacion(nombre)
on delete cascade
on update cascade
);

create or replace table Programador(
nif char(9),
nss char(13) unique not null,
nombre varchar(20)not null,
email varchar(50)not null,

primary key (nif)
);


alter table Incidencia 
add column programador_descubridor,
foreign key (programador_descubridor) references Programador(nif)
on delete restrict
on update cascade;


create or replace table Programador_Incidencia(
incidencia int,
aplicacion varchar(20),
tiempo time not null,
primary key (incidencia, aplicacion),

foreign key (incidencia)references Incidencia(codigo)
on delete cascade 
on update cascade,

foreign key (aplicacion)references Aplicacion(nombre)
on delete cascade 
on update cascade
);