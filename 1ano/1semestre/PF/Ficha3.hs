--2 

--a)

   segundos :: [(a, b)] -> [b]
   segundos [] = []
   segundos ((x, y):t) = y: segundos t