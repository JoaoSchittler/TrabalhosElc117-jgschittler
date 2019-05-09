%Problema Resolvido
%link : https://rachacuca.com.br/logica/problemas/fila-do-banco/
%Informações
%
%
%    Na quarta posição está o homem que gosta de Sinuca.
%    Quem gosta de Futebol está na quinta posição.
%    O homem que gosta de Basquete está na segunda posição.
%    Quem gosta de Vôlei está de Vermelho.
%    O dono do Peixe está exatamente à esquerda de quem pratica Natação.
%    O homem de Branco é o dono do réptil.
%    O Bombeiro está exatamente à direita do Samuel.
%    O Veterinario está na quinta posição
%    O Pesquisador está na terceira posição.
%    Douglas trabalha como Fotógrafo.
%    José está na quinta posição.
%
%    O Pesquisador está exatamente à esquerda do Augusto.
%    Douglas tem um Cachorro.
%    Na quinta posição está o homem da conta de Telefone.
%    O homem de Branco vai pagar a conta de Água.
%    Na terceira posição está quem vai pagar a conta de Luz.
%    Ronaldo vai pagar a conta de Celular.
%    O homem de Verde tem um Pássaro.
%    O homem de Azul está em uma das pontas.
%    O homem que tem um Gato está exatamente à direita de que gosta de Sinuca.
%    O homem que tem um Pássaro está ao lado do que joga Sinuca.
%    pessoa(Camisa,Conta,Nome,Profissao,Animal,Esporte)

naspontas_(X,L):- L = [H|_], X = H.
naspontas_(X,L):- last(L,Last), X = Last.

naesquerda_(X,Y,L):- nextto(X,Y,L).
nadireita_(X,Y,L):- nextto(Y,X,L).

aolado_(X,Y,L):- naesquerda_(X,Y,L);nadireita_(X,Y,L).

solucao1(Fila):-
    Fila = [_,_,_,_,_],
    [_,_,_,pessoa(_,_,_,_,_,sinuca),_] = Fila,
    [_,_,_,_,pessoa(_,_,_,_,_,futebol)] = Fila,
    [_,pessoa(_,_,_,_,_,basquete),_,_,_] = Fila,
    naesquerda_(pessoa(_,_,_,_,peixe,_),pessoa(_,_,_,_,_,natacao),Fila),
    member(pessoa(vermelho,_,_,_,_,volei),Fila),
    member(pessoa(branco,_,_,_,reptil,_),Fila),
    [_,_,pessoa(_,_,_,pesquisador,_,_),_,_] = Fila,
    nadireita_(pessoa(_,_,_,bombeiro,_,_),pessoa(_,_,samuel,_,_,_),Fila),
    [_,_,_,_,pessoa(_,_,_,veterinario,_,_)] = Fila,
    member(pessoa(_,_,douglas,fotografo,_,_),Fila),
    [_,_,_,_,pessoa(_,_,jose,_,_,_)] = Fila,
    naesquerda_(pessoa(_,_,_,pesquisador,_,_),pessoa(_,_,augusto,_,_,_),Fila),
    member(pessoa(_,_,douglas,_,cachorro,_),Fila),
    [_,_,_,_,pessoa(_,telefone,_,_,_,_)] = Fila,
    member(pessoa(branco,agua,_,_,_,_),Fila),
    [_,_,pessoa(_,luz,_,_,_,_),_,_] = Fila,
    member(pessoa(_,celular,ronaldo,_,_,_),Fila),
    member(pessoa(verde,_,_,_,passaro,_),Fila),
    naspontas_(pessoa(azul,_,_,_,_,_),Fila),
    nadireita_(pessoa(_,_,_,_,gato,_),pessoa(_,_,_,_,_,sinuca),Fila),
    aolado_(pessoa(_,_,_,_,passaro,_),pessoa(_,_,_,_,_,sinuca),Fila),
    member(pessoa(_,aluguel,_,_,_,_),Fila),
    member(pessoa(_,_,_,massagista,_,_),Fila),
    member(pessoa(amarela,_,_,_,_,_),Fila).
    



