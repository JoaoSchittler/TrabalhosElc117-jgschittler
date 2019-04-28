%Lista L contem N repetições do símbolo C.
repete(0, _, []).
repete(N, C, L) :- 
 N > 0,
 L = [C | T],
 N1 is N - 1,
 repete(N1, C, T).

%1
impar(N):- Aux is mod(N,2),Aux = 1.
%2
hasN(L,N):- L = [_|T],Aux is N-1,hasN(T,Aux).
hasN([],0).
%3
inc99(L1,L2):- L1 = [H1|T1],L2 = [H2|T2], H2 is H1 + 99,inc99(T1,T2).
inc99([],[]).
%4
incN(L1,L2,N):- L1 = [H1|T1],L2 = [H2|T2], H2 is H1+N,incN(T1,T2,N).
incN([],[],_).
%5 
comment(L1,L2):- L1 = [H1|T1],L2 = [H2|T2],string_concat("%%",H1,H2), comment(T1,T2).
comment([],[]).
%6 
par(X):- Aux is mod(X,2),Aux = 0.
onlyEven([],[]).
onlyEven(L1,L2):- L1 = [H1|T1],par(H1),L2 = [H2|T2],H2 is H1,onlyEven(T1,T2).
onlyEven([H1|T1],L2):- impar(H1),onlyEven(T1,L2).

%7 
countdown(0,[]).
countdown(N,L):- N >= 0, L = [H|T], H is N, N1 is N-1,countdown(N1,T).
%8 
nRandoms(0,[]).
nRandoms(N,L):- L = [H|T],random(0,2194214219642,H),Aux is N-1,nRandoms(Aux,T).
%9
potN0(-1,[]).
potN0(N,L):- L = [H|T], H is 2 ^ N, N1 is N-1, potN0(N1,T). 
%10
zipmult([],[],[]).
zipmult(L1,L2,L3):- L1 = [H1|T1],L2 = [H2|T2],L3 = [H3|T3], H3 is H2*H1,zipmult(T1,T2,T3).
%11
potencias2(0,[],_).
potencias2(N,L,N2):- L = [H|T], H is 2^(N2-N),Aux is N-1,potencias2(Aux,T,N2).
potencias(N,L):- potencias2(N,L,N).
%12
cedulas(_,[],[]).
cedulas(V,L1,L2):- L1 = [H1|T1], L2 = [H2|T2], H2 is div(V,H1),NewV is mod(V,H1),cedulas(NewV,T1,T2).


