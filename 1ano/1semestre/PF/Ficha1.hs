

--4

   type Hora = (Int, Int)
   h2m :: Hora -> Int
   m2h :: Int -> Hora

   somarm :: Hora -> Int -> Hora
   somarm h m = m2h (h2m h + m)