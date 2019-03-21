  import Data.Char
--Exercício 1
  isVowel :: Char -> Bool
  isVowel letra = length(filter(\c -> c == letra)"aeiouAEIOU") > 0

--Exercício 2
  addComma :: [String] -> [String]
  addComma lista = map (\c -> c ++ ",") lista	

--Exercício 3.1
  htmlListItems  :: [String] -> [String]
  htmlListItems  lista = map (\s -> "<LI>" ++ s ++ "</LI>") lista
--Exercício 3.2
  htmlListItems' :: [String] -> [String]
  htmlListItems' lista = map concatenahtml lista
  concatenahtml  :: String -> String
  concatenahtml  s = "<LI>" ++ s ++ "</LI>"	
	
--Exercício 4.1
  tiravirgulas :: String -> String
  tiravirgulas s = filter (\c ->c/='a'&&c/='e'&&c/='i'&&c/='o'&&c/='u'&&c/='A'&&c/='E'&&c/='I'&&c/='O'&&c/='U') s
--Exercício 4.2
  tiravirgulas' :: String -> String
  tiravirgulas' string = filter notVowel string	 
  notVowel :: Char -> Bool
  notVowel letra = length(filter(\c -> c == letra)"aeiouAEIOU") == 0

--Exercício 5.1
  codifica :: String -> String
  codifica string = map (\c -> if c/=' ' then '-' else c ) string 
--Exercício 5.2
  codifica' :: String -> String
  codifica' string = map codificador string 
  codificador :: Char -> Char
  codificador c = if c/= ' ' then '-' else c

--Exercício 6
  firstname :: String -> String
  firstname string = takeWhile (/= ' ') string

--Exercício 7
  isInt  :: String -> Bool
  isInt  string = length (filter notalgarismo string) == 0
  notalgarismo :: Char -> Bool
  notalgarismo c = c /= '0'&& c/= '1' && c/= '2' && c/= '3' && c/= '4' && c/= '5' && c/= '6' && c/= '7' && c/= '8' && c/= '9'

--Exercício 8
  lastname :: String -> String
  lastname string = last(words string)

--Exercício 9
  userName :: String -> String
  userName string = [converte (head(firstname string))] ++ map converte (lastname string)
  converte :: Char -> Char
  converte c = if (isUpper c) then (toLower c) else c

--Exercício 10
  encodeName :: String -> String
  encodeName string = map (\c -> if (c == 'a' || c == 'A') then '4' else (if( c == 'e' || c == 'E') then '3' else (if (c == 'i' || c == 'I') then '2' else (if (c == 'o' || c == 'O') then '1' else (if (c == 'u'|| c == 'U') then '0' else c) )) )) string

--Exercício 11
  betterEncodename :: String -> String
  betterEncodename string = encodeU (contU(encode string)) (encode string)
  
  encodeU :: Int -> String -> String
  encodeU 0 rts = rts 
  encodeU cont str = encodeU (cont-1) ( tiraU (add00 str ) )
  
  contU :: String -> Int
  contU  str = length( filter (\c -> (c == 'U' || c == 'u')) str )
  
  tiraU:: String -> String
  tiraU str = (takeWhile(/= 'u') str) ++ tail(dropWhile(/= 'u') str)
  
  add00  :: String -> String
  add00  string = ((takeWhile(/= 'u') string) ++ "00" ++ (dropWhile(/= 'u') string))
  
  encode :: String -> String
  encode str = map encoder str
  encoder :: Char -> Char
  encoder 'o' = '0'
  encoder 'O' = '0'
  encoder 'i' = '1'
  encoder 'I' = '1'
  encoder 'e' = '3'
  encoder 'E' = '3'
  encoder 'a' = '4'
  encoder 'A' = '4'
  encoder  c  =  c 
  
--Exercício 12
  period10 :: [String] -> [String]
  period10 lista = map period10aux lista
  period10aux :: String -> String
  period10aux string = if length string >= 10 then (take 10 string)  else string ++ addperiods (10 - length string) 
  addperiods :: Int -> String
  addperiods 0 = "" 
  addperiods tam  = "." ++ addperiods (tam-1)