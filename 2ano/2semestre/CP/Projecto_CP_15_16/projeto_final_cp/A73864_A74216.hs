--
-- Projecto CP 2015/16
--
-- O projecto consiste em desenvolver testes para o módulo Graph.hs
-- (para grafos orientados e não pesados).
-- Mais concretamente, o projecto consiste em 3 tarefas que são descritas abaixo.
-- O prazo para entrega é o dia 3 de Abril. Cada grupo deve enviar apenas
-- o módulo de testes (este módulo) por email para calculodeprogramas@gmail.com
-- O nome do ficheiro deve identificar os números dos 2 alunos do grupo (numero1_numero2.hs).
-- Certifiquem-se que o módulo de testes desenvolvido compila correctamente antes
-- de submeter. O módulo Graph.hs não deve ser alterado.
-- Os 2 alunos do grupo devem também indentificar-se nos comentários abaixo.
--
-- Aluno 1
-- Número: A73864
-- Nome: João Miguel Freitas Palmeira
-- Curso: MiEI
--
-- Aluno 2
-- Número: A74216
-- Nome: Rodrigo Tiago Oliveira Ferreira
-- Curso: MiEI
--


module Main where

import Graph
import Test.HUnit hiding (path)
import Test.QuickCheck
import Data.Set as Set

--
-- Teste unitário
--
    
g1 :: Graph Int
g1 = Graph {nodes = fromList [1],
            edges = fromList [Edge 1 1]
           }
--Graph Ciclico
g2 :: Graph Int
g2 = Graph { nodes = fromList [1,2,3,4],
             edges = fromList [Edge 1 2, Edge 2 3, Edge 3 4, Edge 4 1]
}
--Graph Aciclico
g3 :: Graph Int
g3 = Graph { nodes = fromList [1,2,3,4,5],
             edges = fromList [Edge 1 2, Edge 1 5, Edge 5 3, Edge 3 2, Edge 4 3, Edge 4 2]
}
--Graph Empty
g4 :: Graph Int
g4 = Graph { nodes = fromList [],
             edges = fromList []
}
--Graph Invalido
g5 :: Graph Int
g5 = Graph { nodes = fromList [1,2,3,4,5],
             edges = fromList [Edge 1 2, Edge 3 6, Edge 2 3, Edge 3 4, Edge 4 5]
}
--SubGraph_g2
g6 :: Graph Int
g6 = Graph { nodes = fromList [1,2,3,4],
             edges = fromList [Edge 1 2, Edge 2 3, Edge 3 4]
}
--SubGraph_g2
g7 :: Graph Int
g7 = Graph { nodes = fromList [1,2,3],
             edges = fromList [Edge 1 2, Edge 2 3]
}
--SubGraph_g2 (falha nas arestas)
g8 :: Graph Int
g8 = Graph { nodes = fromList [1,2,3,4],
             edges = fromList [Edge 1 2, Edge 2 3, Edge 1 4]
}
--SubGraph_g2 (falha nos nodos)
g10 :: Graph Int
g10 = Graph { nodes = fromList [1,2,3,4,5],
             edges = fromList [Edge 1 2, Edge 2 3, Edge 3 4]
}
--Transposto de g3
g9 :: Graph Int
g9 = Graph { nodes = fromList [1,2,3,4,5],
             edges = fromList [Edge 2 1, Edge 5 1, Edge 3 5, Edge 2 3, Edge 3 4, Edge 2 4]
}
--Teste para a union entre o g9 e o g11
g11 :: Graph Int
g11 = Graph { nodes = fromList [1,2,3,4,5],
              edges = fromList [Edge 1 2, Edge 2 1, Edge 5 1, Edge 3 5, Edge 2 3, Edge 3 4, Edge 2 4]
}
--Exemplo de Graph
g12 :: Graph Int
g12 = Graph { nodes = fromList [10,11,12,13,14,15],
              edges = fromList [Edge 10 11, Edge 11 12, Edge 12 13, Edge 12 14, Edge 13 14, Edge 14 15]
}
--exemplo para bft 1
g13 :: Graph Int
g13 = Graph { nodes = fromList [3,4],
              edges = fromList []
}
--exemplo para bft 2
g14 :: Graph Int
g14 = Graph { nodes = fromList [1,2,3,4],
              edges = fromList [Edge 3 2, Edge 4 3]
}
--exemplo para bft 3
g15 :: Graph Int
g15 = Graph { nodes = fromList [1,2,3,4,5],
              edges = fromList [Edge 1 5, Edge 2 1, Edge 5 3]
}
--exemplo para bft 4
g16 :: Graph Int
g16 = Graph { nodes = fromList [12,13,14,15],
              edges = fromList [Edge 14 12, Edge 15 14]
}
--Transposto de g11
g17 :: Graph Int 
g17 = Graph { nodes = fromList [1,2,3,4,5],
              edges = fromList [Edge 2 1, Edge 1 2, Edge 1 5, Edge 5 3, Edge 3 2, Edge 4 3, Edge 4 2]
}
--Caminhos
p1 :: Graph.Path Int
p1 = [Edge 1 2, Edge 2 3]

