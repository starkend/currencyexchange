-- noinspection SqlNoDataSourceInspectionForFile

--Postgres ce schema

--************
--****NOTE****
--Replace "postgres_user_account" in this file with your postgres db user
--************

create schema ce;

alter schema ce owner to postgres_user_account;

create sequence hibernate_sequence;

alter sequence hibernate_sequence owner to postgres_user_account;

create table if not exists symbol
(
	id serial not null
		constraint symbol_pk
			primary key,
	symbol_pair text not null
);

comment on table symbol is 'Symbol Trading Pairs';

alter table symbol owner to postgres_user_account;

create unique index symbol_id_uindex
	on symbol (id);