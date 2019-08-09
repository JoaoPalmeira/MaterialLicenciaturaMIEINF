module Main where

import Data.Char
import Data.List
import Data.String

--Parte1

data ArvBin a = Vazia | Nodo a (ArvBin a) (ArvBin a)
--1

posNeg ::  [Int] -> (Int,Int)
posNeg (h:t)  = posNegaux (h:t) (0 , 0)

posNegaux ::  [Int] -> (Int, Int) -> (Int,Int)
posNegaux [] (y, z) = (y , z)
posNegaux (0:xs) (y, z) = posNegaux xs (y+1 , z)
posNegaux (x:xs) (y, z) = if (x>0) then posNegaux xs (y+1 , z)
                          else posNegaux xs (y , z+1)

--2

tiraPrefixo ::  String -> String -> Maybe String
tiraPrefixo [] x = Just x
tiraPrefixo (x:xs) (y:ys) = if (x==y) then tiraPrefixo xs ys
                            else Nothing

--3

--fun f l = product (map f (filter (>0) l))

fun :: (Int->Int) -> [Int] -> Int
fun f [] = 1
fun f (x:xs) = if (x>0) then (f x) * (fun f xs)
               else fun f xs

--4
--data ArvBin a = Vazia | Nodo a (ArvBin a) (ArvBin a)

--insere :: Ord a => a -> ArvBin a -> ArvBin a


--5
type Concorrentes = [(Numero, String)]
type Numero = Int -- numero e nome
type Prova = [(Numero, Float)] -- numero e tempo gasto na prova

--a

nomes :: Prova -> Concorrentes -> [(String,Float)]
nomes (x:xs) y = (nomesAux x y):(nomes xs y)

nomesAux :: (Numero,Float) -> Concorrentes -> (String,Float)
nomesAux (a,b) ((x,y):z) = if (a==x) then (y,b)
                               else nomesAux (a,b) z

--b

ordena :: Prova -> Prova
ordena [] = []
ordena ((n1,f1):t) = let t' = ordena t
                     in inserir (n1,f1) t'

inserir :: (Numero,Float) -> Prova -> Prova
inserir (n1,f1) ((n2,f2):t) | (f1<f2) = (n1,f1):(n2,f2):t
                            | otherwise = (n2,f2):(n1,f1):t

--Parte2

--1

camValido :: ArvBin a -> [Bool] -> Bool






















