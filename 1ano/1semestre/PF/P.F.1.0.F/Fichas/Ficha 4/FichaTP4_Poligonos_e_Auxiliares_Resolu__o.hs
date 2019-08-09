-- @autor Pirata
-- @version 2015.v2

module FichaTP4 where

-- Ex1

type Ponto = (Float, Float)				-- (abcissa, ordenada)
type Rectangulo = (Ponto, Float, Float)	-- (canto sup. esq., larg, alt)
type Triangulo = (Ponto, Ponto, Ponto)
type Poligonal = [Ponto]

distancia :: Ponto -> Ponto -> Float
distancia (a,b) (c,d) = sqrt (((c - a) ^ 2) + ((b - d) ^ 2))

poli :: Poligonal
poli = [(1,1),(1,3),(4,3),(4,1),(1,1)]

-- a) calcular o comprimento de uma linha poligonal;
compPoligonal :: Poligonal -> Float
compPoligonal (x:y:xys) = (distancia x y) + (compPoligonal (y:xys))
compPoligonal _ = 0

-- b) converter elemento do tipo Triangulo na correspondente linha Poligonal;
triToPoli :: Triangulo -> Poligonal
triToPoli (x,y,z) = [x,y,z,x]

-- c) repete a alinía anterior para elementos do tipo Rectangulo;
rectToPoli :: Rectangulo -> Poligonal
rectToPoli ((x,y), ab, ord) = [(x,y), (x + ab, y), (x + ab, y - ord), (x, y - ord), (x, y)]

-- d) função fechada que testa se uma linha Poligonal é ou não fechada;
fechada :: Poligonal -> Bool
-- vou pressupor que no mínimo há 2 Pontos na linha Poligonal dada, se houver menos que isso vai dar erro;
-- para evitar que dé erro no caso de ser lista vazia ou só ter um elemento seria preciso programar o que fazer nesses casos;
-- Estou a considerar que uma linha Poligonal pode ser constituida por só dois pontos;
fechada [x, y] = x == y
fechada (x:y:ts) = fechada (x:ts)

-- e) função triangula que dad uma linha Poligonal fechada e convexa, calcule uma lista de Triangulo cuja soma das areas seja igual à àrea delimitada pela linha Poligonal;
triangula :: Poligonal -> [Triangulo]
triangula [x,y,w,z] = [(x,y,w)]
triangula (x:y:w:z:ts) = (x,y,w) : (triangula (x:w:z:ts))
triangula _ = [] -- para [], [x], [x,y], [x,y,z];

-- f) usando a função seguinte, defina uma função que calcule a area delimitada por uma linha Poligonal fechada e convexa;
areaTriangulo :: Triangulo -> Float
areaTriangulo (x,y,z) = sqrt (s * (s - a) * (s - b) * (s - c)) where
				a = distancia x y
				b = distancia y z
				c = distancia z x
				s = (a + b + c) / 2
-- formula de Heron

areaPoligonal :: Poligonal -> Float
areaPoligonal lista = aPAux (triangula lista) where
	aPAux [] = 0
	aPAux (h:ts) = (areaTriangulo h) + (aPAux ts)

-- ou:
-- areaPoligono l = sum (map aux (triangula l)) -- utilização de funçoes predefinidas sum e map
--		where aux (Triangulo p1 p2 p3) = areaTriangulo (p1,p2,p3)

-- g) função mover, que dado um ponto e uma linha Poligonal, dá como resultado uma linha Poligonal idêntica à primeira mas tendo como ponto inicial o ponto dado;
mover :: Ponto -> Poligonal -> Poligonal
mover _ [] = []
mover (a,b) ((x,y):ts) = mAux (a - x,b - y) ((x,y):ts) where
	mAux _ [] = []
	mAux (ab,od) ((x,y):ts) = (x + ab,y + od) : (mAux (ab,od) ts)

