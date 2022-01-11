drop database if exists ejercicio4;
create database if not exists ejercicio4;
use ejercicio4;

create table cliente(
codigo int auto_increment,
nombre varchar(20)not null,
telefono char(9)not null,
direccion varchar(50)not null,
dni char(9) not null unique,
aval int,

primary key(codigo),

foreign key(aval)references cliente(codigo)
on delete cascade
on update cascade
);

create table garaje(
codigo int auto_increment,
num_plazas int not null,
direccion varchar(50)not null,

primary key(codigo)
);


create table agencia(
codigo int auto_increment,
localidad varchar(50)not null,
direccion varchar(50)not null,

primary key(codigo)
);


create table coche(
matricula varchar(9),
precio decimal(6,2)not null,
modelo varchar(20) not null,
marca varchar(20)not null,
color varchar(30)not null,
garaje_asignado int not null,

primary key(matricula),
foreign key(garaje_asignado)references garaje(codigo)
on delete restrict
on update cascade
);


create table reserva(
fecha_inicio date,
cliente int,
fecha_fin date not null,
precio_total decimal(6,2)not null,
agencia_titular int not null,

foreign key (cliente) references cliente(codigo)
on delete cascade
on update cascade,

primary key(fecha_inicio,cliente),

foreign key(agencia_titular)references agencia(codigo)
on update restrict
on delete cascade
);

create table coche_reserva(
fecha_inicio date,
cliente int,
coche varchar(9),
litros int,
indicador enum('disponible','ocupado'), 
foreign key (cliente) references cliente(codigo)
on delete cascade
on update cascade,

foreign key(fecha_inicio)references reserva(fecha_inicio)
on delete cascade
on update restrict,

foreign key(coche)references coche (matricula)
on delete cascade
on update cascade,

primary key(fecha_inicio, cliente, coche)
);














#