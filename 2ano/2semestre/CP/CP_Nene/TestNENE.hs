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

g2 :: Graph Char
g2 = Graph {nodes = fromList ['A', 'B', 'C', 'D'],
			edges = fromList [Edge 'A' 'B', Edge 'A' 'D', Edge 'D' 'B', Edge 'C' 'B', Edge 'C' 'D']
			}

-- Grafo Acíclico (isDAG)
g3 :: Graph Char
g3 = Graph {nodes = fromList ['W', 'X', 'Y', 'Z'],
			edges = fromList [Edge 'Y' 'X', Edge 'X' 'Z', Edge 'Y' 'Z', Edge 'W' 'Z']
			}

-- Grafo Cíclico (isDAG false)
g4 :: Graph Char
g4 = Graph {nodes = fromList ['W', 'X', 'Y', 'Z'],
			edges = fromList [Edge 'X' 'W', Edge 'W' 'Y', Edge 'Y' 'Z', Edge 'Z' 'X']
			}

-- Grafo Inválido (aresta tem como origem e destino um vértice não pertencente ao grafo)
g5 :: Graph Char
g5 = Graph {nodes = fromList ['W', 'X', 'Y', 'Z'],
			edges = fromList [Edge 'X' 'W', Edge 'V' 'X', Edge 'W' 'Y', Edge 'Y' 'Z', Edge 'Z' 'X']
			}

-- Grafo Inválido (tem mais de uma aresta entre dois pares de nós)
-- g6

-- subgrafo de g2
g7 ::Graph Char 
g7 = Graph {nodes = fromList ['A', 'B', 'C', 'D'],
			edges = fromList [Edge 'D' 'B', Edge 'C' 'D']
			}

-- não é subgrafo de g2 (falha nos nós)
g8 :: Graph Char
g8 = Graph {nodes = fromList ['A', 'B', 'C', 'D'],
			edges = fromList [Edge 'D' 'B', Edge 'C' 'D', Edge 'B' 'D']
			}
-- não é subgrafo de g2 (falha nas arestas)
g9 :: Graph Char
g9 = Graph {nodes = fromList ['A', 'B', 'C', 'D', 'E'],
            edges = fromList [Edge 'D' 'B', Edge 'C' 'D']
            }

-- transposta de g3
g10 :: Graph Char
g10 = Graph {nodes = fromList ['W', 'X', 'Y', 'Z'],
			edges = fromList [Edge 'X' 'Y', Edge 'Z' 'X', Edge 'Z' 'Y', Edge 'Z' 'W']
			}

g11 :: Graph Char
g11 = Graph {nodes = fromList [],
			 edges = fromList[]
			 }

g12 :: Graph Char
g12 = Graph {nodes = fromList ['A', 'B', 'C', 'D'],
             edges = fromList [Edge 'A' 'B', Edge 'B' 'C', Edge 'D' 'C']
            }

g13 :: Graph Int
g13 = Graph { nodes = fromList [1,2,3,4,5,6],
              edges = fromList [Edge 1 2, Edge 1 3, Edge 2 4, Edge 4 6, Edge 3 6, Edge 3 5, Edge 5 6]
} 

p1 :: Graph.Path Char
p1 = [Edge 'A' 'C', Edge 'C' 'D']

p2 :: Graph.Path Char
p2 = [Edge 'B' 'C', Edge 'C' 'D']

p3 :: Graph.Path Char
p3 = [Edge 'A' 'C', Edge 'C' 'D', Edge 'B' 'D']

p4 :: Graph.Path Char
p4 = [Edge 'A' 'C', Edge 'C' 'E']      

p5 :: Graph.Path Char
p5 = [Edge 'Z' 'X', Edge 'X' 'Y']

--
-- Tarefa 1
--
-- Defina testes unitários para todas as funções do módulo Graph,
-- tentando obter o máximo de cobertura de expressões, condições, etc.
--

test_adj :: Test
test_adj = TestList[adj g1 1 ~?= fromList [Edge 1 1], adj g2 'A' ~?= fromList [Edge 'A' 'B', Edge 'A' 'D'], adj g3 'Y' ~?= fromList [Edge 'Y' 'X', Edge 'Y' 'Z'], adj g4 'W' ~?= fromList [Edge 'W' 'Y']]  

test_swap :: Test
test_swap = swap (Edge 'A' 'B') ~?= Edge 'B' 'A'

test_isEmpty :: Test
test_isEmpty = TestList[Graph.isEmpty g11~?=True, Graph.isEmpty Graph.empty~?=True, Graph.isEmpty g9~?=False, Graph.isEmpty g8~?=False, Graph.isEmpty g5~?=False]

test_isValid :: Test
test_isValid = TestList[Graph.isValid g5~?=False, Graph.isValid g2~?=True]

test_isDAG :: Test
test_isDAG = TestList[Graph.isDAG g3~?=True, Graph.isDAG g4~?=False, Graph.isDAG g10~?=True, Graph.isDAG g7~?=True, Graph.isDAG g8~?=False]

test_isForest :: Test
test_isForest = TestList[Graph.isForest g4~?=False, Graph.isForest g3~?=False, Graph.isForest g10~?=False, Graph.isForest g7~?=False, Graph.isForest g9~?=True, Graph.isForest g1~?=True]

test_isSubgraphOf :: Test
test_isSubgraphOf = TestList[Graph.isSubgraphOf g7 g2 ~?=True, Graph.isSubgraphOf g8 g2~?=False, Graph.isSubgraphOf g9 g2~?=False]

test_transpose :: Test
test_transpose = TestList[Graph.transpose g3~?=g10]

--test_union :: Test
-- test_union UNION ALERTA

test_reachable :: Test
test_reachable = TestList[Graph.reachable g10 'X' ~?= fromList['X','Y'], Graph.reachable g7 'A' ~?= fromList['A'], Graph.reachable g3 'Y' ~?= fromList['Y','X','Z']]

test_isPathOf :: Test 
test_isPathOf = TestList[Graph.isPathOf p5 g10 ~?= True, Graph.isPathOf p1 g12 ~?= False, Graph.isPathOf p2 g9 ~?= False]

test_path :: Test
test_path = TestList[Graph.path g9 'X' 'A' ~?= Nothing, Graph.path g10 'Z' 'Y' ~?= Just [Edge 'Z' 'Y'], Graph.path g13 1 6 ~?= Just [Edge 1 3, Edge 3 6], Graph.path g12 'A' 'D' ~?= Nothing]

--test_topo :: Test


main = runTestTT $ TestList [test_adj]

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
