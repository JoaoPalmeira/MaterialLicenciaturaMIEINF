import Data.Char

toDigits :: Int -> [Int]
toDigits n | n > 0 = let (q,r) = divMod n 10
					 in r : toDigits q
		   | n == 0 = []


fromDigits :: [Int] -> Int
fromDigits l = sum (zipWith fun l [0..])
	
fun :: Int -> Int -> Int
fun d e = d*(10^e)


fromDigits' :: [Int] -> Int
fromDigits' l = sum (zipWith (*) l (aux l))


aux :: [Int] -> [Int] 
aux a = zipWith (^) [10..] [0.. (length a)] 

--case
fromDigits''' :: [Int] -> Int
fromDigits''' l = foldr (\d r->d+10*r) 0 l               



intStr :: Int -> String
intStr x = map (intToDigit) (reverse (toDigits x))

--strInt :: String -> Int
--strInt x = map fromDigits'' (reverse (map digitToInt x)) 

--agrupa :: String -> [(Char,Int)]
--agrupa a =  [(head (fst(span (==) a)), length (fst(span iguais a)))] ++ ( agrupa ( snd (span iguais a)))


--subLists :: [a] -> [[a]]
subLists (x:xs) = snd(span (==x) (x:xs) 


