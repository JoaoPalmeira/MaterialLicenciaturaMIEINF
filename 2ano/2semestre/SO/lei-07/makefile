all: sobucli.c sobusvr.c
	make servidor
	make cliente
	make clear

servidor: sobucli.c
	gcc sobucli.c -o sobucli

cliente: sobusvr.c
	gcc sobusvr.c -o sobusvr


remove:
	rm -f sobusvr
	rm -f sobucli
