create table if not exists price_history
(
    id      SERIAL PRIMARY KEY,
    before  BIGINT not null,
    after   BIGINT not null,
    created TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    auto_post_id int references price_history(id)
);