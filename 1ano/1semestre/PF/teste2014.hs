--teste2014
   data Heap a = Vazia
               | Nodo a (Heap a) (Heap a)

--1 

   quantos :: Heap a -> Int
   quantos (Vazia) = 0
   quantos (Nodo x y z) = 1 + quantos y + quantos z

--2

   existe :: Ord a => a -> Heap a -> Bool
   existe _ (Vazia) = False
   existe b (Nodo x y z) = (b==x) || (existe b y) || (existe b z)

--3

   --removeMin :: Heap a -> (a,Heap a)
   --removeMin 

--4

   igual :: Heap a -> Heap b -> Bool
   igual a b = 

   toLista :: Heap a -> [a]
   toLista Vazia = []
   toLista Nodo x y z = [x]++y++z

   ordena :: [a] -> [a]
   ordena [] = []
   ordena (x:y) 