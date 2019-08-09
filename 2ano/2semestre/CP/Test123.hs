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
-- Número: A75353
-- Nome: Júlio Dinis Sá Peixoto
-- Curso: MIEI
--
-- Aluno 2
-- Número: A75210
-- Nome: Marcelo Alexandre Matos Fonseca Lima
-- Curso: MIEI
--


module Main where

import Graph
import Test.HUnit hiding (path)
import Test.QuickCheck
import Data.Set as Set
import Data.Maybe
--
-- Teste unitário
--

--Edge
edge1 :: Edge Int
edge1 = Edge 1 5

--Grafos Validos--------------------------------------------
g0 :: Graph Int
g0 = Graph {nodes = fromList [],
            edges = fromList []
           }
g1 :: Graph Int
g1 = Graph {nodes = fromList [1,2,3,5],
            edges = fromList [Edge 1 2, Edge 3 2, Edge 2 5]
           }
g2 :: Graph Int
g2 = Graph {nodes = fromList [1,2,3],
            edges = fromList []
           }
--Grafo ciclico
g3 :: Graph Int
g3 = Graph {nodes = fromList [1,2,3],
            edges = fromList [Edge 1 2, Edge 2 3, Edge 3 1]
           }
--Grafo que tem um nodo com mais de um adjacente
g4 :: Graph Int
g4 = Graph {nodes = fromList [1,2,3,4],
            edges = fromList [Edge 1 2, Edge 4 1, Edge 1 1]
           }
--Subgrafo de g1
g5 :: Graph Int
g5 = Graph {nodes = fromList [1,2],
            edges = fromList [Edge 1 2]
           }
--Grafo transposto de g4
g6 :: Graph Int
g6 = Graph {nodes = fromList [1,2,3,4],
            edges = fromList [Edge 2 1, Edge 1 4, Edge 1 1]
           }
g7 :: Graph Int
g7 = Graph {nodes = fromList [1,2,3,4,5],
            edges = fromList [Edge 1 2, Edge 1 3, Edge 2 5, Edge 3 4, Edge 4 5]
           }
--Grafos Invalidos-----------------------------------------
--Grafo com aresta para nodo que nao existe
g0x :: Graph Int
g0x = Graph {nodes = fromList [1,3],
             edges = fromList [Edge 1 2, Edge 1 3]
           }
--Grafo com mais do que uma aresta entre dois pares de nos
--g1x :: Graph Int
--g1x = Graph {nodes = fromList [1,2,3],
  --           edges = fromList [Edge 1 2, Edge 1 2]
    --       }


--
-- Tarefa 1
--
-- Defina testes unitários para todas as funções do módulo Graph,
-- tentando obter o máximo de cobertura de expressões, condições, etc.
--
--Test swap----------------
test_swap :: Test
test_swap = swap edge1 ~=? Edge 5 1

--Test empty-----------------
test_empty :: Test
test_empty = Graph.empty ~=? g0

--Test isEmpty----------------
test_isEmpty :: Test
test_isEmpty = isEmpty g0 ~=? True

test_isEmpty2 :: Test
test_isEmpty2 = isEmpty g1 ~=? False

--Test isValid------------------
test_isValid :: Test
test_isValid = isValid g1 ~=? True

test_isValid2 :: Test
test_isValid2 = isValid g0x ~=? False

--nao esta a funcionar
--test_isValid3 :: Test 
--test_isValid3 = isValid g1x ~=? False

--Test isDAG---------------------
test_isDAG :: Test
test_isDAG = isDAG g1 ~=? True 

test_isDAG2 :: Test
test_isDAG2 = isDAG g0x ~=? False

test_isDAG3 :: Test
test_isDAG3 = isDAG g3 ~=? False

--Test isForest----------------------
test_isForest :: Test
test_isForest = isForest g1 ~=? True

test_isForest2 :: Test
test_isForest2 = isForest g3 ~=? False

test_isForest3 :: Test
test_isForest3 = isForest g4 ~=? False

--Test isSubgraphOf-----------------
test_isSubgraphOf :: Test
test_isSubgraphOf = isSubgraphOf g5 g1 ~=? True

test_isSubgraphOf2 :: Test
test_isSubgraphOf2 = isSubgraphOf g1 g5 ~=? False

--Test adj----------------------------
test_adj :: Test
test_adj = adj g1 1 ~?= fromList [Edge 1 2]

