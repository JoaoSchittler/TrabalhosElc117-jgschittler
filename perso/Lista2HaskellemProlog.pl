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
%Não estou conseguindo fazer as questões que necessitam da mudança de chars numa string
codifica2([],[]).
codifica2([H1|T1],[H2|T2]):- not(H1 = ' '),H2 = '-',codifica2(T1,T2).
codifica2([H1|T1],[H2,T2]):- H1 = ' ',H2 = ' ' ,codifica2(T1,T2).
%concat([],"").
%concat([H|T],Str):- T = [H2|T2],string_concat(H,H2,Str),concat(T2,Str). 
codifica(Str1,Str2):- string_chars(Str1,L1),codifica2(L1,L2),string_chars(Str2,L2).%,concat(L2,Str2).
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
%%Exercício 10
%Escreva uma função encodeName :: String -> String que substitua vogais em uma string, conforme o esquema a seguir: a = 4, e = 3, i = 2, o = 1, u = 0.
%>% encodeName "Anderson Silva"
%"4nd3rs1n s2lv4"
%> encodeName "Badtz Maru"
%"B4dtz M4r0"
%Exercício 11
%Escreva uma função betterEncodeName :: String -> String que substitua vogais em uma string, conforme este esquema: a = 4, e = 3, i = 1, o = 0, u = 00. Exemplos de uso da função:

%> betterEncodeName "Anderson Silva"
%"4nd3rs0n s1lv4"
%> betterEncodeName "Badtz Maru"
%"B4dtz M4r00"
%Dada uma lista de strings, produzir outra lista com strings de 10 caracteres, usando o seguinte esquema: strings de entrada com mais de 10 caracteres são truncadas, strings com até 10 caracteres são completadas com '.' até ficarem com 10 caracteres. Exemplo:

%> func ["palavras","paralelas","pedal","paralelepipedo"]
%["palavras..","paralelas.","pedal.....","paralelepi"]

