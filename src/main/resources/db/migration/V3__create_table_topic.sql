CREATE TABLE topic(
    id bigint not null,
    title varchar(50) not null,
    message varchar(300) not null,
    createdAt datetime not null,
    status varchar(50) not null,
    fk_course_id bigint not null,
    fk_author_id bigint not null,

    primary key(id),
    foreign key(fk_course_id) references course(id),
    foreign key(fk_author_id) references users(id)
);
