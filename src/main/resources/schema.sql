drop table if exists coche ;
drop table if exists alquiler ;
drop table if exists coche_alquilado ;
drop sequence if exists hibernate_sequence;

create sequence hibernate_sequence start with 100 increment by 1;

create table coche (
                           id bigint not null,
                           existencia float not null,
                           imagen varchar,
                           marca varchar,
                           matricula varchar,
                           precio float,
                           primary key (id)
);

create table alquiler (
                          id bigint not null,
                          fechayhora varchar,
                          primary key (id)
);

create table coche_alquilado (
                            id bigint not null,
                            marca varchar not null,
                            matricula varchar not null,
                            meses float,
                            precio float,
                            primary key (id)
);

alter table coche_alquilado add constraint fk_alquiler_coche_alquilado foreign key (alquiler_id) references alquiler;
