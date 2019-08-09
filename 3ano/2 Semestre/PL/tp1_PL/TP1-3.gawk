#!/usr/local/bin/gawk -f

BEGIN{
	FS = "[:|]";
	utf8 = "<meta charset>=\"utf-8\">";	
	print "Índice:<br />" > "index.html"
	referencia = "<li><a href='%s-associated.html'>%s</a></li>\n"
}

/^[^%#].*/ { 
		printf(referencia,$1,$1) >> "index.html";  
		for(i = 2; i <= NF; i++){
			if(length($i) > 0){
				printf("%s<br />\n",$i) >> $1"-associated.html";
			}
		}
}
