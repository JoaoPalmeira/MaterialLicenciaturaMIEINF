V=dicionario
$V: $V.fl
	flex $V.fl
	cc -g `pkg-config --cflags glib-2.0` lex.yy.c -o $V `pkg-config --libs glib-2.0`
