type TurmaL = [(Numero,Aluno)]
type Aluno = (Nome,Nota)
type Numero = Int
type Nome = String
type Nota = Float

--1
--a

--taxaAp :: TurmaL -> Float
--taxaAp x = (div (sum (notas x))(length x)) -- / nao funciona por nao serem floats


notas:: TurmaL -> [Int]
notas [] = []
notas ((num, (nom, not)): xs) = if not >= 9.5 then (1 : notas xs)
								else (0: notas xs)

--b
top :: Int -> TurmaL -> [String]
top _ [] = []
top 0 x = []
top x y = (map (nomes) (taker x (ord y)))
-------------------------------------------------------------------
taker :: Int -> TurmaL -> TurmaL
taker _ [] = []
taker 0 x = []
taker x (y:ys) = (y : (taker (x-1) ys))

nomes :: (Numero, Aluno) -> Nome
nomes (num, (nom, not)) = nom

------------------------------------------------------------------
ord :: TurmaL -> TurmaL
ord [] = []
ord [x] = [x]
ord (x: y: xs) = if maxi x (y:xs) == True then (x : (ord (y:xs)))
				 else (ord ((y:xs) ++ [x]))


maxi:: (Numero, Aluno) -> TurmaL -> Bool
maxi _ [] = True
maxi (num, (nom, not)) ((a, (b, c)): xs) = if not >=  c then maxi (num, (nom, not)) xs
										   else False
--------------------------------------------------------------------
--c
lNomeMax :: TurmaL -> Int
lNomeMax x = maximum ( map maxinom x)

maxinom:: (Numero, Aluno) -> Int
maxinom (num, (nom, not)) = length nom

--d
--listaT :: TurmaL -> IO ()



--2
data TurmaA = Al (Numero,Aluno)
			| Fork (Numero,Numero) TurmaA TurmaA



toList :: TurmaA -> TurmaL
toList (Al (x, b)) = [(x, b)]
toList (Fork a x b) = (toList x) ++ (toList b)


fromList :: TurmaL -> TurmaA
fromList ((num, (nome, nota)):xs) = Fork (num, (fst (last xs)) e d
	where (l1, l2)= splitAt (div length (((num, (nome, nota)): xs)) 2)
				e = fromList l1
				d = fromList l2


remove :: TurmaA -> Numero -> Maybe TurmaA
remove (Al (num, alu)) n = if num == alu then Nothing
						 else Just Al (num, alu)
remove (Fork a l r) num 

-----------------------------------------------------------------------------------
--lookupA :: TurmaA -> Numero -> Maybe Aluno
--lookupA (Al (x, b)) num = if x == num then (Just b)
--						  else Nothing
--lookupA (Fork (x, y) a b) num = if num > y || num < x then Nothing
--								else ((lookupA a num) ++ (lookupA b) num)

--(snd (head (limpa 


limpa :: [Maybe Aluno] -> [Maybe Aluno]
limpa [] = [Nothing]
limpa (x:xs) = if x == Nothing then limpa xs
			   else (x: limpa xs)

------------------------------------------------------------------------------------			   