p2 :: Graph.Path Int
p2 = []

p3 :: Graph.Path Int
p3 = [Edge 2 3, Edge 3 4]

p4 :: Graph.Path Int
p4 = [Edge 2 1, Edge 5 1]

p5 :: Graph.Path Int
p5 = [Edge 1 2, Edge 2 3, Edge 3 4]

p6 :: Graph.Path Int
p6 = [Edge 1 3, Edge 4 3]      


--
-- Um exemplo de um teste unitário.
test_adj :: Test
test_adj = TestList[adj g1 1 ~?= fromList [Edge 1 1],adj g2 2 ~?= fromList [Edge 2 3],adj g3 1 ~?= fromList[Edge 1 2, Edge 1 5]]

test_isDAG :: Test
test_isDAG = TestList[Graph.isDAG g2~?=False, Graph.isDAG g3~?=True]

test_Swap :: Test
test_Swap = swap (Edge 1 2)~?=(Edge 2 1)

test_isEmpty :: Test
test_isEmpty = TestList[Graph.isEmpty g4~?=True, Graph.isEmpty g3~?=False, Graph.isEmpty Graph.empty~?=True, Graph.isEmpty g9~?=False, Graph.isEmpty g7~?=False]

test_isValid :: Test
test_isValid = TestList[Graph.isValid g5~?=False, Graph.isValid g2~?=True]

test_isForest :: Test
test_isForest = TestList[Graph.isForest g2~?=False, Graph.isForest g3~?=False]

test_isSubgraphOf :: Test
test_isSubgraphOf = TestList[Graph.isSubgraphOf g6 g2~?=True, Graph.isSubgraphOf g7 g2~?=True, Graph.isSubgraphOf g8 g2~?=False, Graph.isSubgraphOf g10 g2~?=False]

test_transpose :: Test
test_transpose = TestList[Graph.transpose g3~?=g9, Graph.transpose g11~?=g17]

test_union :: Test
test_union = TestList[Graph.union g6 g10 ~?= g10, Graph.union g7 g8 ~?= g8, Graph.union g7 g9 ~?= g11]

test_bft :: Test
test_bft = TestList[Graph.bft g6 (fromList[3,4]) ~?= g13, Graph.bft g6 (fromList[1,2]) ~?= g14, Graph.bft g11 (fromList[3,4]) ~?= g15, Graph.bft g12 (fromList[12,13]) ~?= g16]

test_reachable :: Test
test_reachable = TestList[Graph.reachable g11 4 ~?= fromList[4], Graph.reachable g9 2 ~?= fromList[1, 2, 3, 4, 5], Graph.reachable g7 2 ~?= fromList[2, 3]]

test_isPathOf :: Test
test_isPathOf = TestList[Graph.isPathOf p1 g7 ~?= True, Graph.isPathOf p2 g4 ~?= True, Graph.isPathOf p3 g6 ~?= True, Graph.isPathOf p4 g9 ~?= False, Graph.isPathOf p5 g10 ~?= True, Graph.isPathOf p6 g11 ~?= False]

test_path :: Test
test_path = TestList[Graph.path g12 10 13 ~?= Just [Edge 10 11, Edge 11 12, Edge 12 13], Graph.path g10 1 9 ~?= Nothing, Graph.path g11 3 5 ~?= Just [Edge 3 5], Graph.path g9 3 4 ~?= Just [Edge 3 4], Graph.path g8 3 4 ~?= Nothing]

