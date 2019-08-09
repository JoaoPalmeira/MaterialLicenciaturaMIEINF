module Main where

import Data.Char
import Data.List
import Data.String

--1
enumFromTo1 :: Int -> Int -> [Int]
enumFromTo1 x y = if (x < y) then x:(enumFromTo1 (x + 1) y)
                  else if y<x then y:(enumFromTo1 x (y + 1))
                  else [y]
--2
enumFromThenTo1 :: Int -> Int -> Int -> [Int]
enumFromThenTo1 a b c = if (a<c) then (a: (enumFromThenTo1 b (b+(b-a)) c))
                        else []
--3
maismais :: [a] -> [a] -> [a]
maismais [] x = x
maismais (h:t) z = h: (maismais t z)
--4
last1 :: [a] -> a
last1 [x] = x
last1 (x:y) = last1 y
--5
init1 :: [a] -> [a]
init1 [x] = []
init1 (h:t) = h: init1 t
--6
(!-!) :: [a] -> Int -> a
(!-!) (x:y) a = if (a==0) then x
                else (!-!) y (a-1)
--7
reverse1 :: [a] -> [a]
reverse1 [x] = [x]
reverse1 (h:t) = (reverse1 t) ++ [h]
--8
take1 :: Int -> [a] -> [a]
take1 x [] = []
take1 a (x:y) = if (a==0) then []
                else x: take1 (a-1) y
--9
drop1 :: Int -> [a] -> [a]
drop1 0 l = l
drop1 x (h:t) = if (x==1) then t
                else drop1 (x-1) t
--10
zip1 :: [a] -> [b] -> [(a,b)]
zip1 [] l = []
zip1 l [] = []
zip1 (a:b) (c:d) = (a,c):(zip1 b d)
--11
elem1 :: Eq a => a -> [a] -> Bool
elem1 x [] = False
elem1 a (b:c) = if (a==b) then True 
                else elem1 a c
--12
replicate1 :: Int -> a -> [a]
replicate1 0 x = []
replicate1 x y = y: (replicate1 (x-1) y)
--13
intersperse1 :: a -> [a] ->[a]
intersperse1 _ [x] = [x]
intersperse1 x (y:z) = y:x:(intersperse1 x z)
--14
group1 :: Eq a => [a] -> [[a]]
group1 [] = []:[]
group1 [x] = (x:[]):[]
group1 (h:t:r) = if (h==t) then ((h:[])++ head (group1 (t:r))):tail(group1 (t:r))
                 else (h:[]):group1 (t:r)
--15
concat1 :: [[a]] -> [a]
concat1 [] = []
concat1 (h:t) = (h ++ concat1 t)
--16
inits1 :: [a] -> [[a]]
inits1 [] = [[]]
inits1 x = inits1 (init1 x) ++ [x]
--17
tails1 :: [a] -> [[a]]
tails1 x = reverse1 (inits1 x)
--18
isPrefixOf1 :: Eq a => [a] -> [a] -> Bool
isPrefixOf1 [] x = True
isPrefixOf1 x [] = False
isPrefixOf1 (x:y) (h:t) = if (x==h) then (isPrefixOf1 y t)
                          else False
--19
isSuffixOf1 :: Eq a => [a] -> [a] -> Bool
isSuffixOf1 x y = isPrefixOf1 (reverse1 x) (reverse1 y) 
--20
isSubsequenceOf1 :: Eq a => [a] -> [a] -> Bool
isSubsequenceOf1 [] _ = True
isSubsequenceOf1 _ [] = False
isSubsequenceOf1 (h:t) (x:y) = if (h==x) then (isSubsequenceOf1 t y)
                               else (isSubsequenceOf1 (h:t) y)
--21
elemIndices1 :: Eq a => a -> [a] -> [Int]
elemIndices1 x y = elemIndicesAux x y 0

elemIndicesAux :: Eq a => a -> [a] -> Int -> [Int]
elemIndicesAux _ [] _ = []
elemIndicesAux a (h:t) b = if (a==h) then b: (elemIndicesAux a t (b+1))
                           else (elemIndicesAux a t (b+1))
--22
nub1 :: Eq a => [a] -> [a]
nub1 [] = []
nub1 (h:t) = h: nub1 (nubAux t h)

nubAux :: Eq a => [a] -> a -> [a]
nubAux [] _ = []
nubAux (h:t) x = if (h==x) then (nubAux t x)
                 else h:(nubAux t x)
--23
delete1 :: Eq a => a -> [a] -> [a]
delete1 x [] = []
delete1 x (h:t) = if (x==h) then t
                  else h:(delete1 x t)
--24
(\-\) :: Eq a => [a] -> [a] -> [a]
(\-\) x [] = x
(\-\) [] x = []
(\-\) (h:t) (x:y) = if (h==x) then ((\-\) t y)
                    else h: ((\-\) t (x:y))
--25
union1 :: Eq a => [a] -> [a] -> [a]
union1 x [] = x
union1 [] x = x
union1 (h:t) (x:y) = if (h==x) then h:(union1 t y)
                     else h: (union1 t (x:y))
--26
intersect1 :: Eq a => [a] -> [a] -> [a]
intersect1 [] x = []
intersect1 x [] = []
intersect1 (h:t) (x:y) = if (elem1 h (x:y)) then h:(intersect1 t (x:y))
                         else intersect1 t (x:y)
--27
insert1 :: Ord a => a -> [a] -> [a]
insert1 x [] = x:[]
insert1 x (h:t) = if (h>=x) then (x:(h:t))
                  else (h:insert1 x t)
