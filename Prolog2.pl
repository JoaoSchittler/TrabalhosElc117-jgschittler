% Exercício 1

ismember(A,L):- L = [H|T], A = H.
ismember(A,[_|T]):-  ismember(A,T).

% Exercício 2

ao_lado(X,Y,L):- nextto(X,Y,L).
ao_lado(X,Y,L):- nextto(Y,X,L).

% Exercício 3

um_entre(X,Y,L):-  nextto(X,Z,L),nextto(Z,Y,L).
um_entre(X,Y,L):-  nextto(Y,Z,L),nextto(Z,X,L).

% Exercício 5

solucao(Casas):- 
 Casas = [_,_,_,_,_],
 member(casa(milton,vermelho,_,_,_),Casas),
 member(casa(walter,_,radio,_,_),Casas),
 member(casa(_,verde,_,_,pesca),Casas),
 member(casa(rui,_,_,_,futebol),Casas),
 ao_lado(casa(_,verde,_,_,_),casa(_,branco,_,_,_),Casas),
 member(casa(_,_,altimetro,leite,_),Casas),
 member(casa(_,preto,_,cerveja,_),Casas),
 member(casa(_,vermelho,_,_,natacao),Casas),
 [casa(farfarelli,_,_,_,_),_,_,_,_] = Casas,
 ao_lado(casa(_,_,_,cafe,_),casa(_,_,hidraulico,_,_),Casas),
 ao_lado(casa(_,_,_,cerveja,_),casa(_,_,bussola,_,_),Casas),
 member(casa(_,_,_,cha,equitacao),Casas),
 member(casa(nascimento,_,_,agua,_),Casas),
 ao_lado(casa(farfarelli,_,_,_,_),casa(_,azul,_,_,_),Casas),
 um_entre(casa(_,_,hidraulico,_,_),casa(_,_,altimetro,_,_),Casas),
 member(casa(_,_,_,_,tenis),Casas),
 member(casa(_,_,temperatura,_,_),Casas).

% Exercício 6

positivos1([],[]).
positivos1([H|T],L) :- H > 0, positivos1(T,Resto), L = [H|Resto].
positivos1([H|T],L) :- H =< 0, positivos1(T,L).

%Faz o head tail antes de chamar o predicado(fazer uma busca), um pouco mais eficiente. 
positivos2([],[]).
positivos2([H|T],L) :- H > 0, L = [H|Resto], positivos2(T,Resto).
positivos2([H|T],L) :- H =< 0, positivos2(T,L).

% Exercício 7
%Busca, depois compara.
largest1([X],X).
largest1([X|Xs],X) :- largest1(Xs,Y), X>=Y.
largest1([X|Xs],N) :- largest1(Xs,N), N>X.
%Compara, depois busca.
largest2([X|Xs], N) :- aux(Xs, X, N).
aux([], N, N).
aux([X|Xs], M, N) :-
   M1 is max(X, M),
   aux(Xs, M1, N).

