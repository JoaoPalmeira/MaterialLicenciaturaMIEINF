clear = doAgain (putStrLn "\n") 25

doAgain p 0 = return ()
doAgain p n = do p
                 doAgain p (n-1)

------------------------------------------------------------
--11

pares :: [Int] -> Int
pares [] = 0
pares (x:xs) |(mod x 2 == 0) = 1 + pares xs
             | otherwise = pares xs

--12

isDigit x = (x == '0')||(x == '1')||(x == '2')||(x == '3')||(x == '4')||(x == '5')||(x == '6')||(x == '7')||(x == '8')||(x == '9')

contaAlg :: [Char] -> Int
contaAlg [] = 0
contaAlg (x:xs) = if isDigit x then 1 + (contaAlg xs) else contaAlg xs

--13

type Rectangulo = (Int,Int)

--a
quadrados :: [Rectangulo] -> Int 
quadrados [] = 0
quadrados ((a,b):t) = if a == b then 1 + quadrados t else quadrados t

--b
areaTotal :: [Rectangulo] -> Int
areaTotal [] = 0
areaTotal ((a,b):t) = a*b + areaTotal t

--14
descomprime :: [(a,Int)] -> [a]
descomprime [] = []
descomprime ((x,n):t) = aux (x,n) ++ (descomprime t)
 where aux (_,0) = []
       aux (x,n) = x:(aux (x,n-1))

test14 = descomprime [('J',1),('o',3),('a',6),('o',3),('!',3)]

--15

remove :: [a] -> Int -> [a]
remove (x:xs) 0 = xs
remove (x:xs) (n+1) = x:(remove xs n)

--16

copia :: [a] -> [Int] -> [a]
copia _ [] = []
copia l@(x:xs) (n:ns) = (aux l n):(copia l ns)
  where aux l n = l !! n
     
test16 = copia ['J','a','o'] [0,2,1,2]

--17

replicate' :: Int -> a -> [a]
replicate' 0 x = []
replicate' (n+1) x = x:(replicate n x)


--18

gama :: Int -> Int -> [Int]
gama x y | x < y = x:(gama (x+1) y)
         | x == y = [x]
         | x > y = gama y x

--19

intercala :: a -> [a] -> [a]
intercala x [y] = [y]
intercala x (y:ys) = (y:x:(intercala x ys))

test19 = intercala ',' "123"

--20

iguais :: [Int] -> [Int] -> Bool
iguais [][] = True
iguais _ [] = False
iguais [] _ = False
iguais (x:xs) (y:ys) = (x == y) && (iguais xs ys)

test20 = iguais [1,2,3] [1,2,3]

--21

init' :: [a] -> [a]
init' [x] = []
init' (x:xs) = x:(init' xs)

test21 = init' [1,2,3]


--22

splitAt' :: Int -> [a] -> ([a],[a]) 
splitAt' 0 l = ([],l)
splitAt' n (x:xs) = (x:a,b)
     where (a,b) = splitAt' (n-1) xs

test22 = splitAt 2 "abcde"

--23

type Matriz = [Linha]
type Linha  = [Float]

--a

maxMat :: Matriz  ->  Float
maxMat (l:ls) = maximum (map maximum (l:ls))

test23a = maxMat [[1,2],[6,7,8],[9,3,100]]

--b

quantos :: Float -> Matriz -> Int

quantos n m = sum (map (howMany n) m)


test23b = quantos 1 [[1,2],[1,1,1],[2,1],[]]

howMany n [] = 0
howMany n (x:xs) = if x == n then 1 + (howMany n xs) else howMany n xs

--c

ok :: Matriz -> Bool
ok (l:ls) = ok2 (length l) ls
ok2 n [] = True
ok2 n (h:t) = (length h == n) && (ok2 n t)

test23c = ok [[1,2],[1,2]]

--d

zero :: Int -> Int -> Matriz

zero c l = map (++(replicate c 0)) (aux1 l)
    where aux1 0 = []
          aux1 (l+1) = []:(aux1 l)

