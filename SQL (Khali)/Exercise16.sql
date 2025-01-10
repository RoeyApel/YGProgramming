create schema roey_db;

create table employees (
	id int primary key auto_increment,
    first_name varchar(40),
    last_name varchar(40),
    city varchar(50),
    birth_date datetime
);

create table changes (
	emp_id int ,
    first_name varchar(40), 
    last_name varchar(40),
	city varchar(50),
    birth_date datetime,
    change_date datetime primary key,
    change_kind varchar(3),
    check(change_kind = "INS" or change_kind = "UPD" or change_kind = "DEL")
);