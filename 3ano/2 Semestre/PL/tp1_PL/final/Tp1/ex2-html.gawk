#!/usr/bin/gawk  -f

BEGIN {
        FS = ":";
        utf8 = "<meta charset=\"utf-8\">";
        corIOF = "red";
}

/^%inv/ { relacao = gensub(/\s*(\w+)\s*/,"\\1","g",$2);
          inverso = gensub(/\s*(\w+)\s*/,"\\1","g",$3);
          inv[relacao]=inverso;
         }

/^%dom/ {
          #Serve para imprimir últimos triplos, para quando se passam vários ficheiros ao mesmo tempo, igual ao END
          if(num > 1){
            endOfDomain();
          }

          
          num= 1;
          htmlFile = removeSurroundingSpaces($2".html");
          cssFile = removeSurroundingSpaces($2".css");
          print "<!DOCTYPE html>\n<html>" > htmlFile;
          print "\t<head>\n\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\""cssFile"\"media=\"screen\" />\n\t</head>" >> htmlFile;
          print "\t<body>" >> htmlFile;
          printf ("\t\t------  Dominio: %s  ------<br><br>\n",$2) >> htmlFile ;
          print "" > cssFile;
          }

# TODO: @comprimento aparece com IOF @ ??
/^%THE/ {
              if(num > 1){
                triplosText = triplosText"\t\t</ul>" 
                print triplosText >> htmlFile;
              }
              headerText = "\t\t[Lista de Relacões "num":";
              for (i=2; i <= NF; i++){
                relacao = gensub(/\s*(\w+)\s*<?\w*/,"\\1","g",$i);
                relacoes[i] = relacao;
                iof[relacoes[i]] = gensub(/\s*\w+\s*<?(\w*)/,"\\1","g",$i);
                if(length(iof[relacoes[i]]) > 0){
                  print "li." relacao " span:hover:after{\n\tcontent:'->IOF->" iof[relacoes[i]] "';\n\tcolor:"corIOF";\n}\n" >> cssFile; 
                  print "li." relacao " span:hover{\n\tcolor:"corIOF";\n}\n" >> cssFile;
                }

                headerText = headerText relacao;
                if(i!=NF) headerText = headerText",";
                else headerText = headerText".]";
              }
              headerText=headerText"<br><br><br>\n";

              headerText=headerText"\n\t\tIOF's:\n";
              headerText = headerText"\t\t<ul style=\"list-style-type:none\">\n"
              for(i = 2; i <= NF; i++){
	              if(length(iof[relacoes[i]]) > 0)
	              	headerText = headerText "\t\t\t<li>("relacoes[i]",iof,"iof[relacoes[i]]")</li>\n";
	            }
              headerText = headerText"\t\t</ul>\n"
              headerText = headerText"\n\t\t<br>Triplos:"
              print headerText >> htmlFile;
              triplosText = "\t\t<ul style=\"list-style-type:none\">\n"
              num++;
        }
/^[^%#].*/ {
          		for(i = 2; i <= NF; i++){
            		if(length($i) > 0){
	                    relacao = relacoes[i];
	                    split($i,opcoes,"|",seps);
	                    for(j=1; j<=length(opcoes);j++){
	            				    triplosText = triplosText"\t\t\t<li class=\""relacao"\">("$1",<span>"relacao"</span>,"opcoes[j]")</li>\n";
	                        if(length(inv[relacao])!=0){
                            triplosText = triplosText"\t\t\t<ul style=\"list-style-type:none\">\n";
	                          triplosText = triplosText"\t\t\t\t<li>Inversa: ("opcoes[j]","inv[relacoes[i]]","$1")</li>\n";
                            triplosText = triplosText"\t\t\t</ul>\n\n";
	                        }
                    	}
            		}
          		}
            }
END{
  triplosText = triplosText"\t\t</ul>";
  print triplosText >> htmlFile;
  print "\t</body>" >> htmlFile;
  print "</html>" >> htmlFile;
}

function endOfDomain(){
  triplosText = triplosText"\t\t</ul>";
  print triplosText >> htmlFile;
  print "\t</body>" >> htmlFile;
  print "</html>" >> htmlFile;
}

function removeSurroundingSpaces(line, ret){
  ret = gensub(/\s*(.+)\s*/,"\\1","g",line);
  return ret;
}