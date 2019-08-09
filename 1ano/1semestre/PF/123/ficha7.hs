module Main where

import Data.Char
import Data.List
import Data.String

--1
data Contacto = Casa Integer
              | Trab Integer
              | Tlm Integer
              | Email String
   deriving Show
type Nome = String
type Agenda = [(Nome, [Contacto])]
--a
acrescEmail :: Nome -> String -> Agenda -> Agenda
acrescEmail a b [] = [(a,[Email b])]
acrescEmail a b ((c,l):t) = if (a==c) then (c,(Email b):l) : t
                            else ((c,l):acrescEmail a b t)
--b
verEmails :: Nome -> Agenda -> Maybe [String]
verEmails _ [] = Nothing
verEmails nome ((name,list):ts) = if (nome == name) then Just (searchEmailsAux list) 
                                  else verEmails nome ts 
                                  where
                                    searchEmailsAux [] = []
                                    searchEmailsAux ((Email h):ts) = h : (searchEmailsAux ts) 
                                    searchEmailsAux (_:ts) = searchEmailsAux ts
--c
consTelefs :: [Contacto] -> [Integer]
consTelefs [] = []
consTelefs ((Casa x):t) = x : consTelefs t
consTelefs ((Trab x):t) = x : consTelefs t
consTelefs ((Tlm x):t) = x : consTelefs t
consTelefs (_:t) = consTelefs t
--d
casa :: Nome -> Agenda -> Maybe Integer
casa _ [] = Nothing
casa a ((b, c):t) = if (a==b) then casaAux c
                    else casa a t
casaAux :: [Contacto] -> Maybe Integer
casaAux [] = Nothing
casaAux ((Casa x):ts) = Just x
casaAux (_:ts) = casaAux ts
--2
type Dia = Int
type Mes = Int
type Ano = Int

data Data = D Dia Mes Ano
   deriving Show

type TabDN = [(Nome,Data)]
--a
procura :: Nome -> TabDN -> Maybe Data
procura _ [] = Nothing
procura a ((b,c):d) = if (a==b) then Just c
                      else procura a d
--b
idade :: Data -> Nome -> TabDN -> Maybe Int
idade _ _ [] = Nothing
idade d n ((a,b):c) | (n==a) = Just (calcIdade d b)
                    | otherwise = idade d n c
calcIdade :: Data -> Data -> Int
calcIdade (D d m a) (D dd mm aa) | (a>=aa) = 0
                                 | ((a<aa) && (m>mm) && (d> dd)) = aa - a - 1
                                 | ((a < aa) && (m <= mm) && (d <= dd)) = aa - a
--c
anterior :: Data -> Data -> Bool
anterior (D d m a) (D dd mm aa) = (a < aa) || ((a == aa) && (m < mm)) || ((a == aa) && (m == mm) && (d < dd))
--d
ordena :: TabDN -> TabDN
ordena tab = oAux [] tab where
                              oAux new [] = new
                              oAux new (h:ts) = oAux (insereOrdData h new) ts
insereOrdData :: (Nome,Data) -> TabDN -> TabDN
insereOrdData (nm,dt) [] = [(nm,dt)]
insereOrdData (nm,dt) ((name,date):ts) = if (anterior dt date) then (nm,dt):((name,date): ts) 
                                         else (name,date) : (insereOrdData (nm,dt) ts)
--e
--porIdade :: Data -> TabDN -> [(Nome,Int)]
--porIdade h@(D d m a) l@((b,c):t) = calcIdade ( h (head (ordena l))) : calcIdade (h (drop 1 (ordena l)))














