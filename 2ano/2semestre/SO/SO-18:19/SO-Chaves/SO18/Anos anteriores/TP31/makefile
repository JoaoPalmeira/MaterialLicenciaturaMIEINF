notebook:	notebook.c
			gcc -o notebook notebook.c

execute:	notebook
			./notebook *.nb

install:	notebook
			sudo cp notebook /usr/local/bin

remove: 	notebook
			rm notebook errors.txt