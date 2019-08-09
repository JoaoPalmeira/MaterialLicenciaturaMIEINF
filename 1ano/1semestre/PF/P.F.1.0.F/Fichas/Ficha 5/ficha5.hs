--import qualified Data.Map 
import Data.Char

type Polinomio = [Monomio]
type Monomio = (Float, Int)

iguais:: Int-> Monomio -> Bool
iguais a (x,b) = if a==b then True
 			       else False


selgrau :: Int -> Polinomio -> Polinomio
--selgrau _ [] = []
selgrau a b = filter (iguais a) b

intToFloat :: Int -> Float
intToFloat n = fromInteger (toInteger n)

deriva:: Monomio -> Monomio
deriva (_,0) = (0,0)
deriva (a,b) = ((a * (intToFloat b)), b-1)

deriv:: Polinomio -> Polinomio
deriv a = map deriva a

----------------------------------------------------
valor :: Float -> Monomio -> Float
valor x (a,b) = a * (expoente b x)

expoente:: Int -> Float -> Float
expoente 0 b = 1
expoente a b = b * (expoente (a-1) b)

calcula :: Float -> Polinomio -> Float
calcula a x = sum (map (valor a) x)
-- busca do erro
----------------------------------------------------

--f
simp:: Polinomio -> Polinomio
simp a = filter (diferentes 0) a

diferentes:: Int-> Monomio -> Bool
diferentes a (x,b) = if a /=b then True
 			       else False

--g
multip :: Monomio -> Monomio -> Monomio
multip (a,b) (x,y) = (a*x , b + y)

mult :: Monomio -> Polinomio -> Polinomio
mult (c,e) p = map ( multip (c,e)) p

--h
--normaliza :: Polinomio -> Polinomio
--normaliza 

---------------------
--2
nzp :: [Int] -> (Int,Int,Int)
nzp x = (length (filter (<0) x), length (filter (==0) x), length (filter (>0) x))

--3
digitAlpha :: String -> (String,String)
digitAlpha x = (filter (isAlpha) x, filter (isDigit) x)

--