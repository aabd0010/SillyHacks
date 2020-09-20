show databases;
use sillyhacks;
show tables;

drop table QUESTIONS;

create table QUESTIONS(
	que_id int NOT NULL AUTO_INCREMENT,
    que_desc varchar(255),
    que_hint varchar(255),
    
    PRIMARY KEY (que_id)    
);

INSERT INTO QUESTIONS(que_desc,que_hint) VALUES ("If not blue, what color do you think the sky should be?", "blue?");

/* Display Table */
select * from QUESTIONS;