test_topo :: Test
test_topo = TestList[Graph.topo g3 ~?= [fromList [1, 4], fromList [5], fromList [3], fromList [2]], Graph.topo g6 ~?= [fromList [1], fromList [2], fromList [3], fromList [4]], Graph.topo g7 ~?= [fromList [1], fromList [2], fromList [3]],
                     Graph.topo g9 ~?= [fromList [2], fromList [3], fromList [4, 5], fromList [1]]]
--
-- Tarefa 1
--
-- Defina testes unitários para todas as funções do módulo Graph,
-- tentando obter o máximo de cobertura de expressões, condições, etc.
--
           
main = runTestTT $ TestList [test_adj, test_isDAG, test_Swap, test_isEmpty, test_isValid, test_isForest, test_isSubgraphOf, test_transpose, test_union, test_reachable, test_isPathOf, test_path, test_topo, test_bft]

--
-- Teste aleatório
--

--
-- Tarefa 2
--
-- A instância de Arbitrary para grafos definida abaixo gera grafos
-- com muito poucas arestas, como se pode constatar testando a
-- propriedade prop_valid.
-- Defina uma instância de Arbitrary menos enviesada.
-- Este problema ainda é mais grave nos geradores dag e forest que
-- têm como objectivo gerar, respectivamente, grafos que satisfazem
-- os predicados isDag e isForest. Estes geradores serão necessários
-- para testar propriedades sobre estas classes de grafos.
-- Melhore a implementação destes geradores por forma a serem menos enviesados.
--

-- Instância de Arbitrary para arestas
instance Arbitrary v => Arbitrary (Edge v) where
    arbitrary = do s <- arbitrary
                   t <- arbitrary
                   return $ Edge {source = s, target = t}

instance (Ord v, Arbitrary v) => Arbitrary (Graph v) where
    arbitrary = geraGrafo `suchThat` isValid
        where geraGrafo = do ns <- arbitrary
                             return $ Graph {nodes = fromList ns, edges = fromList (cEdges ns)}

cEdges :: [v] -> [Edge v]
cEdges [] = []
cEdges [x] = [Edge x x]
cEdges (h:t:z) = [Edge h t] ++ cEdges z

 
prop_valid :: Graph Int -> Property
prop_valid g = collect (length (edges g)) $ isValid g

-- Gerador de DAGs
dag :: (Ord v, Arbitrary v) => Gen (DAG v)
dag = arbitrary `suchThat` isDAG

prop_dag :: Property
prop_dag = forAll (dag :: Gen (DAG Int)) $ \g -> collect (length (edges g)) $ isDAG g

-- Gerador de florestas
forest :: (Ord v, Arbitrary v) => Gen (Forest v)
forest = arbitrary `suchThat` isForest

prop_forest :: Property
prop_forest = forAll (forest :: Gen (Forest Int)) $ \g -> collect (length (edges g)) $ isForest g

--
-- Tarefa 3
--
-- Defina propriedades QuickCheck para testar todas as funções
-- do módulo Graph.
--

-- Exemplo de uma propriedade QuickCheck para testar a função adj          
prop_adj :: Graph Int -> Property
prop_adj g = forAll (elements $ elems $ nodes g) $ \v -> adj g v `isSubsetOf` edges g

-- Exemplos de propriedades QuickCheck para testar a função Swap
prop_Swap :: Eq v => Edge v -> Bool
prop_Swap  (Edge x y) = ((target(Edge x y)) == (source(swap(Edge x y)))) && ((source(Edge x y)) == (target(swap(Edge x y))))

prop_swap2 :: Edge Int -> Bool
prop_swap2 x = ((swap(swap x)) == x)

-- Exemplos de propriedades QuickCheck para testar a função isEmpty
prop_isEmpty :: Graph Int -> Bool
prop_isEmpty x = (vazio x == isEmpty x)

vazio :: Graph v -> Bool
vazio x = Prelude.null (elems (nodes x))

prop_isEmpty2 :: Graph Int -> Property
prop_isEmpty2 x = isEmpty x ==> (length (nodes x)) == 0

-- Exemplos de propriedades QuickCheck para testar a função isValid
prop_isValid :: Graph Int -> Bool
prop_isValid x = (Prelude.map source (toList(edges x)) `unionP` Prelude.map target (toList(edges x))) `isLista` (toList(nodes x)) == (isValid x)

