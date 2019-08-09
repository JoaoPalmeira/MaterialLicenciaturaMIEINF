compiler.exe: y.tab.c lex.yy.c program.c variable.c function.c
	gcc -o compiler.exe y.tab.c lex.yy.c program.c variable.c function.c `pkg-config --cflags --libs glib-2.0` -ll

y.tab.c: compiler.y program.h variable.h function.h
	yacc -d compiler.y

lex.yy.c: compiler.l y.tab.h
	flex compiler.l

clean:
	rm -rf *.exe *.tab.c *.tab.h *.yy.c || true

.PHONY: clean
