data BTree a = Empty
			 | Node a (BTree a) (BTree a)
			  deriving Show


altura :: BTree a -> Int
altura Empty = 0
altura (Node a c d) = 1 + (max (altura c)(altura d))

folhas :: (BTree a) -> Int
folhas (Node a Empty Empty) = 1
folhas (Node a l r) = (folhas l) + (folhas r)

prune :: Int -> (BTree a) -> BTree a
prune 0 _ = Empty
prune x Empty = Empty
prune x (Node a c d) |x < 0 = Empty
					 |x > 0 = Node a (prune (x-1) c) (prune (x-1) d)

path :: [Bool] -> (BTree a) -> [a]
path _ Empty = []
path [] _ = []
path (x:xs) (Node a e d) = if x == True then (a:(path xs d))
						else (a: (path xs e))

mirror :: (BTree a) -> BTree a
mirror Empty = Empty
mirror (Node a e d) = Node a (mirror d) (mirror e)

zipWithBT :: (a -> b -> c) -> (BTree a) -> (BTree b) -> BTree c
zipWithBT f Empty _ = Empty
zipWithBT f _ Empty = Empty
zipWithBT f (Node a e d) (Node b l r) = Node (f a b) (zipWithBT f e l) (zipWithBT f d r)

--unzipBT :: (BTree (a,b,c)) -> (BTree a,BTree b,BTree c)
--unzipBT Empty = (Empty, Empty, Empty)
--unzipBT ( Node (x,y,z) e d) == ( Node x e1 d1, Node y e2 d2, Node z e3 d3)
--		where (e1, e2,e3) =unzipBT e
--			  (d1, d2,d3) =unzipBT d


--nada testado apenas compila
--2
--a
minimo :: (Ord a) => BTree a -> a
minimo (Node a Empty Empty) = a
minimo (Node a Empty r) = minimo r
minimo (Node a l r) = minimo l

--b
semMinimo :: (Ord a) => BTree a -> BTree a
semMinimo (Node a Empty Empty) = Empty
semMinimo (Node a b c) = (Node a (semMinimo b) c)


--c
minSmin :: (Ord a) => BTree a -> (Maybe a,BTree a)
minSmin (Node a Empty Empty) = ( (Just a), Empty)
minSmin (Node a b c) = ( Nothing ,Node a (semMinimo b) c)
--NAO POSSO USAR MAYBE, como RESOLVER?

--3
type Aluno = (Numero,Nome,Regime,Classificacao)

type Numero = Int

type Nome = String

data Regime = ORD | TE | MEL deriving Show

data Classificacao = Aprov Int
					| Rep
					| Faltou
	deriving Show

type Turma = BTree Aluno --  arvore binaria de procura (ordenada por numero)

inscNum :: Numero -> Turma -> Bool
inscNum x (Node a Empty Empty) = False
inscNum x (Node (a,nom, reg,clas) l r) = if x == a then True 
						  else if x > a then inscNum x r
						  else inscNum x l

--inscNome :: Nome -> Turma -> Bool


trabEst :: Turma -> [(Numero,Nome)]
trabEst (Node (a,nom, reg,clas) Empty Empty) = [(a, nom)]
trabEst (Node (a,nom, reg,clas) l r ) = ((a,nom):(trabEst l) ++ (trabEst r))

nota :: Numero -> Turma -> Maybe Classificacao
nota x (Node (a,nom, reg,clas) l r ) = if inscNum x (Node (a,nom, reg,clas) l r ) == False then Nothing
									   else if x == a then Just (clas)
						 			   else if x > a then nota x r
						  			   else nota x l

--percFaltas :: Turma -> Float
--percFaltas x/100

--como se representa?
--aux :: Aluno -> Int
--aux (a,nom, reg,clas) = if clas == Faltou then 1
--						else 0

--contagem :: Turma -> Int
--contagem (Node a Empty Empty) = aux a
--contagem (Node a Empty r) = aux a + contagem r
--contagem (Node a l Empty) = aux a + contagem l
--contagem (Node a l r) = aux a + contagem l + contagem r

