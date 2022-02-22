drop database if exists ejercicio9;
create database if not exists ejercicio9;
use ejercicio9;

create table agente(
dni char(9),
nombre varchar(20)not null,
telefono int(9)not null,
direccion varchar(60)not null,

primary key(dni)
);

create table cliente(
dni char(9),
telefono int(9)not null,
direccion varchar(60)not null,
nombre varchar(20)not null,

primary key(dni)
);


create table agente_cliente(
agente char(9),
cliente char(9),

foreign key (agente)references agente(dni)
on delete cascade
on update cascade,

foreign key(cliente)references cliente (dni)
on delete cascade
on update cascade,

primary key(agente,cliente)
);

create table seguro(
id int,
prima int not null,
tipo enum('1','2')not null,
agente char(9) not null,
cliente char(9)not null,
fecha_creacion date not null,
cartera varchar(30)not null,

primary key(id),

foreign key (agente)references agente(dni)
on delete restrict
on update cascade,

foreign key(cliente)references cliente (dni)
on delete cascade
on update cascade
);


create table seguro_vida(
id int,
fecha_nac_cliente date not null,
profesion_cliente varchar(60)not null,
cobertura varchar(50)not null,

foreign key(id)references seguro(id)
on delete cascade
on update cascade,

primary key(id)
);


create table beneficiario_seg_vida(
seguro int,
nombre_beneficiario varchar(50),

foreign key(seguro)references seguro_vida(id)
on delete cascade
on update cascade,

primary key(seguro,nombre_beneficiario)
);


create table seguro_hogar(
id int,
continente varchar(30)not null,
contenido text(200)not null,
direccion varchar(60)not null,

foreign key(id)references seguro(id)
on delete cascade
on update cascade,

primary key(id)
);


create table riesgo_seg_hogar(
seguro int,
riesgo varchar(50),

foreign key(seguro)references seguro_hogar(id)
on delete cascade
on update cascade,

primary key(seguro,riesgo)
);


create table estudio_seg_hogar(
seguro int,
estudio varchar(50),

foreign key(seguro)references seguro_hogar(id)
on delete cascade
on update cascade,

primary key(seguro,estudio)
);


create table seguro_automovil(
id int,
fecha_nac_conductor date not null,
matricula varchar(9)not null,
cobertura varchar(50)not null,
fabricacion date not null,
categoria enum('estándar','alta','media'),

foreign key(id)references seguro(id)
on delete cascade
on update cascade,

primary key(id)
);
#