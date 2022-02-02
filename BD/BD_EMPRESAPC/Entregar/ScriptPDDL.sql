drop database if exists Market_Place_PC;
create database if not exists Market_Place_PC;
use Market_Place_PC;

create or replace table usuario(
id int auto_increment,
dni char(9)not null unique,
nombre varchar(50)not null,
apellidos varchar(100)not null,
telefono varchar(15)not null,
pais varchar(20)not null,
residencia int not null,
primary key(id)
); 

create or replace table direccion(
id_dir int auto_increment,
calle varchar(20)not null,
numero int not null,
cp int(5)not null,
poblacion varchar(20)not null,
piso int not null,
letra char(9)not null,

primary key(id_dir)
);

alter table usuario add constraint foreign key(residencia)references direccion(id_dir) on delete restrict on update cascade;

create or replace table direccion_usuario(
direccion int,
usuario int,

primary key(direccion,usuario),

foreign key (direccion)references direccion (id_dir)
on delete cascade
on update cascade,

foreign key(usuario)references usuario(id)
on delete cascade 
on update cascade
);

create or replace table login(
usuario int,
nickname varchar(50)not null,
email varchar(70)not null,
contrasenha_sha256 varchar(1000)not null,

primary key (usuario),
foreign key(usuario)references usuario(id)
on delete cascade
on update cascade
);

create or replace table venta(
usuario int,
fecha date,
hora time,

primary key(usuario,fecha,hora),
foreign key(usuario)references login(usuario)
on delete cascade
on update cascade
);

create or replace table envio(
id int auto_increment,
companhia_envio varchar(50)not null,
fecha_envio date not null,
descripcion text(200)not null,
direccion int not null,
usuario int not null,
fecha_venta date not null,
hora_venta time not null,

primary key(id),

foreign key(direccion)references direccion(id_dir)
on delete restrict 
on update cascade,

foreign key(usuario,fecha_venta,hora_venta) references venta (usuario,fecha,hora)
on delete cascade 
on update cascade
);

create or replace table producto(
id int auto_increment,
modelo varchar(100)not null,
marca varchar(50)not null,
precio_venta double(7,2)not null,
pvp double(7,2)not null,
stock int not null,

primary key(id),

check(stock>=0),
check(precio_venta>=0),
check(pvp>=0)
);

create or replace table pack(
id int auto_increment,
precio double(7,2),

primary key(id),

check(precio>=0)
);

create or replace table venta_producto(
usuario int,
fecha_venta date,
hora_venta time,
producto int,

primary key(fecha_venta,hora_venta,usuario,producto),
foreign key(usuario,fecha_venta,hora_venta)references venta(usuario,fecha,hora)
on delete cascade
on update cascade,
foreign key(producto)references producto(id)
on delete cascade
on update cascade
);

create or replace table pro_componente(
id_producto int,
tipo_comp enum('tarjeta gráfica', 'memoria ram', 'placa base', 'disco duro ssd', 'disco duro hdd', 'tarjeta de red', 'tarjeta de sonido', 'ventilador', 'refrigeración líquida', 'fuente de alimentación', 'procesador' , 'otro' ) not null default 'otro',
conex_placa varchar(20),

primary key(id_producto),
foreign key (id_producto)references producto(id)
on delete cascade
on update cascade
);

create or replace table pro_periferico(
id_producto int,
tipo_per enum('monitor', 'teclado', 'ratón', 'impresora', 'escáner', 'proyector', 'auriculares', 'altavoces', 'impresora multifunción', 'impresora 3D' , 'otro' )not null default 'otro',

primary key(id_producto),
foreign key (id_producto)references producto(id)
on delete cascade
on update cascade
);

create or replace table medio_conex_periferico(
periferico int,
medio_conex enum('usb', 'hdmi', 'vga', 'bluetooth', 'wifi', 'LAN', 'usb-c', 'micro-hdmi', 'micro-usb', 'lightning' , 'otro') default 'otro',

primary key(periferico,medio_conex),
foreign key(periferico)references pro_periferico(id_producto)
on delete cascade
on update cascade
);

create or replace table venta_pack(
usuario int,
fecha_venta date,
hora_venta time,
pack int,

primary key(fecha_venta,hora_venta,usuario,pack),
foreign key(usuario,fecha_venta,hora_venta)references venta(usuario,fecha,hora)
on delete cascade
on update cascade,
foreign key (pack)references pack(id)
on delete cascade
on update cascade
);

create or replace table pack_producto(
pack int,
producto int,

primary key (pack,producto),
foreign key (pack)references pack(id)
on delete cascade
on update cascade,
foreign key (producto)references producto(id)
on delete cascade
on update cascade
);






#