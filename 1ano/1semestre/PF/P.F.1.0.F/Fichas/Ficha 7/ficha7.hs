data Contacto = Casa Integer
			  | Trab Integer
			  | Tlm Integer
			  | Email String
	deriving Show

type Nome = String
type Agenda = [(Nome, [Contacto])]

--c
consTelefs:: [Contacto] -> [Integer]
consTelefs [] = []
consTelefs [Tlm x] = [x]
consTelefs [Casa x] = [x]
consTelefs x = (map select x)

select :: Contacto -> Integer
select (Tlm x) = x
select (Casa x) = x

--d
--casa:: Nome -> Agenda -> Maybe Integer
--casa x [] = Nothing
--casa x ((a,b):c) = 
--	                let getNum :: [Contacto] -> Maybe Integer
--			 		   getNum [] = Nothing
--			  		   getNum [x:xs] = case x of Casa x0 -> (Just x0) : getNum xs)
--				   									     -> getNum xs
--				   	in if x == a then getNum b
--				       else casa x c

--como fazer sem criar mais nenhuma auxiliar?


type Dia = Int
type Mes = Int
type Ano = Int

data Data = D Dia Mes Ano
	deriving Show

type TabDN = [(Nome,Data)]

procura :: Nome -> TabDN -> Maybe Data
procura x [] = Nothing
procura x ((a,b):xs) = if x == a then Just b
					   else procura x xs

anterior :: Data -> Data -> Bool
anterior (D a b c) (D x y z) = if z < c then True
							   else if z == c && b < y then True
							   else if z == c && b == y && a < x then True
							   else False

--d
ordena :: TabDN -> TabDN
ordena [(a,b)] = [(a,b)]
ordena ((a,b): (x,y): xs) = if (aux b ((x,y):xs)) == True then ((a,b): (ordena ((x,y):xs)))
							else ordena (((x,y):xs) ++ [(a,b)])


aux :: Data -> TabDN -> Bool
aux a [] = True
aux a ((x,y):xs) = if anterior a y == True then aux a xs
				   else False
--

data Movimento = Credito Float | Debito Float
	deriving Show

data Extracto = Ext Float [(Data, String, Movimento)]
	deriving Show

--e

saldo :: Extracto -> Float
saldo (Ext x []) = x
saldo (Ext x a) = x + (sum (map alter a))


alter :: (Data, String, Movimento) -> Float
alter (a, b, Credito c) = c
alter (a, b, Debito c) = -c

--d
