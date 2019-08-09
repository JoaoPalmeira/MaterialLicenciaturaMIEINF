-- @Miguel Lobo

module Teste where
import Data.List

--1
--a)
nub1 :: Eq a => [a] -> [a]
nub1 [] = []
nub1 (h : t) = if elem h t then nub (h : (delete h t))
                          else h : nub t

--b)
zipWith1 :: (a -> b -> c) -> [a] -> [b] -> [c]
zipWith1 _ [] _ = []
zipWith1 _ _ [] = []
zipWith1 f (x : xs) (y : ys) = f x y : zipWith f xs ys


--2
type MSet a = [(a , Int)]
--a)
converte :: Eq a => [a] -> MSet a
converte [] = []
converte (h : t) = conta 1 h t : converte (deleteAll h t)
            where conta n x [] = (x , n)
                  conta n x (h : t) = if x == h then conta (n + 1) x t
                                                else conta n x t
                  deleteAll _ [] = []
                  deleteAll x (h : t) = if x == h then deleteAll x t
                                                  else h : deleteAll x t
--b)
intersect1 :: Eq a => MSet a -> MSet a -> MSet a
intersect1 [] _ = []
intersect1 (h : t) list = case aux h list of
            Nothing -> intersect1 t list
            Just a -> a : intersect1 t list
        where aux _ [] = Nothing
              aux (a , n1) ((b , n2) : t) = if a == b then Just (a , min n1 n2)
                                                      else aux (a , n1) t
--3
data Prop = Var String | Not Prop | And Prop Prop | Or Prop Prop
--a)
instance Show Prop where
    show (Var a) = a
    show (Not prop) = "-" ++ show prop
    show (And prop1 prop2) = "(" ++ show prop1 ++ " e " ++ show prop2 ++ ")"
    show (Or prop1 prop2) = "(" ++ show prop1 ++ " ou " ++ show prop2 ++ ")"
--b)
eval :: [(String , Bool)] -> Prop -> Bool
eval list (Var a) = searchValue a list
eval list (Not prop) = not $ eval list prop
eval list (And prop1 prop2) = (eval list prop1) && (eval list prop2)
eval list (Or prop1 prop2) = (eval list prop1) || (eval list prop2)

searchValue :: String -> [(String , Bool)] -> Bool
searchValue n ((m , bool) : t) = if n == m then bool
                                           else searchValue n t
--c)
nnf :: Prop -> Prop
nnf (Var a) = Var a
nnf (Not prop) = negate1 (prop)
nnf (And prop1 prop2) = And (nnf prop1) (nnf prop2)
nnf (Or prop1 prop2) = Or (nnf prop1) (nnf prop2)

negate1 :: Prop -> Prop
negate1 (Var a) = Not (Var a)
negate1 (Not prop) = nnf prop
negate1 (And prop1 prop2) = Or (negate1 prop1) (negate1 prop2)
negate1 (Or prop1 prop2) = And (negate1 prop1) (negate1 prop2)

--d)

readVar :: Prop -> [String]
readVar a = nub $ aux a
        where aux (Var a) = [a]
              aux (Not prop) = aux prop
              aux (And prop1 prop2) = (aux prop1) ++ (aux prop2)
              aux (Or prop1 prop2) = (aux prop1) ++ (aux prop2)

avalia :: Prop -> IO Bool
avalia prop = do let variaveis = readVar prop
                 lista <- (askValue variaveis [])
                 return (eval lista prop)

askValue :: [String] -> [(String , Bool)] -> IO ([(String , Bool)])
askValue [] b = return b
askValue (h : t) b = do putStrLn ("Qual o valor de " ++ h ++ "?")
                        putStr "1/0: "
                        bool <- getLine 
                        if bool == "1" then askValue t ((h , True) : b)
                                       else if bool == "0" then askValue t ((h , False) : b)
                                                           else do putStrLn "Valor não existente."
                                                                   askValue (h : t) b
