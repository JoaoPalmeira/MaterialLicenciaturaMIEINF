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
enumFromThenTo1 x y z = if (x<z) then (x : (enumFromThenTo1 y (y+(y-x)) z))
                        else []
--3

(+-+) :: [a] -> [a] -> [a]
(+-+) [] x = x
(+-+) (h:t) x = h:((+-+) t x)
--4

last1 :: [a] -> a
last1 [x] = x
last1 (h:t) = last1 t 
--5

init1 :: [a] -> [a]
init1 [x] = []
init1 (h:t) = h : init1 t
--6

(!-!) :: [a] -> Int -> a
(!-!) (h:t) y = if (y==0) then h
                else (!-!) t (y-1)
--7

reverse1 :: [a] -> [a] 
reverse1 [x] = [x]
reverse1 (h:t) = (reverse t)++[h]
--8

take1 :: Int -> [a] -> [a]
take1 y [] = []
take1 y (h:t) = if (y==0) then []
                else h: take1 (y-1) t
--9

drop1 :: Int -> [a] -> [a] 
drop1 0 x = x
drop1 y (h:t) = drop1 (y-1) t
--10

zip1 :: [a] -> [b] -> [(a,b)]
zip1 [] x = []
zip1 x [] = []
zip1 (h:t) (x:y) = (h,x):(zip1 t y)
--11

elem1 :: Eq a => a -> [a] -> Bool
elem1 x [] = False
elem1 x (h:t) = if (x==h) then True else elem1 x t
--12

replicate1 :: Int -> a -> [a]
replicate1 0 x = []
replicate1 y z = z : replicate1 (y-1) z
--13

intersperse1 :: a -> [a] -> [a]
intersperse1 x [u] = [u]
intersperse1 x (h:t) = h : x : intersperse1 x t
--14

group1 :: Eq a => [a] -> [[a]]
group1 [] = []:[]
group1 [u] = (u:[]):[]
group1 (h:t:r) = if (h==t) then ((h:[])++ head (group1 (t:r))):tail(group1 (t:r))
                 else (h:[]):group1 (t:r)
--15

concat1 :: [[a]] -> [a]
concat1 [] = []
concat1 (h:t) = h ++ concat1 t
--16

inits1 :: [a]->[[a]]
inits1 [] = [[]]
inits1 x = inits1 (init1 x) ++ [x]
--17

tails1 :: [a]->[[a]]
tails1 [] = [[]]
tails1 x = [x] ++ tails1 (tail x)
--18

isPrefixOf1 :: Eq a => [a] -> [a] -> Bool
isPrefixOf1 [] u = True
isPrefixOf1 x [] = False
isPrefixOf1 (h:t) (x:y) = if (h==x) then (isPrefixOf1 t  y) else False
--19

isSuffixOf1 :: Eq a => [a] -> [a] -> Bool
isSuffixOf1 x y = isPrefixOf1 (reverse x) (reverse y)
--20

isSubsequenceOf1 :: Eq a => [a] -> [a] -> Bool
isSubsequenceOf1 [] _ = True
isSubsequenceOf1 _ [] = False
isSubsequenceOf1 (h:t) (x:y) = if (h==x) then (isSubsequenceOf1 t y)
                               else isSubsequenceOf1 (h:t) y
--21

elemIndices1 :: Eq a => a -> [a] -> [Int]
elemIndices1 x y = elemIndicesAux1 x y 0

elemIndicesAux1 :: Eq a => a -> [a] -> Int -> [Int]
elemIndicesAux1 _ [] _ = []
elemIndicesAux1 x (h:t) z = if (x==h) then z:elemIndicesAux1 x t (z+1)
                            else elemIndicesAux1 x t (z+1)
--22

nub1 :: Eq a => [a] -> [a]
nub1 [] = []
nub1 (h:t) = h: nub1 (nub1Aux t h) 

nub1Aux :: Eq a => [a] -> a -> [a]
nub1Aux [] _ = []
nub1Aux (h:t) x = if (h==x) then nub1Aux t x
                  else h:nub1Aux t x
--23

delete1 :: Eq a => a -> [a] -> [a]
delete1 x [] = []
delete1 x (h:t) = if (x==h) then t
                  else h: delete1 x t
--24

(\-\) :: Eq a => [a] -> [a] -> [a]
(\-\) x [] = x
(\-\) (h:t) (x:y) = (\-\) (delete1 x (h:t)) y
--25