--28
maximum1 :: Ord a => [a] -> a
maximum1 [x] = x
maximum1 (h:t:z) = if (h<t) then maximum1 (t:z)
                   else maximum1 (h:z)
--29
minimum1 :: Ord a => [a] -> a
minimum1 [x] = x
minimum1 (h:t:z) = if (h<t) then minimum1 (h:z)
                   else minimum1 (t:z)
--30
sum1 :: Num a => [a] -> a
sum1 [] = 0
sum1 (h:t) = h+ (sum1 t)
--31
product1 :: Num a => [a] -> a
product1 [x] = x
product1 (h:t) = h * product1 t
--32
and1 :: [Bool] -> Bool
and1 [] = True
and1 (h:t) = if (h==True) then and1 t
             else False
--33
or1 :: [Bool] -> Bool
or1 [] = True
or1 (h:t) = if (h==True) then True
             else or1 t
--34
unwords1 :: [String] -> String
unwords1 [] = []
unwords1 (h:t) = if (t/=[]) then (h++" "++ (unwords1 t))
                 else h
--35
unlines1 :: [String] -> String
unlines1 [] = []
unlines1 (h:t) = if (t/=[]) then (h++"\n"++ (unwords1 t))
                 else h
--36
pMaior1 :: Ord a => [a] -> Int
pMaior1 x = pMaiorAux x (maximum1 x) 0 

pMaiorAux :: Ord a => [a] -> a -> Int -> Int
pMaiorAux (h:t) x a = if (h==x) then a
                      else pMaiorAux t x (a + 1)
--37
temRepetidos1 :: Eq a => [a] -> Bool
temRepetidos1 [] = False
temRepetidos1 (h:t) = (elem1 h t) || temRepetidos1 t
--38
algarismos1 :: [Char] -> [Char]
algarismos1 [] = []
algarismos1 (h:t) = if (h>='0') && (h<='9') then h: algarismos1 t
                    else algarismos1 t
--39
posImpares1 :: [a] -> [a]
posImpares1 [] = []
posImpares1 [x] = []
posImpares1 (h:t:z) = t:(posImpares1 z)
--40
posPares1 :: [a] -> [a]
posPares1 [] = []
posPares1 [x] = [x]
posPares1 (h:t:z) = h:(posImpares1 z)
--41
isSorted1 :: Ord a => [a] -> Bool
isSorted1 [] = True
isSorted1 [x] = True
isSorted1 (h:t:z) = (h<=t) && isSorted1 (t:z)
--42
iSort1 :: Ord a => [a] -> [a]
iSort1 [] = []
iSort1 x = minimum1 x : iSort1 (delete1 (minimum1 x) x)
--43
menor :: String -> String -> Bool
menor "" _ = True
menor _ "" = False
menor (x:xs) (y:ys) | (toLower x) > (toLower y) = False
                    | (toLower x) == (toLower y) = menor xs ys
                    | (toLower x) < (toLower y) = True
--44
elemMSet1 :: Eq a => a -> [(a,Int)] -> Bool
elemMSet1 x [] = False
elemMSet1 x ((a,b):c) = if (x==a) then True
                        else elemMSet1 x c
--45
lengthMSet1 :: [(a,Int)] -> Int
lengthMSet1 [] = 0
lengthMSet1 ((h,t):z) = t + (lengthMSet1 z)
--46
converteMSet1 :: [(a,Int)] -> [a]
converteMSet1 [] = []
converteMSet1 ((h,t):z) = if (t==0) then converteMSet1 z
                          else h: converteMSet1 ((h, t-1):z)
--47
insereMSet1 :: Eq a => a -> [(a,Int)] -> [(a,Int)]
insereMSet1 x [] = [(x,1)]
insereMSet1 x ((a,b):c) = if (x==a) then ((a,b+1):c)
                          else (a,b):insereMSet1 x c
--48
removeMSet1 :: Eq a => a -> [(a,Int)] -> [(a,Int)]
removeMSet1 x [] = []
removeMSet1 x ((a,b):c) = if (x==a) && (b-1/=0) then ((a,b-1):c) 
                          else if (x==a) then c
                          else (a,b):removeMSet1 x c
--49
constroiMSet1 :: Ord a => [a] -> [(a,Int)]
constroiMSet1 [] = []
constroiMSet1 (h:t) = (h,constroiMSet1Aux h (h:t)) : constroiMSet1 (drop1 (constroiMSet1Aux h (h:t)) (h:t))
                          
constroiMSet1Aux :: Ord a => a -> [a] -> Int
constroiMSet1Aux z [] = 0
constroiMSet1Aux z (x:y) = if (z==x) then (1 + constroiMSet1Aux z y)
                           else 0
--50
somaPares1 :: [Int] -> Int
somaPares1 [] = 0
somaPares1 (h:t) = if ((mod h 2)==0) then h + somaPares1 t
                   else somaPares1 t 


--constroiMSet2 :: Ord a => [a] -> [(a,Int)]
--constroiMSet2 [] = []
--constroiMSet2 (a:b:c) = if (a==b) then (((a,(cmsaux a (b:c)1))): constroiMSet2 b c)
--                       else ((a,1):constroiMSet2 b c)

--cmsaux :: Ord a => a -> [a] -> Int -> Int
--cmsaux a [] d = ((a,1))
--cmsaux a (b:c) d = if (a==b) then d + cmsaux a c (d+1)
--                   else cmsaux a c d
