test23d = zero 3 2

--24

compMaisLonga :: [String] -> Int
compMaisLonga l = maximum (map length l)

test24 = compMaisLonga ["12345678","123456","abce","a"]

--25

nomesProp :: [String] -> Int
nomesProp l = length (filter proprio l)

proprio (c:_) = isUpper (c)

test25 = nomesProp ["teste:","Sérgio","Dias"]

-------------------------------------------------------------
isUpper c  =  c >= 'A' && c <= 'Z' ||
              c >= '\xC0' && c <= '\xD6' ||
              c >= '\xD8' && c <= '\xDE'

-- Copiado de http://hackage.haskell.org/packages/archive/base/4.2.0.2/doc/html/src/GHC-Unicode.html
-------------------------------------------------------------

--26
--(conta == howMany) ?

conta :: Eq a => a -> [a] -> Int

conta x l = length (filter (==x) l)

test26 = conta 1 [2,1,3,4,5,32,6,4,6,8,4,5364567,1]

--27

quadrado (h,v) = h==v
area (h,v) = h*v

areaQuadrados :: [(Int,Int)] -> Int
areaQuadrados l = foldr (+) 0 (map area (filter quadrado l))

test27 = areaQuadrados [(2,2),(2,3),(4,4)]

--28

diferentes :: Eq a => [a] -> Bool
diferentes [] = True
diferentes (x:xs) = (aux x xs) && (diferentes xs)
    where aux _ [] = True
          aux x (y:ys) = (x /= y) && (aux x ys)

test28a = diferentes [1,2,3,4,1]
test28b = diferentes [1,2,3,4,5]
            
--29

leq :: String -> String -> Bool
leq [] _ = True
leq _ [] = False
leq (x:xs) (y:ys) = (x < y) ||
                    ((x == y) && (leq xs ys) )

--Capitalização importa, toUpper e toLower não funcionam

test29a = leq "A Joana" "A Joana II"
test29b = leq "A Joana" "A Joan"

--30

type TabTemp = [(Data,TempMin,TempMin)]
type Data = (Int,Int,Int)
type TempMin = Float
type TempMax = Float

--a

medias ::  TabTemp -> [(Data,Float)]
medias l = map aux l
   where aux (d,x,y) = (d,(x+y)/2)

test30a = medias [((1,1,11),14,20),((2,1,11),-20,2)]

--b

minMin :: TabTemp -> Float 
minMin [(d,tmin,tmax)] = tmin
minMin ((d,tmin,tmax):t) = min (tmin) (minMin t)

test30b = minMin [((1,1,11),14,20),((2,1,11),-20,2)]

--31

type Jornada = [Jogo]
type Jogo = ((Equipa,Golos),(Equipa,Golos))
type Equipa = String
type Golos = Int

--a

golosMarcados :: Jornada -> Int
golosMarcados j = sum (map aux j)
    where aux ((_,x),(_,y)) = x + y

test31a = golosMarcados [(("FCP",10),("SLB",0)),(("FCP",5),("SCP",1))]

--b

pontos :: Jornada -> [(Equipa,Int)]
pontos jor = pontos2 (splitJogo jor)

pontos2 [(eq1,x)] = [(eq1,x)]

pontos2 ((eq1,x):js) = if eq1 == eq then ((eq,(n+1)):t) else ((eq,n):(pontos2 ((eq1,x):t)))
  where ((eq,n):t) = pontos2 js

splitJogo [] = []
splitJogo (((eq1,x),(eq2,y)):js) = ((eq1,x):(eq2,y):(splitJogo js))


test31b = pontos [(("FCP",10),("SLB",0)),(("FCP",5),("SCP",1))]

-- Não faz o que a pergunta pede, soma golos.

--32

type TabTemp' = [(Data,TempMin,TempMin,Precipacao)]
-- type Data = (Int,Int,Int)
-- type TempMin = Float
-- type TempMax = Float
type Precipacao = Float

--a

