drop table if exists tamanho cascade;
drop table if exists sabor cascade;
drop table if exists pedido cascade;
drop table if exists adicional cascade;
drop table if exists pedido_adicional cascade;

create table tamanho (
	id		serial		not null,
	nome		varchar(100)	not null,
	valor		numeric 	not null,
	tempo_minuto	numeric 	not null,
	data_criacao	timestamp	not null,
	constraint pk_tamanho
		primary key (id)
);

create table sabor (
	id		serial		not null,
	nome		varchar(100)	not null,
	tempo_minuto	numeric		not null,
	data_criacao	timestamp 	not null,
	constraint pk_sabor
		primary key (id)
);

create table adicional (
	id		serial		not null,
	nome		varchar(100)	not null,
	valor		numeric		not null,
	tempo_minuto	numeric		not null,
	data_criacao	timestamp	not null,
	constraint pk_adicional
		primary key (id)
);

create table pedido (
	id		serial		not null,
	id_tamanho	integer		not null,
	id_sabor	integer		not null,
	data_criacao	timestamp	not null,
	constraint pk_pedido
		primary key (id)
);

create table pedido_adicional (
	id_pedido	integer		not null,
	id_adicional	integer		not null,
	constraint pk_pedido_adicional
		primary key(id_pedido, id_adicional)
);

alter table pedido
	add constraint fk_pedido_tamanho
		foreign key (id_tamanho)
		references tamanho,
	add constraint fk_pedido_sabor
		foreign key (id_sabor)
		references sabor;


alter table pedido_adicional
	add constraint fk_pedido_adicional_pedido
		foreign key (id_pedido)
		references pedido,
	add constraint fk_pedido_adicional_adicional
		foreign key (id_adicional)
	references adicional;
