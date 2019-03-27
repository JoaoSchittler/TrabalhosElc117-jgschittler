--Aula 4

--Exercício 1

	add10toall  :: [Int] -> [Int]
	add10toall  list = [x+10| x <- list]

--Exercício 2

	multN :: Int -> [Int] -> [Int]
	multN n list = [x*n| x <- list] 

--Exercício 3

	applyExpr :: [Int] -> [Int]
	applyExpr list = [3*x+2| x <- list]

--Exercício 4

	addSuffix :: String -> [String] -> [String]
	addSuffix sufix list = [x++sufix| x <- list]

--Exercício 5

	selectgt5 :: [Int] -> [Int]
	selectgt5 list = [x| x<-list,x>5]

--Exercício 6

	isodd  :: Int -> Bool
	isodd x =  if mod x 2 == 1 then True else False
	sumOdds :: [Int] -> Int
	sumOdds list =  sum [x| x<-list, isodd x]

--Exercício 7

	iseven :: Int -> Bool
	iseven x = if mod x 2 == 0 then True else False
	selectExpr :: [Int] -> [Int]
	selectExpr list = [x| x <- list, iseven x, x > 20, x < 50]
	
--Exercício 8

	countShorts :: [String] -> Int
	countShorts list = length ([x| x<-list, (length x) < 5 ] )

--Exercício 9

	calcExpr :: [Float] -> [Float] 
	calcExpr list = [x^2/2| x<-list, (x^2/2) > 10 ]

--Exercício 10

	trSpaces :: String -> String
	trSpaces str = [if x == ' ' then '-' else x | x <- str]

--Exercício 11

	--a) Cria uma lista de tuplas onde o primeiro elemento é um número par de 1 a 5 e o segundo é um número impar do primeiro elemento da tupla até 6
	--Resultado : [(2,3),(2,5),(4,5)]
	--b) Cria uma lista de tuplas com as combinações(concatenações) das palavras "lazy" e "big" com "frog" e "dog"
	--Resultado: ["lazyfrog","lazydog","bigfrog","bigdog"]
	--c) Troca todas as vogais de uma string por '-'
	--Resultado : "p-r-l-l-p-p-d-"

--Exercício 12

	selectSnd :: [(Int,Int)] -> [Int]
	selectSnd list = [ snd x | x <- list]

--Exercício 13

	dotProd :: [Int] -> [Int] -> Int
	dotProd list1 list2 = sum[fst x * snd x|x <- (zip list1 list2)]

--Exercício 14

	genRects :: Int -> (Int,Int) -> [(Float,Float,Float,Float)]
	genRects n ip = [(fromIntegral(fst ip)+fromIntegral(c)*5.5,fromIntegral(snd ip),5.5,5.5)| c <- [0..(n-1)]]

--Exercício 15

	dotProd' :: [Int] -> [Int] -> Int
	dotProd' list1 list2 = sum (zipWith(*) list1 list2)






	
