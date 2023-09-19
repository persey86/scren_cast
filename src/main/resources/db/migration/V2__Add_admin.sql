insert into usr (id, username, password, active, email)
    values (1, 'admin', '123', true, '555kost@gmail.com');

insert into user_role (user_id, roles)
    values (1, 'USER'), (1, 'ADMIN');


insert into usr (id, username, password, active, email)
values (2, 'user', '123', true, '123qwerty@gmail.com');

insert into user_role (user_id, roles)
values (2, 'USER');