-- h) função zoom2 que, dada uma linha poligonal, dê como resultado uma linha poligonal semelhante e com o mesmo ponto inicial mas em que o comprimento de cada segmento de recta é multiplicado por 2;
zoom2 :: Poligonal -> Poligonal
zoom2 [] = []
zoom2 [x] = [x]
zoom2 (x:y:t) = x:(zoomAux x (y:t))
	where zoomAux :: Ponto -> Poligonal -> Poligonal
	      zoomAux _ [] = []
	      zoomAux x (y:t) = ((\(a,b) (c,d) -> (a+c,b+d))((\(a,b) c -> (a*c,b*c))((\(a,b) (c,d) -> (a-c,b-d))y x) 2) x):(zoomAux x t)

-- Ex 2
type TabTemp = [(Data,Temp,Temp)]
type Data = (Int,Int,Int)
type Temp = Float

-- a) função medias que calcula a temperatura media de cada dia;
medias :: TabTemp -> [(Data,Temp)]
medias [(dt,tp1,tp2)] = [(dt,((tp1 + tp2) / 2))]
medias ((dt,tp1,tp2):ts) = (dt,((tp1 + tp2) / 2)) : (medias ts)

-- b) função decrescente que testa se uma TabTemp está ordenada por ordem decrescente de data;
decrescente :: TabTemp -> Bool
decrescente (x:y:ts) = (x > y) && (decrescente (y:ts))
decrescente _ = True -- para [] e [x];

-- c) função conta, que dada uma lista de datas e a tabela de registos de temperaturas, conta quantas datas da lista tem registo na tabela;
conta :: [Data] -> TabTemp -> Int
conta [] _ = 0
conta (x:xs) list = if (apAux x list) then 1 + (conta xs list) else 0 + (conta xs list) where
	apAux _ [] = False
	apAux x ((dt,_,_):ts) = (x == dt) || (apAux x ts)

-- Ex 3
type MSet a = [(a,Int)]

-- a) função union que calcula a union de dois multi-conjuntos;
union :: (Eq a) => MSet a -> MSet a -> MSet a
union lista [] = lista
union lista ((a,b):ts) = union (iMSetAux (a,b) lista) ts where
	iMSetAux (w,z) [] = [(w,z)]
	iMSetAux (w,z) ((x,y):ts) = if (w == x) then (x,(y + z)) : ts else (x,y) : (iMSetAux (w,z) ts)

-- b) função intersect, que calcula a intersecção de dois multi-conjuntos;
intersect :: (Eq a) => MSet a -> MSet a -> MSet a
intersect [] _ = []
intersect _ [] = []
intersect (h:ts) list = let result = intAux h list in
	if (snd(result) == -1) then intersect ts list else result : intersect ts list where
		intAux (x,y) [] = (x,-1)
		intAux (x,y) ((a,b):ts) = if (x == a) then if (y < b) then (x,y) else (x,b)
											  else intAux (x,y) ts

-- c) função diff que calcula a diferença de dois multi conjuntos;
diff :: (Eq a) => MSet a -> MSet a -> MSet a
diff [] list = list
diff list [] = list
diff (h:ts) list = let result = difAux h list [] in
	if ((snd(fst result)) == 0) then diff ts (snd result) else (fst result) : diff ts (snd result) where
		difAux (x,y) [] new = ((x,y),new)
		difAux (x,y) ((a,b):ts) new = if (x == a) then if (y > b) then ((x,(y - b)), new ++ ts) else ((x,(b - y)), new ++ ts)
												  else difAux (x,y) ts (new ++ [(a,b)])

-- d) função ordena que ordena o multi-conjunto por ordem crescente de ocorrências;
ordena :: MSet a -> MSet a
ordena [] = []
ordena list = ordAux list [] where
	ordAux [] new = new
	ordAux (h:ts) new = ordAux ts (iOrdAux h new) where
		iOrdAux el [] = [el]
		iOrdAux (x,y) ((a,b):ts) = if (y > b) then (a,b) : (iOrdAux (x,y) ts) else (x,y) : (a,b) : ts

-- e) função moda que devolve a lista dos elementos com maiores numeros de ocorrências;
moda :: MSet a -> [a]
moda [] = []
moda ((x,y):ts) = mAux ts y [x] where
	mAux [] _ new = new
	mAux ((x,y):ts) hg new = if (hg > y) then mAux ts hg new
										 else if (hg == y) then mAux ts hg (new ++ [x])
														   else mAux ts y [x]
