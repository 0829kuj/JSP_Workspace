# demo 라는 이름의 db가 없으면 생성하고 utf8mb4(모든 문자, 한글 및 이모지포함) 로 설정
create database if not exists demo
default character set UTF8Mb4 COLLATe UtF8Mb4_UNICode_ci;

USE dEMO;	# DEmO 데이터베이스를 선택

DROP tABLE IF EXISTS users; #Users테이블이 있으면 삭제
create table users (
	id int NOT NULL aUTo_INCREment,			# 자동으로 1씩 증가 생성됨
    firstName varchAR(20) DEFAUlt null,
    LAStNAMe VARCHAR(20) DEFAULT null,
    userName varchar(20) default null,		# 유저이름
    PASSWORd VARCHAr(20) default null,		# 비밀번호
    PRIMARY KEY(Id)
);
drop tabLE IF EXISTs TODOS; #TODOs테이블이 있으면 삭제
create table todos (
	ID INT NOT NULl AUTO_increment,				# 자동으로 1씩 증가 생성됨
    dEscRIPTiON VArCHaR(255) default null,		# 할일 설명
    is_done bit(1) nOT NULl,					# 완료됨
    taRGEt_DAtE DAtETIME(6) DEFAULt null,		# 목표 날짜
    username varchar(255) default null,			# 유저 이름
    TITLE vARCHAr(255) default null,			# 할일 이름
    PrIMArY KEY(id)
);

insert into users(firstname,lastname,username,password)
values ('홍', '길동', 'hong', '1111');
insert into users(firstname,lastname,username,password)
values ('손', '흥민', 'son', '1234');

# 설정>SQL에디터>쿼리에디터>use uppcase 체크시 키워드가 모두 대문자로 자동완성됨
# 데이터를 한 행씩은 삭제가능하지만 여러행은 삭제가 안되도록 막혀있음. 풀려면 EDIT>PREference>SQL에디터>맨아래 safe... 체크해제(단 권장하지 않음)
SELECT * FROm users;
delete from usERS;
DElETE frOm useRS WHErE ID = 1;

# todo기능
INSERT INTO todos(title, username, description, target_date, is_done) VALUES(?,?,?,?,?);
SELECT id, title, username, description, target_date, is_done FROM todos WHERE id = 1;
UPDATE () from todos where id =1;

SELECT * FROM demo.todos;
INSERT INTO todos(title, username, target_date, is_done, description)
VALUES("할일3", "son", 2022-1-24, 0, "세번째 할일 설명");
