data ExpInt = Const Int
            | Simetrico ExpInt
			| Mais ExpInt ExpInt
			| Menos ExpInt ExpInt
			| Mult ExpInt ExpInt


calcula :: ExpInt -> Int
calcula (Const x) = x
calcula (Simetrico x) =  -(calcula x)
calcula (Mais x y) = (calcula x) + (calcula y)
calcula (Menos x y) = ( calcula x) - (calcula y)
calcula (Mult x y) = (calcula x) * (calcula y)
--definir para todas as possiblidades??



--infixa :: ExpInt -> String
--infixa (Const x) = show ( calcula (Const x))
--infixa (Simetrico x) =  ('-' : (infixa  x))
--infixa (Mais x y) = ('(':(infixa x) ++ "+" ++ (infixa y) ++ ")"
--infixa (Menos x y) = "(" (infixa x) ++ "-" ++ (infixa y) ++ ")"
--infixa (Mult x y) = "(" (infixa x) ++ "*" ++ (infixa y) ++ ")"


--infixa :: ExpInt -> String
--infixa


--posfixa :: ExpInt -> String
--posfi


data RTree a = R a [RTree a]


--soma :: (Num a) => (RTree a) -> a
--soma (R a []) = a
--soma (R x b) = sum (soma (concat b))


soma :: (Num a) => (RTree a) -> a
soma (R r []) = r
soma (R r (rt:rts)) = r + soma rt + aux(rts)
                   where aux [] = 0
                         aux ((R r l):t) = r + aux l + aux t




postorder :: (RTree a) -> [a]
postorder (R a []) = [a]
postorder (R a b) = ([a] ++ concat (map postorder b))

--altura :: (RTree a) -> Int
--altura (R a []) = 1
--altura (R a b) = 1 + (altura b)

--mirror :: (RTree a) -> (RTree a)
--mirror (R a []) = a
--mirror (R a b) = R a (mirror (reverse b))




data LTree a = Tip a | Fork (LTree a) (LTree a)


ltSum :: (Num a) => (LTree a) -> a
ltSum (Tip a) = a
ltSum  (Fork a b) = (ltSum a) + (ltSum b)

mapLT :: (a->b) -> LTree a -> LTree b
mapLT f (Tip x) =  Tip (f x)
mapLT f (Fork l r) = Fork (mapLT f l) (mapLT f r)



--NAO SEI SE CORRETO, acho que sim.
listaLT :: (LTree a) -> [a]
listaLT (Tip a) = [a]
listaLT (Fork a b) = (listaLT a) ++ (listaLT b)

--errada?
ltHeight :: (LTree a) -> Int
ltHeight (Tip a) = 1
ltHeight (Fork a b) = 1 + (max (ltHeight a) (ltHeight b))

--

data FTree a b = Leaf b | No a (FTree a b) (FTree a b)
--data LTree a = Tip a | Fork (LTree a) (LTree a)
data BTree a = Empty | Node a (BTree a) (BTree a)


--splitFTree :: (FTree a b) -> (BTree a, LTree b)
--splitFTree Leaf b = (Empty, Tip b)
--splitFTree (No a b c) = (Node a e1 d1, Forl e2 d2)
--		   where (e1, e2) = splitFTree b
--				 (d1, d2) = splitFTree c


joinTree :: BTree a -> LTree b -> Maybe (FTree a b)
joinTree Empty (Tip x) = Just (Leaf x)
joinTree (Node x e d) (Fork e' d') = case (joinTree e e') of
									 Nothing -> Nothing
									 (Just e1) -> case (joinTree d d') of
									 			  Nothing -> Nothing
									 			  (Just d1) -> Just (No x e1 d1)