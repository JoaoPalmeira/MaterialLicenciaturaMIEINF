module QuestoesTeste where

import Data.List
import Data.Char

-- 1)
enumFromTo' :: Int -> Int -> [Int]
enumFromTo' i f = if (i <= f) then (i : (enumFromTo' (i + 1) f)) else []

-- 2)
enumFromThenTo' :: Int -> Int -> Int -> [Int]
enumFromThenTo' i s f = if (i <= f) then (i : (enumFromThenTo' s (s + (s - i)) f)) else []

-- 3)
(+-+) :: [a] -> [a] -> [a]
(+-+) [] list = list
(+-+) (h:ts) list2 = h : ((+-+) ts list2)

-- 4)
last' :: [a] -> a
last' [x] = x
last' (h:ts) = last' ts

-- 5)
init' :: [a] -> [a]
init' [x] = []
init' (h:ts) = h : (init' ts)

-- 6)
(!-!) :: [a] -> Int -> a
(!-!) (h:ts) 0 = h
(!-!) (h:ts) i = (!-!) ts (i-1)

-- 7)
reverse' :: [a] -> [a]
reverse' list = revAux list [] where
	revAux [] new = new
	revAux (h:ts) new = revAux ts (h : new)

-- 8)
take' :: Int -> [a] -> [a]
take' _ [] = []
take' 0 _ = []
take' x (h:ts) = h : (take' (x-1) ts)

-- 9)
drop' :: Int -> [a] -> [a]
drop' 0 l = l
drop' _ [] = []
drop' x (h:ts) = drop' (x-1) ts

-- 10)
zip' :: [a] -> [b] -> [(a,b)]
zip' [] _ = []
zip' _ [] = []
zip' (x:xs) (y:ys) = (x,y) : (zip' xs ys)

-- 11)
elem' :: Eq a => a -> [a] -> Bool
elem' x [] = False
elem' x (h:ts) = if (x == h) then True else elem' x ts

-- 12)
replicate' :: Int -> a -> [a]
replicate' 0 _ = []
replicate' x item = item : (replicate' (x-1) item)

-- 13)
intersperce' :: a -> [a] -> [a]
intersperce' _ [] = []
intersperce' _ [a] = [a]
intersperce' x (h:ts) = h : x : (intersperce' x ts)

-- 14)
group' :: Eq a => [a] -> [[a]]
group' [] = []
group' (h:ts) = l1 : group' l2 where (l1,l2) = span ( == h) (h:ts) -- span :: (a -> Bool) -> [a] -> ([a],[a])

-- 15)
concat' :: [[a]] -> [a]
concat' [] = []
concat' (h:ts) = h ++ concat' ts

-- 16)
inits' :: [a] -> [[a]]
inits' list = initsAux list [] where
		initsAux [] end = [end]
		initsAux (h:ts) list = (list : initsAux ts (list ++ [h]))

-- 17)
tails' :: [a] -> [[a]]
tails' [] = [[]]
tails' (h:ts) = (h:ts) : tails' ts

-- 18)
isPrefixOf' :: Eq a => [a] -> [a] -> Bool
isPrefixOf' [] _ = True
isPrefixOf' list [] = False
isPrefixOf' (x:xs) (y:ys) = if (x == y) then isPrefixOf' xs ys else False

-- 19)
isSuffixOf' :: Eq a => [a] -> [a] -> Bool
isSuffixOf' list1 list2 = isPrefixOf (reverse list1) (reverse list2)

-- 20)
isSubsequentOf' :: Eq a => [a] -> [a] -> Bool
isSubsequentOf' [] _ = True  -- razao antes da False. No caso de ambas listas vazias, dá True.
isSubsequentOf' _ [] = False
isSubsequentOf' (x:xs) (y:ys) = if (x == y) then isSubsequentOf' xs ys else isSubsequentOf' (x:xs) ys

-- 21)
elemIndices' :: Eq a => a -> [a] -> [Int]
elemIndices' elem lista = elemIndicesAux 0 elem lista where
	elemIndicesAux _ _ [] = []
	elemIndicesAux i y (x:xs) = if (x == y) then i : (elemIndicesAux (i + 1) y xs) else elemIndicesAux (i + 1) y xs

-- 22)
nub' :: Eq a => [a] -> [a]
nub' list = nubAux list [] where
	nubAux [] new = new
	nubAux (x:xs) new = if (elem x new == False) then x : (nubAux xs new) else nubAux xs new

-- 23)
delete' :: Eq a => a -> [a] -> [a]
delete' _ [] = []
delete' x (h:ts) = if (x == h) then ts else h : (delete' x ts)

-- 24)
(\-\) :: Eq a => [a] -> [a] -> [a]
(\-\) [] _ = []
(\-\) list [] = list
(\-\) list (x:xs) = (\-\) (delete x list) xs

-- 25)
union :: Eq a => [a] -> [a] -> [a]
union list1 list2 = list1 ++ (list2 \\ list1)

-- 26)
intersect' :: Eq a => [a] -> [a] -> [a]
intersect' [] _ = []
intersect' (h:ts) chList = if (elem h chList == True) then h : (intersect' ts chList) else intersect' ts chList

-- 27)
insert' :: Ord a => a -> [a] -> [a]
insert' x [] = [x]
insert' x (h:ts) = if (x > h) then h : (insert' x ts) else x : (h:ts)

