lisp: lisp.y lisp.fl aux.c aux.h
	yacc lisp.y
	flex lisp.fl
	cc y.tab.c aux.c -o lisp
