create database taqueriadb;

use taqueriadb;

create table tbl_usuario(
    cveUsuario int not null auto_increment primary key,
    nombre varchar(50),
    apellido varchar(50),
    username varchar(25),
    pwd varchar(20),
    puesto tinyint
);

create table tbl_plato(
    idPlato int not null primary key auto_increment,
    nombrePlato varchar(30),
    descPlato varchar(255),
    precioPlato float(4,2)
);

create table tbl_bebida(
    idBebida int not null primary key auto_increment,
    nombreBebida varchar(30),
    descBebida varchar(255),
    precioBebida float(4,2)
);

create table tbl_mesa(
    idMesa int not null primary key auto_increment,
    descripcion varchar(50),
    cveUsuario int,
    constraint fk_usr foreign key (cveUsuario) references tbl_usuario(cveUsuario)
);

create table tbl_pedido(
    idPedido int not null auto_increment,
    idPlato int,
    cantidadPlato int,
    idBebida int,
    cantidadBebida int,
    idMesa int not null,
    constraint fk_pl foreign key (idPlato) references tbl_plato(idPlato),
    constraint fk_beb foreign key (idBebida) references tbl_bebida(idBebida),
    constraint fk_ms foreign key (idMesa) references tbl_mesa(idMesa),
    constraint pk_pd primary key (idPedido, idMesa)
);

create table ticket(
    idTicket int not null auto_increment primary key,
    idPedido int,
    idMesa int,
    precioTotal float(6,2),
    constraint fk_pedido foreign key (idPedido, idMesa) references tbl_pedido(idPedido, idMesa)
);