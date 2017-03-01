Nicula Catalin George
Automatica si Calculatoare

Pentru a rezolva problema am inceput prin implementarea interfetelor in clase pe 
care le-am pus intr-un fisier separat. Am implementat clasele a 2 tipuri de
operatori unari(sqrt, Log) si binari(adunare, scadere, etc.) realizand pe cazuri
optiunile metodei calculate. 

Pentru convertirea din numere arabe in numere romane m-am inspirat dintr-un algorit
gasit pe internet facand o enumerare cu numele Roman table de unde am extras 
caracterele necesare pentru scrierea symbolurilor noi. Iar pentru convertirea din
roman in arab am folosit un algoritm propriu, luand pe rand fiecare litera din
simbolul roman si atribuindu-i o valoare pe care o adaugam la o suma, verificand 
daca litera precedenta avea o valoare mai mica, situatie in care scadeam valoarea
acesteia de doua ori din suma(o data pt ca am adunat-o si o data pt numarul roman).

Dupa ce am realizat comvertirile, am implementat forma poloneza si algoritmul de
calcul al expresiilor scrise in forma polneza. 

In metoda publish am analizat tokenurile primite si in functie de acestea am 
respectat regulile din forma poloneza, construind-o pe aceasta intr-o lista de
tokens si folosind in paralel o stiva de tokens.

In metoda calculate din server am urmat algoritmul de calcul dat, luand pe rand
tokenuri din lista care reprezenta forma post-fixata. Cand intalneam un element
l-am transformat direct in numar arab, float si l-am bagat intr-o stiva de 
tip double din care le scoteam si aplicam operatiile date conform algoritmului.

Din metoda publish am apelat direct metoda calculate la sfarsitul careia am
adaugat rezultatul(elementul ramas in stack) in lista de rezultate sub forma
romana.

De mentionat faptul ca a fost necesara folosirea unei variabile double in care
am tinut resturile impartirilor numerelor la 1 (ce e dupa virgula) deoarece faceam
conversia in roman iar apoi in arab unde foloseam floor si aceste valori se 
pierdeau. Asa ca in operand am introdus Double leftOvers pe care il incarcam
cu resturile in metoda calculate din Operatori.

De asemenea a trebuit sa le adaug numerelor negative cu valori dupa virgula 
un -1 eoarece floor nu facea rotunjirea bine, lucru pe care l-am facut inaintea
inscrierii rezultatului in lista de rezultate
