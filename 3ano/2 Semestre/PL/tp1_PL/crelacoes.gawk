#!/usr/bin/gawk  -f

BEGIN {FS=",";}
      {if (length($2)>0) conta[$2]++;}
END   {for (p in conta) printf ("Relacao %s, Número de ocorrencias %d \n",p,conta[p]);}
