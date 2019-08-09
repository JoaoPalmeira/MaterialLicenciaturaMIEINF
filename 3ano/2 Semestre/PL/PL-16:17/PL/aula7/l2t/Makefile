V=lyr2tex
$V: $V.fl
	flex $V.fl
	cc lex.yy.c -o $V


test: $V
	cat musica/* | ./lyr2abc
	-abcm2ps -n out.abc
	ps2pdf Out.ps
