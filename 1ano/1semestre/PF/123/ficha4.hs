module Main where

import Data.Char
import Data.List
import Data.String

type Ponto = (Float,Float)
type Rectangulo = (Ponto,Float,Float)
type Triangulo = (Ponto,Ponto,Ponto)
type Poligonal = [Ponto]

distancia :: Ponto -> Ponto -> Float
distancia (a,b) (c,d) = sqrt (((c-a)^2) + ((b-d)^2))

--1

--a
compPoligonal :: Poligonal -> Float
compPoligonal (x:y:z) = distancia x y + compPoligonal (y:z)
compPoligonal _ = 0
--b
triTopoli :: Triangulo -> Poligonal
triTopoli (x,y,z) = [x,y,z,x]
--c
recTopoli :: Rectangulo -> Poligonal
recTopoli ((x,y),abc,ord) = [(x,y),(x+abc,y),(x+abc,y-ord),(x,y-ord),(x,y)]
--d
fechada :: Poligonal -> Bool
fechada (a:b) = if (a==(last b)) then True
                else False
--e
triangula :: Poligonal -> [Triangulo]
triangula [a,b,c,d] = [(a,b,c)]
triangula (a:b:c:d:e) = (a,b,c) : (triangula (a:c:d:e))
triangula _ = []
--f
areaTriangulo :: Triangulo -> Float 
areaTriangulo (x,y,z)
    = let a = distancia x y
          b = distancia y z
          c = distancia z x
          s = (a+b+c) / 2 -- semi-perimetro
      in -- formula de Heron
          sqrt (s*(s-a)*(s-b)*(s-c))

areaPoligonal :: Poligonal -> Float
areaPoligonal lista = aPAux (triangula lista) where
    aPAux [] = 0
    aPAux (h:ts) = (areaTriangulo h) + (aPAux ts)
--g
mover :: Ponto -> Poligonal -> Poligonal
mover _ [] = []
mover (a,b) ((x,y):ts) = mAux (a - x,b - y) ((x,y):ts) where
    mAux _ [] = []
    mAux (ab,od) ((x,y):ts) = (x + ab,y + od) : (mAux (ab,od) ts)
--e
zoom2






























