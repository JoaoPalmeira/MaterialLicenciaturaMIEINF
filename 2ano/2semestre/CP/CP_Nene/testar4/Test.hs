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
-- Número: André Rodrigues Freitas
-- Nome: a74619
-- Curso: MiEI
--
-- Aluno 2
-- Número: Sofia Manuela Gomes de Carvalho 
-- Nome: a76658
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

--
-- Grafos para teste
--

g2 :: Graph Int
g2 = Graph {nodes = fromList [1, 2, 3, 4],
            edges = fromList [Edge 1 2, Edge 1 4, Edge 4 2, Edge 3 2, Edge 3 4]
            }

-- Grafo Acíclico (isDAG)
g3 :: Graph Int
g3 = Graph {nodes = fromList [10, 11, 12, 13],
            edges = fromList [Edge 12 11, Edge 11 13, Edge 12 13, Edge 10 13]
            }

-- Grafo Cíclico (isDAG false)
g4 :: Graph Int
g4 = Graph {nodes = fromList [10, 11, 12, 13],
            edges = fromList [Edge 11 10, Edge 10 12, Edge 12 13, Edge 13 11]
            }

-- Grafo Inválido (aresta tem como origem e destino um vértice não pertencente ao grafo)
g5 :: Graph Int
g5 = Graph {nodes = fromList [10, 11, 12, 13],
            edges = fromList [Edge 11 10, Edge 9 11, Edge 10 12, Edge 12 13, Edge 13 11]
        }

-- Grafo Inválido (tem mais de uma aresta entre dois pares de nós)
-- g6

-- subgrafo de g2
g7 ::Graph Int 
g7 = Graph {nodes = fromList [1, 2, 3, 4],
            edges = fromList [Edge 4 2, Edge 3 4]
           }

-- não é subgrafo de g2 (falha nas arestas)
g8 :: Graph Int
g8 = Graph {nodes = fromList [1, 2, 3, 4],
            edges = fromList [Edge 4 2, Edge 3 4, Edge 2 4]
           }

-- não é subgrafo de g2 (falha nos nós)
g9 :: Graph Int
g9 = Graph {nodes = fromList [1, 2, 3, 4, 5],
            edges = fromList [Edge 4 2, Edge 3 4]
            }

-- transposta de g3
g10 :: Graph Int
g10 = Graph {nodes = fromList [10, 11, 12, 13],
             edges = fromList [Edge 11 12, Edge 13 11, Edge 13 12, Edge 13 10]
            }

g11 :: Graph Int
g11 = Graph {nodes = fromList [],
             edges = fromList[]
            }

g12 :: Graph Int
g12 = Graph {nodes = fromList [1, 2, 3, 4],
             edges = fromList [Edge 1 2, Edge 2 3, Edge 4 3]
            }

g13 :: Graph Int
g13 = Graph { nodes = fromList [1,2,3,4,5,6],
              edges = fromList [Edge 1 2, Edge 1 3, Edge 2 4, Edge 4 6, Edge 3 6, Edge 3 5, Edge 5 6]
}

-- union de g10 e g12
g14 :: Graph Int
g14 = Graph {nodes = fromList [10, 11, 12, 13, 1, 2, 3, 4], 
             edges = fromList [Edge 11 12, Edge 13 11, Edge 13 12, Edge 13 10, Edge 1 2, Edge 2 3, Edge 4 3]
            }

-- grafos para a bft
g15 :: Graph Int 
g15 = Graph {nodes = fromList [10,11,12,13], 
             edges = fromList [Edge {source = 12, target = 10},Edge {source = 13, target = 12}]
           }           

g16 :: Graph Int
g16 = Graph {nodes = fromList [3,4,5,6], 
             edges = fromList [Edge {source = 6, target = 3}]
           }

g17 :: Graph Int
g17 = Graph {nodes = fromList [10,13], 
             edges = fromList []
            }



p1 :: Graph.Path Int
p1 = [Edge 1 3, Edge 3 4]

p2 :: Graph.Path Int
p2 = [Edge 2 3, Edge 3 4]

    

p5 :: Graph.Path Int
p5 = [Edge 13 11, Edge 11 12]

p6 :: Graph.Path Int
p6 = []

--
-- Tarefa 1
--
-- Defina testes unitários para todas as funções do módulo Graph,
-- tentando obter o máximo de cobertura de expressões, condições, etc.
--

