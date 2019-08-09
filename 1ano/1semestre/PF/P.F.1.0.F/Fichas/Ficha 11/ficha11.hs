import System.IO.Error

data Aposta = Ap [Int] (Int,Int)


--a
valida::Aposta -> Bool
valida (Ap l (x,y)) = x /= y && x>=9 && y>=9 && length l == 5 && (mVal l)

mVal:: [Int] -> Bool
mVal [] = True
mVal (x:xs) | x>=1 && x<=50 && not( elem x xs) = mVal xs
			| otherwise = False
--b
comuns :: Aposta -> Aposta -> (Int,Int)
comuns (Ap le (x1,y1)) (Ap lr (x2,y2)) = ( comL le lr, comL [x1,y1] [x2,y2] )

comL :: [Int] -> [Int] -> Int
comL (x:xs) l | elem x l = 1 + (comL xs l)
			  | otherwise = comL xs l
comL [] l = 0

instance  Eq Aposta where
	a == b = comuns a b == (5,2)


premio :: Aposta -> Aposta -> Maybe Int
premio a c | (comuns a c) == (5,2) = Just 1
		   | (comuns a c) == (5,1) = Just 2
		   | (comuns a c) == (5,0) = Just 3
		   | (comuns a c) == (4,2) = Just 4
		   | (comuns a c) == (4,1) = Just 5
		   | (comuns a c) == (4,0) = Just 6
		   | (comuns a c) == (3,2) = Just 7
		   | (comuns a c) == (2,2) = Just 8
		   | (comuns a c) == (3,1) = Just 9
		   | (comuns a c) == (3,0) = Just 10
		   | (comuns a c) == (2,1) = Just 11
		   | (comuns a c) == (1,2) = Just 12
		   | (comuns a c) == (2,0) = Just 13
		   | otherwise = Nothing

--leAposta:: IO Aposta
--leAposta = do putStr "Escolha uma lista de 5 numeros (1..50)"
--			  num <- getLine
--			  putStr "Escolha um par de estrelas (1..9)"
--			  est <- getLine
--			  x <- tryIOError (readIO num)
--			  y <- tryIOError (readIO est)
--			  case (x, y) of
--			  	(right n, right e) ->  let ap = Ap n e--(read num) (read est)
--			 						   if (validas ap) then return ap
--			  				  		   				   else putStr "Erro, aposta nula" >> leAposta
			 


--tryIOError :: IO a -> IO (Either IOError a)
--readIO :: Read a => String -> IO a


joga :: Aposta -> IO ()
joga c = do ap <- leAposta
			case (premio ap c) of 
				just n -> putStrLn ("Tem o" ++ (show n) ++ "premio")
				Nothing -> putStrLn "Nao tem premio"


geraChave :: IO Aposta
geraChave = do x <- geralist 5 []
			   e <- geraEst
			   return (Ap n e)

geraEst::IO (Int, Int)
geraEst = do x <- randomRIO (1, 11)
			 y <- randomRIO (1, 11)
			 if x == y then geraEst
			 else return (x,y)

geralist :: Int -> [Int] -> IO [Int]
geralist 0 l = return l
geralist n l = do x <- randomRIO(1,50)
				  if (elem x l) then geralist n l
				  else geralist (n-1) (x:l)



