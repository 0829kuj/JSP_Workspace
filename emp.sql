CREATE DATABASE IF NOT EXISTS demo DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE demo;
DROP TABLE IF EXISTS EMP;
CREATE TABLE IF NOT EXISTS EMP (
  id INT AUTO_INCREMENT,
  name VARCHAR(50),
  PRIMARY KEY(id)
);

INSERT INTO emp(name) VALUES("펭수");
INSERT INTO emp(name) VALUES("길동");
INSERT INTO emp(name) VALUES("철수");
INSERT INTO emp(name) VALUES("영희");
INSERT INTO emp(name) VALUES("영심");
INSERT INTO emp(name) VALUES("땡칠");

#members 테이블
DROP TABLE IF EXISTS `members`;
CREATE TABLE `members` (
  `id` int unsigned NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `phone` varchar(100) default NULL,
  `email` varchar(255) default NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;

INSERT INTO `members` (`name`,`phone`,`email`)
VALUES
  ("Dolan","010-9264-2551","phasellus.vitae@naver.com"),
  ("Aladdin","010-0733-3864","quisque.nonummy@naver.com"),
  ("Herrod","010-7523-8853","lacus.mauris@naver.com"),
  ("Jermaine","010-1533-2151","et.magnis.dis@naver.com"),
  ("Hyatt","010-6482-7191","turpis@naver.com"),
  ("Ethan","010-0372-0683","mauris.a.nunc@google.com"),
  ("Flynn","010-7113-1672","ornare.tortor.at@google.com"),
  ("Preston","010-2641-3095","suspendisse@google.com"),
  ("Arthur","010-6388-5512","vitae@naver.com"),
  ("Josiah","010-3335-9042","molestie.sodales@google.com"),
  ("Chaney","010-4184-3835","turpis.nulla@naver.com"),
  ("Eagan","010-5425-5619","leo@naver.com"),
  ("Barrett","010-1415-0513","non@naver.com"),
  ("Geoffrey","010-8841-7288","ligula.aenean@google.com"),
  ("Zephania","010-1527-2286","in.at.pede@google.com"),
  ("Dennis","010-6223-4992","facilisis.facilisis@naver.com"),
  ("Keith","010-2168-4708","nascetur.ridiculus@naver.com"),
  ("Myles","010-5170-7616","suspendisse.dui@google.com"),
  ("Keith","010-1617-4216","quis.accumsan@naver.com"),
  ("Kuame","010-2713-8633","nulla.ante@naver.com");
INSERT INTO `members` (`name`,`phone`,`email`)
VALUES
  ("Gabriel","010-9476-1256","lorem.tristique@naver.com"),
  ("Hedley","010-5556-1696","tempor.augue@naver.com"),
  ("Yoshio","010-6588-3785","non@google.com"),
  ("Micah","010-2876-9179","nunc@google.com"),
  ("Gage","010-1745-2124","massa@google.com"),
  ("Plato","010-3604-8384","orci.luctus@google.com"),
  ("Paki","010-4751-8378","nullam@google.com"),
  ("Kenneth","010-5763-1084","eleifend.nec@naver.com"),
  ("Jakeem","010-4715-5788","ultrices.iaculis.odio@google.com"),
  ("Hayden","010-5764-7538","natoque.penatibus@google.com"),
  ("Clarke","010-1736-2514","sit@naver.com"),
  ("Kirk","010-9215-6939","magna.sed@naver.com"),
  ("Raphael","010-8866-5261","diam.nunc@naver.com"),
  ("Xavier","010-6565-9188","eget.laoreet.posuere@google.com"),
  ("Kibo","010-2971-9654","blandit.mattis.cras@naver.com"),
  ("Hayes","010-4832-7312","eget.varius@google.com"),
  ("Abbot","010-1786-0323","consequat.nec@naver.com"),
  ("Colby","010-1158-9303","eu.dolor@naver.com"),
  ("Tarik","010-1382-9793","amet.consectetuer@naver.com"),
  ("Galvin","010-1804-3575","eget.metus.in@naver.com");
INSERT INTO `members` (`name`,`phone`,`email`)
VALUES
  ("Simon","010-8638-4573","elit.curabitur@naver.com"),
  ("Galvin","010-2885-6723","posuere.enim.nisl@naver.com"),
  ("Cyrus","010-3634-8322","nonummy.ultricies@google.com"),
  ("Tucker","010-8310-8488","facilisis.non@naver.com"),
  ("William","010-1101-1575","nisi.magna@naver.com"),
  ("Cadman","010-8610-9228","et.rutrum@naver.com"),
  ("Gil","010-2662-0131","quam@google.com"),
  ("Zane","010-4125-1838","magna.a.tortor@google.com"),
  ("Isaac","010-2972-4127","pede.nonummy@google.com"),
  ("Jermaine","010-9752-5259","tellus.lorem@naver.com");
INSERT INTO `members` (`name`,`phone`,`email`)
VALUES
  ("Ashton","010-5362-5424","interdum.nunc@naver.com"),
  ("Henry","010-4284-3187","ridiculus.mus@google.com"),
  ("Nero","010-6416-5859","pellentesque.sed@google.com"),
  ("Colt","010-1382-8418","risus.donec@naver.com"),
  ("Holmes","010-8294-2773","egestas.rhoncus@google.com"),
  ("Noble","010-2977-9231","pede.et.risus@google.com"),
  ("Peter","010-9525-8347","massa@google.com"),
  ("Shad","010-5970-7355","leo@naver.com"),
  ("Alexander","010-2555-8052","tellus.suspendisse.sed@naver.com"),
  ("Stuart","010-8489-5472","eu.dui.cum@google.com"),
  ("Aquila","010-5514-9511","augue.malesuada@google.com"),
  ("Benedict","010-7849-8282","sed.dictum.eleifend@naver.com"),
  ("Kevin","010-2981-6523","posuere.cubilia@google.com"),
  ("Thaddeus","010-1196-7845","congue.turpis@google.com"),
  ("Eric","010-1160-2713","leo.cras@naver.com"),
  ("Uriel","010-7634-8721","et.libero@naver.com"),
  ("Jelani","010-4717-2436","enim.commodo.hendrerit@naver.com"),
  ("Aaron","010-3547-4554","morbi.vehicula@google.com"),
  ("Martin","010-1867-7020","fringilla.est.mauris@google.com"),
  ("Kieran","010-3163-6222","aenean@naver.com");
INSERT INTO `members` (`name`,`phone`,`email`)
VALUES
  ("Ray","010-6861-4331","integer@google.com"),
  ("Ferdinand","010-2657-0364","cursus@naver.com"),
  ("Samuel","010-3035-4367","non.dapibus@google.com"),
  ("Gil","010-9942-9271","et.ipsum@naver.com"),
  ("Warren","010-3848-3532","ut@naver.com"),
  ("Daquan","010-3919-6644","nisl@google.com"),
  ("Caesar","010-5115-3152","morbi@google.com"),
  ("Byron","010-7744-8092","neque.vitae@naver.com"),
  ("Bert","010-6148-2824","vulputate@google.com"),
  ("Luke","010-7654-8254","velit.justo@google.com"),
  ("Hedley","010-3212-4835","purus.sapien@google.com"),
  ("Gareth","010-6775-8475","phasellus@google.com"),
  ("Mufutau","010-3245-8863","in@google.com"),
  ("Armand","010-7188-0340","faucibus.id.libero@google.com"),
  ("Emmanuel","010-4189-5946","nunc.laoreet.lectus@google.com"),
  ("Simon","010-8295-2038","non@google.com"),
  ("Eaton","010-7223-9878","gravida@naver.com"),
  ("Declan","010-8574-8800","ornare.sagittis.felis@google.com"),
  ("Sylvester","010-1520-2728","tellus@naver.com"),
  ("Logan","010-1315-2983","et.ultrices@naver.com");
INSERT INTO `members` (`name`,`phone`,`email`)
VALUES
  ("Ali","010-2843-7638","sem.egestas@naver.com"),
  ("Preston","010-3436-7196","ut@naver.com"),
  ("Upton","010-4079-6969","ultrices.vivamus@google.com"),
  ("Solomon","010-2294-1677","egestas.hendrerit@naver.com"),
  ("Ryan","010-0766-6580","risus@naver.com"),
  ("Tyler","010-4241-5666","volutpat.nunc@google.com"),
  ("Beau","010-4698-6487","mauris.suspendisse.aliquet@naver.com"),
  ("Thor","010-1977-5115","etiam.gravida@google.com"),
  ("Price","010-5753-8557","eget.volutpat@naver.com"),
  ("Hedley","010-3828-1500","malesuada@naver.com");
