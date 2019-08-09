{-|
Module      : Graph
Description : Módulo de grafos.

Módulo com vários algoritmos de grafos orientados e não pesados.
-}
module Graph where

import Control.Monad.State
import Data.Maybe
import Data.Set as Set
   
-- | O tipo 'Edge' representa uma aresta entre dois vértices.
data Edge v = Edge {source :: v, target :: v}
              deriving (Show,Eq,Ord)

-- | A função 'swap' troca a origem com o destino de um vértice.
swap :: Edge v -> Edge v
swap e = Edge {source = target e, target = source e}
      
-- | O tipo 'Graph' representa um grafo orientado.
data Graph v = Graph {nodes :: Set v, edges :: Set (Edge v)}
               deriving Show

-- | Instância de 'Eq' para grafos.
instance Ord v => Eq (Graph v) where
    g == g' = nodes g == nodes g' && edges g == edges g'
              
-- | O grafo vazio.
empty :: Graph v
empty = Graph {nodes = Set.empty, edges = Set.empty}
                        
-- | A função 'isEmpty' testa se um grafo é vazio.
isEmpty :: Graph v -> Bool
isEmpty g = Set.null (nodes g)
            
-- | A função 'isValid' teste se um grafo é valido, ou seja, se todos as arestas têm
-- como origem e destino um dos vértices do grafo, e se não há mais do que uma aresta
-- entre dois pares de nós.
isValid :: Ord v => Graph v -> Bool
isValid g = (Set.map source (edges g) `Set.union` Set.map target (edges g)) `isSubsetOf` nodes g
          
-- | A função 'isDAG' testa se um grafo é acíclico.
isDAG :: Ord v => Graph v -> Bool
isDAG g = isValid g && all nocycle (nodes g)
    where nocycle v = all (\a -> v `notMember` reachable g a) $ Set.map target (adj g v)

-- | O tipo 'DAG v' é um alias para grafos que satisfazem o predicado 'isDAG'.
type DAG v = Graph v
                      
-- | A função 'isForest' testa se um DAG válido é uma floresta (um conjunto de árvores), ou seja,
-- se cada vértice tem no máximo um adjacente.
isForest :: Ord v => DAG v -> Bool
isForest g = isDAG g && all (\v -> length (adj g v) <= 1) (nodes g)
          
-- | O tipo 'Forest' é um alias para DAGs que satisfazem o predicado 'isForest'.
type Forest v = DAG v

-- | A função 'isSubgraphOf' testa se o primeiro grafo é um subgrafo do segundo.
isSubgraphOf :: Ord v => Graph v -> Graph v -> Bool
isSubgraphOf g g' = isSubsetOf (nodes g) (nodes g') && isSubsetOf (edges g) (edges g')

-- | A função 'adj' retorna a lista de arestas adjacentes de um dado vértice.
adj :: Ord v => Graph v -> v -> Set (Edge v)
adj g v = Set.filter ((==v) . source) $ edges g
    
-- | A função 'transpose' inverte todas as arestas de um grafo.
transpose :: Ord v => Graph v -> Graph v
transpose g = Graph {nodes = nodes g, edges = Set.map swap (edges g)}

-- | A função `union` faz a união de dois grafos.
union :: Ord v => Graph v -> Graph v -> Graph v
union g g' = Graph {nodes = nodes g `Set.union` nodes g', edges = edges g `Set.union` edges g'}

-- | A função 'bft' faz uma travessia por níveis a partir de um conjunto de vértices.
-- Os adjacentes não visitados de um vértice são visitados por ordem crescente.
bft :: Ord v => Graph v -> Set v -> Forest v
bft g vs = fst $ execState aux (Graph {nodes = vs, edges = Set.empty},elems vs)
    where aux = do (forest,queue) <- get
                   if Prelude.null queue
                   then return ()
                   else do let c = head queue
                           let es = Set.filter (\e -> target e `notMember` (nodes forest)) (adj g c)
                           let new = Graph {nodes = Set.map target es, edges = Set.map swap es}
                           put (forest `Graph.union` new, tail queue ++ elems (Set.map target es))
                           aux

-- | A função 'reachable' determina os vértices acessíveis a partir de um determinado vértice.
reachable :: Ord v => Graph v -> v -> Set v
reachable g v = nodes (bft g (singleton v))

-- | Um caminho é representado por uma sequência de arestas.
type Path v = [Edge v]

-- | A função 'isPathOf' testa se um caminho é válido para um dado grafo.
isPathOf :: Ord v => Path v -> Graph v -> Bool
isPathOf [] _ = True
isPathOf [e] g = e `member` edges g
isPathOf (e1:e2:es) g = e1 `member` edges g && target e1 == source e2 && isPathOf (e2:es) g                   
                
-- | A função 'path' calcula o caminho mais curto entre dois vértices.
-- Devolve 'Nothing' se não existir caminho.
path :: Ord v => Graph v -> v -> v -> Maybe (Path v)
path g o d = aux (bft g (singleton o)) o d
    where aux f o d | o == d = return []
                    | otherwise = do guard (not $ Prelude.null $ adj f d)
                                     let [Edge _ p] = elems $ adj f d
                                     r <- aux f o p
                                     return $ r ++ [Edge p d]

-- | A função 'topo' faz a ordenação topológica de um DAG.
topo :: Ord v => DAG v -> [Set v]
topo g | Set.null (nodes g) = []
       | otherwise = let s = Set.filter (\v -> Set.null (adj (transpose g) v)) (nodes g)
                         g' = Graph {nodes = nodes g `difference` s,
                                     edges = Set.filter (\e -> source e `notMember` s) (edges g)
                                    }
                      in s : topo g'