%Problema inicial que tentei fazer, porem tive dificuldade para que os predicados adireita,aesquerda e entre
%funcionem corretamente quando é passado uma Lista variável como argumento, tentei de algumas maneiras.
%link: https://rachacuca.com.br/logica/problemas/carro-do-morango/
%A mulher de 37 anos está em uma das pontas.
%A Vendedora está em algum lugar à direita da mulher da sacola Branca.
%Inês comprará 5 caixas de morango.
%A mulher da sacola Verde está em algum lugar à esquerda da mulher da sacola Azul.
%A Jornalista está ao lado de quem comprará 4 caixas de morango.
%Giovana está na segunda posição.
%As mulheres das sacolas Verde e Azul estão lado a lado.
%A mulher mais nova(26 anos ) está em algum lugar à direita da mulher da sacola Branca.
%A Projetista está em algum lugar entre a mulher da sacola Vermelha e a Garçonete, nessa ordem.
%A mulher da sacola Azul está na terceira posição.
%Mariana está exatamente à direita de quem vai fazer um bolo.
%Giovana está ao lado de quem comprará 4 caixas de morango.
%A dona da sacola Verde está ao lado de quem vai fazer Geleia de morango.
%Silvia tem 42 anos.
%A Jornalista está na quarta posição.
%Inês está ao lado de quem vai fazer Torta de morango.
%Na primeira posição está a mulher que deseja comprar 6 caixas.
%A Projetista está exatamente à esquerda da mulher de 42 anos.
%A mulher da sacola Verde está em algum lugar entre quem vai fazer Pavê de morango e quem tem 55 anos, nessa ordem.
%Quem comprará 2 caixas está ao lado de quem vai fazer Bolo de morango.
% Sacolas : Branca,Verde,Vermelha,Azul,*Amarela*
% Ncaixas : 2,*3*,4,5,6
% Doces : Bolo,Torta,*Mousse*,Geleia,Pave
% Nomes : *Carla*, Giovana, Ines, Mariana,Silvia
% Idades : 26,*31*,37,42,55
% Profissoes : *Estilista*,Garconete,Jornalista,Projetista,Vendedora
%As palavras entre * são informações não mencionadas pelas dicas
% Informações adicionais, para poder completar o problema.
% Carla está na primeira posição
% A mulher que leva 3 caixas está aolado de quem tem a sacola verde
% A mulher que tem 31 anos está a direita de quem vai fazer pavê
% A estilita está a esquerda de quem  tem 42 anos
% A mulher que vai fazer mousse está imediatamente a esquerda de Silvia
% A mulher que tem a sacola amarela imediatamente a direita de Ines
%Predicados auxiliares

%Last é o ultimo elemento da lista
last([Last],Last):- !.
last([_|T],Last):- last(T,Last),!.
%Predicado que indica a posicao I de um valor X em uma lista L, supondo que só há X uma vez na lista 
indice(X,[X|_],0):- !.
indice(X,[_|T],I):- indice(X,T,I2),I is I2 + 1,!.

%A está em uma das pontas da lista L
naspontas(A,L):- L = [H|_], A = H.
naspontas(A,L):- last(L,Last), A = Last.

%A está a esquerda de B em uma lista L
aesquerda2(A,B,L):- indice(A,L,IA),indice(B,L,IB), IA < IB.
aesquerda(A,B,[A|T]):- member(B,T). %Quando chegar em A, diz que B é membro do tail da lista
aesquerda(A,B,[H|T]):- not(A=H),aesquerda(A,B,T). %Enquanto não chega em A, percorre a lista

