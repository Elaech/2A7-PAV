##############################################
ENVIRONMENT:
##############################################
Path to .java classes :
1.SERVER:
1.1.Compulsory: SERVER/src/compulsory
1.2.Compulsory: SERVER/src/optional
2.Client:
2.1. Compulsory+Optional: CLIENT/src/compulsory
2.2. Copie pentru client pentru rulat in paralel: CLIENT - copy/src/compulsory
##############################################
EXPLANATION(RO)-+:
##############################################
----------------------------------------------
Compulsory:
----------------------------------------------
1.Am creat proiectul de server.
2.Am creat clasa GameServer ce creeaza socketul la un port anumit si accepta conexiuni de la clienti.
Pentru fiecare client se va facut un nou thread ce ii va deservi.
3.Am creat clasa Client thread ce extinde Thread si este folosita in interactiunea cu un client.
Primeste comenzi nelimitat de la client, il anunta ca le primeste; daca comanda este "exit" va incheia conexiunea cu clientul anuntandu-l.
4.Am creat proiectul de client.
5.Am facut conexiunea catre server localhost la portul respectiv.
Cream un bufferedreader pentru consola pentru a lua inputul utilizatorului.
Inputul este transmis linie cu linie catre server.
Outputul primit de la server va fi afisat la consola.
Daca clientul primeste comanda de la server de terminare, anunta la consola si se inchide pe urma.

###############################################
Mentiuni finale
###############################################
Din greseala am suprascris clientul compulsory ce cel optional my bad :(.
Mai multe detalii despre functionalitati in comentarii in cod

-----------------------------------------------
Optional:
-----------------------------------------------
1.Am creat package-ul pentru optional la server.
2.Am recreat clasa GameServer: acum ea are si o instanta  clasei Game pe care se vor juca jucatorii.
Daca nu mai exista jucatori, atunci va reseta jocul pentru a permita jucatul al nesfarsit.
3.Am recreat clasa ClientThread: acum contine elementele de sincronizare si validare ale comenzilor pentru jucatori.
Monitorieaza lucruri precum: joc deja in desfasurare (cazul in care vine si al 3lea client, insa maxim sunt 2), numarul de ordine al mutarii (aka semafor),
mutari valide, mutarea corecta, comenzi de ajutor cum ar fi vizualizarea tabelei sau a faptului daca este tura playerului.
La final analizeaza instanta de joc pentru a da un aftermath.
Am implementat deconectarea cu "exit" in timpul jocului si ambii jucatorii vor fi informati de incheierea partidei si de cine s-a deconectat.
4.Am creat clasa Game: in mare parte o clasa cu variabile volatile pentru real time data, furnizeaza metode pentru lucrul cu tabla jocului dar si de validare a anumitor actiuni.
Are elemente ce verifica starea jocului pentru un mai usor control din ClientThread. Tot ea furnizeaza o metoda de verificare si ocupare a locului unui jucator pentru jocul curent.
5.Am creat clasa Player ce tine informatiile unui jucator, mai exact simbolul cu care va juca cat si numarul de ordine(pt semafor).
6.Am creat clasa Board ce contine bordul jocului si starea acestuia. Tot ea detine si metode de verificare si schimbare a propriei stari.
###############################################
Mentiuni finale
###############################################
Din greseala am suprascris clientul compulsory ce cel optional my bad :(.
Mai multe detalii despre functionalitati in comentarii in cod