test_adj :: Test
test_adj = TestList[adj g1 1 ~?= fromList [Edge 1 1], adj g2 1 ~?= fromList [Edge 1 2, Edge 1 4], adj g3 12 ~?= fromList [Edge 12 11, Edge 12 13], 
                    adj g4 10 ~?= fromList [Edge 10 12]]  

test_swap :: Test
test_swap = swap (Edge 1 2) ~?= Edge 2 1

test_isEmpty :: Test
test_isEmpty = TestList[Graph.isEmpty g11~?=True, Graph.isEmpty Graph.empty~?=True, Graph.isEmpty g9~?=False, 
                        Graph.isEmpty g8~?=False, Graph.isEmpty g5~?=False]

test_isValid :: Test
test_isValid = TestList[Graph.isValid g5~?=False, Graph.isValid g2~?=True]

test_isDAG :: Test
test_isDAG = TestList[Graph.isDAG g3~?=True, Graph.isDAG g4~?=False, Graph.isDAG g10~?=True, Graph.isDAG g7~?=True, Graph.isDAG g8~?=False]

test_isForest :: Test
test_isForest = TestList[Graph.isForest g4~?=False, Graph.isForest g3~?=False, Graph.isForest g10~?=False, Graph.isForest g7~?=True, 
                         Graph.isForest g9~?=True, Graph.isForest g1~?=False]

test_isSubgraphOf :: Test
test_isSubgraphOf = TestList[Graph.isSubgraphOf g7 g2 ~?=True, Graph.isSubgraphOf g8 g2~?=False, Graph.isSubgraphOf g9 g2~?=False]

test_transpose :: Test
test_transpose = TestList[Graph.transpose g3~?=g10]

test_union :: Test 
test_union = TestList[Graph.union g7 g9 ~?= g9, Graph.union g7 g8 ~?= g8, Graph.union g10 g12 ~?= g14, Graph.union g10 g11 ~?= g10]

test_bft :: Test
test_bft = TestList[Graph.bft g4 (fromList[10,11]) ~?= g15, Graph.bft g13 (fromList[3,4,5]) ~?= g16, Graph.bft g3 (fromList[10,13]) ~?= g17]

test_reachable :: Test
test_reachable = TestList[Graph.reachable g10 11 ~?= fromList[11, 12], Graph.reachable g7 1 ~?= fromList[1], Graph.reachable g3 12 ~?= fromList[12, 11, 13]]

test_isPathOf :: Test 
test_isPathOf = TestList[Graph.isPathOf p5 g10 ~?= True, Graph.isPathOf p1 g12 ~?= False, Graph.isPathOf p2 g9 ~?= False, Graph.isPathOf p6 g9 ~?= True]

test_path :: Test
test_path = TestList[Graph.path g9 11 1 ~?= Nothing, Graph.path g10 13 12 ~?= Just [Edge 13 12], Graph.path g13 1 6 ~?= Just [Edge 1 3, Edge 3 6], 
                     Graph.path g12 1 4 ~?= Nothing]

test_topo :: Test
test_topo = TestList[Graph.topo g3 ~?= [fromList [10, 12], fromList [11], fromList [13]], Graph.topo g9 ~?= [fromList [1, 3, 5], fromList [4], fromList [2]], 
                     Graph.topo g2 ~?= [fromList [1, 3], fromList [4], fromList [2]]]


main = runTestTT $ TestList [test_adj, test_swap, test_isEmpty, test_isValid, test_isDAG, test_isForest, test_isSubgraphOf, test_transpose, test_union, test_bft, 
                             test_reachable, test_isPathOf, test_path, test_topo]

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
    arbitrary = aux `suchThat` isValid
        where aux = do ns <- arbitrary
                       es <- arbitrary 
                       return $ Graph {nodes = fromList ns, edges = fromList es}


--geraArestas :: Arbitrary v => Gen (Edge v)
--geraArestas = arbitrary `suchThat`  

--verifica :: Edge v -> [Int] -> Bool
--verifica e (x:xs) | (Edge {(source e == x), target e}) = (Edge {source e, elem (target e)}) xs
  --                | ((target e)==x) = elem (source e) xs
    --              | otherwise = verifica (Edge e) xs 

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

prop_swap :: Graph Int -> Property
prop_swap g = forAll (elements $ elems $ edges g) $ \e -> swap(swap e) `member` edges g 
