##############################################
ENVIRONMENT:
##############################################
Path to .java classes (spring project):
1.All classes made by me : src/main/java/com/PAV11/lab/{controllers/errors/exceptions/models/repository/services}
2.App configuration for https and spring : src/main/resources/app.properties
3. Https generated false certificate : src/main/resources/keystore/demo.p12
##############################################
EXPLANATION(RO)-+:
##############################################
----------------------------------------------
Compulsory & Optional:
----------------------------------------------
1.Am initializat proiectul folosing Spring Initializr. 
1.1.Am ales dependintele:
1.2.H2 (baza de date in ram ce se sterge la terminarea aplicatiei, am vrut sa vad cat de greu de gestionat este pentru test, turns out pretty easy)
1.3.Spring Data JPA pentru layerul de persistence
1.4.Spring Repositories pentru layerul de repository.
2.Am creat modelele pentru game si player marcandule ca entities pentru repository, am folosit si functionalitatea de ID si generated value pentru a tine jocurile si playerii unici.
3.Am creat cele 2 repositories pentru games si player, nu am adaugat nicio metoda noua deoarece cerintele temei respectau standardul CRUD deja existent.
4.Am creat layerul de service pentru Players cat si pentru Games: acesta este format de operatiile ce se pot executa asupra repositoriului fiecarei entitati.
5.Am creat si controllerele aferente serviciilor pt player si games, ce expun un API REST corespunzator cerintelor.
6.Pentru a trata erorile ce pot aparea in timpul executiei am creat urmatoarele:
6.1. Un nou tip eroare ce va fi tratata ca si componenta a API-ului.
6.2. Un nou tip de exceptie ce va avea ca response status 404.
6.3. Un controller, componenta RestControllerAdvice, pentru exceptii ce trateaza exceptiile de tipul declarat de mine si returneaza prin API mesajul erorii generate sub forma erorii definite de mine impreuna cu statusul 404.
7.Pentru a securiza aplicatia cu protocolul HTTPS, am creat cu keytool o noua cheie https care am pus-o in resorces/keystore/demo.p12. Am refacut configuratia aplicatiei din application.properties pentru a permite utilizarea cheii.
8.Am testat continuu pe decursul dezvoltarii diverste tipuri de inputuri folosind POSTMAN si din ce am observat functioneaza decent.
###############################################
Mentiuni finale
###############################################
Am considerat ca Compulsory si Optional sunt foarte apropiate una de cealalta asa ca le-am explicat mai sus deodata.
Mai multe detalii de implementare in cod.
Dependintele sunt trecute in gitignore deci pentru a verifica proiectul va trebui asteptata configurarea initiala.