create table participants
(
    user_id int not null references auto_user(id),
    post_id int not null references auto_post(id)
);