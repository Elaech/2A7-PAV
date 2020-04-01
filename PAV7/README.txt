##############################################
ENVIRONMENT:
##############################################
Path to .java classes :
1.src/compulsory  - for the MAIN & compulsory classes
2.src/optional -for the MAIN & optional classes
##############################################
EXPLANATION(RO)-+:
##############################################
Compulsory:
----------------------------------------------
Am creat clasele specificate Token, Board, Player si Game.

1.Clasa Token:
	Are 2 campuri ce spun daca este sau nu un wild card si daca nu este wildcard atunci ce numar are.
	Initializarea se face cu sansa de 10% pentru wildcard si 90% pentru un numar random intre 0 si m-ul specificat in enunt.
	Am creat getters pentru campurile mentionate si o metoda toString(ce a fost folosita la testare, nu are alta implicare)
2.Clasa Board:
	Tine o lista de tokens. Aceasta este initializata cu n tokeni de marime n si este de tip synchronized list pentru a putea lucra concurent cu aceasta.
	Metoda printTokens() este folosita pentru testare insa cred ca cerinta implica o redare a jocului.
	Metoda isEmpty() verifica daca mai sunt tokeni de luat.
	Metoda getToken este synchronized pentru a nu permite accesul simultan a 2 threaduri la ea, alfel doi playeri ar putea lua acelasi token si sterge pe acelasi ceea ce va cauza erori. Lucru care l-am testat cu ajutorul exceptiei aruncate de mine din metoda. Functionalitatea este de a lua un token la o pozitie si de al si sterge pe urma.

3.Clasa Player:
	Are nume, o referinta la bordul cu tokeni pe care va juca, marimea progresiei pe care trebuie sa o creeze si propria lista cu tokeni alesi.
	Variabila gameFinished este o variabila care spune ca jucatorul nu va mai folosi functia getToken de acum. Folositoare pentru Game pentru a sti din timp de incheierea jocului.
	Variabila won este pentru a verifica daca un jucator a castigat sau nu iar cea de terminated este folosita pentru a sti cand threadul playerului se termina.
	Campurile de mai sus au diferite set-ere si get-ere . O functie folosita la afisare si testare este printProgression ce va afisa progresia castigatoare din cadrul unui player atunci cand acesta o va construi.
	Metoda isProgression vefica daca de la pozitia indicata se formeaza o progresie, de marimea indicata in constructor, in lista de tokene preluate de player.
	Metoda hasArithmeticProgression verifica daca exista vreo expresie aritmetica in tokenii alesi de player.
	Metoda addToken ia mereu primul token din BOard si il adauga la playerul respectiv(f important ca e sync functia din board).
	Ignore la exceptie se face pentru cand tabloul devine gol in timp ce unul din playeri e in addToken insa nu a ajuns la getToken(din Board) dar a depasit pragul de testare a bordului.
	Se face sortarea tokenilor dupa number sau daca sunt wildcards se pun la inceput iar dupa se verifica existenta progresiei.
	PrintTokens() este folosita pentru a afisa tokenii detinuti de player.
	run() suprascrisa pentru a lucra cu threads si doar adauga tokeni pana cand jocul este gata, ulterior marcand threadul ca finished.
4.Game
	Creeaza cei 2 playeri, board-ul in constructor si incepe jocul in start() creeand threadurile. Apoi intra intr-o bucla de asteptare a incheierii jocului ce se termina fie cand cineva a gasit o progresie.
	Este posibil ca cei 2 playeri sa gaseasca o progresie aritmetica la scurt timp fata de celelalt iar Game sa nu fi verificat decat dupa ce au terminat amandoua ceea ce va rezulta intr-un Draw.
	Functia isGameFinished este folosita pentru a verificat terminarea jocului cu cazurile : amandoi castiga, castiga primul ,castiga al doilea sau bordul e gol.
	Inainte de a afisa rezultatul final se asigura ca ambii playeri au valoarea terminated pe true pentru a nu se mai face afisari pe urma.
	In main doar se creeaza o instanta a jocului si se porneste cu functia start().


%%%%%%%%%%%%%%%%%%%%%%%
Final Remarks
%%%%%%%%%%%%%%%%%%%%%%%
Afisarea din timpul jocului nu este comform scurgerii timpului deci nu playerul ce castiga primul pe afisare castiga si jocul (e greu de monitorizat temporal asta de aici deoarece lasam 2 playeri controlati de calculator sa joace ceea ce face timpul unei mutari la nivel de nano-secunde (am testat cu thread.sleep)). Deci ca si concluzie afisarea este pentru a vedea mutarile nu ordinea lor.
 
***********************************************
Detalii mai multe de implementare si tratare(sau netratare in cazuri speciale) a erorilor se gasesc in cod sub forma de doc comments (romgleza sorry)
***********************************************
-----------------------------------------------
 Optional:
-----------------------------------------------


%%%%%%%%%%%%%%%%%%%%%%%
Final Remarks
%%%%%%%%%%%%%%%%%%%%%%%
