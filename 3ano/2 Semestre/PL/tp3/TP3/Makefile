all: htmlAndDot result.dot
	dot -Tsvg result.dot -o grafo.svg

compile: cflex cyacc lex.yy.c
	gcc -o tp3 y.tab.c

cflex: tp3.lex
	flex tp3.lex

cyacc: tp3.y
	yacc tp3.y

htmlAndDot: compile dados.mdp
	./tp3 < dados.mdp > result.dot

