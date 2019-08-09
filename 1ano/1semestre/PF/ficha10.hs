

data Frac = F Integer Integer
--1
--a
normaliza :: Frac -> Frac
normaliza (F n d) | n < 0 && d<0 = F (-(n 'div' m)) (-(d 'div' m))
                  | d <0 = F (-(n 'div' m)) (d 'div' m)
                  |otherwise = F (n 'div' m) (d 'div' m)
          where m = get n d

--b
 
