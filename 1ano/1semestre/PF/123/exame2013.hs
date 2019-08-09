module Main where

import Data.Char
import Data.List
import Data.String
--Parte2
--1
sufixos :: [a] -> [[a]]
sufixos [] = [[]]
sufixos (h:t) = ((h:t) : sufixos t)
--2
dropWhile1 :: (a->Bool) -> [a] -> [a]
dropWhile1 _ [] = []
dropWhile1 f (h:t) = if (f h == True) then (h: dropWhile1 f t)
                     else dropWhile1 f t
--3
data Alunos = Vazia | Nodo (Numero,Nome,Nota) Alunos Alunos
type Numero = Int
type Nome = String
type Nota = Int
--a
aprovados :: Alunos -> Int
aprovados a = let (ap,total) = conta a
              in (div (ap * 100) total)
conta :: Alunos -> (Int, Int)
conta a = (aprov a, totalidade a)
aprov :: Alunos -> Int
aprov Vazia = 0
aprov (Nodo (x,y,z) e d) = if (z>10) then 1 + aprov e + aprov d
                           else aprov e + aprov d
totalidade :: Alunos -> Int
totalidade Vazia = 0
totalidade (Nodo a e d) = 1 + totalidade e + totalidade d
--b
nota :: Numero -> Alunos -> Maybe Nota
nota _ Vazia = Nothing
nota x (Nodo (a,b,c) e d) = if (x==a) then Just c
                            else if (x>a) then nota x e
                            else if (x<a) then nota x d
                            else Nothing
--4
type ConjInt = [Intervalo]
type Intervalo = (Int,Int)
--a
pertence :: Int -> ConjInt -> Bool
pertence _ [] = False
pertence a ((x,y):z) = if (a>=x) && (a<=y) then True
                       else pertence a z
--b
quantos :: ConjInt -> Int
quantos [] = 0
quantos ((x,y):t) = (y-x+1) + quantos t

--Parte2
--1
--a
elems :: ConjInt -> [Int]
elems [] = []
elems ((x,y):t) = elemsAux (x,y) ++ elems t 
elemsAux :: Intervalo -> [Int]
elemsAux (x,y) = if (x==y) then [x]
                 else x : (elemsAux ((x+1),y))
--b
--geraconj :: [Int] -> ConjInt
--geraconj [] = []
--geraconj (h:t:z) = if (t/=h+1) then (h,h): geraconj (t:z:x)
--                     else if (t == h+1) then 
--                     else (h,t) : geraconj (z:x)

geraconj :: [Int] -> ConjInt
geraconj [] = []
geraconj (a:b:c) = if (b/=a+1) then (a,a) : geraconj (b:c)
                   else if (b==a+1) then (a,a+1): geraconj (b:c)
                   else (a,b): geraconj (b:c)

geraAux :: Int -> Int -> Int -> Int
geraAux h t z = if (t+1/=z) then t-h
                else 






















