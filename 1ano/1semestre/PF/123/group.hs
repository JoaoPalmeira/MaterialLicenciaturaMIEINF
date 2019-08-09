module Main where

import Data.Char
import Data.List
import Data.String

--group :: Eq a => [a] -> [[a]]
--group [] = []:[]
--group [x] = [x]:[]
--group (h:t) = replicate (groupAux (h:t) h) ++ replicate (groupAux (h:t) t)

--groupAux :: Eq a => [a] -> a -> Int
--groupAux [] _ = 0
--groupAux (h:t) a = if (h==a) then 1+ groupAux t a
--                   else 0 + groupAux t a 
replicate1 :: Int -> a -> [a]
replicate1 0 x = []
replicate1 x y = y: (replicate1 (x-1) y)

group1 :: Eq a => [a] -> [[a]]
group1 [] = [[]]
group1 (a:b) = (replicate1 (groupAux a b) a): group1 b

groupAux :: Eq a => a -> [a] -> Int
groupAux a (b:c) = if (a==b) then 1 + (groupAux b c)
                   else 0 + (groupAux b c)