create table BOOKS_AUD(
EVENT_ID int(11) not null auto_increment primary key,
EVENT_DATE datetime not null,
EVENT_TYPE varchar(10) default null,
BOOK_ID int(11) not null,
OLD_TITLE varchar(255),
NEW_TITLE varchar(255),
OLD_PUBYEAR int(4),
NEW_PUBYEAR int(4),
OLD_BESTSELLER boolean,
NEW_BESTSELLER boolean
);

create table READERS_AUD(
EVENT_ID int(11) not null auto_increment primary key,
EVENT_DATE datetime not null,
EVENT_TYPE varchar(10) default null,
READER_ID int(11) not null,
OLD_FIRSTNAME varchar(255),
NEW_FIRSTNAME varchar(255),
OLD_LASTNAME varchar(255),
NEW_LASTNAME varchar(255),
OLD_PESELID varchar(11),
NEW_PESELID varchar(11),
OLD_VIP_LEVEL varchar(20),
NEW_VIP_LEVEL varchar(20)
);

DELIMITER $$

create trigger BOOKS_INSERT after insert on books
for each row
begin
	insert into books_aud (EVENT_DATE, EVENT_TYPE, BOOK_ID, NEW_TITLE, NEW_PUBYEAR, NEW_BESTSELLER)
		values (CURTIME(), "INSERT", NEW.BOOK_ID, NEW.TITLE, NEW.PUBYEAR, NEW.BESTSELLER);
end $$

create trigger BOOKS_DELETE after delete on books
for each row
begin
	insert into books_aud (EVENT_DATE, EVENT_TYPE, BOOK_ID)
		values (CURTIME(), "DELETE", OLD.BOOK_ID);
end $$

create trigger BOOKS_UPDATE after update on books
for each row
begin
	insert into books_aud (EVENT_DATE, EVENT_TYPE, BOOK_ID, NEW_TITLE, NEW_PUBYEAR, NEW_BESTSELLER,
						   OLD_TITLE, OLD_PUBYEAR, OLD_BESTSELLER)
		values (CURTIME(), "UPDATE", OLD.BOOK_ID, NEW.TITLE, NEW.PUBYEAR, NEW.BESTSELLER,
				OLD.TITLE, OLD.PUBYEAR, OLD.BESTSELLER);
end $$

create trigger READERS_INSERT after insert on readers
for each row
begin
	insert into readers_aud (EVENT_DATE, EVENT_TYPE, READER_ID, NEW_FIRSTNAME, NEW_LASTNAME, NEW_PESELID, NEW_VIP_LEVEL)
		values (CURTIME(), "INSERT", NEW.READER_ID, NEW.FIRSTNAME, NEW.LASTNAME, NEW.PESELID, NEW.VIP_LEVEL);
end $$

create trigger READERS_DELETE after delete on readers
for each row
begin
	insert into readers_aud (EVENT_DATE, EVENT_TYPE, READER_ID)
	    values (CURTIME(), "DELETE", OLD.READER_ID);
end $$

create trigger READERS_UPDATE after update on readers
for each row
begin
	insert into readers_aud (EVENT_DATE, EVENT_TYPE, READER_ID, NEW_FIRSTNAME, NEW_LASTNAME, NEW_PESELID, NEW_VIP_LEVEL,
							 OLD_FIRSTNAME, OLD_LASTNAME, OLD_PESELID, OLD_VIP_LEVEL)
		values (CURTIME(), "UPDATE", OLD.READER_ID, NEW.FIRSTNAME, NEW.LASTNAME, NEW.PESELID, NEW.VIP_LEVEL,
				OLD.FIRSTNAME, OLD.LASTNAME, OLD.PESELID, OLD.VIP_LEVEL);
end $$

DELIMITER ;