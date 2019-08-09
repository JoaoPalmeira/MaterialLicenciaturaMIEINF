type TabClass = [(Piloto,Equipa,Pontos)]
type Piloto = String
type Pontos = Int
type Equipa = String

--1
--a
pontosEquipa :: Equipa -> TabClass -> Pontos
pontosEquipa _ [] = -1
pontosEquipa x ((a, b, c):xs) = if  x == b then c 
								else pontosEquipa x xs

--b
junta :: [Piloto] -> TabClass -> TabClass
junta x tabela = alter x [25,18,15,12,10,8,6,4,2,1] tabela


alter :: [Piloto] -> [Pontos] -> TabClass -> TabClass
alter [] _ a = a
alter _ [] a = a
alter (x:xs) (y:ys) a = alter xs ys (classfin x y a) 


classfin:: Piloto -> Pontos -> TabClass -> TabClass
classfin _ _ [] = []
classfin a b ((x, y, z):xs) = if a == x then ((x, y, (z+b)):xs)
							  else ((x, y, z): ( classfin a b xs))

--c
ordena :: TabClass -> TabClass
ordena [] = []
ordena [x] = [x]
ordena (x: y: xs) = if maxi x (y:xs) == True then (x : (ordena (y:xs)))
				 else (ordena ((y:xs) ++ [x]))


maxi:: (Piloto,Equipa,Pontos) -> TabClass -> Bool
maxi _ [] = True
maxi (pil, equi, ponto) (((a, b, c)): xs) = if ponto >=  c then maxi (pil, equi, ponto) xs
										    else False

--2
type Mat a = [[a]]


zipWMat ::(Num a) => Mat a -> Mat a -> Mat a
--zipWMat :: (a -> b -> c) -> Mat a -> Mat b -> Mat c
zipWMat [] [] = []
zipWMat (x:xs) (y:ys) = ((zipWith (+) x y ): zipWMat xs ys)


--soma :: (Num a) => a -> a -> a
--soma a b = a + b

--linha :: (a -> b -> c) -> [a] -> [a] -> [a]
--linha _ [] = []
--linha [] _ = []
--linha (x:xs) (y:ys) = ((x + y) : (linha xs ys))

--rotaLeft :: Mat a -> Mat a
--rotaLeft [] = []
--rotaLeft (x:ys) = rotaLeft

--aux :: Mat a -> Mat a
--aux []

--const:: Mat a -> ([a], Mat a)
--const [] = []
--const ((x:xs):ys) = ((head x, xs) : (const ys))




data Questionario = Solucao String
				  | Questao String Questionario Questionario

--3
--a
respostas :: Questionario -> Int
respostas (Solucao x) = 1
respostas (Questao x a b) = 1 + (respostas a) + (respostas b)

--b
--seqResp :: Questionario -> String -> Maybe [Bool]
--seqResp 

--instance Show Questionario where
--	func = 