--Test transpose---------------------
test_transpose :: Test
test_transpose = transpose g4 ~=? Graph {nodes = fromList [1,2,3,4],
                                         edges = fromList [Edge 2 1, Edge 1 4, Edge 1 1]
                                        }

--Test union---------------------------
test_union :: Test
test_union = Graph.union g1 g4 ~=? Graph {nodes = fromList [1,2,3,4,5],
                                          edges = fromList [Edge 1 2, Edge 4 1, Edge 1 1, Edge 3 2, Edge 2 5]
                                         }

--Test bft------------------------------
test_bft :: Test
test_bft = bft g1 (fromList[5]) ~=? Graph {nodes = fromList [5],
                                           edges = fromList []
                                          }
test_bft2 :: Test
test_bft2 = bft g1 (fromList[1]) ~=? Graph {nodes = fromList [1,2,5],
                                            edges = fromList [Edge 2 1, Edge 5 2]
                                           }

--Test reachable--------------------------
test_reachable :: Test
test_reachable = reachable g1 1 ~=? fromList[1,2,5]

--Test isPathOf-----------------------------
test_isPathOf :: Test
test_isPathOf = isPathOf [Edge 1 2, Edge 2 5] g1 ~=? True

test_isPathOf2 :: Test
test_isPathOf2 = isPathOf [Edge 1 2, Edge 3 2] g1 ~=? False

test_isPathOf3 :: Test
test_isPathOf3 = isPathOf [Edge 1 2, Edge 2 6] g1 ~=? False

--Test path--------------------------------
test_path :: Test
test_path = path g1 1 3 ~=? Nothing

test_path2 :: Test
test_path2 = path g7 1 5 ~=? Just[Edge 1 2, Edge 2 5]

--Test topo---------------------------------
test_topo :: Test
test_topo = topo g0 ~=? []

test_topo2 :: Test 
test_topo2 = topo g1 ~=? [fromList [1,3],fromList [2],fromList [5]]



main = runTestTT $ TestList [test_swap, test_empty, test_isEmpty, test_isEmpty2, 
                             test_isValid, test_isValid2, test_isDAG, 
                             test_isDAG2, test_isDAG3, test_isForest, test_isForest2, 
                             test_isForest3, test_isSubgraphOf, test_isSubgraphOf2,test_transpose, test_union,
                             test_adj,test_bft ,test_bft2,test_reachable, test_topo,test_isPathOf,
                             test_isPathOf2, test_isPathOf3,test_path,test_path2, test_topo, test_topo2]

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
    arbitrary = do ns <- arbitrary
                   if (Set.null $ fromList ns)
                   then do return $ Graph {nodes = Set.empty, edges = Set.empty}
                   else do es <- listOf $ ger_edge(ns)
                           return $ Graph {nodes = fromList ns, edges = fromList es}

ger_edge :: Arbitrary a => [a] -> Gen (Edge a)
ger_edge l = do f <- elements l 
                s <- elements l
                return (Edge f s) 

prop_valid :: Graph Int -> Property
prop_valid g = collect (length (edges g)) $ isValid g

-- Gerador de DAGs
dag :: (Ord v, Arbitrary v) => Gen (DAG v)
dag = do number <- choose(0,50)
         ns <- arbitrary
         if (Prelude.null ns)
         then return $ Graph {nodes = Set.empty, edges = Set.empty}
         else ger_dag (Graph {nodes = (fromList ns), edges = Set.empty}) number

ger_dag :: (Ord v, Arbitrary v) => Graph v -> Int -> Gen(DAG v)
ger_dag graph 0 = do return graph
ger_dag graph n = do es <- ger_edge (elems (nodes graph))
                     if (isDAG $ Graph (nodes graph) (insert  es (edges graph)))
                     then ger_dag (Graph (nodes graph) (insert es (edges graph))) (n-1)
                     else ger_dag graph (n-1)

prop_dag :: Property
prop_dag = forAll (dag :: Gen (DAG Int)) $ \g -> collect (length (edges g)) $ isDAG g


-- Gerador de florestas
forest :: (Ord v, Arbitrary v) => Gen (Forest v)
forest = do number <- choose(0,50)
            ns <- arbitrary
            if (Prelude.null ns)
            then return $ Graph {nodes = Set.empty, edges = Set.empty}
            else ger_forest (Graph {nodes = (fromList ns), edges = Set.empty}) number

ger_forest :: (Ord v, Arbitrary v) => Graph v -> Int -> Gen(DAG v)
ger_forest graph 0 = do return graph
ger_forest graph n = do es <- ger_edge (elems (nodes graph))
                        if (isForest $ Graph (nodes graph) (insert  es (edges graph)))
                        then ger_forest (Graph (nodes graph) (insert es (edges graph))) (n-1)
                        else ger_forest graph (n-1)

