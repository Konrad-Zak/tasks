ALTER TABLE BOOKS ADD BESTSELLER BOOLEAN default false;

drop procedure if exists UpdateBestsellers;

DELIMITER $$

create procedure UpdateBestsellers()
begin
	declare BDR_ID, BOOKSREAD int;
	declare FINISHED int default 0;
	declare ALL_BOOKS cursor for select BOOK_ID from BOOKS;
    declare continue handler for not found set FINISHED = 1;
    open ALL_BOOKS;
    while (FINISHED = 0) do
		fetch ALL_BOOKS into BDR_ID;
        select count(*) from rents where BOOK_ID = BDR_ID into BOOKSREAD;
		if(BOOKSREAD > 2) then
				update books set BESTSELLER = true where BOOK_ID = BDR_ID;
				commit;
		end if;
    end while;
    close ALL_BOOKS;
end $$
DELIMITER ;    

call UpdateBestsellers();
select * from books;