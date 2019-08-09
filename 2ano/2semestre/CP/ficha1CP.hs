module Main where 

--1
--a    
inter :: a -> [a] -> [a]
inter _ [] = []
inter _ [x] = [x]
inter x (h:t) = H:x:(inter x t)

teste1 = Testlist [inter 5 [1,2,3] ~ ?= [1,5,2,5,3], inter 5 [] ~ ?= ([]::[Int])] 
--b
inits :: [a] -> [[a]]
inits [] = [[]]
inits l = aux l [[]]
    where aux [] a = a
          aux (h:t) k@(x:xs) = aux t ((x++[h]):k)

teste = Testlist [inits [1,2,3] ~ ?= [[],[1],[1,2],[1,2,3]] , inits [] ~ ?= [[]]]

map :: (a->b) -> [a] -> [b]
map _ [] = []
map f (x:xs) = (fx):(map f x)
--c
type Aluno = (Numero,Nome,Classificacao)
type Numero = Int
type Nome = String
data Maybe a = Nothing | Just a 
data Classificacao = Aprov Int | Rep | Faltou
data Turma = Vazia | Nodo Aluno Turma Turma
--i
inscrito :: Numero -> Turma -> Bool
inscrito _ Vazia = False
inscrito n (Nodo (m,_,_) ae ad) | n==m = True
                                | n<m = inscrito n ae
                                | n>m = inscrito n ad

teste2 = Testlist [inscrito 8 a1 ~?=False, inscrito 10 a1 ~?=True]
--ii
--melhor :: Turma -> Maybe Int
--melhor Vazia = Nothing
--melhor (Nodo (_,_,c) ae ad) = 
--    let me = melhor ae
--        md = melhor ad
--    in aux c me md 
--    where aux :: Classificacao -> Maybe Int -> Maybe Int
--          aux (Ap x) (Just y) (Just z) = max (max y z) x
--          aux (Ap x) Nothing (Just z) = max x z
--          aux (Ap x) (Just y) Nothing = max x y
--          aux (Ap x) Nothing Nothing = Just x

melhor :: Turma -> Maybe Int
melhor l = let l = aux t
           in if l==[] then Nothing
              else Just (maximum l)

aux :: Turma -> [Int]
aux Vazia = []
aux (Nodo (_,_,c) ae ad) = let le = aux ae
                               ld = aux ad
                            in case c of 
                                (Ap n) -> [n] ++ le ++ ld
                                _ -> le ++ ld



