##############################################
ENVIRONMENT:
##############################################
Path to .java classes:
1.All classes made by me : src/com/compulsory/ | src/app
2.Resources : src/res
##############################################
EXPLANATION(RO)-+:
##############################################
----------------------------------------------
Compulsory:
----------------------------------------------
1. Am creat pachetul res ce contine Resource Bundleul Messages pt default-en-US si ro-RO.
Inafara de valorile din laborator am adaugat inca cate care am considerat ca sunt necesare pentru a schimba complet limbajul aplicatiei


2.Am folosit patternul command, interfata comanda de baza fiind LocaleCommand(ce are metdota execute) iar comenzile fiind:
2.1. Error ce va trata cazul de eroare a inputului 
2.2. Info ce va afisa informatii ca in enunt despre limba selectata la momentul respectiv 
2.3. SetLocate ce va seta locale intr-o regiune cu o anumita limba, insa doar en_US sau ro_RO.
2.4. DisplayLocale ce va afisa toate valorile locale disponibile

3. Comenzile ce pot fi executate de catre user se afla in LocaleExplore si sunt tratate folosind patternul
Comenzile accesibile sunt cele de mai sus + quit en (iesire in RO)

4. Am creat clasa ce se ocupa cu preluarea parametrilor din bundle: LocaleManager
5. Toate comenzile, cuvintele etc.... se transforma in functie de locale ro-RO sau en-US. 
