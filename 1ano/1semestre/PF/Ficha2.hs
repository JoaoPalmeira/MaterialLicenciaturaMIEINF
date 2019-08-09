

--1 
--a)

   funA :: [Float] -> Float 
   funA [] = 0
   funA (h:t) = h^2 + funA t

--2
--a)

   dobros :: [Float] -> Float
   dobros [] = []
   dobros (h:t) = (2*h) : (dobros t)

--b)

   numOcorre :: Char -> String -> Int
   numOcorre c [] = 0
   numOcorre c (h:t) = if c == h then (1 + numOcorre c t) 
                       else numOcorre c t

--c)

   positivos :: [Int] -> Bool
   positivos [] = True
   positivos (h:t) = if h>=0 then positivos t
                     else False

--d)

   soPos :: [Int] -> [Int]
   soPos [] = 

--5

--a)

   sodigitos :: String -> String 
   sodigitos [] = []
   sodigitos (h:t) | isDigit h = h: sodigitos t
                   | otherwise = sodigitos t