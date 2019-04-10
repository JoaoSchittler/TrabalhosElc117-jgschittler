% Exemplo de programa em Prolog que define
% fatos e regras sobre pessoas e localizacoes geograficas.

localizado_em(santa_maria, rs).
localizado_em(salvador, bahia).
localizado_em(rs, brasil).
localizado_em(bahia, brasil).
localizado_em(paris, franca).
localizado_em(franca, europa).

nasceu_em(andre, santa_maria).
nasceu_em(joao, salvador).
nasceu_em(X, Y) :- localizado_em(Z, Y), nasceu_em(X, Z).

mora_em(andre, paris).
mora_em(joao, salvador).
mora_em(X, Y) :- localizado_em(Z, Y), mora_em(X, Z).

idade(andre, 25).
idade(joao, 32).

gaucho(X) :- nasceu_em(X, rs).
brasileiro(X) :- nasceu_em(X, brasil).
europeu(X) :- nasceu_em(X, europa).

% Exercício 1
%
% europeu(andre).
% false.
%
% gaucho(andre).
% true.
%
% brasileiro(X).
% X = andre;
% X = joao.
%
% Exercício 2

%?- nasceu_em(joana,salvador).
%?- idade(joana,22).
%?- nasceu_em(michel,paris),idade(michel,40).

% Exercício 3
%"José nasceu no Brasil?"
%"Quais são as pessoas nascidas na Europa?"
%"Quais são as pessoas com mais de 30 anos?"
%"Quem são os brasileiros com menos de 30 anos?"
% ?- nasceu_em(joao,brasil).
% ?- nasceu_em(X,europa).
% ?- idade(X,I),I>30.
% ?- idade(X,I),I=<30.

% Exercício 4

maisvelho(A,B) :- idade(A,I),idade(B,Id),I > Id.

% Exercício 5

soma(A,B,C) :- C is A + B. 
pred(A,B,C) :- X is (A+B)^2, C is X*2+1.

%?- soma(8,5,S).
%   S = 14.

%?- pred(3,2,X).
%   X =  51.


% Exercício 6

anoNasc(P,A) :- idade(P,I),A is 2019-I.

% Exercício 7

%?- member(a, [a,b,c]).
% true.
%?- member(x, [a,b,c]).
% false.
%?- member(A, [a,b,c]).
% A = a ;
% A = b ;
% A = c.
%?- member(a, [a,b,c,a]).
%   true;
%   true.
%?- length([a,b,c], L).
%    L = 3.
%?- length([], X).
%    X = 0.	
%?- length(a, X).
% ERROR: length/2: Type error: `list' expected, found `a' (an atom)
%?- length([a,b,c], 2).
%   false.
%?- nextto(1, 2, [1,2,3]).
%   true;
%   false.
%?- nextto(2, Y, [1,2,3]).
%   Y = 3 ;
%   false.
%?- nextto(4, X, [1,2,3]).
%   false.
%?- nextto(1, 2, [1,2,3,1,2]).
%   true ;
%   true ;
%   false.

% Exercício 8
 
isVowel(a),
isVowel(e).
isVowel(i).
isVowel(o).
isVowel(u).