%A está a direita de B em uma lista L
adireita2(A,B,L):- indice(A,L,IA),indice(B,L,IB), IB < IA.
adireita(A,B,[H|T]):-  not(H=B),adireita(A,B,T).  %Enquanto não chega em B, percorre a lista
adireita(A,B,[B|T]):-  member(A,T). %Quando chegar em B, diz que A é membro do tail da lista
%A está imediatamente a esquerda de  B em uma lista L
naesquerda(A,B,L):- indice(A,L,IA),indice(B,L,IB), IA is IB-1.

%A está imediatamente a direita de  B em uma lista L
nadireita(A,B,L):- indice(A,L,IA),indice(B,L,IB), IA is IB+1.

%A está ao lado de B em uma lista L
aolado(A,B,L):- nadireita(A,B,L);naesquerda(A,B,L).

%B está entre A e C em uma lista L
entre2(A,B,C,L):- indice(A,L,IA),indice(B,L,IB),indice(C,L,IC), IA < IB, IB < IC.
entre(A,B,C,L):-adireita(B,A,L),aesquerda(B,C,L).
%posicao (Sacola,Ncaixas,Doce,Nome,Idade,Profissao).
solucaoex(Posicoes):-
    Posicoes = [_,_,_,_,_],
    [posicao(_,_,_,carla,_,_),_,_,_,_] = Posicoes,
    aolado(posicao(verde,_,_,_,_,_),posicao(_,3,_,_,_,_),Posicoes),
    adireita(posicao(_,_,_,_,31,_),posicao(_,_,pave,_,_,_),Posicoes),
    aesquerda(posicao(_,_,_,_,_,estilista),posicao(_,_,_,_,42,_),Posicoes),
    naesquerda(posicao(_,_,mousse,_,_,_),posicao(_,_,_,silvia,_,_),Posicoes),
    nadireita(posicao(amarela,_,_,_,_,_),posicao(_,_,_,ines,_,_),Posicoes),
    naspontas(posicao(_,_,_,_,37,_),Posicoes),
    adireita(posicao(_,_,_,_,_,vendedora),posicao(branca,_,_,_,_,_),Posicoes),
    member(posicao(_,5,_,ines,_,_),Posicoes),
    aesquerda(posicao(verde,_,_,_,_,_),posicao(azul,_,_,_,_,_),Posicoes),
    aolado(posicao(_,_,_,_,_,jornalista),posicao(_,4,_,_,_,_),Posicoes),
    [_,posicao(_,_,_,giovana,_,_),_,_,_] = Posicoes,
    aolado(posicao(azul,_,_,_,_,_),posicao(verde,_,_,_,_,_),Posicoes),
    adireita(posicao(_,_,_,_,26,_),posicao(branca,_,_,_,_,_),Posicoes),
    entre(posicao(vermelha,_,_,_,_,_),posicao(_,_,_,_,_,projetista),posicao(_,_,_,_,_,garconete),Posicoes),
    [_,_,posicao(azul,_,_,_,_,_),_,_] = Posicoes,
    nadireita(posicao(_,_,_,maraiana,_,_),posicao(_,_,bolo,_,_,_),Posicoes),
    aolado(posicao(_,_,_,giovana,_,_),posicao(_,4,_,_,_,_),Posicoes),
    aolado(posicao(verde,_,_,_,_,_),posicao(_,_,geleia,_,_,_),Posicoes),
    member(posicao(_,_,_,silvia,42,_),Posicoes),
    [_,_,_,posicao(_,_,_,_,_,jornalista),_] = Posicoes,
    aolado(posicao(_,_,_,ines,_,_),posicao(_,_,torta,_,_,_),Posicoes),
    [posicao(_,6,_,_,_,_),_,_,_,_] = Posicoes,
    naesquerda(posicao(_,_,_,_,_,projetista),posicao(_,_,_,_,42,_),Posicoes),
    entre(posicao(_,_,pave,_,_,_),posicao(verde,_,_,_,_,_),posicao(_,_,_,_,55,_),Posicoes).