import Text.Printf

type Point     = (Float,Float)
type Rect      = (Point,Float,Float)
type Circle    = (Point,Float)


-------------------------------------------------------------------------------
-- Paletas
-------------------------------------------------------------------------------

rgbGradientPalette :: Int -> [(Int,Int,Int)]
rgbGradientPalette n = take n $ cycle [(255,0,0),(255,165,0),(255,255,0),(173,255,47),(0,255,0),(81,217,146),(0,191,255),(100,149,237),(0,0,255),(138,43,226),(255,20,147),(219,112,147)]

rgbAltPalette :: Int -> [(Int,Int,Int)]
rgbAltPalette n = take n $ cycle [(255,0,0),(0,255,0),(0,0,255)]

rgbAltLightPallete :: Int -> [(Int,Int,Int)] -- Palette que alterna entre r,g e b, clareando a cada ciclo
rgbAltLightPallete n = [geraCor color init incr | color<-[0,1,2],incr<-[0,10..130]]
              where init = 80
 
geraCor :: Int -> Float -> Float -> (Int,Int,Int)
geraCor  0  init incr = (round $ init+incr,0,0) 
geraCor  1  init incr = (0,round $ init+incr,0)
geraCor  2  init incr = (0,0,round $ init+incr)

    
greenPalette :: Int -> [(Int,Int,Int)]
greenPalette n = [(0,80+i*3,0) | i <- [0..n] ]


-------------------------------------------------------------------------------
-- Geração de retângulos em suas posições
-------------------------------------------------------------------------------

genRectsInLine :: Int -> [Rect]
genRectsInLine n  = [((m*(w+gap),0.0),w,h) | m <- [0..fromIntegral (n-1)]]
  where (w,h) = (50,50)
        gap = 10


-------------------------------------------------------------------------------
-- Strings SVG
-------------------------------------------------------------------------------

-- Gera string representando retângulo SVG 
-- dadas coordenadas e dimensoes do retângulo e uma string com atributos de estilo
svgRect :: Rect -> String -> String 
svgRect ((x,y),w,h) style = 
  printf "<rect x='%.3f' y='%.3f' width='%.2f' height='%.2f' style='%s' />\n" x y w h style

-- String inicial do SVG
svgBegin :: Float -> Float -> String
svgBegin w h = printf "<svg width='%.2f' height='%.2f' xmlns='http://www.w3.org/2000/svg'>\n" w h 

-- String final do SVG
svgEnd :: String
svgEnd = "</svg>"

-- Gera string com atributos de estilo para uma dada cor
-- Atributo mix-blend-mode permite misturar cores
svgStyle :: (Int,Int,Int) -> String
svgStyle (r,g,b) = printf "fill:rgb(%d,%d,%d); mix-blend-mode: screen;" r g b

-- Gera strings SVG para uma dada lista de figuras e seus atributos de estilo
-- Recebe uma funcao geradora de strings SVG, uma lista de círculos/retângulos e strings de estilo
svgElements :: (a -> String -> String) -> [a] -> [String] -> String
svgElements func elements styles = concat $ zipWith func elements styles




--Case 1 -- 

genPattern1 :: Int -> Int  ->[Rect]
genPattern1 nlin rplin  = [((x*(w+gap),y*(h+gap)),w,h) | x <- [0..fromIntegral(rplin-1)], y <- [0..fromIntegral(nlin-1)]]
  where (w,h) = (50,50)
        gap = 20


genCase1 :: IO()
genCase1 = do
    writeFile "case1.svg" $ svgstrs
    where svgstrs  = svgBegin w h ++ svgfigs ++ svgEnd
          svgfigs  = svgElements svgRect rects (map svgStyle palette)
          rects    = genPattern1 nlins rectplin
          palette  = greenPalette (nlins*rectplin)
          nlins    = 5
          rectplin = 10
          (w,h)    = (1500,600)

-- Case 2 -- 

genPattern2 :: Float -> [Circle]
genPattern2 r = [((xi + gap*cos x, yi + gap*sin x),r) | x <- [0,0.523..5.759]]--0.523 = pi/6, 5.759 = 11pi/6
    where (xi,yi)  = (500,300)
          gap = 50    
svgCirc :: Circle -> String -> String 
svgCirc ((x,y),r) style = 
  printf "<circle cx='%.3f' cy='%.3f' r='%.2f' style='%s' />\n" x y r style

genCase2 :: IO()
genCase2 = do
    writeFile "case2.svg" $ svgstrs	
    where svgstrs  = svgBegin w h ++ svgfigs ++ svgEnd
          svgfigs  = svgElements svgCirc circs (map svgStyle palette)
          circs    = genPattern2 radious
	  radious  = 10.0
          palette  = rgbGradientPalette(12) -- 12 = Circle number	
          (w,h)    = (1500.0,600.0)

-- Case 3 -- 

headconcat:: Int -> [a] -> [a] -> [a] -> [a]
headconcat tam a b  c = [head a] ++ [head b] ++ [head c] ++ headconcat (tam-1)(tail a)(tail b)(tail c)
headconcat 0 _ _ _ = []

genPattern3 :: Float -> [Circle]
genPattern3 r = headconcat (length reds) reds greens blues
          where reds   = genCirc3 r 160 160 	-- Esses números mágicos são as posições iniciais 
                greens = genCirc3 r 210 160     -- dos circulos 
                blues  = genCirc3 r 185 110

genCirc3 :: Float -> Float -> Float  -> [Circle]
genCirc3 r xi yi = [((xi + x*gap , yi + y*gap),r)| y <- [0,1], x <-[0..2]]
	where gap = 160 	

genCase3 :: IO()
genCase3 = do
    writeFile "case3.svg" $ svgstrs	
    where svgstrs  = svgBegin w h ++ svgfigs ++ svgEnd
          svgfigs  = svgElements svgCirc circs (map svgStyle palette)
          circs    = genPattern3 radious
	  radious  = 50.0
          palette  = rgbAltPalette(nCircs)
	  nCircs   = 18	
          (w,h)    = (1500.0,600.0)

-- Case 4 -- 
genPattern4 :: Float -> [Circle]
genPattern4 r = [((150+1.5*r*x,100+(1.5*r*sin(x*(0.523))+(y*3*r))),r) | y<-[0,1,2], x <- [0,1..13]]

genCase4 :: IO()
genCase4 = do
    writeFile "case4.svg" $ svgstrs
    where svgstrs  = svgBegin w h ++ svgfigs ++ svgEnd
          svgfigs  = svgElements svgCirc circs (map svgStyle palette)
          circs    = genPattern4 radious
	  radious  = 15.0
          palette  = rgbAltLightPallete(nCircs)
	  nCircs   = 42	
          (w,h)    = (1500.0,600.0)

