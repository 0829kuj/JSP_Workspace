# 데이터베이스 webshop이 없으면 새로생성. uft8mb4는 한글뿐만 아니라 이모지를 포함한 문자까지 사용가능
create database if not exists webshop default character set utf8mb4 collate utf8mb4_unicode_ci;
use webshop;	# webshop을 선택
# user테이블이 있으면 삭제 후 새로만듦
drop table if exists user;
create table user (
	id int primary key auto_increment,		# 유저 아이디
    email varchar(50),						# 이메일
    password varchar(50)					# 비밀번호
);