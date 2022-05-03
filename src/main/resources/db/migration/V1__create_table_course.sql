CREATE TABLE course(
    id bigint not null auto_increment,
    name varchar(50),
    category varchar(50),
    primary key(id)
);

INSERT INTO course(id, name, category) VALUES(1, 'KOTLIN', 'PROGRAMACAO');