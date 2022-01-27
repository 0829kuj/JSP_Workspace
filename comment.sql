USE demo;
DROP TABLE IF EXISTS comment;
CREATE TABLE IF NOT EXISTS comment (
  id INT not null AUTO_INCREMENT,
  comment text default NUll,
  email varchar(255),
  PRIMARY KEY(id)
);

insert into comment (comment, email) values ("첫 댓글입니다.", "0829kuj@naver.com");