amplTerm :: TabTemp' -> [(Data,Float)]
amplTerm [] = []
amplTerm ((d,x,y,p):t) = (d,y-x):(amplTerm t)

test32a = amplTerm [((1,1,11),14,20,25),((2,1,11),-20,2,30)]

--b
maxChuva :: TabTemp' -> (Data,Float)
maxChuva [(d,x,y,p)] = (d,p)
maxChuva (h:t) = aux h (maxChuva t)
     where aux (d,x,y,p) (d2,p2) = if (p > p2) then (d,p) else (d2,p2)

test32b = maxChuva [((1,1,11),14,20,25),((2,1,11),-20,2,99)]

--33

type Tabela = [(Nome,Nota)]
type Nome = String
type Nota = Int

--a

parte :: Tabela -> ([Nome],[Nome])
parte [] = ([],[])
parte ((nome,nota):t) = if (nota >= 10) then (a,nome:b) else (nome:a,b)
   where (a,b) = parte t


test33a = parte [("Ricardo",0),("Joao",10),("Idalecio",20)]

--b

nota :: Nome -> Tabela -> Maybe Nota
nota _ [] = Nothing
nota x ((nome,n):t) | (x == nome) = Just n
                    | otherwise = nota x t

test33b = nota "Ricardo" [("Ricardo",0),("Joao",10),("Idalecio",20)]

--34

splitAt'' :: Int -> [a] -> ([a],[a])
splitAt'' 0 l = ([],l)
splitAt'' (n+1) (x:xs) = (x:a,b)
       where (a,b) = splitAt'' n xs

test34 = splitAt 3 [5,3,4,2,1]

--35

maiorDoQue :: Int -> [Int] -> Maybe Int
maiorDoQue x [] = Nothing
maiorDoQue x (y:ys) = if x < y then Just y else maiorDoQue x ys

test35 = maiorDoQue 3 [1,2,5]

--36
filtragem :: (a->Bool) -> [a] -> ([a],[a]) 
filtragem p [] = ([],[])
filtragem p (x:xs) = if p x then (x:a,b) else (a,x:b)
    where (a,b) = filtragem p xs

test36 = filtragem (>3) [1,2,3,4,5,6,7,8,9,0]

--37

data Avaliacao = A Float Float -- Teste, prática
               | B Float
    deriving Show
type Aluno = (Int, String, Avaliacao) -- Numero, Nome, Av
type Turma = [Aluno]

--a
-- Foram usados Float em vez de Int

nota' :: Avaliacao -> Maybe Float
nota' (A t p) |(t >= 8.0) && (total > 9.5) = Just total
              | True = Nothing
    where total = (0.7 * t + 0.3 * p)


nota' (B t) | t >= 9.5 = Just t
            | True = Nothing

--b

