delete from user_role;
delete from usr;

insert into usr(id, active, password, username) values
(1, true, '$2a$08$B4F1mz1q7AyADzQ9tVTGgeF9BTZob/ZPK/JhtqupMMuuAOKXdTOBO', 'admin'),
(2, true, '$2a$08$vztQLDDQpll3G/XKXZQrS.ylUztMlIyyP0kXT4ZIu1z4ryKF2RpDy', 'user');

insert into user_role(user_id, roles) VALUES
(1, 'USER'), (1, 'ADMIN'),
(2, 'USER')