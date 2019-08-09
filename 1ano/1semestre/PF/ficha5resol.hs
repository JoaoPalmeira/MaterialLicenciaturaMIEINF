--ficha5
type Polinomio = [Monomio]
type Monomio = (Float,Int)

--a)

   --conta :: Int -> Polinomio -> Int
   --conta a [] = 0
   --conta a ((x,y):z) = if a==y then 1 + conta a z 
                       --else conta a z

conta :: Int -> Polinomio -> Int
conta a x = length(filter (==a) (map (snd) x))

--b)

grau :: Polinomio -> Int
grau x = maximum (map (snd) x)

--c)

selgrau :: Int -> Polinomio -> Polinomio
selgrau a x = filter (\(z,y)->(y==a)) x

--d)

deriv :: Polinomio -> Polinomio
deriv p = map (\(z,y)->(z*(fromIntegral y),y-1)) p

--e)

calcula :: Float -> Polinomio -> Float
calcula x p = sum (map (\(z,y)-> (z*(x^(fromIntegral y)))) p)
