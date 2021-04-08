DROP TABLE IF EXISTS VEHICLE_INFO;

DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS user_roles;

CREATE TABLE `user_roles` (
 `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
 `username` varchar(50) NOT NULL,
 `AUTHORITY` varchar(50) NOT NULL,
 PRIMARY KEY (`user_role_id`),
 UNIQUE KEY `uni_username_role` (`AUTHORITY`,`username`)
 );

CREATE TABLE `users` (
 `username` varchar(50) NOT NULL,
 `password` varchar(200) NOT NULL,
 `enabled` tinyint(3) NOT NULL DEFAULT '1',
 PRIMARY KEY (`username`)
 );

CREATE TABLE VEHICLE_INFO (
	vehicle_id INT NOT NULL AUTO_INCREMENT,
	vehicle_name varchar(150) NOT NULL,
	vehicle_type varchar(150) NOT NULL,
	vehicle_model varchar(150) NOT NULL,
	vehicle_manufacturer varchar(250) NOT NULL,
	PRIMARY KEY (vehicle_id)
);