create table appuser (
	id number,
	firstName varchar2(30),
	lastName varchar2(30),
	userName varchar2(30),
	password varchar2(40),
	constraint appuser_pk primary key (id),
	constraint app_username_uk unique (userName)
);