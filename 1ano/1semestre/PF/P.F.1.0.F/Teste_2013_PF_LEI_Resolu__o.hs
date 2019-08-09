--1
-- merge [1, 3, 4, 5] [2, 4, 6] = [1, 2, 3, 4, 5, 6]

merge :: Ord a => [a] -> [a] -> [a]
merge xs [] = xs
merge [] xs = xs
merge (x:xs) (y:ys)
    | x < y     = x : merge xs (y:ys) 
    | otherwise = y : merge (x:xs) ys

--2
--triplos "abcdefghijl" = [(’a’,’b’,’c’),(’d’,’e’,’f’),(’g’,’h’,’i’)]

triplos :: [a] -> [(a, a, a)]
triplos [] = []
triplos [_] = []
triplos [_, _] = []
triplos (x:y:z:xs) = (x, y, z) : triplos xs

--3
fun' :: Num a => [a] -> [a]
fun' [] = []
fun' (x:xs) = 2 * x : fun' xs

--4
type Filme = (Titulo, Realizador, [Actor], Ano, Duracao)
type Titulo = String
type Realizador = String
type Actor = String
type Ano = Int
type Duracao = Int

--a
doActor :: Actor -> [Filme] -> [(Titulo, Ano)]
doActor _ [] = []
doActor actor ((titulo, _, actores, ano, _):xs) = if actor `elem` actores then (titulo, ano):doActor actor xs
                                                                          else               doActor actor xs

--b

total :: [Titulo] -> [Filme] -> Int
total xs filmes = sum $ map (duracao filmes) xs
    where duracao :: [Filme] -> Titulo -> Int
          duracao [] _ = 0
          duracao ((t, _, _, _, d):xs) titulo = if t == titulo then d else duracao xs titulo
                      
--5

data LTree a = Leaf a | Fork (LTree a) (LTree a)
    deriving (Eq, Show)

select :: LTree a -> [Bool] -> (Maybe a)
select (Leaf x)   []         = Just x
select (Leaf x)   xs         = Nothing
select (Fork x y) []         = Nothing
select (Fork _ y) (True :xs) = select y xs 
select (Fork x _) (False:xs) = select x xs

tree = Fork 
           (Fork (Leaf 3) 
                 (Fork (Leaf 2) (Leaf 1))) 
           (Fork (Leaf 4) 
                 (Fork (Leaf 12) 
                       (Fork (Leaf 10) (Leaf 5))))

--Main> select tree [False, False]
--Just 3
--Main> select tree [False, True, True]
--Just 1
--Main> select tree [True, True]
--Nothing



--PARTE II

--1
--procura tree 1 == Just [False, True, True]

--a
procura :: Eq a => LTree a -> a -> Maybe [Bool]
procura (Leaf x) y   = if x == y then Just [] else Nothing
procura (Fork x y) z 
    | left  == Nothing && right == Nothing = Nothing
    | left  == Nothing = Just (True : r)
    | right == Nothing = Just (False  : l)
    where left     = procura x z
          right    = procura y z
          (Just l) = left
          (Just r) = right

--b

--função dada no enunciado
travessia :: LTree a -> [(a,Int)]
travessia (Leaf x) = [(x,0)]
travessia (Fork e d) = map (\(x,n) -> (x,n+1)) (travessia e ++ travessia d)

build :: [(a, Int)] -> LTree a
build xs = (fst . head . head) (dropWhile (\x -> length x /= 1) (iterate buildAssist ys))
    where ys = map (\(x, n) -> (Leaf x, n)) xs

buildAssist :: [(LTree a, Int)] -> [(LTree a, Int)]
buildAssist [] = []
buildAssist [x] = [x]
buildAssist (x@(t1, n1):y@(t2, n2):xs) = if n1 == n2 then ((Fork t1 t2), n1 - 1):buildAssist xs
                                                     else x:(buildAssist (y:xs))                                                 

--2

type RelP a = [(a, a)]
type RelL a = [(a, [a])]

--a
convertePL :: (Eq a) => RelP a -> RelL a
convertePL [] = []
convertePL ((x, e):xs) = (x, e: map snd (filter ((==x).fst) xs)) : convertePL (filter ((/=x).fst) xs)

--b

converso :: (Eq a) => RelL a -> RelL a
converso = convertePL . (map (\(x, y) -> (y, x))) . converteLP

--função dada no enunciado do teste
converteLP :: RelL a -> RelP a
converteLP l = concat (map junta l)
    where junta (x, xs) = map (\y -> (x, y)) xs




















