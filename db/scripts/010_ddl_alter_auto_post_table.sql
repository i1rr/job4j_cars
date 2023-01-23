alter table auto_post
    add column vehicle_id int unique references vehicle (id)