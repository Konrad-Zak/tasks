create table STATS(
STAT_ID INT(11) auto_increment PRIMARY KEY,
STAT_DATE DATETIME NOT NULL,
STAT VARCHAR(20) NOT NULL,
`VALUE` INT(11) NOT NULL
);

create view BESTSELLERS_COUNT as
	select count(*) as NUMBER_OF_BESTSELLER from books
	 where BESTSELLER = true;

use KODILLA_COURSE;

DELIMITER $$

create event UPDATE_BESTSELLER
	on schedule every 1 minute
        do
		begin
        	declare quantity int(11);
        	call UpdateBestsellers();
        	select NUMBER_OF_BESTSELLER from BESTSELLERS_COUNT into quantity;
        	insert into stats(STAT_DATE, STAT, `VALUE`)
			values(curtime(), "BESTSELLERS", quantity);
		end $$

DELIMITER ;
