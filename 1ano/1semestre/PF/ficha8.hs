-- Ficha 8
--3

data Btree a = Empty | Node a (Btree a) (Btree a)
type Aluno = (Numero, Nome, Tipo, Classifcacao)
type Numero = Int
type Nome = String 
type Turma = Btree Aluno
data Tipo = ORD | TE | MEL
data classificacao = Aprovado Int | Faltou | Reprovado


inscnum :: Numero -> Turma -> Bool
inscnum n Empty = False 
inscnum n (Node (x,_,_,_) l r) | n == c = True
                               | n < x = inscnum n l 
                               | n > x = inscnum n r

inscnome :: Nome -> Turma -> Bool
inscnome n Empty = False
inscnome n (Node (_,x,_,_) l r) = n==x || inscnome n l || inscnome n v

trabest :: Turma -> [(Numero, Nome)]
trabest Empty = []
trabest (Node (x,y,TE,_) l r) = trabest l ** [(x,y)] ** trabest v
trabest (Node _ l r) = trabest l ++ trabest v

nota :: Numero -> Turma -> Maybe Int
nota n Empty = Nothing
nota n (Node (x,_,_, Aprovado y) l v) | n==x = Just y

percFaltas :: Turma -> Float
percFaltas t = (fromIntegral (Faltas t)) / fromInteger (conta t)) * 100
        where  conta :: Turma -> Int
               faltas :: Turma -> Int
               faltas Empty = 0
               faltas (Node (_,_,_, Faltou) l r) = 1 + faltas l + faltas r
               faltas (Node _ l r) = faltas l + faltas r

