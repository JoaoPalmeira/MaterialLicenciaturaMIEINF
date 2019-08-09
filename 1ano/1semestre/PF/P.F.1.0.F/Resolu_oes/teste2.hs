data Tree a = Empty
			| Leaf a
			| Fork (Tree a) (Tree a)


instance (Show a)=> Show (Tree a) where
	show Empty = "<>"
	show (Leaf a) = show a
	show (Fork a b) = "(" ++ (show a) ++ " <-*-> " ++ (show b) ++ ")"


--ultimo :: Tree a -> Maybe a
--ultimo Empty = Nothing
--ultimo (Fork a b) = last (aux (Fork a b))


--aux :: Tree a -> [a]
--aux Empty = []
--aux (Fork a b) = if b == Empty && a != Empty then (a: aux b)
--				 else if b == Empty && a == Empty ()
--				 else (b: aux b)

--arv :: Tree a ->  [a]
--arv Empty = []
--arv Leaf a = [a]
--arv (Fork a b) = if b == Empty && a /= Empty then (a: arv a)
--				 else if b == Empty && a == Empty then []
--				 else (b: arv b)



ultimo :: Tree a -> Maybe a
ultimo Empty = Nothing
ultimo (Leaf a) = Just a
ultimo (Fork a b) = ultimo b
--nao funciona corretamente

--rafado
ultimoraf :: Tree a -> Maybe a
ultimoraf Empty = Nothing
ultimoraf (Leaf a) = Just a
ultimoraf (Fork a b) = Just (last ( crialist (Fork a b) ))

crialist :: Tree a -> [a]
crialist Empty = []
crialist ( Leaf a ) = [a]
crialist (Fork a b) = ((crialist a) ++ (crialist b))


--------------------------------------------------------

diflist :: Tree a -> [Maybe a]
diflist Empty = [Nothing]
diflist ( Leaf a ) = [Just a]
diflist (Fork a b) = ((diflist a) ++ (diflist b))


apaguei :: Eq a => a -> Tree a -> Tree a
apaguei x Empty = Empty
apaguei x (Leaf a) = if x == a then Empty
					 else (Leaf a)

apaga :: Eq a => a -> Tree a -> Tree a
apaga x Empty = Empty
apaga x (Fork a b) = (Fork (apaguei x a) (apaguei x b))


---------------------------------------------------------

--limpa :: Tree a -> Tree a
--limpa  Empty = Empty
--limpa (Leaf a) = Leaf a
--limpa (Fork a b) = if a == Empty && b /= Empty then limpa b
--				   else if a /= Empty && b == Empty then limpa a
--				   else (Fork (limpa a) (limpa b))




--como mexer nesta arvore??