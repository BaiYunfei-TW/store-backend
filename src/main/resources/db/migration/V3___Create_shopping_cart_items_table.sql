create table shopping_cart_item(
  id int primary key auto_increment,
  product_id char(36) not null,
  quantity int not null,
  user_id int not null
)