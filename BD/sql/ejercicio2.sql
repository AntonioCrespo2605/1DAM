drop database if exists ejercicio2;
create database ejercicio2;
use ejercicio2;

create or replace table Becario(
dni char(9),
nss char(13)unique not null,
nombre varchar(20)not null,
apellidos varchar(50)not null,
fecha_nac date not null,
sustituto char(9) not null,

primary key(dni),
foreign key (sustituto)references Becario(dni)
on delete cascade
on update cascade
);


create or replace table Laboratorio(
id int auto_increment,
num_planta int not null,
supervisor char(9),

primary key(id),
foreign key(supervisor)references Becario(dni)
on delete restrict
on update cascade
);

create table if not exists Ordenador(
direccion_ip char(14)not null,
id int auto_increment,
fecha_compra date not null,
laboratorio_asignado int not null,

primary key (id),
foreign key (laboratorio_asignado)references laboratorio(id)
on delete restrict
on update cascade
);

create or replace table Fabricante(
codigo int auto_increment,
nombre varchar(20)not null,
direccion_web varchar(60)not null unique,
telf1 char(9)not null unique,
telf2 char(9)unique,
check(telf1!=telf2),
primary key(codigo)
);

create or replace table Componente(
codigo_modelo varchar(10),
descripcion text not null,
fabricante int,
primary key(codigo_modelo),
foreign key(fabricante)references Fabricante(codigo)
on delete restrict
on update cascade
);

create or replace table componente_ordenador(
ordenador int,
componente varchar(10),
primary key(ordenador,componente),
foreign key (ordenador)references Ordenador(id)
on delete cascade
on update cascade,

foreign key (componente)references Componente(codigo_modelo)
on delete cascade
on update cascade
);













