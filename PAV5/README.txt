##############################################
ENVIRONMENT:
##############################################
Path to .java classes :
1.src/compulsory  - for the compulsory classes
2.src/Optional - for the optional classes

Path to .jar FILE for the Optional:
1.out/artifacts/PAV5_jar

##############################################
EXPLANATION(RO)-+:
##############################################
Compulsory:
----------------------------------------------
1.Am creat clasa Document in asa fel inca nu se poate instantia decatdandui un path valid din sistemul din fisiere (testand acest lucru).
ID-ul si Numele sunt date de calea catre fisier si sunt generate in constructor.
Am dat override si la toString() pentru o afisare mai frumoasa.

2.Am creat clasa Catalog cu posibilitatea instantelor de:
A. a retine mai multe Documente (a simple list)
B. a se salva singure serializat intr-un fisier extern (object serialised buffer)
C. a se incarca singure (stricand datele existente) dintr-un fisier extern (object serialised buffer)
D. a edita documente dupa numa care sunt prezente in catalogul incarcat curent (desktop class)
***********************************************
Detalii mai multe de implementare si tratare(sau netratare in cazuri speciale) a erorilor se gasesc in cod sub forma de doc comments
***********************************************
-----------------------------------------------
 Optional:
-----------------------------------------------
1.Am revenit asupra clasei Catalog si:
A. am implementat o optiune de save a instantelor in plaintext
B. am implementat o optiune de incarcare a instantelor din plain text (doar plain textul generat de functia anterioara)

2.Am creat o clasa Shell care simuleaza un terminal pentru comenzile: quit,load,list,view,html

3.Fiecare comanda din terminal apeleaza metode statice din clasele de Comenzi: ViewCommand,ListCommand,LoadCommand,RepHTMLCommand:
A. Aceste clase fac parte din ierarhia de clase Command (interfata implementata de mine)
B. Metodele statice apeleaza in mare parte metode din clasa catalog si verifica niste exceptii la datele introduse

4.Commanda html ce apeleaza la RepHTMLCommand rezolva aceasta cerinta si este mai bine de vizualizat in cod pentru implementare

5.1.Am implementat din start in clasele Document si Catalog verificari pentru unicitate si validitate a datelor si ca cailor din sistemul de gestiune a fisierelor
5.2. Am tratat si exceptiile ce pot fi aruncate de metode IO si descriptori si am lasat netratate anumite erori ce tin de rularea cu extensiile corecte ale programului
(la 4 si 5 mai multe detalii in doc comments in cod)

6. Arhiva .JAR se gaseste compilata la adresa specificata mai sus. Atentie ca are nev de terminal pentru a rula(alfel va da eroare) (comanda JAR)