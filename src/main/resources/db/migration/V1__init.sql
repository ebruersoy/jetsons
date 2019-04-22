
create sequence seq_ilan increment by 1 minvalue 1;
create sequence seq_danisman increment by 1 minvalue 1;
create sequence seq_ofis increment by 1 minvalue 1;
create sequence seq_emlakci increment by 1 minvalue 1;

create table ilan(
  id bigint primary key not null ,
  detail varchar not null,
  vitrin_hakki boolean default false,
  create_date timestamp not null,
  update_date timestamp not null,
  danisman_id bigint not null,
  ofis_id bigint not null
);

create table danisman(
  id bigint primary key not null ,
  name varchar not null,
  ofis_id bigint not null
);

create table ofis(
  id bigint primary key not null ,
  name varchar not null,
  emlakci_id bigint not null
);

create table emlakci(
  id bigint primary key not null ,
  name varchar not null,
  vitrin_hakki INTEGER default 0 not null
);

