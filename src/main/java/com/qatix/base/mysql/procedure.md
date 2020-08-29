
CREATE PROCEDURE insert_person()
begin
    declare c_id integer default 1;
    while c_id<=100000 do
            insert into person values(c_id, concat('name',c_id), c_id+100, date_sub(NOW(), interval c_id second));
            set c_id=c_id+1;
    end while;
end

call insert_person();