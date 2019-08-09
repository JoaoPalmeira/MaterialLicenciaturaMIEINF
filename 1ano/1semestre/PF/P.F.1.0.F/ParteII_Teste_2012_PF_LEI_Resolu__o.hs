module TESTE where

import System.Random

-- PARTE II
data Cmd = RD | RE | AV
	 deriving (Eq,Show)



--1 

type Ponto = (Int,Int)
data Orientacao = N | S | E | W
	 deriving Show 	
type Pos = (Ponto,Orientacao)


next1 :: Pos -> Cmd -> Pos
next1 ((x,y),N)  RD = ((x,y),E)
next1 ((x,y),N)  RE = ((x,y),W) 
next1 ((x,y),N) AV = ((x,y+1),N)
next1 ((x,y),S)  RD = ((x,y),W)
next1 ((x,y),S)  RE = ((x,y),E) 
next1 ((x,y),S) AV = ((x,y-1),S)
next1 ((x,y),E)  RD = ((x,y),S)
next1 ((x,y),E)  RE = ((x,y),N) 
next1 ((x,y),E) AV = ((x+1,y),E)
next1 ((x,y),W)  RD = ((x,y),N)
next1 ((x,y),W)  RE = ((x,y),S) 
next1 ((x,y),W) AV = ((x-1,y),W)


-- 2
percurso :: Pos -> [Cmd] -> [Pos]
percurso p c = percursoaux [p] c

percursoaux :: [Pos] -> [Cmd] -> [Pos]
percursoaux p (h:t) = let n = (next1 (last p) h) in percursoaux (p++[n]) t
percursoaux p [] = p

-- 3
haCol :: [Ponto] -> [Ponto] -> Bool
haCol [h] l = elem h l
haCol l [h] = elem h l
haCol (h:t) (x:xs) = h == x || haCol t xs
haCol _ _ = False

retiraOrienta :: [Pos] -> [Ponto]
retiraOrienta [] = []
retiraOrienta (h:t) = (fst h):retiraOrienta t

verificaColisao :: Pos -> Pos -> [Cmd] -> [Cmd] -> Bool
verificaColisao p q c d = let pp = percurso p c ;
							  qp = percurso q d ;
							  sp = retiraOrienta pp;
							  sq = retiraOrienta qp
							  in haCol sp sq
							  
							  

--4

criaObst :: Int -> [(Int,Int)] -> IO[(Int,Int)]
criaObst n l = do { if n == 0 then return l
                    else do { x<- randomRIO (0,9);
                              y<- randomRIO (0,9);
                              if elem (x,y) l then do criaObst n l
                              else do criaObst (n-1) ((x,y):l)   }
                  }


jogo :: IO()
jogo = do{ obs <- criaObst 10 [];
            jogoaux [((0,0),N)] obs
          }

jogoaux :: [Pos] -> [(Int,Int)]-> IO()
jogoaux l o = let no = retiraOrienta l
                   in do
                 { putStrLn "Insira um comando";
                cmd <- getLine;
                if cmd == "AV" then do {let npa = next1 (head l) AV;
                                            pa@(xa,ya) = fst npa ;
                                            nla = [npa]++l   
		in do {if (elem pa no ) || (elem pa o) || (xa<0) || (xa>9) || (ya<0) || (ya >9)  
		       then do putStrLn "O jogo terminou"
		       else do {print npa ; (jogoaux nla o)} }}
		
		else if cmd == "RD" then do {let npd = next1 (head l) RD;
		                                 pd@(xd,yd) = fst npd ;
						 nld = [npd]++l   
		in do {if (elem pd o) || (xd<0) || (xd>9) || (yd<0) || (yd >9)  
		then do putStrLn "O jogo terminou"
		else do {print npd ; (jogoaux nld o)} }}
								                    
	        else  if cmd == "RE" then do {let npe = next1 (head l) RE;
                                                  pe@(xe,ye) = fst npe;
                                                  nle = [npe]++l   
	       in do {if (elem pe o) || (xe<0) || (xe>9) || (ye<0) || (ye >9)  
		      then do putStrLn "O jogo terminou"
		      else do {print npe ;(jogoaux nle o)} }}
				
                else do {putStrLn "Comando invalido"
	         	     ;jogoaux l o}
	         	     }
										                 
                
                     
