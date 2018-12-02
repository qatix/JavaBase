use test;
drop table if exists product;
create table product(
  id int auto_increment primary key ,
  name varchar(32) not null default '',
  category_id int not null default 0,
  no varchar(32) not null default '',
  price decimal(10,2) not null default 0,
  status tinyint not null default 0,
  create_time datetime not null default NOW(),
  update_time datetime not null default NOW()
)default  charset=utf8 comment='Product';

drop table if exists category;
create table category(
  id int auto_increment primary key ,
  name varchar(32) not null default '',
  create_time datetime not null default NOW(),
  update_time datetime not null default NOW()
)default  charset=utf8 comment='Category';

insert into category(name) values('Apple'),('Huawei'),('Xiaomi'),('Oppo');

insert into product(name,category_id,no,price,status) values('P20',2,'P000011',5000,1),('iPhoneX',1,'P000012',9000,1),('Mi6',3,'P000013',2000,0),('Mi5',3,'P000013',1000,0);
