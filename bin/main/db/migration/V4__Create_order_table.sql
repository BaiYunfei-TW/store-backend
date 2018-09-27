create table shopping_order(
  id int primary key auto_increment,
  user_id int not null,
  total_price decimal(10,2) not null
)