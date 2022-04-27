CREATE TABLE users(
    id bigint not null,
    name varchar(50),
    email varchar(50),
    primary key(id)
);

INSERT INTO users(id, name, email) VALUES(1, 'Lucas Medeiros', 'lucas.medeiros@teste.com.br');