unionP :: Eq a => [a] -> [a] -> [a]
unionP [] x = x
unionP (h:t) (x:y) = if (x==h) then h: unionP t y
                     else h: unionP t (x:y)

isLista :: [Int] -> [Int] -> Bool
isLista [] [] = True
isLista [] x = True
isLista x [] = False
isLista (x:y) (h:t) | (elem x (h:t)) = (isLista y (h:t))
                    | otherwise = False

-- Exemplos de propriedades QuickCheck para testar a função isDag
prop_isDAG :: Graph Int -> Property
prop_isDAG g  | (length (edges g) == 0) = label "Vazio" True  
              | otherwise = isDAG g ==> (forAll (elements (toList (edges g)))) $ \v -> forAll( elements (toList (reachable g (target v)))) $ \n -> not((source v) `elem` toList(reachable g  n))

prop_isDAG_t :: Graph Int -> Property
prop_isDAG_t x = isDAG x ==> (isDAG (transpose x))

-- Exemplos de propriedades QuickCheck para testar a função isValid
prop_isForest :: Graph Int -> Property
prop_isForest f = isForest f ==> forAll (elements (toList (nodes f))) $ \v -> length (adj f v) == 0 || length (adj f v) == 1

-- Exemplos de propriedades QuickCheck para testar a função isSubgraphOf
prop_isSubgraphOf :: Graph Int -> Graph Int -> Property
prop_isSubgraphOf x y = isSubgraphOf x y ==> isSubsetOf (nodes x) (nodes y) && isSubsetOf (edges x) (edges y)

prop_isSubgraphOf_E :: Graph Int -> Graph Int -> Property
prop_isSubgraphOf_E g h = isEmpty g ==> isSubgraphOf g h

-- Exemplos de propriedades QuickCheck para testar a função transpose
prop_transpose :: Graph Int -> Bool
prop_transpose x = ((nodes x) == (nodes (transpose x))) && (all prop_Swap (edges x))

prop_transpose2 :: Graph Int -> Bool
prop_transpose2 x = ((transpose(transpose x))==x)

-- Exemplos de propriedades QuickCheck para testar a função union
prop_union :: Graph Int -> Graph Int -> Bool
prop_union g1 g2 = (prop_union_nodes g1 g2) && (prop_union_edges g1 g2)

prop_union_nodes :: Graph Int -> Graph Int -> Bool
prop_union_nodes x y = (fromList $ (elems (nodes x)) ++ (elems (nodes y))) == (fromList (elems (nodes (Graph.union x y))))

prop_union_edges :: Graph Int -> Graph Int -> Bool
prop_union_edges x y = (fromList $ (elems (edges x)) ++ (elems (edges y))) == (fromList (elems (edges (Graph.union x y))))

-- Exemplos de propriedades QuickCheck para testar a função bft
prop_bft :: Graph Int -> Property
prop_bft g = forAll (sublistOf (elems (nodes g))) $ \v -> ((Set.map (source) (edges (bft g (fromList v)))) `Set.union` (Set.map (target) (edges (bft g (fromList v))))) `Set.union` (fromList v) == ((nodes (bft g (fromList v))))

-- Exemplos de propriedades QuickCheck para testar a função reachable

prop_reachable :: Graph Int -> Property
prop_reachable x = if (length (nodes x) == 0) then label "Sem nodos" True
                   else forAll (elements (elems (nodes x))) $ \v -> (reachable x v) `isSubsetOf` (nodes x)

-- Exemplos de propriedades QuickCheck para testar a função isPathOf
prop_isPathOf :: Graph Int -> Property
prop_isPathOf g = if (length (nodes g) == 0) || (length (edges g) == 0) then label "Sem arestas" True
                  else forAll (elements (elems (edges g))) $ \a -> isPathOf [a] g

-- Exemplos de propriedades QuickCheck para testar a função path
prop_path :: Graph Int -> Property
prop_path g | ((length (nodes g) == 0) || (length (edges g) == 0)) = label "Zero caminhos" True
            | otherwise = forAll (elements (elems (nodes g))) $ \v -> forAll (elements (elems (nodes g))) $ \s -> if ((path g v s) == Nothing) then True else fromList(isJust(path g v s)) `isSubsetOf` (edges g)
                                                                                                                  where isJust (Just t) = t

-- Exemplos de propriedades QuickCheck para testar a função topo
--prop_topo :: Graph Int -> Property
--prop_topo








