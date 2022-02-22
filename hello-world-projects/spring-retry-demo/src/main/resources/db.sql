create database test;

create table if not exists `user`(
   `id` int auto_increment,
   `name` varchar(64) not null,
   `age` int null,
   primary key ( `id` )
)engine=InnoDB default charset=utf8;