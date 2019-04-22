% Exercícios de haskell em prolog

% Prática 1
% Ex 1
 sumsquares(X,Y,R):- R is X*X + Y*Y.
% Ex 2
 hasEqHeads([H1|_],[H2|_]):- H1 = H2.
% Ex 3
 makesuper(N,R):- append([S,u,p,e,r,_],N,R).


% Ex 4 (Acredito que não funciona).
 numspaces([Head|Tail],N):- isSpace(Head),numspaces(Tail,Aux),N is 1+Aux. 
 numspaces([],0).

% Ex 5 
 applyeq([H|T],X):- X is H*H*3 + 2/H + 1,applyeq(T,Y). 
% Ex 6
 ltz([H|_],X):- H < 0,X = H.
 ltz([_|T],X):- getltz(T).  
% Ex 7
 interv([H|_],X):- H>=1,H<=100,X = H.
 interv([_|T],X):- getinterv(T,X).

% Ex 8
 newer([H|_],X):- Aux is 2019-H,Aux>1980,X = H. 
 newer([_|L],X):- getnewer(L,X).

% Ex 9
 even([H|_],X):- 0 is mod(H,2),X = H.
 even([_|T],X):- geteven(T,X).
% Ex 10
 ismember(C,[H|_]):- C = H.
 ismember(C,[_|T]):- ismember(C,T).
% Ex 11
 last(X,[_|T]) :- last(X,T).
 last(X,[X]).
 nameswithenda([H|_],X):- last(a,H),X = H.
 nameswithenda([_|T],X):- nameswithenda(T,X).
 

  
 
