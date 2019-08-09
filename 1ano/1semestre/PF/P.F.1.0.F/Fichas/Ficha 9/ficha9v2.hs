data ExpInt = Const Int
			| Simetrico ExpInt
			| Mais ExpInt ExpInt
			| Menos ExpInt ExpInt
			| Mult ExpInt ExpInt

calcula :: ExpInt -> Int
calcula (Const x) = x
calcula (Simetrico x) = - ( calcula x)
calcula (Mais x y) = (calcula x) + (calcula y)
calcula (Menos x y) = (calcula x) - (calcula y)
calcula (Mult x y) = (calcula x) * (calcula y)

infixa :: ExpInt -> String
infixa (Const x) = (show x)
infixa (Simetrico x) = " - " ++ (infixa x)
infixa (Mais x y) = "( " ++ (infixa x) ++ " + " ++ (infixa y) ++" ) "
infixa (Menos x y) = "( " ++ (infixa x) ++ " - " ++ (infixa y) ++" ) "
infixa (Mult x y) = "( " ++ (infixa x) ++ " * " ++ (infixa y) ++" ) "
---------------------------------------------------------------------------------------------------------
--posfixa :: ExpInt -> String
--posfixa (Const x) =  une (finaliza ( (show x) ))
--posfixa (Simetrico x) = une (finaliza ( (posfixa x) ++ "-") (length (posfixa x) ++ "-") [])
--posfixa (Mais x y) =  une (finaliza ( (posfixa x) ++ " " ++ (posfixa y) ++ "+" ) (length (posfixa x) ++ " " ++ (posfixa y) ++ "+" ) [])
--posfixa (Menos x y) =  une (finaliza ( (posfixa x) ++ " " ++ (posfixa y) ++ "-" ) (length (posfixa x) ++ " " ++ (posfixa y) ++ "-" ) [])
--posfixa (Mult x y) =   une (finaliza ( (posfixa x) ++ " " ++ (posfixa y) ++ "*" ) (length (posfixa x) ++ " " ++ (posfixa y) ++ "*" ) [])

--finaliza :: String -> Int -> String -> ( String, String)
--finaliza a 0 b = (a, b)
--finaliza (x:xs) a y = if x == '+' || x == '-' || x == '*' then finaliza xs (a-1) (x:y)
--					  else finaliza (xs ++ show x) (a-1) y

--une :: (String, String) -> (String)
--une (a,b)= a ++ b
------------------------------------------------------------------------------------------------------

--fixa :: ExpInt -> String
--fixa (Const x) =  (show x)
--fixa (Simetrico x) = (fixa x) ++ "-")
--fixa (Mais x y) =  (fixa x) ++ " " ++ (fixa y) ++ "+" )
--fixa (Menos x y) = (fixa x) ++ " " ++ (fixa y) ++ "-" )
--fixa (Mult x y) =   (fixa x) ++ " " ++ (fixa y) ++ "*" )

--ordem :: String -> String
--ordem a 0 = a
--ordem (x:xs) a = if x == '+' || x == '-' || x == '*' then ordem xs (a-1) (x:y)
--					  else finaliza (xs ++ show x) (a-1) y
------------------------------------------------------------------------------------------------------

data RTree a = R a [RTree a]

postorder :: (RTree a) -> [a]
postorder (R a []) = [a]
postorder (R a b) = ([a] ++ concat (map postorder b))


soma :: (Num a) => (RTree a) -> a
soma a = sum (postorder a)


--altura :: (RTree a) -> Int
--altura (R a []) = 1
--altura (R a (x:xs)) = 1 + maximo (x:xs)
--				   where maximo [] = 0


--niv:: (RTree a) -> Int
--niv (R a []) = 1
--niv (R a (x:xs)) = 1 + maxi ( map (niv) x maxi xs)
--				 where maxi (R a [], R a (x:xs)) = 1
--				 	   maxi


mirror :: (RTree a) -> (RTree a)
mirror (R a []) = (R a [])
mirror (R a (x:xs)) = R a reverse (mirror x ++ mirror xs)