--ficha5
type Polinomio = [Monomio]
type Monomio = (Float,Int)

--a)

--conta :: Int -> Polinomio -> Int
--conta n [] = 0
--conta n ((x,y):z) = if n==y then 1 + conta n z
--                    else conta n z

conta :: Int -> Polinomio -> Int
conta n p = length(filter (==n) (map (snd) p))

--b)

grau :: Polinomio -> Int
grau x = maximum (map (snd) x)

--c)

selgrau :: Int -> Polinomio -> Polinomio
selgrau

--d)


--e)