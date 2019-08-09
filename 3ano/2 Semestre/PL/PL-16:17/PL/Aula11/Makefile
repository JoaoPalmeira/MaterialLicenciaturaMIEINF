c = calc
c2 = calc_v2


all:
	flex $c.flex
	yacc $c.y
	gcc -o RESULT y.tab.c -lm

v2:
	flex $c.flex
	yacc $(c2).y
	gcc -o RESULT y.tab.c -lm
