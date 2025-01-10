create schema classes;

create table students (
student_id int primary key,
first_name varchar(30) not null, 
last_name varchar(30) not null, 
city varchar(40)
);

create table courses (
course_id int primary key, 
course_name varchar(30) not null, 
points int 
);

create table grades (
student_id int,
course_id int, 
grade int,

primary key (student_id, course_id),

constraint fk_student
foreign key (student_id) references students(student_id),

constraint fk_course
foreign key (course_id) references courses(course_id)
);

create table good_grades (
student_id int,
course_id int,
grade int
);



insert into courses 
values (1,"Sql", 20),
(2,"Java", 20),
(3,"Mario",60);

insert into grades 
values(1,2,80),
(2,3,60),
(2,2,90),
(1,2,100);

insert into good_grades 
select student_id,course_id,grade from grades
where grade >= 80;