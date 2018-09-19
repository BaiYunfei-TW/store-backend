create table product(
  id char(36) primary key,
  name varchar(255) not null,
  price decimal(10,2) not null,
  unit varchar(50) not null,
  total_amount int not null,
  img_url varchar(500)
);