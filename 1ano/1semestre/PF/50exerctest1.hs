module Main where



   reverse :: [a] -> [a]
   reverse [] = []
   reverse l : last l : reverse (init l)