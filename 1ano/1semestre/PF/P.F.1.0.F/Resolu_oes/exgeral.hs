data LTree a = Leaf a | Fork (LTree a) (LTree a)

select :: LTree a -> [Bool] -> (Maybe a)
select (Leaf a) [] = Just a
select _ [] = Nothing
select (Fork l r) (x:xs) = if x == True then select r xs
						   else select l xs




------------------------------------------------------------------
--procura :: Eq a => LTree a -> a -> Maybe [Bool]
--procura x a = if elem a (total x) == True then Just (caminho x a)
--			  else Nothing

--caminho


--total :: LTree a -> [a]
--total (Leaf a) = [a]
--total (Fork l r) = (total l) ++ (total r)
------------------------------------------------------------------

type ConjInt = [Intervalo]
type Intervalo = (Int,Int)


--geraconj :: [Int] -> ConjInt
--geraconj x = map criatuplo x

--criatuplo:: [Int] -> Intervalo
--criatuplo (x:xs) = (x, criafim (x:xs))

--criafim:: [Int] -> Int
--criafim [x] = x
--criafim (x:y:xs) = if x +1 == y then criafim (y:xs)
--					 else x

--igual:: Int -> Int -> Bool
--igual a b = if a == b then True
--			else False


--cria :: [Int] -> ([Int], [Int])
--cria a = span igual a


-----------------------------------------------------------