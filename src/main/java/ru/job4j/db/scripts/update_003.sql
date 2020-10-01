create table if not exists items
(
    id          serial primary key,
    name        varchar(2000),
    created     bytea,
    description varchar(255)
);