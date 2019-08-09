--ficha6
--2
--a)

--fromDigits :: [Int] -> Int
--fromDigits x = sum (zipWith (\y z-> z*(10^y)) [0..] x)

--c)

fromDigits :: [Int] -> Int
fromDigits x = (foldr (\z y -> z+10*y) 0 x)



