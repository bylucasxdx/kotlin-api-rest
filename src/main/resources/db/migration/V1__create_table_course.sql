CREATE TABLE course(
    id bigint not null,
    name varchar(50),
    category varchar(50),
    primary key(id)
);

INSERT INTO course(id, name, category) VALUES(1, 'KOTLIN', 'PROGRAMACAO');