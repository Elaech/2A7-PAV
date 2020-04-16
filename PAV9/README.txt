##############################################
ENVIRONMENT:
##############################################
Path to .java classes :
1.src/main/java/directorul din problema dorit
##############################################
EXPLANATION(RO)-+:
##############################################
----------------------------------------------
Compulsory:
----------------------------------------------
Am creat proiectul cu maven.
Am adaugat dependintele pentru Java Persistence si Hibernate in pom.xml
M-am conectat la baza de date oracle create in laboratorul anterior.
Am creat in resources fisierul hibernate.cfg.xml ce contine configuratia si maparea obiectelor pentru baza de date oracle.
Am creat folderul META-INF tot in resources ce contine fisierul persistence.xml ce contine persistence unitul format din: configuratia pentru oracle si providerul hibernate.
Am creat package-ul entity in care am storat cele 3 entitati generate automat prin intermediul hibernate pentru cele 2 tabele din baza de date:album,artist.
Am creat package-ul util si clasa singleton PersistenceUtil ce da manage al o instanta de EntityManagerFactory.
Am creat repository-urile cu metodele cerute pentru Artists si Albums.
Am creat package-ul app unde exemplific metodele implementate.

-----------------------------------------------
Optional:
-----------------------------------------------
Am adaugat in packageul entities o noua entitate charts.
Am creat AbstractRepository in care am implementat cateva metode din CRUD. Din cauza ca nu am reusit sa implementez queries over generics am sfarsit prin a face un switch (a se vedea in cod).
Am considerat ca pentru entitatea de charts metodele ce provin din AbstractRepository sunt exact cele ce trebuiau implementate apriori deci nu exista nimic in plus fata de AbstractRepo aici.


-----------------------------------------------
Bonus:
-----------------------------------------------
Am adaugat o coloana noua in baza de date la tabelul albums: alter table ALBUMS add column GENRE varchar2(200);
De acum fiecare album va avea si un gen de muzica asociat.
Am adaugat in pom.xml dependecy pentru Faker.
Am creat o noua metoda in AlbumsRepository ce ia ID-urile ale artistilor curenti si adauga cate un album (cu nume si gen generate cu faker) pentru fiecare ID de artist.