% Prática 3 Prolog

%Primeira parte
% Exercício 1
b(2).
b(3).
c(4).
c(5).
regra1(X, Y) :- b(X), c(Y).
regra2(X, Y) :- b(X), !, c(Y).
% Exercício 2
% Considerando o programa acima, qual será o resultado da seguinte consulta? regra1(X,Y).
% O resultado será X = 2, Y = 4; X  = 2, Y = 5;X = 3, Y = 4; X  = 3, Y = 5.
% Exercício 3
% Considerando o programa acima, qual será o resultado da seguinte consulta? regra2(X,Y).
% O resultado será X = 2, Y = 4; X  = 2, Y = 5. Pois só será buscado o primeiro resultado para X.

%Segunda parte
fatv1(0,1).
fatv1(N,X) :- 
	N1 is N - 1,
	fatv1(N1, X1),
	X is N*X1.

fatv2(0,1).
fatv2(N,X) :- 
	N1 is N - 1,
	fatv2(N1, X1),
	X is N*X1, !.


fatv3(0,1).
fatv3(A,B) :-  
	A > 0, 
        C is A-1,
        fatv3(C,D),
        B is A*D.
% Exercício 3,4
% O programa tenta achar outros resultados para fatv1(0,N) e ocorre stack overflow.
% Exercício 5,6,7
% Em fatv2, é utilizado ! para impedir backtracing, que mostra para o interpretador que só 
% deseja-se o primeiro resultado
% Em fatv3, é utilizado A>0 como consição que impede a tentativa de cálcular fatoriais negativos.
% Exercício 8
% fatv2 é mais eficiente que fatv3 pois a operação A > 0 tem que ser testada para todas as execuções da recursão e ! é executado apenas no final da execução da recursão, deixando o predicado mais eficiente.

