%Segunda
segunda(pedro,sm).
segunda(caren,pa).
segunda(henrique,ap).
segunda(bia,ap).
segunda(maria,ap).
segunda(adriano,ap).
segunda(alice,ap).
segunda(bernado,sm).
%Terca
terca(pedro,sm).
terca(caren,pa).
terca(henrique,pa).
terca(bia,pa).
terca(maria,sm).
terca(adriano,ap).
terca(alice,pa).
terca(bernado,sm).
%Quarta
quarta(pedro,pa).
quarta(caren,pa).
quarta(henrique,ap).
quarta(bia,pa).
quarta(maria,sm).
quarta(adriano,sm).
quarta(alice,pa).
quarta(bernado,pa).
%Quinta
quinta(pedro,sm).
quinta(caren,sm).
quinta(henrique,ap).
quinta(bia,sm).
quinta(maria,sm).
quinta(adriano,ap).
quinta(alice,ap).
quinta(bernado,sm).
%Sexta
sexta(pedro,ap).
sexta(caren,ap).
sexta(henrique,ap).
sexta(bia,ap).
sexta(maria,ap).
sexta(alice,ap).
sexta(bernado,ap).
%Pobre
pobre(pedro).
pobre(bia).
pobre(maria).
%Ciumes
ciumes(anita,caren).
ciumes(maria,caren).
ciumes(anita,maria).
ciumes(anita,alice).

chave(bia).
chave(X):- segunda(X,sm);terca(X,pa).
%Insano
insano(maria).
insano(adriano).

arma(X):- quarta(X,sm);quinta(X,pa);quarta(X,ap);quinta(X,ap).
lugar(X):- quinta(X,ap);sexta(X,ap).
motivo(X):- insano(X);pobre(X);ciumes(anita,X).
acesso(X):- arma(X),chave(X),lugar(X).
assasino(X):-  motivo(X),acesso(X).
