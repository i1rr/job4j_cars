create table vehicle_history (
    vehicle_id int not null references vehicle(id),
    driver_id int not null references driver(id),
    startAt timestamp,
    endAt timestamp
);
