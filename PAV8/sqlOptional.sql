CREATE TABLE CHART(
    artist_id number(38) primary key references artists(id),
    popularity number(38) not null
);


#################################################
TESTING SQL USED FOR THE HOMEWORK
#################################################
SELECT A.ID FROM ARTISTS A LEFT OUTER JOIN CHART C ON A.ID=C.ARTIST_ID WHERE C.ARTIST_ID=NULL;

(SELECT ID FROM ARTISTS)MINUS(SELECT ARTIST_ID FROM CHART);

SELECT A.ID,A.NAME,A.COUNTRY,C.POPULARITY FROM ARTISTS A JOIN CHART C ON A.ID=C.ARTIST_ID;

delete from albums;
delete from chart;
delete from artists;
COMMIT;
INSERT ALL
  INTO chart (ARTIST_ID, POPULARITY) VALUES (58, 58)INTO chart (ARTIST_ID, POPULARITY) VALUES (59, 59)
SELECT * FROM dual;

SELECT A.ID FROM ARTISTS A LEFT OUTER JOIN CHART C ON a.id=c.artist_id;