##############################################
ENVIRONMENT:
##############################################
Path to .java classes :
1.src/compulsory  - for the compulsory classes

##############################################
EXPLANATION(RO)-+:
##############################################
Compulsory:
----------------------------------------------
In primul rand: sorry pentru romgleza din cod. Am codat in 2 zile diferite si asta e rezultatul.
1.Pentru inceput am facut un o clasa de frame: MainFrame cu layout borderlayout pentru a pozitiona elementele dupa poz cardinale
2.Am facut clasa configuration panel (grid layout)(instanta ei este in main frame si se vede sus in frame) cu 3 butoane ce au variabile si listenere ce updateaza aceste variabile.
2.1.Butoanele sunt respectiv pentru marimea figurilor, numarul de muchii si culoare(negru sau random).
2.2.Butoanele au si niste labels sub e cu valorile lor ce sunt la fel updatate live cu valorile din input.
3.Am facut clasa canvas ce implementeaza tot panel (instanta ei este in main frame si se vede in mijlocul frameului). Pe aceasta am suprascris paintCompoment si am implementat mouse listener pentru
a putea desena. Instantele canvas necesita un config panel ca parametru in constructor, acesta este pentru a avea acess la optiuni.
3.1.Am adaugat metodele de save si load ce salveaza in directorul curent imaginea creata in canvas.
3.2.Initial nu se puteau crea decat poligoane in canvas.
3.3.Am adaugat si metoda exit ce inchide main frame-ul.
3.4.Am adaugat metoda reset ce deseneaza un dreptunghi mare alb pentru a reseta canvasul(el fiind alb initial)
4.Am creat clasa control panel(grid layout) ce implementeaza partea de jos a interfetei: adauga cele trei butoane save,load si exit ce vor implementa prin listeners metodele descrise in canvas mai sus.
***********************************************
Detalii mai multe de implementare si tratare(sau netratare in cazuri speciale) a erorilor se gasesc in cod sub forma de doc comments (romgleza sorry)
***********************************************
-----------------------------------------------
 Optional:(am marcat in cod unde este vorba de parte optionala a temei)(am tratat si erorile ce pot aparea din cauza IO -- a se vedea in cod)
-----------------------------------------------
1.Am revenit in primul rand la clasele configuration panel si control panel pentru a modificat layoutul de grid pentru a incapea inca cateva butoane.
1.2.Am adaugat butoanele, variabilele si listenerele pentru butoanele noi adaugate pentru a updata cum trebui valorile.
1.3. Butoanele ce apar noi in interfata pentru config. panel: 
 -Rubber ON/OFF(simple button) ce functioneaza ca un switch pt rubber mode (va cere si un color ca input prin JColorChooser)
 -Shape Selector (combo box) dropdown din care se poate selecta figura
1.4. Butoanele ce apar noi in interfata pentru control panel:
  -Save@Location si Load@Location care au acelasi comportament ca si save si load doar ca cu ajutorul unui JFileChooser si a unui JOptionPanel
acum end userul poate specifica un director si un nume pentru imaginea ce va fi salvata ca .png

2.Implementarea butonului de select shape este facuta cu ajutorul unei liste de stringuri si a unui combo box ce se comporta ca un meniu dropdown cu optiunile din lista.
In spate este un int ce is schimba valoare si este mapat la fiecare din figuri

3.Implementarea butonului de switch pentru rubber se face cu o variabila boolean si un if statement pentru schimbarea modului.
Pentru alegerea culorii radierii am optat la un JColorChooser.
In canvas cand se da click se va verifica in mouse listener daca rubber-ul e ON sau OFF pentru a stabili tipul de desenare.
In cazul rubberului activat aplicatia va desena la pozitia cursorului un patrat cu coloarea specificata rubberului si de marimea din size(config panel)

4.Implementarea Save@Location button se face cu un JFileChooser setat pentru a primi numai directoare ca input urmand a aparea un pop-up de scriere a numelui
ce este implementat prin JOptionPanel si prin care luam numele dorit. Construirea pathului este explicata detaliat in cod.

5.Implementarea Load@Location button se face tot cu un file chooser insa acum setat pentru a primi numai fisiere ca input urmand ca apoi sa se face pe input direct
operatiile IO.

%%%%%%%%%%%%%%%%%%%%%%%
Final Remarks
%%%%%%%%%%%%%%%%%%%%%%%
Am folosit clasa RegularPolygon din slideurile-cursului 6 de Programare Avansata. Este mentionat si in interiorul clasei.
Mentionez din cauza ca este posibil sa apara la plagiator.
Clasa Circle este implementata de mine.
Am inclus si o imagine de test in acest director.