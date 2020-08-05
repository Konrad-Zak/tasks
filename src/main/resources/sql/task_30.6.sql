create index BOOK_TITLE_INDEX on books (TITLE);

explain select * from readers where FIRSTNAME = "John";

create index READER_FULLNAME_INDEX on readers (FIRSTNAME, LASTNAME);

explain select * from readers where FIRSTNAME = "John";