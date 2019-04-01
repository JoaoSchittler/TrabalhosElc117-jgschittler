-- Aula 5 de Haskell
-- Prática haskell04

-- 1)

{-
   Programa em Haskell para validar os digitos de um CPF
   Mais info em: http://pt.wikipedia.org/wiki/Cadastro_de_Pessoas_F%C3%ADsicas
-}

import Data.Char

isCpfOk :: [Int] -> Bool
isCpfOk cpf = 
  let -- calcula primeiro digito
      digitos1 = take 9 cpf
      expr1 = (sum $ zipWith (*) digitos1 [10,9..2]) `mod` 11
      dv1 = if expr1 < 2 then 0 else 11-expr1

      -- calcula segundo digito
      digitos2 = digitos1 ++ [dv1]
      expr2 = (sum $ zipWith (*) digitos2 [11,10..2]) `mod` 11
      dv2 = if expr2 < 2 then 0 else 11-expr2
   in dv1 == cpf !! 9 && dv2 == cpf !! 10

main = do
  let cpf = "12345678909"
      digitos = (map digitToInt cpf)
      result = isCpfOk digitos
  putStrLn (show result)


-- 2)
{-
   O cógido do slide usa uma função auxiliar "cpfDV" para calcular
   os digitos verificadores, enquanto que o código baixado 
   usa apenas a função "isCpfOk"(desconsiderando funções já prontas)	

-}


-- 3)

main2 :: IO()
main2 = do
   putStr "CPF: " 
   cpf <- getLine
   let digits = (map digitToInt cpf) 
   putStr (if cpfValid digits then "Ok" else "Not Ok")

--4)
cpfValid :: [Int] -> Bool
cpfValid cpf = dv1 == cpf !! 9 && dv2 == cpf !! 10
  where dv1 = if expr1 < 2 then 0 else 11-expr1
 	digitos1 = take 9 cpf
        expr1 = (sum $ zipWith (*) digitos1 [10,9..2]) `mod` 11

        dv2 = if expr2 < 2 then 0 else 11-expr2
	digitos2 = digitos1 ++ [dv1]
        expr2 = (sum $ zipWith (*) digitos2 [11,10..2]) `mod` 11
        
		
    