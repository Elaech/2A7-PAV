##############################################
ENVIRONMENT:
##############################################
Path to .java classes :
1.src/main/java/compulsory  - for the MAIN & compulsory classes
2.src/main/java/optional -for the MAIN & optional classes
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
Mai multe detalii se gasesc in cod sub forma de doc comments.
%%%%%%%%%%%%%%%%%%%%%%%
Final Remarks - Compulsory
%%%%%%%%%%%%%%%%%%%%%%%
Din cauza ca am folosit oracle 11g a trebuit sa modific tabelele deoarece nu exista autoincrement.
Am facut un sequence si un trigger de inserare ce foloseste acest sequence pentru a obtine ID. (acest lucru se face la ambele tabele pentru campul ID insa cu sequence-uri diferite)
-----------------------------------------------
 Optional:
-----------------------------------------------
In primul rand am convertit proiectul la un proiect maven si am stabilit dependencies si plugins cu Faker pe github
Am creat Controllerul pentru Chart ce se ocupa cu updateul si obtinerea de date din noua tabela Chart. Aceasta tabela a fost creeata nativ de mine pe contul specificat ulterior.
Gasiti in directorul curent tot ce am folosit din punct de vedere a sql nativ pentru testare si stabilire a mediului de lucru sub forma unui fisier numit sqlOptional.sql
Am creat pe urma dupa modelul problemei clasele Chart,Album si Artist cu implementarile cerute iar la Chart am adaugat metode de update pentru a da fetch la live data din controller direct.
Am modificat controllerele de Artist si Album pentru a lucra cu noile clase create in loc de raw data.
In main am generat pe urma 1k artisti(cu ajutorul faker) ce sunt si inserati in baza de date. Iar pe urma pentru acesti artisti am inserat si albume.
Dupa am creat un chart tot in main si am updatat topul artistilor cu popularitati(mai multe detalii in cod) random pentru a genera un top.
Dupa generarea topului, l-am preluat si am afisat la consola informatiile din acesta.
Mai multe detalii se gasesc in cod sub forma de doc comments.
%%%%%%%%%%%%%%%%%%%%%%%
Final Remarks - Optional
%%%%%%%%%%%%%%%%%%%%%%%
Am avut mare grija cu inchiderea de cursoare, statements si conexiuni pentru a nu genera erori. Am documentat aceste lucruri si in cod.
Nu am modificat mai deloc clasa singleton fata de cea din compulsory insa ea se regasesti si aici.