union1 :: Eq a => [a] -> [a] -> [a]
union1 [] x = x
union1 (h:t) (x:y) = if (x==h) then h: union1 t y
                     else h: union1 t (x:y)
--26

intersect1 :: Eq a => [a] -> [a] -> [a]
intersect1 [] _ = []
intersect1 (h:t) (x:y) = if (elem1 h (x:y)) then h:intersect1 t (x:y)
                         else intersect1 t (x:y)
--27

insert1 :: Ord a => a -> [a] -> [a]
insert1 x [] = x:[]
insert1 x (h:t) = if (h>=x) then (x:(h:t))
                  else (h:insert1 x t)
--28

maxinum1 :: Ord a => [a] -> a
maxinum1 [x] = x
maxinum1 (h:t:x) = if (h>t) then maxinum1 (h:x)
                   else maxinum1 (t:x)
--29

mininum1 :: Ord a => [a] -> a
mininum1 [x] = x
mininum1 (h:t:x) = if (h<t) then mininum1 (h:x)
                   else mininum1 (t:x)
--30

sum1 :: Num a => [a] -> a
sum1 [] = 0
sum1 (h:t) = h+ (sum1 t)
--31

product1 :: Num a => [a] -> a
product1 [] = 1
product1 (h:t) = h * (product1 t)
--32

and1 :: [Bool] -> Bool
and1 [] = True
and1 (h:t) = h && (and1 t)
--33

or1 :: [Bool] -> Bool
or1 [] = False
or1 (h:t) = h || (or1 t)
--34

unwords1 :: [String] -> String
unwords1 [] = []
unwords1 (h:t) = if (t/=[]) then (h ++ " " ++ (unwords1 t)) 
                 else h
--35

unlines1 :: [String] -> String
unlines1 [] = []
unlines1 (h:t) = if (t/=[]) then (h ++ "\n" ++ (unlines1 t)) 
                 else h
--36

pMaior1 :: Ord a => [a] -> Int
pMaior1 x = pMaior1Aux x (maxinum1 x) 0 


pMaior1Aux :: Ord a => [a] -> a -> Int -> Int
pMaior1Aux (h:t) x a = if (h==x) then a
                       else pMaior1Aux t x (a + 1)
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
posImpares1 [x] = []
posImpares1 [] = []
posImpares1 (h:t:x) = t: posImpares1 x
--40

posPares1 :: [a] -> [a]
posPares1 [x] = [x]
posPares1 [] = []
posPares1 (h:t:x) = h: posPares1 x
--41

isSorted1 :: Ord a => [a] -> Bool
isSorted1 [x] = True
isSorted1 [] = True
isSorted1 (h:t:x) = (h<=t) && isSorted1 (t:x)
--42

iSort1 :: Ord a => [a] -> [a]
iSort1 [] = []
iSort1 x = mininum1 x: iSort1 (delete1 (mininum1 x) x)
--43

menor1 :: String -> String -> Bool
menor1 [] [] = False
menor1 [] _ = True
menor1 _ [] = False  
menor1 (h:t) (x:y) = if (h<x) then True
                     else if (h>x) then False
                     else  menor1 t y
--44

elemMSet1 :: Eq a => a -> [(a,Int)] -> Bool
elemMSet1 x [] = False
elemMSet1 x ((z,y):t) = (x==z) || (elemMSet1 x t)
--45

lengthMSet1 :: [(a,Int)] -> Int
lengthMSet1 [] = 0
lengthMSet1 ((z,y):t) = y + lengthMSet1 t
--46

converteMSet1 :: [(a,Int)] -> [a]
converteMSet1 [] = []
converteMSet1 ((z,y):t) = if (y==0) then converteMSet1 t
                          else z: converteMSet1 ((z, y-1):t)
--47

insereMSet1 :: Eq a => a -> [(a,Int)] -> [(a,Int)]
insereMSet1 x [] = [(x,1)]
insereMSet1 x ((z,y):t) = if (x==z) then ((z, y+1):t)
                         else (z,y):insereMSet1 x t
--48

removeMSet1 :: Eq a => a -> [(a,Int)] -> [(a,Int)]
removeMSet1 x [] = []
removeMSet1 x ((z,y):t) = if (z==x) && (y-1/=0) then ((z,y-1):t)
                          else if (z==x) then t
                          else (z,y): removeMSet1 x t
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
somaPares1 (h:t) = if ((mod h 2)==0) then (h + somaPares1 t)
                   else somaPares1 t

