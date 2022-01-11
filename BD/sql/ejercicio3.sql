drop database if exists ejercicio3;
create database if not exists ejercicio3;
use ejercicio3;

create table departamento(
codigo int auto_increment,
nombre varchar(30) not null,
director char(15)not null,

primary key (codigo)
);


create table profesor(
nrp char(15),
nombre varchar(20)not null,
categoria varchar(20)not null,
departamento_asignado int not null,
area varchar(20) not null,
primary key (nrp),
foreign key(departamento_asignado)references departamento(codigo)
on delete restrict
on update cascade
);


alter table departamento
add foreign key(director)references profesor(nrp);


create table grupo(
id int,
max_alumnos int not null,
numero int,
tipo enum('teoria','practica'),

primary key (id,numero)
);




create table asignatura(
codigo int auto_increment,
nombre varchar(20)not null,
caracter enum('truncal','optativa','obligatorio')not null,
curso varchar(20),

primary key(codigo)
);


create table alumno(
dni char(9),
direccion varchar(50)not null,
beca decimal(7,2),

fecha_nacimiento date not null,

primary key(dni)
);


create table alumnos_matriculados(
convocatoria_inicio date,
dni_alumno char(9),
codigo_asignatura int,
nota int,

primary key(convocatoria_inicio, dni_alumno, codigo_asignatura),

foreign key (dni_alumno) references alumno (dni)
on delete cascade
on update cascade,

foreign key (codigo_asignatura) references asignatura (codigo)
on delete cascade
on update cascade
);


create table profesor_grupo(
profesor char(15),
numero_grupo int,
codigo_grupo int,

primary key(profesor, numero_grupo, codigo_grupo),
foreign key(profesor)references profesor(nrp)
on delete cascade
on update cascade,

foreign key(codigo_grupo)references grupo(id)
on delete cascade
on update cascade/*,

foreign key(numero_grupo)references grupo(numero)
on delete cascade
on update cascade*/
);