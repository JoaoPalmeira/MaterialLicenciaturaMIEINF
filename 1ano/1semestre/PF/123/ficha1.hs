module Main where

import Data.Char
import Data.List
import Data.String

--ficha1

--1

--a
perimetro :: Float -> Float
perimetro r = (2 * 3.14) * r
--b
dist :: (Float,Float) -> (Float,Float) -> Float
dist (x, y) (h, t) = (x-h)/(y-t)
--c
primUlt :: [Float] -> (Float,Float)
primUlt l = (head l, last l)
--d
multiplo :: Int -> Int -> Bool
multiplo a b = if (mod a b == 0) then True
               else False
--e
truncaImpar :: [Int] -> [Int]
truncaImpar l@(a:b) = if (mod (length l) 2 == 0) then l
                else b
--f
max2 :: Int -> Int -> Int
max2 a b = if (a > b) then a
           else b
--g
max3 :: Int -> Int -> Int -> Int
max3 a b c = if ((max2 a b)>c) then max2 a b
             else c

--2

--a
--nRaizes :: Int -> Int -> Int -> Float
--nRaizes
--b
--raizes

--3

type Ponto = (Float,Float)

--a
tuplo :: Ponto -> Ponto -> Ponto -> (Float,Float,Float)
tuplo (a,b) (c,d) (e,f) = (sqrt((a-c)^2+(b-d)^2),sqrt((a-e)^2+(b-f)^2),sqrt((c-e)^2+(d-f)^2))
--b
per :: Ponto -> Ponto -> Ponto -> Float
per (a,b) (c,d) (e,f) = sqrt((a-c)^2+(b-d)^2) + sqrt((a-e)^2+(b-f)^2) + sqrt((c-e)^2+(d-f)^2)
--c
rect:: Ponto -> Ponto -> [Ponto]
rect (a,b) (c,d) = [(a,b),(a,d),(c,d),(c,b)]

--4

type Hora = (Int,Int)

--a
testPar :: Hora -> Bool
testPar (a,b) = if ((a>24) || (a<0) || (b<0) || (b>60)) then False
                else True

--testHour :: Hora -> Bool
--testHour (a,b) | (a>24 || a<0 || b>60 || b<0) = False
--               | otherwise = True
--b
--testHora :: Hora -> Hora -> Bool
--testHora (a,b) (c,d) = if (a>c) then True
--                       else if (a<c) then False
--                       else if (a==c) then if (b>d) then True
--                       else False else True
firstHour :: Hora -> Hora -> Bool
firstHour (a,b) (f,g) = if (testPar (a,b) && testPar(f,g)) then firstHourAux (a,b) (f,g)
                        else False

firstHourAux :: Hora -> Hora ->  Bool
firstHourAux (a,b) (f,g) = if ((a>f && b>g) || (a==f && b<g)) then True 
                           else False
--c
toMinutos :: Hora -> Int
toMinutos (a,b) = (a*60)+b
--d
toHoras :: Int -> Hora
toHoras a = (div a 60, mod a 60)
--e
difHoras :: Hora -> Hora -> Int
difHoras (a,b) (c,d) = if ((toMinutos(a,b))>(toMinutos(c,d))) then (toMinutos(a,b))-(toMinutos(c,d))
                       else (toMinutos(c,d))-(toMinutos(a,b))
--f
addMintohora :: Int -> Hora -> Hora
addMintohora a (b,c) = toHoras(a+(toMinutos(b,c)))











