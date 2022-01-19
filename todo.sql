# demo 라는 이름의 db가 없으면 생성하고 utf8mb4(모든 문자, 한글 및 이모지포함) 로 설정
create database if not exists demo
default character set utf8mb4 collate utf8mb4_unicode_ci;

use demo;	# demo 데이터베이스를 선택

drop table if exists users; #users테이블이 있으면 삭제
create table users (
	id int not null auto_increment,			# 자동으로 1씩 증가 생성됨
    firstName varchar(20) default null,
    lastName varchar(20) default null,
    userName varchar(20) default null,		# 유저이름
    password varchar(20) default null,		# 비밀번호
    primary key(id)
);
drop table if exists todos; #todos테이블이 있으면 삭제
create table todos (
	id int not null auto_increment,				# 자동으로 1씩 증가 생성됨
    description varchar(255) default null,		# 할일 설명
    is_done bit(1) not null,					# 완료됨
    target_date datetime(6) default null,		# 목표 날짜
    username varchar(255) default null,			# 유저 이름
    title varchar(255) default null,			# 할일 이름
    primary key(id)
);

insert into users(firstName,lastName,userName,password)
values ('홍', '길동', '🎈🧨🎈🧨', '1234');
