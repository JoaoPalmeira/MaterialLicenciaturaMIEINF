--exame2012
--Parte1
--1
posNeg :: [Int] -> (Int,Int)
posNeg [] = (0,0)
posNeg l = ((posNegaux l), (length l)-(posNegaux l))

posNegaux :: [Int] -> Int
posNegaux [] = 0
posNegaux (a:b) = if (a>=0) then (1+(posNegaux b))
                  else posNegaux b
--2
tiraPref :: String -> String -> Maybe String
tiraPref [] l = Just l
tiraPref a@(h:t) b@(d:e) = if ((length a)>(length b)) then Nothing 
                           else if (h==d) then tiraPref t e 
                           else Nothing
--3
--fun f l = product (map f (filter (>0) l))
fun :: (Int->Int) -> [Int] -> Int
fun a (h:t) = if (h>0) then (a h) * (fun a t)
              else fun a t
--4
data ArvBin a = Vazia | Nodo a (ArvBin a) (ArvBin a)
insere :: Ord a => a -> ArvBin a -> ArvBin a
insere x Vazia = Nodo x Vazia Vazia
insere x (Nodo a e d) | (x>a) = Nodo a e (insere x d)
                      | (x<a) = Nodo a (insere x e) d
                      | (x==a) = Nodo a e d
--Parte1
--1
--a
camValido :: ArvBin a -> [Bool] -> Bool
camValido Vazia _ = True
camValido (Nodo r e d) [] = False
camValido (Nodo r e d) (x:xs) | (x==True) = camValido d xs
                              | (x==False) = camValido e xs
--b
--caminho :: (Eq a) => ArvBin a -> a -> Maybe [Bool]
--caminho Vazia _ = Nothing
--caminho (Nodo a e d) = 

--caminhoAux :: (Eq a) => a -> ArvBin a -> Bool
--caminhoAux _ Vazia = False
--caminhoAux y (Nodo a e d) = if (y==a) then True
--                            else ((caminhoAux y e) || (caminhoAux y d))

caminho1 :: (Eq a) => ArvBin a -> a -> Maybe [Bool]
caminho1 Vazia x = Nothing
caminho1 (Nodo x l r) y | y==x = Just []
                       | otherwise = case caminho1 l y of
                                          Nothing -> case caminho1 r y of Nothing -> Nothing
                                          Just b -> Just (False:b)
                                          Just a -> Just (True:a)











