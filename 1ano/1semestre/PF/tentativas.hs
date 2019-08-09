module Main where

import Data.Char
import Data.List
import Data.String

--1

enumFromToS :: Int -> Int -> [Int]
enumFromToS x y = if (x < y) then x:(enumFromToS (x + 1) y)
                 else if y<x then y:(enumFromToS x (y + 1))
                 else [y]

--2


--3

(+-+) :: [a] -> [a] -> [a]
(+-+) [] x = x
(+-+) (h:t) x = h: ((+-+) t x)

--4

lastS :: [a] -> a
lastS [x] = x
lastS (h:t) = lastS t

--5

initS :: [a] -> [a]
initS [x] = []
initS  (h:t) = h: initS t

--6

(!-!) :: [a] -> Int -> a
(!-!) (h:t) z = if (z==0) then h
                else (!-!) t (y-1)

--7

reverseS :: [a] -> [a]
reverseS [x] = [x]
reverseS (h:t) = (reverseS t) ++ [h]

--8

takeS :: Int -> [a] -> [a]
takeS y [] = []
takeS y (h:t) = if (y==0) then []
                else h: takeS (y-1) t

--9 

dropS :: Int -> [a] -> [a] 
dropS 0 x = x
dropS x (h:t) = (dropS (x-1) t)

--10

zipS :: [a] -> [b] -> [(a,b)]
zipS [] x = []
zipS x [] = []
zipS (h:t) (x:y) = (h,t) : zipS t y

--11

elemS :: Eq a => a -> [a] -> Bool
elemS _ [] = False
elemS x (h:t) = if (x==h) then True
                else elemS x t

--12

replicateS :: Int -> a -> [a]
replicateS 0 x = []
replicateS x y = y:(replicateS (x-1) y

--13

intersperseS :: a -> [a] -> [a]
intersperseS x [u] = [u]
intersperseS x (h:t) = h:x:intersperseS x t

--14

groupS :: Eq a => [a] -> [[a]]
groupS [] = []
groupS [x] = (x:[]):[]
groupS (h:t:r) = if (h==t) then ((h:[])++ head (groupS (t:r))):tail(groupS (t:r))
                 else (h:[]):groupS (t:r)

--15

concatS :: [[a]] -> [a]
concatS [] = []
concatS (h:t) = h ++ concatS t

--16

initsS :: [a] -> [[a]]
initsS [] = [[]]
initsS x = initsS (initS x) ++ [x]

--17

tailsS :: [a] -> [[a]]
tailsS [] = [[]]
tailsS x = [x] ++ tailsS (tail x)

--18

isPrefixOfS :: Eq a => [a] -> [a] -> Bool
isPrefixOfS [] u = True
isPrefixOfS u [] = False
isPrefixOfS (h:t) (x:y) = if (h==x) then (isPrefixOfS t y)
                          else False

--19

--isSuffixOf :: Eq a => [a] -> [a] -> Bool
--isSuffixOf 
--isSuffixOf
--isSuffixOf

--20

isSubsequenceOfS :: Eq a => [a] -> [a] -> Bool
isSubsequenceOfS [] _ = True
isSubsequenceOfS _ [] = False
isSubsequenceOfS (h:t) (x:y) = if (h==x) then isSubsequenceOfS t y
                               else (isSubsequenceOfS (h:t) y)

--21

elemIndicesS :: Eq a => a -> [a] -> [Int]
elemIndicesS x y = elemIndicesSAux x y 0

elemIndicesSAux :: Eq a => a -> [a] -> Int -> [Int]
elemIndicesSAux _ [] _ = []
elemIndicesSAux x (h:t) z = if (x==h) then z: elemIndicesSAux x t (z+1)
                            else elemIndicesSAux x t (z+1) 

--22

nubS :: Eq a => [a] -> [a]
nubS [] = []
nubS (h:t) = h: nubS (nubSAux h t)

nubSAux :: Eq a => a -> [a] -> [a]
nubSAux _ [] = []
nubSAux x (h:t) = if (x==h) then nubSAux x t
                  else h: nubSAux x t

--23

deleteS :: Eq a => a -> [a] -> [a]
deleteS _ [] = []
deleteS x (h:t) = if (x==h) then t
                  else h: deleteS x t

--24

(\-\) :: Eq a => [a] -> [a] -> [a]
(\-\) [] x = []
(\-\) x [] = x
(\-\) (h:t) (x:y) = if (h==x) then (\-\) t y
                    else h: (\-\) t (x:y)

--25

unionS :: Eq a => [a] -> [a] -> [a]
unionS [] x = x
unionS (h:t) (x:y) = if (h==x) then h: unionS t y
                     else h: unionS t (x:y)

--26

intersectS :: Eq a => [a] -> [a] -> [a]
intersectS [] _ = []
intersectS (h:t) (x:y) = if (elem h (x:y)) then h: intersectS t (x:y)
                         else intersectS t (x:y)

--27

insertS :: Ord a => a -> [a] -> [a]
insertS x [] = x:[]
insertS x (h:t) = if (h>=x) then (x:(h:t))
                  else h: (insertS x t)

--28

maxinumS :: Ord a => [a] -> a
maxinumS [x] = x
maxinumS (h:t:r) = if (h>t) then maxinumS (h:r)
                   else maxinumS (t:r)

--29

mininum1 :: Ord a => [a] -> a
mininum1 [x] = x
mininum1 (h:t:x) = if (h<t) then mininum1 (h:x)
                   else mininum1 (t:x)

--30

sumS :: Num a => [a] -> a
sumS [] = 0
sumS (h:t) = h + (sumS t)

--31

productS :: Num a => [a] -> a
productS [] = 1
productS (h:t) = h * (productS t)

--32

andS :: [Bool] -> Bool
andS [] = True
andS (h:t) = h && (andS t)

--33

orS :: [Bool] -> Bool
orS [] = True
orS (h:t) = h || (orS t)

--34

unwordsS :: [String] -> String
unwordsS [] = []
unwordsS (h:t) = if (h/=[]) then (h ++ " " ++ (unwordsS t))
                 else h

--35

unlines1 :: [String] -> String
unlines1 [] = []
unlines1 (h:t) = if (t/=[]) then (h ++ "\n" ++ (unlines1 t)) 
                 else h

--36

--pMaior :: Ord a => [a] -> Int
--pMaior 

--37

temRepetidos1 :: Eq a => [a] -> Bool
temRepetidos1 [] = False
temRepetidos1 (h:t) = (elem1 h t) || temRepetidos1 t

--38

algarismos :: [Char] -> [Char]


--39

posImparesS :: [a] -> [a]
posImparesS [x] = []
posImparesS [] = []
posImparesS (h:t:z) = t : posImparesS z

--40

posParesS :: [a] -> [a]
posParesS [x] = [x]
posParesS [] = []
posParesS (h:t:z) = h : posParesS z

--46

converteMSetS :: [(a,Int)] -> [a]
converteMSetS [] = []
converteMSetS ((x,y):t) = if (y==0) then converteMSetS t
                          else x : converteMSetS((x,y-1):t)










