create database `demo`;

use `demo`;
create table `user`
(
    id bigint auto_increment primary key,
    username varchar(20) not null,
    password varchar(20) not null
) engine=InnoDB default charset=utf8mb4;

insert into `user` (username, password) values ("Tom", "pass1");
insert into `user` (username, password) values ("John", "pass2");