prop_forest :: Property
prop_forest = forAll (forest :: Gen (Forest Int)) $ \g -> collect (length (edges g)) $ isForest g

--
-- Tarefa 3
--
-- Defina propriedades QuickCheck para testar todas as funções
-- do módulo Graph.
--


--Prop swap----------------
prop_swap :: Edge Int -> Property
prop_swap g = (source g == target (swap g)) .&&. (target g == source (swap g))

--Prop empty-----------------
prop_empty :: Property
prop_empty = property (isEmpty (Graph.empty))

--Prop isEmpty----------------
prop_isEmpty :: Graph Int -> Property
prop_isEmpty g  = Set.null(nodes g) ==> property (isEmpty g)

--Prop isValid------------------
prop_isValid :: Graph Int -> Property
prop_isValid g | Set.null(edges g) = label "isValid" True
               | otherwise = forAll (elements $ elems $ edges g) $ \a -> fromList[source a,target a] `isSubsetOf` nodes g ==> isValid g

--Prop isDAG---------------------
prop_isDAG :: Graph Int -> Property
prop_isDAG d = isDAG d ==> forAll (elements $ elems $ nodes d) $ \v -> if (not(Set.null (adj d v))) then forAll (elements $ elems $ (adj d v)) $ \a -> v `notMember` reachable d (target a) else property True

--Prop isForest----------------------
prop_isForest :: Graph Int -> Property
prop_isForest f = isForest f ==> forAll (elements $ elems $ nodes f) $ \v -> length (adj f v) <= 1

--Prop isSubgraphOf-----------------
prop_isSubgraphOf :: Graph Int -> Graph Int -> Property
prop_isSubgraphOf g h = isSubgraphOf g h ==> isSubsetOf (nodes g) (nodes h) && isSubsetOf (edges g) (edges h)

prop_isSubgraphOf2 :: Graph Int -> Graph Int -> Property
prop_isSubgraphOf2 g h = isEmpty g ==> isSubgraphOf g h

-- Exemplo de uma propriedade QuickCheck para testar a função adj
prop_adj :: Graph Int -> Property
prop_adj g = forAll (elements $ elems $ nodes g) $ \v -> adj g v `isSubsetOf` edges g

prop_adj2 :: Graph Int -> Property
prop_adj2 g = forAll (elements $ elems $ nodes g) $ \v -> Set.map target(adj g v) `isSubsetOf` nodes g

--Prop transpose----------------------
prop_transpose :: Graph Int -> Property
prop_transpose g = property (transpose(transpose g) == g)

--Prop union---------------------------
prop_union :: Graph Int -> Graph Int -> Property
prop_union g h = isSubgraphOf g (Graph.union g h) .&&. isSubgraphOf h (Graph.union g h)

--Prop bft------------------------------
--prop_bft :: Graph Int -> Set Int -> Property
--prop_bft g 

--Prop reachable--------------------------
prop_reachable :: Graph Int -> Property
prop_reachable g | Set.null(nodes g) = label "Vazio" True
                 | otherwise = forAll (elements $ elems $ nodes g) $ \v -> (reachable g v) `isSubsetOf` nodes g

--Prop isPathOf--------------------------
prop_isPathOf :: Graph Int -> Property
prop_isPathOf g | Set.null(nodes g) || Set.null(edges g) = label "Não existem caminhos" True
                | otherwise = forAll (elements $ elems $ edges g) $ \a -> isPathOf [a] g

prop_isPathOf2 :: Graph Int -> Property
prop_isPathOf2 g | Set.null(nodes g) || Set.null(edges g) = label "Não existem caminhos" True
                 | otherwise = forAll (elements $ elems $ nodes g) $ \v -> forAll (elements $ elems $ (nodes g)) $ \v1 -> if ((path g v v1) /= Nothing) then isPathOf (fromJust(path g v v1)) g else True

--Prop path---------------------------
prop_path :: Graph Int -> Property
prop_path g | Set.null(nodes g) || Set.null(edges g) = label "Não existem caminhos" True
            | otherwise = forAll (elements $ elems $ nodes g) $ \v -> forAll (elements $ elems $ (nodes g)) $ \v1 -> if ((path g v v1) /= Nothing) then fromList(fromJust(path g v v1)) `isSubsetOf` edges g else True 

--Prop topo--------------------------