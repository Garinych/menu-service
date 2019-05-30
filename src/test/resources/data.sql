delete from menu_food;
delete from menus;
delete from food;

insert into food(id, name, price) values (1, 'Burito', 50.0);
insert into food(id, name, price) values (2, 'Chimichanga', 70.0);
insert into food(id, name, price) values (3, 'Tartilia', 60.0);
insert into food(id, name, price) values (4, 'Nacios', 30.0);
insert into food(id, name, price) values (5, 'Holopenio', 20.0);
insert into food(id, name, price) values (6, 'Tecuila', 50.0);


insert into menus(id, name, is_archived) values (1, '1 Mexican Menu', true);
insert into menus(id, name, is_archived) values (2, '2 Mexican Menu', true);
insert into menus(id, name, is_archived) values (3, '3 Mexican Menu', true);

insert into menu_food(menu_id, food_id) values (1, 1);

insert into menu_food(menu_id, food_id) values (2, 2);
insert into menu_food(menu_id, food_id) values (2, 3);

insert into menu_food(menu_id, food_id) values (3, 4);
insert into menu_food(menu_id, food_id) values (3, 5);
insert into menu_food(menu_id, food_id) values (3, 6);