pauta:: Turma -> [(String, String, String)]
pauta [] = []
pauta ((num,nome,ava):t) = case (nota' ava) of Nothing -> (show num, nome, "Rep"):(pauta t)
                                               Just a  -> (show num, nome, show a):(pauta t)

test37b = pauta [(123, "Joao", A 10 10), (124, "Maria", B 11), (125, "Manuel", A 5 10)]

--c

melhorA :: Turma -> Maybe Float --orig. Int
melhorA [(num,nome,ava)] = nota' ava
melhorA ((num,nome,ava@(A a b)):t) = if (nota' ava > melhorA t) then (nota' ava)
                                                                else (melhorA t)

melhorA (_:t) = melhorA t

test37c = melhorA [(123, "Joao", A 20 10), (124, "Maria", B 11), (125, "Manuel", A 5 10)]

--d

finais :: Turma -> [(Int,Float)]
finais [] = []
finais ((num,nome,ava):t) = (num, x):(finais t)
  where x = nota2 ava

test37d = finais [(123, "Joao", A 20 10), (124, "Maria", B 11), (125, "Manuel", A 5 10)]


nota2 :: Avaliacao -> Float
nota2 (A t p) = (0.7 * t + 0.3 * p)
nota2 (B t) = t

--e

aprovado :: Aluno -> Bool

aprovado (_,_,av) =
     case av of A x y -> (x >= 8) && (nota2 av >= 10)
                B x   -> (x >= 10)


test37e = map aprovado [(123, "Joao", A 20 10), (124, "Maria", B 11), (125, "Manuel", A 5 10)]

--f

stat :: Turma -> (Float,Float)
stat [] = (0,0)
stat (aluno@(num,nome,av):t)=
    case av of (A x y) -> if aprovado aluno then (1+a,b) else (a,b)
               (B x)   -> if aprovado aluno then (a,1+b) else (a,b)
  where (a,b) = stat t

--38

compMaisLonga2 :: [String] -> Int
compMaisLonga2 ls = maximum (map length ls)

--39

nomesProp2 :: [String] -> Int
nomesProp2 ss = length (filter proprio ss)

--40

(!!?) :: [a] -> Int -> a
(h:t) !!? 0 = h
(h:t) !!? (n+1) = t !!? n

test40 = "Teste" !!? 3

--41

allBut :: [a] -> Int -> [a]
allBut (h:t) 0 = t
allBut (h:t) (n+1) = h:(allBut t n)

test41 = allBut "Tes4te" 3

--42

split2 :: (Ord a) => a -> [a] -> ([a],[a])
split2 _ [] = ([],[])
split2 x (h:t) = if (h <= x) then (h:a,b) else (a,h:b)
   where (a,b) = split2 x t

test42 = split2 5 [1,4,2,3,4,6,8,6,7,9,7,3]

--43

extractMultiples :: [Int] -> Int -> ([Int],[Int])
extractMultiples [] _ = ([],[])
extractMultiples (h:t) x =
  if (mod h x == 0) then (h:a,b) else (a,h:b)
    where (a,b) = extractMultiples t x

test43 = extractMultiples [0,1,2,3,4,5,6,7,8,9,10] 2

--44

-- data Maybe a = Nothing | Just a

catMaybes :: [Maybe a] -> [a]
catMaybes [] = []
catMaybes ((Nothing):t) = catMaybes t
catMaybes ((Just a):t) = a:(catMaybes t)

test44 = catMaybes [Just 1, Just 2, Just 3, Just 4, Nothing, Just 5]

--45

data Tree a = Leaf a | Fork (Tree a) (Tree a)

--a

folhas :: Tree a -> [a]
folhas (Leaf a) = [a]
folhas (Fork a b) = (folhas a) ++ (folhas b)

test45a = folhas (Fork (Fork (Leaf 1) (Leaf 2)) (Fork (Fork (Leaf 3) (Leaf 4)) (Leaf 5)))

--b

altura :: Tree a -> Int
altura (Leaf a) = 1
altura (Fork a b) = 1 + max (altura a) (altura b)

test45b = altura (Fork (Fork (Leaf 1) (Leaf 2)) (Leaf 3))

--     /\
--    /\ 3
--   1 2

--46

data Nat = Zero | Succ Nat

toInt :: Nat -> Int
toInt (Zero) = 0
toInt (Succ a) = 1 + (toInt a)

test46 = toInt (Succ (Succ (Succ Zero)))

--47


--lookup :: (Eq a) => [(a,b)] -> a -> Maybe b

lookupSort :: (Ord a) => [(a,b)] -> a -> Maybe b
lookupSort [] x = Nothing
lookupSort ((a,b):t) x
  | a < x  = lookupSort t x
  | a == x = Just b
  | a > x  = Nothing

test47 = lookupSort [(1,'a'),(5,'b'),(7,'c')] 5
test47' = lookupSort [(1,'a'),(5,'b'),(7,'c')] 10

--48

data BTree a = Vazia | Nodo a (BTree a) (BTree a)
    deriving (Show,Eq)
--a

lookupBST :: (Ord a) => (BTree (a,b)) -> a -> Maybe b
lookupBST (Vazia) _ = Nothing
lookupBST (Nodo (a,b) esq dir) x
  | x < a  = lookupBST esq x
  | x > a  = lookupBST dir x
  | x == a = Just b

test48a = lookupBST (Nodo (2,'b') (Nodo (1,'a') Vazia Vazia)
                                  (Nodo (4,'d') (Nodo (3,'c') Vazia Vazia) Vazia)
                     ) 3
{-
      (2,b)
      /   \
  (1,a)   (4,d)
          /
       (3,c)
-}

--b

-- Usar inorder e procurar

--c

mapBT :: (a -> b) -> (BTree a) -> BTree b
mapBT p Vazia = Vazia
mapBT p (Nodo x e d) = Nodo (p x) (mapBT p e) (mapBT p d)


test48c = mapBT p (Nodo (2,'b') (Nodo (1,'a') Vazia Vazia)
                                (Nodo (4,'d') (Nodo (3,'c') Vazia Vazia) Vazia)
                   ) 
            where p (n,l) = (n*100,l)

--d

deProcura :: (Ord a) => (BTree a) -> Bool
deProcura (Vazia) = True
deProcura (Nodo x e d) =
  (deProcura e) && (deProcura d) && (((e == Vazia)||(x > maximum (inorder e))) && ((d == Vazia)||(x < minimum (inorder d))))


test48d = deProcura (Nodo 2 (Nodo 1 Vazia Vazia)
                            (Nodo 4 (Nodo 3 Vazia Vazia) Vazia)
                     )

{-
      2
     / \
    1   4
       /
      3
-}

inorder :: BTree a -> [a]
inorder Vazia = []
inorder (Nodo x e d) = (inorder e) ++ [x] ++ (inorder d)




--e

eHeap :: (Ord a) => (BTree a) -> Bool
eHeap (Vazia) = True
eHeap (Nodo x e d) =
  (eHeap e) && (eHeap d) && (((e == Vazia)||(x < minimum (inorder e))) && ((d == Vazia)||(x < minimum (inorder d))))

test48e = eHeap (Nodo 1 (Nodo 2 Vazia Vazia)
                        (Nodo 3 (Nodo 4 Vazia Vazia) Vazia)
                 )

test48e' = eHeap (Nodo 1 (Nodo 2 Vazia Vazia)
                         (Nodo 3 (Nodo 2 Vazia Vazia) Vazia)
                  )
{-
      1
     / \
    2   3
       /
      2
-}

