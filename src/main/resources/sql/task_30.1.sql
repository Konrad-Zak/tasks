drop function if exists FindReader;
DELIMITER $$
create function FindReader(readerId int) returns varchar(255) deterministic
begin
	declare result varchar(255) default 'Wrong id reader';
    if readerId > 0 then
		select FIRSTNAME from readers where READER_ID = readerId into result;
    end if;
    return result;
end $$
DELIMITER ;
select FindReader(3) as READER_NAME;
