create sequence account_seq start with 1 increment by 50;
create sequence transaction_seq start with 1 increment by 50;
create table account (id bigint not null, is_company boolean, name varchar(100) not null, primary key (id));
create table transaction (id bigint not null, expiration_date timestamp(6), transaction_amount integer, transaction_date timestamp(6), transaction_type smallint, emiter_id bigint not null, reciever_id bigint not null, primary key (id));
alter table if exists transaction add constraint FK37y9wboa3qd3jo8ah6g6n2e6a foreign key (emiter_id) references account;
alter table if exists transaction add constraint FKmgfg22cx5k3w7tw257xu7qq8c foreign key (reciever_id) references account;
create sequence account_seq start with 1 increment by 50;
create sequence transaction_seq start with 1 increment by 50;
create table account (id bigint not null, is_company boolean, name varchar(100) not null, primary key (id));
create table transaction (id bigint not null, expiration_date timestamp(6), transaction_amount integer, transaction_date timestamp(6), transaction_type smallint, emiter_id bigint not null, reciever_id bigint not null, primary key (id));
alter table if exists transaction add constraint FK37y9wboa3qd3jo8ah6g6n2e6a foreign key (emiter_id) references account;
alter table if exists transaction add constraint FKmgfg22cx5k3w7tw257xu7qq8c foreign key (reciever_id) references account;
create sequence account_seq start with 1 increment by 50;
create sequence transaction_seq start with 1 increment by 50;
create table account (id bigint not null, is_company boolean, name varchar(100) not null, primary key (id));
create table transaction (id bigint not null, expiration_date timestamp(6), transaction_amount integer, transaction_date timestamp(6), transaction_type smallint, emiter_id bigint not null, reciever_id bigint not null, primary key (id));
alter table if exists transaction add constraint FK37y9wboa3qd3jo8ah6g6n2e6a foreign key (emiter_id) references account;
alter table if exists transaction add constraint FKmgfg22cx5k3w7tw257xu7qq8c foreign key (reciever_id) references account;
create sequence account_seq start with 1 increment by 50;
create sequence transaction_seq start with 1 increment by 50;
create table account (id bigint not null, is_company boolean, name varchar(100) not null, primary key (id));
create table transaction (id bigint not null, expiration_date timestamp(6), transaction_amount integer, transaction_date timestamp(6), transaction_type smallint, emiter_id bigint not null, reciever_id bigint not null, primary key (id));
alter table if exists transaction add constraint FK37y9wboa3qd3jo8ah6g6n2e6a foreign key (emiter_id) references account;
alter table if exists transaction add constraint FKmgfg22cx5k3w7tw257xu7qq8c foreign key (reciever_id) references account;
create sequence account_seq start with 1 increment by 50;
create sequence transaction_seq start with 1 increment by 50;
create table account (id bigint not null, is_company boolean, name varchar(100) not null, primary key (id));
create table transaction (id bigint not null, expiration_date timestamp(6), transaction_amount integer, transaction_date timestamp(6), transaction_type smallint, emiter_id bigint not null, reciever_id bigint not null, primary key (id));
alter table if exists transaction add constraint FK37y9wboa3qd3jo8ah6g6n2e6a foreign key (emiter_id) references account;
alter table if exists transaction add constraint FKmgfg22cx5k3w7tw257xu7qq8c foreign key (reciever_id) references account;
