create table order_detail(
  id int primary key auto_increment,
  order_id int,
  product_id char(36) not null,
  quantity int not null
)