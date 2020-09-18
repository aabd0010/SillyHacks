show databases;
use sillyhacks;
show tables;
drop table ANSWERS;

create table ANSWERS(
	ans_id int NOT NULL AUTO_INCREMENT,
    ans_desc varchar(255),
    ans_image varchar(255),
    ans_comment varchar(255),
    
    PRIMARY KEY (ans_id)    
);

INSERT INTO ANSWERS(ans_desc,ans_image,ans_comment) VALUES ("Roses are red, violets are blue, the sky should always be blue","https://i.pinimg.com/736x/d6/3e/dd/d63edd9af879f866baea5e3c5b506959.jpg", "Even my cat knows that");

/* Display Table */
select * from ANSWERS;