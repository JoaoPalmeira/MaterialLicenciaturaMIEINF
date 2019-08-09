module Recurso12 where
--Parte II
--1

data ArvBin a = Empty | Node a (ArvBin a) (ArvBin a)

camValido :: ArvBin a  -> [Bool] -> Bool
camValido Empty _ = True
camValido (Node r e d) [] = False
camValido (Node r e d) (x:xs) | (x==True) = camValido e xs
															| (x==False) = camValido d xs

caminho :: (Eq a) => ArvBin a -> a -> Maybe [Bool]
caminho x n | (pertence x n) = Just (caminhoAux x n)
						| otherwise = Nothing

caminhoAux :: (Eq a) => ArvBin a -> a -> [Bool]
caminhoAux Empty _ = []
caminhoAux (Node r e d) n | (r==n) = []
											 | (pertence e n) = False : caminhoAux e n
											 | (pertence d n) = True : caminhoAux d n
											 | otherwise = []

pertence :: (Eq a) => ArvBin a -> a -> Bool
pertence Empty _ = False
pertence (Node r e d) n | (r==n) = True
 												| otherwise = pertence e n || pertence d n

--2

type Tabuleiro = [String]

rainhasOK :: Tabuleiro -> Bool
rainhasOK [] = True
rainhasOK xs = rainhasOKAccum xs 0 0

rainhasOKAccum :: Tabuleiro -> Int -> Int -> Bool
rainhasOKAccum t@((x:xs):xys) i j | (x=='Q') = casaR i j t && rainhasOKAccum (xs:xys) i (j+1)
																	| otherwise = rainhasOKAccum (xs:xys) i (j+1)
rainhasOKAccum ([]:xys) i j = rainhasOKAccum xys (i+1) 0
rainhasOKAccum [] _ _ = True

casaR :: Int -> Int -> Tabuleiro -> Bool
casaR i j t = vLinha (i,j) 0 0 t && vColuna (i,j) 0 0 t && vDiagonal (i+1,j+1) 0 0 t

vLinha :: (Int,Int) -> Int -> Int -> Tabuleiro -> Bool
vLinha (i,j) a b ((x:xs):xys) | (i==a) = if(b==j) then vLinha (i,j) a (b+1) (xs:xys) else if(x=='Q') then False else vLinha (i,j) a (b+1) (xs:xys)
	 														| otherwise = vLinha (i,j) (a+1) 0 xys
vLinha (i,j) a b ([]:xys) = vLinha (i,j) (a+1) 0 xys	
vLinha (i,j) a b [] = True

vColuna :: (Int,Int) -> Int -> Int -> Tabuleiro -> Bool
vColuna (i,j) a b ((x:xs):xys) | (j==b) = if(a==i) then vColuna (i,j) (a+1) 0 xys else if(x=='Q') then False else vColuna (i,j) (a+1) 0 xys
	 														 | otherwise = vColuna (i,j) a (b+1) (xs:xys)
vColuna (i,j) a b ([]:xys) = vColuna (i,j) (a+1) 0 xys	
vColuna (i,j) a b [] = True

vDiagonal :: (Int,Int) -> Int -> Int -> Tabuleiro -> Bool
vDiagonal (i,j) a b ((x:xs):xys) | (i==a) = if(b==j) then if(x=='Q') then False else vDiagonal (i+1,j+1) (a+1) 0 xys else vDiagonal (i,j) a (b+1) (xs:xys) 
	 														   | otherwise = vDiagonal (i,j) (a+1) 0 xys
vDiagonal (i,j) a b ([]:xys) = vDiagonal (i,j) (a+1) 0 xys	
vDiagonal (i,j) a b [] = True