drop database if exists Jugueteria;
create database if not exists Jugueteria;
use Jugueteria;

create or replace table empleado(
nombreCorto varchar(20),

primary key(nombreCorto)
);

create or replace table ticket(
id int auto_increment,
fecha date not null,
empleado varchar(20)not null,

primary key(id),

foreign key (empleado)references empleado(nombreCorto)
on delete restrict
on update cascade 
);

create or replace table articulo(
cod int auto_increment,
stock int not null,
nombre varchar(50)not null,
precio decimal(6,2)not null,

primary key(cod)
);

create or replace table articulo_ticket(
cod_articulo int,
id_ticket int,
cantidad int not null,

primary key(cod_articulo, id_ticket),

foreign key (cod_articulo)references articulo (cod)
on delete restrict 
on update cascade,

foreign key (id_ticket)references ticket (id)
on delete restrict 
on update cascade
);

create or replace table registro(
empleado varchar(20),
fecha_hora_ini datetime,
fecha_hora_fin datetime,

primary key (empleado, fecha_hora_ini),

foreign key (empleado) references empleado (nombreCorto)
on delete cascade
on update cascade
);