--f

balanceada :: (BTree a) -> Bool
balanceada Vazia = True
balanceada (Nodo x e d) = ((alturas <= 1)) && (balanceada e) && (balanceada d)
  where alturas = abs((altura' e) - (altura' d))

altura' :: BTree a -> Int
altura' (Vazia) = 0
altura' (Nodo x e d) = 1 + max (altura' e) (altura' d)

test48f = balanceada (Nodo 1 (Nodo 2 Vazia Vazia)
                             (Nodo 3 (Nodo 2 Vazia (Nodo 100 Vazia Vazia)) Vazia)
                      )

{-
      1
     / \
    2   3
       /
      2
       \
        100
-}


--g

equilibrada :: (BTree a) -> Bool
equilibrada Vazia = True
equilibrada (Nodo x e d) = ((contas <= 1)) && (equilibrada e) && (equilibrada d)
  where contas = abs((contaNodos e) - (contaNodos d))

contaNodos :: BTree a -> Int
contaNodos (Vazia) = 0
contaNodos (Nodo x e d) = 1 + (contaNodos e) + (contaNodos d)

test48g = equilibrada (Nodo 1 (Nodo 2 Vazia Vazia)
                              (Nodo 3 (Nodo 2 Vazia (Nodo 100 Vazia Vazia)) Vazia)
                       )
{-
      1
     / \
    2   3
       /
      2
       \
        100
-}

test48g' = equilibrada (Nodo 1 (Nodo 2 Vazia (Nodo 100 Vazia Vazia))
                               (Nodo 3 (Nodo 4 Vazia Vazia) Vazia)
                        )
{-
         1
     /        \
    2          3
     \        /
      100    4
            

-}






















