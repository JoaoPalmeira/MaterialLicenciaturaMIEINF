#!/usr/bin/gawk  -f

BEGIN {FS = ":";
        utf8 = "<meta charset=\"utf-8\">";
}
/^%inv/ { relacao = gensub(/\s*(\w+)\s*(<?)\w*/,"\\1","g",$2);
          inverso = gensub(/\s*(\w+)\s*(<?)\w*/,"\\1","g",$3);
          inv[relacao]=inverso;
         }

/^%dom/ {
          num= 1;
          printf ("------  Dominio: %s  ------\n\n",$2)}

# TODO: @comprimento aparece com IOF @ ??
/^%THE/ {printf ("[Lista de Relacões %d: ",num);
              for (i=2; i <= NF; i++){
                relacao = gensub(/\s*(\w+)\s*<?\w*/,"\\1","g",$i);
                relacoes[i] = relacao;
                iof[relacoes[i]] = gensub(/\s*\w+\s*<?(\w*)/,"\\1","g",$i);
                printf("%s",relacao);
                if(i!=NF) printf(",");
                else print ".]";
              }
              print "\n";

              print "IOF's:"
              for(i = 2; i <= NF; i++){
	              if(length(iof[relacoes[i]]) > 0)
	              	printf("\t(%s,iof,%s)\n",relacoes[i],iof[relacoes[i]]);
	          }
              printf("\n");
              print "Triplos:"
              num++;
        }
/^[^%#].*/ {
				
          		for(i = 2; i <= NF; i++){
            		if(length($i) > 0){
	                    relacao = relacoes[i];
	                    split($i,opcoes,"|",seps);
	                    for(j=1; j<=length(opcoes);j++){
	            				    printf("\t(%s,%s,%s)\n",$1,relacao,opcoes[j]);
	                        if(length(inv[relacao])!=0){
	                          printf("\t(%s,%s,%s)\n",opcoes[j],inv[relacoes[i]],$1);
	                        }
                    	}
            		}
          		}
            }
