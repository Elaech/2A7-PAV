##############################################
ENVIRONMENT:
##############################################
Path to .java classes :
1.src/compulsory  - for the MAIN & compulsory classes
2.src/optional -for the MAIN & optional classes
##############################################
EXPLANATION(RO)-+:
##############################################
----------------------------------------------
Compulsory:
----------------------------------------------
Am folosit oracle sql databases pentru a create si tine baza de date MusicAlbums. 
Am creat un user "padba" cu parola "pasql" (oracle nu permite folosirea numelui dba).
Am dat toate drepturile de editare acestui nou user.
M-am logat in contul nou creat si am facut cele 2 tabele specificate in laborator.
Am luat driverul pentru versiunea mea oracle 11g si l-am atasat JDK-ului folosit de Java.
Am creat clasa singleton Database ce face managing la conexiunea cu baza de date: daca nu exista sau e inchisa o creaza si apoi returneaza.
Am creat cele 2 DAO si am scris functiile ce lucreaza cu conexiunea la baza de date obtinuta din singleton.
Am implementat un test in main ce evidentiaza toate metodele scrise in DAOs.
%%%%%%%%%%%%%%%%%%%%%%%
Final Remarks - Compulsory
%%%%%%%%%%%%%%%%%%%%%%%
Din cauza ca am folosit oracle 11g a trebuit sa modific tabelele deoarece nu exista autoincrement.
Am facut un sequence si un trigger de inserare ce foloseste acest sequence pentru a obtine ID. (acest lucru se face la ambele tabele pentru campul ID)
***********************************************
Detalii mai multe de implementare si tratare(sau netratare in cazuri speciale) a erorilor se gasesc in cod sub forma de doc comments (romgleza sorry)
***********************************************
-----------------------------------------------
 Optional:
-----------------------------------------------

%%%%%%%%%%%%%%%%%%%%%%%
Final Remarks - Optional
%%%%%%%%%%%%%%%%%%%%%%%
