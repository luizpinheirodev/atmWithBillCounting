DROP TABLE IF EXISTS bill_dispenser;

CREATE TABLE bill_dispenser (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  value INT NOT NULL,
  quantity INT  NOT NULL
);

insert into bill_dispenser (value, quantity) values (10, 20);
insert into bill_dispenser (value, quantity) values (20, 20);
insert into bill_dispenser (value, quantity) values (50, 20);
insert into bill_dispenser (value, quantity) values (100, 20);
