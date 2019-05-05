%Lista 2 Haskell em Prolog

%Exercício 1
isVowel(X):- member(X,['A','E','I','O','U','a','e','i','o','u']),!.
%Exercício 2
addComma([],[]).
addComma([H|T],[H2|T2]):- string_concat(H,",",H2),addComma(T,T2).
%Exercício 3
htmlListItems([],[]).
htmlListItems([H1|T1],[H2|T2]):- string_concat(H1,"</LI>",H3),string_concat("<LI>",H3,H2),htmlListItems(T1,T2).
%Exercício 4
semVogais2([],[]).
semVogais2([H1|T1],[H2|T2]):- not(isVowel(H1)),H2 = H1,semVogais2(T1,T2),!.
semVogais2([H1|T1],L2):- isVowel(H1),semVogais2(T1,L2),!.
semVogais(Str1,Str2):- string_chars(Str1,L1),semVogais2(L1,L2),string_chars(Str2,L2).
%Exercício 5
codifica2([],[]).
codifica2([H1|T1],[H2|T2]):- not(H1 = ' '),H2 = '-',codifica2(T1,T2).
codifica2([H1|T1],[H2|T2]):- H1 = ' ',H2 = ' ' ,codifica2(T1,T2).
codifica(Str1,Str2):- string_chars(Str1,L1),codifica2(L1,L2),string_chars(Str2,L2),!.
%Exercício 6
firstName([],[]).
firstName(Name,Firstname):-split_string(Name, " ", "", L),L = [H|_],Firstname = H.
%Exercício 7
isDigit(X):- member(X,['0','1','2','3','4','5','6','7','8','9']).
isInt2([]).
isInt2([H1|T1]):- isDigit(H1),isInt2(T1),!.
isInt(Str):- string_chars(Str,L),isInt2(L).
%Exercício 8
last([X],X).
last([_|T],X):- last(T,X),!.
lastName(Name,Lastname):- split_string(Name, " ", "", L),last(L,Lastname).
%Exercício 9
%> userName "Anderson Silva"
%"asilva"
%> userName "JON BON JOVI"
%"jjovi"
%Exercício 10
encode('a','4').
encode('A','4').
encode('e','3').
encode('E','3').
encode('i','2').
encode('I','2').
encode('o','1').
encode('O','1').
encode('u','0').
encode('U','0').
encodeName2([],[]).
encodeName2([H1|T1],[H2|T2]):- isVowel(H1),encode(H1,H2),encodeName2(T1,T2).
encodeName2([H1|T1],[H2|T2]):- not(isVowel(H1)),H2 = H1,encodeName2(T1,T2).
encodeName(Str1,Str2):- string_chars(Str1,L1),encodeName2(L1,L2),string_chars(Str2,L2),!.
