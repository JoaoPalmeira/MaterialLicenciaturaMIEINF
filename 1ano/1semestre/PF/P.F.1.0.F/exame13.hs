sufixos :: [a] -> [[a]]
sufixos [] = [[]]
sufixos (x:xs) = ((x:xs) : (sufixos xs))

dropWhile' :: (a->Bool) -> [a] -> [a]
dropWhile' _ [] = []
dropWhile' f (x:xs) = if f x == True then (x: (dropWhile' f xs))
					 else dropWhile' f xs


data Alunos = Vazia | Nodo (Numero,Nome,Nota) Alunos Alunos
type Numero = Int
type Nome = String
type Nota = Int

aprovados :: Alunos -> Int
aprovados a = let (ap,total) = conta a
			  in (div (ap * 100)  total)

----------------------------------------------------------------------
conta :: Alunos -> (Int, Int)
conta a = (positiva a, totali a)
----------------------------------------------------------------------
totali :: Alunos -> Int
totali Vazia = 0
totali (Nodo a l r) = 1 + totali l + totali r

positiva :: Alunos -> Int
positiva Vazia = 0
positiva (Nodo (a, b, c) l r) = if c < 10 then ( positiva l + positiva r)
								else (1 + positiva l + positiva r)
--nao funciona com 9.5?
----------------------------------------------------------------------

nota :: Numero -> Alunos -> Maybe Nota
nota _ Vazia = Nothing
nota x (Nodo (a, b, c) l r) | x == a = ( Just c)
						    | x < a = nota x l
						    | x > a = nota x r
						    | otherwise = Nothing

type ConjInt = [Intervalo]
type Intervalo = (Int,Int)

pertence :: Int -> ConjInt -> Bool
pertence _ [] = False
pertence a ((x,y):xs) = if a >= x && a <= y then True
						else pertence a xs

------------------------------------------
quantos :: ConjInt -> Int
quantos a = sum (map tuplo a)

tuplo:: Intervalo -> Int
tuplo (a, b) = b - a +1					

------------------------------------------
elems :: ConjInt -> [Int]
elems a = concat (map inter a)

inter:: Intervalo -> [Int]
inter (a, b) = [a..b]
------------------------------------------

data ArvIrr a = No a [ArvIrr a]


--maximo :: Ord a => ArvIrr a   (a, Int)


todos :: ArvIrr a -> [a]
todos (No a []) = [a]
todos (No a (x:xs)) = [a] ++ (todos x) ++ (todos xs)

