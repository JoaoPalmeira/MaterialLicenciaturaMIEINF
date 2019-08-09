module Main where

import System.Random
import Data.List

--1
putChar :: Char -> IO ()
getChar :: IO Char

--a
putstr :: String -> IO ()
putstr [] = return ()
putstr (c:cs) = do putChar c
                   putstr cs
--b
putstrln :: String -> IO ()
putstrln [] = putChar '\n'
putstrln (c:cs) = do putChar c
                     putstrln cs
--c
getline :: IO String
getline = do c <- getChar
             if c == '\n' then return " "
             else do cs <- getline
                        return (c:cs)
--2
randomIO :: Random a => IO a
randomRIO :: Random a => (a,a) -> IO a

--a
craps :: IO Int 
craps = do x <- randomRIO (1,6)
           y <- randomRIO (1,6)
           return (x+y)
--b
randomlist :: Int -> IO [Int]
randomlist 0 = return []
randomlist n = do k <- randomIO
                  ks <- randomlist (n-1)
                  return (k:ks)
--c
randomPermitations :: [a] -> IO [a]
randomPermitations = do k <- randomRIO (0, (fac (length l))-1)
                        return ((permitations l)!! k)
--4
--a
sequence :: Monad m => [m a] -> m [a]
sequence [] = return []
sequence (x:xs) = do y <- x
                     ys <- sequence xs
                     return (y:ys)
--b
mapMon :: Monad m => (a -> m b) -> [a] -> m [b]
mapMon _ [] = return []
mapMon f (x:xs) = do y <- (f x)
                     ys <- mapMon f xs
                     return (y:ys)
--c
replication :: Monad m => Int -> m a -> m [a]
replication 0 _ = return []
replication m x = do k <- x
                     ks <- replication (m-1) x
                     return (k:ks)













