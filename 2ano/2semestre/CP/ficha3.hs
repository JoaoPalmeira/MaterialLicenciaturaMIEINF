module Main where

import Data.List

--4
data Nota = Faltou | Reprovado | Nota Int
	deriving (Eq, Show)
data Aluno = Aluno {numero :: Int , nome :: String , nota :: Nota}
	deriving (Eq, Show)
type Turma = [Aluno]

instance Arbitrary Nota where
	arbitrary = frequency [(1, return Faltou),(3, return Reprovado), (6, do {n<-choose (10,20);return(Nota n)})]