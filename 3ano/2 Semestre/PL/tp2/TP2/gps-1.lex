%{
  #include <vector>
  #define HEADER "<kml xmlns=\"http://www.opengis.net/kml/2.2\"> <Document><name>Caminho</name><description>Descricao de Teste</description>\n\t<Style id=\"LinhaAmarela\">\n\t\t<LineStyle>\n\t\t\t<color>7f00ffff</color>\n\t\t\t<width>20</width>\n\t\t</LineStyle>\n\t</Style>\n"
  #define SETTINGS "\t<Placemark>\n\t<styleUrl>#LinhaAmarela</styleUrl>\n\t<LineString>\n\t<extrude>1</extrude>\n\t<tesselate>1</tesselate>\n\t<altitudeMode>clampToGround</altitudeMode>\n\t<coordinates>\n"
  #define MARKER "<Placemark>\n\t<Point>\n\t\t<coordinates>%lf,%lf</coordinates>\n\t</Point>\n</Placemark>\n"
  using namespace std;
  vector<double> lons, lats;
%}
%option noyywrap outfile="gps.cc"
%s IGNORE PATH
trkBegin  .*\<trk\>.*
trkEnd  .*\<\/trk\>.*


%%
  double lat;
  double lon;
  int pointNr = 0;
  
\<gpx.* {printf(HEADER); BEGIN IGNORE;}
<IGNORE>{trkBegin} {printf(SETTINGS); BEGIN PATH; }
<IGNORE>.|\n {}
<PATH>.|\n {}
<PATH>{trkEnd}  {printf("</coordinates>\n</LineString>\n</Placemark>");}
<PATH>\<trkpt.*  {
                  sscanf(yytext,"<trkpt lat=\"%lf\" lon=\"%lf\">",&lat, &lon); 
                  printf("%lf, %lf,0\n",lon,lat); 
                  if(pointNr == 0 || pointNr % 15 == 0){
                    lons.push_back(lon); 
                    lats.push_back(lat); 
                  }
                  pointNr++;
                }

<<EOF>> {
          if(pointNr % 15 != 0){
            lons.push_back(lon);
            lats.push_back(lat); 
          }
          return 0;
        }
%%
int main(int argc, const char* argv[]) {
  vector<double>::iterator itLons, itLats;
  int position;
  FILE *fw;
  if(argc>1){
    yyin=fopen(argv[1],"r");
  }
  if(argc>2){
    fw =fopen(argv[2],"w"); 
    dup2(fileno(fw),1);
  }

  
  
  yylex();
  for(position = 0, itLons = lons.begin(),itLats = lats.begin();itLats != lats.end() && itLons != lons.end();itLons++,itLats++,position++){
	 printf(MARKER,lons.at(position),lats.at(position));
  }
  printf("</Document></kml>");
  return 0;
}
