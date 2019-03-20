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
  userName string = [converte head(head(words string))] ++ map converte tail(words string)
  converte :: Char -> Char
--  converte c = if (isUpper c) then (toLower c) else c

--isSpace
--isUpper
--isLower
--toUpper
--toLower