-- 28)
maximum' :: Ord a => [a] -> a
maximum' list = maximumAux (head list) list where
	maximumAux x [] = x
	maximumAux x (h:ts) = if (x > h) then maximumAux x ts else maximumAux h ts

-- 29)
minimum' :: Ord a => [a] -> a
minimum' list = minimumAux (head list) list where
	minimumAux x [] = x
	minimumAux x (h:ts) = if (x < h) then minimumAux x ts else minimumAux h ts

-- 30)
sum' :: Num a => [a] -> a
sum' [] = 0
sum' (h:ts) = h + sum' ts

-- 31)
product' :: Num a => [a] -> a
product' [] = 1
product' (h:ts) = h * (product' ts)

-- 32)
and' :: [Bool] -> Bool
and' [] = True
and' (h:ts) = if (h) then and' ts else h

-- 33)
or' :: [Bool] -> Bool
or' [] = False
or' (h:ts) = if (h) then h else or' ts

-- 34)
unwords' :: [String] -> String
unwords' [] = []
unwords' [x] = x
unwords' (h:ts) = h ++ " " ++ unwords' ts

-- 35)
unlines' :: [String] -> String
unlines' [] = []
unlines' [x] = x	-- na definição original, este ponto de paragem não existe;
unlines' (h:ts) = h ++ "\n" ++ (unlines' ts)

-- 36)
pMaior :: Ord a => [a] -> Int
pMaior list = pMaiorAux 0 (head list) 0 list where
	pMaiorAux iMaior maior iAgora [] = iMaior
	pMaiorAux iMaior maior iAgora (h:ts) = if (maior < h) then pMaiorAux iAgora h (iAgora + 1) ts else pMaiorAux iMaior maior (iAgora + 1) ts

-- 37)
temRepetidos :: Eq a => [a] -> Bool
temRepetidos [] = False
temRepetidos (h:ts) = if (elem h ts) then True else temRepetidos ts

-- 38)
algarismos :: String -> String
algarismos "" = ""
algarismos (h:ts) = if ((h >= '0') && (h <= '9')) then h : (algarismos ts) else algarismos ts

-- 39)
posImpares :: [a] -> [a]
posImpares [] = []
posImpares [x] = []
posImpares [x,y] = [y]
posImpares (h:m:ts) = m : posImpares ts

-- ou com uma função auxiliar:
-- posImpares list = posImparesAux 0 list where
-- 		posImparesAux _ [] = []
-- 		posImparesAux i (h:ts) = if (mod i 2 == 0) then posImparesAux ts else h : (posImparesAux ts)

-- 40)
posPares :: [a] -> [a]
posPares [] = []
posPares [x] = [x]
posPares [x,y] = [x]
posPares (h:m:ts) = h : posPares ts

-- ou com uma função auxiliar:
-- posPares list = posParesAux 0 list where
-- 		posParesAux _ [] = []
-- 		posParesAux i (h:ts) = if (mod i 2 == 0) then h : (posParesAux ts) else posParesAux ts

-- 41)
isSorted :: Ord a => [a] -> Bool
isSorted [] = True
isSorted [x] = True
isSorted (h:ts) = if (h > (head ts)) then False else isSorted ts

-- 42)
iSort :: Ord a => [a] -> [a]
iSort list = iSortAux list [] where
	iSortAux [] new = new
	iSortAux (h:ts) new = iSortAux ts (insert h new)

-- 43)
menor :: String -> String -> Bool
menor "" _ = True
menor _ "" = False
menor (x:xs) (y:ys) | (toLower x) > (toLower y) = False
					| (toLower x) == (toLower y) = menor xs ys
					| (toLower x) < (toLower y) = True

-- 44)
elemMSet :: Eq a => a -> [(a,Int)] -> Bool
elemMSet _ [] = False
elemMSet z ((x,y):ts) = if (z == x) then True else elemMSet z ts

-- 45)
lengthMSet :: [(a,Int)] -> Int
lengthMSet [] = 0
lengthMSet ((x,y):ts) = y + (lengthMSet ts)

-- 46)
converteMSet :: [(a,Int)] -> [a]
converteMSet [] = []
converteMSet ((x,y):ts) = if (y > 0) then x : (converteMSet ((x,(y - 1)):ts)) else converteMSet ts

-- 47)
insereMSet :: Eq a => a -> [(a,Int)] -> [(a,Int)]
insereMSet z [] = [(z,1)]
insereMSet z ((x,y):ts) = if (z == x) then (x,(y + 1)) : ts else (x,y) : (insereMSet z ts)

-- 48)
removeMSet :: Eq a => a -> [(a,Int)] -> [(a,Int)]
removeMSet _ [] = []
removeMSet z ((x,y):ts) = if (z == x) then (if (y > 1) then (x,(y - 1)) :ts else ts) else (x,y) : (removeMSet z ts)

-- 49)
constroiMSet :: Ord a => [a] -> [(a,Int)]
constroiMSet [] = []
constroiMSet list = cMSAux (tail list) (head list) 1 [] where
	cMSAux [] agora qts new = new ++ [(agora, qts)]
	cMSAux (h:ts) agora qts new = if (h == agora) then cMSAux ts agora (qts + 1) new else cMSAux ts h 1 (new ++ [(agora, qts)])

-- 50)
somaPares :: [Int] -> Int
somaPares [] = 0
somaPares (h:ts) = if (mod h 2 == 0) then h + (somaPares ts) else somaPares ts
