create table usuario (
    id_usuario serial primary key,
    nome varchar(255) not null,
    login varchar(255) not null unique,
    senha varchar(255) not null
);

create table situacao (
    id_situacao serial primary key,
    id_usuario integer not null references usuario(id_usuario),
    nome varchar(255) not null unique,
    descricao varchar(255) not null,
    data_inicio date not null,
    data_fim date not null
);

create table dispositivo (
    id_dispositivo serial primary key,
    nome varchar(255) not null,
    tipo varchar(255) not null
);

create table agente (
    id_agente serial primary key,
    id_situacao integer references situacao(id_situacao),
    id_dispositivo integer references dispositivo(id_dispositivo),
    nome varchar(255) not null,
    descricao varchar(255) not null,
    tipo varchar(255) not null
);

create table localizacao (
    timestamp timestamp not null,
    id_dispositivo integer not null references dispositivo(id_dispositivo),
    latitude varchar(255) not null,
    longitude varchar(255) not null,
    primary key (timestamp, id_dispositivo)
);
