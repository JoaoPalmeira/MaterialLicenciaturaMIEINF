data Frac = F Integer Integer

--mdc :: Integer -> Integer -> Integer

--sigma :: Num a -> a -> a

--normaliza :: Frac -> Frac
--normaliza (F x 0) = error "invalido"
--normaliza (F 0 y) = (F 0 1)
--normaliza (F x y) = let d = mdc ( abs x) (abs y)
--						s = (sigm x) * (sigm y)
--					in F (s*( dic (abs x) d) (div (abs y) d))


instance  Eq Frac where
	(F a b) == (F c d) = a*d == c*d

--instance Ord Frac where
--	f1 <= f2 = let (F a b) = normaliza f1
--				   (F c d) = normaliza f2
--				in a * d <= c * d

instance Show Frac where
	show (F a b) = "(" ++ (show a) ++ "/" ++ (show b) ++")"


instance Num Frac where
-- (+) :: Frac -> Frac -> Frac
 (F a b) + (F c d) = (F (a*d + c*b) (b*d))
-- (*) :: Frac -> Frac -> Frac
 (F a b) * (F c d) = (F (a*c) (b*d))
abs (F a b) = (F (abs a) (abs b))
signum (F a b) = F ((sigm a) * (sigm b) 1) 
negate (F a b) = (F (-a) b)
fromInteger n = F n 1

data Exp a = Const a
           | Simetrico Exp a
		   | Mais (Exp a) (Exp a)
		   | Menos (Exp a) (Exp a)
		   | Mult (Exp a) (Exp a)


instance Num a => Eq (Exp a) where
	e1 == e2 = (calc e1) == (calc e2)

calc :: Num a => Exp a -> a


instance Num a -> Num (Exp a) Data where
	-- (+) :: Exp a -> Exp a -> Exp a	
	e1 + e2 = Mais e1 e2
	(*) = Mult
	(-) = Menos
	abs e = if sigm (calc e) == -1
			then Simetrico e
			else e
	signum e == Const (signum (calc e))
	--fromInteger:: Integer -> Exp a
	fromInteger n = Const (fromInteger n)