% Exercícios de haskell em prolog
% Prática 1
% Ex 1 
 sumsquares(X,Y,R):- R is X*X + Y*Y.
% Ex 2
 hasEqHeads([H1|_],[H2|_]):- H1 = H2.
% Ex 3
 makesuper(Str,R):- append([s,u,p,e,r,-],Str,R).
% Ex 5 
 mapeq([],[]).
 mapeq([H|T],[H2|T2]):- H2 is H*H*3 + 1,mapeq(T,T2).
% Ex 6
 ltz(X):- X < 0.
 filterltz([],[]).
 filterltz([H1|T1],[H2|T2]):- H1 < 0,H2 is H1, filterltz(T1,T2).
 filterltz([H1|T1],L2):- H1>=0,filterltz(T1,L2).
% Ex 7
 interv(X):- X>=1,X=<100.
 filterinterv([],[]).
 filterinterv([H1|T1],[H2|T2]):- interv(H1),H2 is H1, filterinterv(T1,T2).
 filterinterv([H1|T1],L2):- not(interv(H1)),filterinterv(T1,L2).
% Ex 8
 newer(X):- Aux is 2019 - X, Aux > 1980.
 filternewer([],[]).
 filternewer([H1|T1],[H2|T2]):- newer(H1),H2 is H1, filternewer(T1,T2).
 filternewer([H1|T1],L2):- not(newer(H1)),filternewer(T1,L2).
% Ex 9
 even(X):- 0 is mod(X,2).
 filtergeneric([],[],_).
 filtergeneric([H1|T1],[H2|T2],Func):- call(Func,H1),H2 is H1, filtergeneric(T1,T2,Func).
 filtergeneric([H1|T1],L2,Func):- not(call(Func,H1)),filtergeneric(T1,L2,Func).
% Ex 10
 ismember(C,[H|_]):- C = H.
 ismember(C,[_|T]):- ismember(C,T).
