CREATE TABLE answer(
    id bigint not null auto_increment,
    message varchar(300) not null,
    created_at datetime not null,

    author_id bigint not null,
    topic_id bigint not null,
    solved int(1),

    primary key(id),
    foreign key(author_id) references users(id),
    foreign key(topic_id) references topic(id)
);