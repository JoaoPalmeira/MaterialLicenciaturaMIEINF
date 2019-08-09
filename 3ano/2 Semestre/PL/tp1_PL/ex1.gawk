#!/usr/bin/awk  -f

BEGIN {FS = ":"}
/^%dom/ {num= 1;printf ("Dominio: %s \n"),$2}
/^%THE/ {      printf ("\tLista de Relacões %d:",num);
              for (i=2; i <= NF; i++){
                b =gensub(/<.*/,"","g",$i);
                printf("%s",b);
                if(i==NF) printf(".");
                else printf(",");
              }
               printf("\n");
              num++;
       }
