module Teste where

--Parte 1

--1
l::[(Int,Int)]
l=[(1,10),(6,6),(10,1),(15,5),(2,7),(3,5)]

maxSumPair :: [(Int,Int)]->(Int,Int)
maxSumPair ((a,b):(c,d):t) = if ((a+b)>=(c+d)) then (a,b) else
				if ((a+b)<(c+d)) then (c,d)
					else maxSumPair t

-- ou
maxSumPair2 :: [(Int,Int)] -> (Int,Int)
maxSumPair2 [x] = x
maxSumPair2 ((x,y):(f,s):xs) = if (x+y) < (f+s) then maxSumPair2 ((f,s):xs) else maxSumPair2 ((x,y):xs)


--2
maxes :: [(Int,Int)]->(Int,Int)
maxes [(a,b)]= (a,b)
maxes ((a,b):(c,d):t) | (a>=c) && (b>=d) = maxes ((a,b):t)
		      | (b>=d) && (a<c) = maxes ((c,b):t)
		      | (a<c) && (b<d) = maxes ((c,d):t)
                      | (b<d) && (a<c) = maxes ((b,c):t)

-- ou
maxes2 :: [(Int,Int)] -> (Int,Int)
maxes2 [x] = x
maxes2 ((x,y):xs) = let (h,t)=maxes2 xs in (max x h,max y t)

--3
data BTree a = Empty | Node a (BTree a) (BTree a)
	deriving (Show,Eq)

arv:: BTree Int
arv = (Node 7 (Node 5 (Node 4 Empty Empty)
		  (Node 6 Empty Empty)
            )
      	    (Node 9 (Node 8 Empty Empty)
	          (Node 10 Empty (Node 11 Empty Empty))	
    	    )
      )

proc :: (Eq a) => a -> BTree a -> Bool
proc x Empty = False
proc x (Node a esq dir) = (x==a) || proc x esq || proc x dir

--4
concatMaper :: (a->[b]) -> [a] -> [b]
concatMaper f [] = []
concatMaper f (h:t) = (f h) ++ concatMaper f t

--5
type Concorrentes = [(Nu,String)]
type Nu = Int
type Prova = [(Nu,Float)]

conc:: Concorrentes
conc = [(1,"tone"),(2,"ze"),(3,"quim"),(4,"nel")]

prov:: Prova
prov= [(1,15.2),(2,14.0),(3,10.1),(4,14.0)]

--a
junta :: Prova -> Concorrentes -> [(Nu,String,Float)]
junta l [] = []
junta [] l = []
junta (x:xs) l = juntaaux x l : junta xs l
	where
	     juntaaux :: (Nu,Float) -> Concorrentes -> (Nu,String,Float)
             juntaaux _ [] = error "Atleta não se encontra na lista de Concorrentes"
             juntaaux a@(num,marca) ((n,s):xs) | num == n = (n,s,marca)
					       | otherwise = juntaaux a xs

--b
quantos :: Float -> Prova -> Int
quantos x p = length (filter (aux x) p)

aux ::  Float -> (Int, Float) -> Bool
aux t (x,y) = y < t


--Parte II
--1 
type Ponto = (Int,Int)
data Orientacao = N | S | E | W
type Pos = (Ponto,Orientacao)

next :: Pos -> Cmd -> Pos
next ((x,y),N)  RD = ((x,y),E)
next ((x,y),N)  RE = ((x,y),W) 
next ((x,y),N) AV = ((x,y+1),N)
next ((x,y),S)  RD = ((x,y),W)
next ((x,y),S)  RE = ((x,y),E) 
next ((x,y),S) AV = ((x,y-1),S)
next ((x,y),E)  RD = ((x,y),N)
next ((x,y),E)  RE = ((x,y),S) 
next ((x,y),E) AV = ((x+1,y),E)
next ((x,y),W)  RD = ((x,y),N)
next ((x,y),W)  RE = ((x,y),S) 
next ((x,y),W) AV = ((x-1,y),W)

-- 2
percurso :: Pos -> [Cmd] -> [Pos]
percurso p c = percursoaux [p] c

percursoaux :: [Pos] -> [Cmd] -> [Pos]
percursoaux p (h:t) = let n = (next (last p) h) in percursoaux (p++[n]) t
percursoaux p [] = p

-- 3
haCol :: [Ponto] -> [Ponto] -> Bool
haCol [h] l = elem h l
haCol l [h] = elem h l
haCol (h:t) (x:xs) = h == x || haCol t xs
haCol _ _ = False

retiraOrienta :: [Pos] -> [Ponto]
retiraOrienta [] = []
retiraOrienta (h:t) = (fst h):retiraOrienta t

verificaColisao :: Pos -> Pos -> [Cmd] -> [Cmd] -> Bool
verificaColisao p q c d = let pp = percurso p c ;
 qp = percurso q d ;
 sp = retiraOrienta pp;
 sq = retiraOrienta qp
 in haCol sp sq

--4
