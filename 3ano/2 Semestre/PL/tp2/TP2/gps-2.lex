%{
  #include <vector>
  #include <math.h>
  #define HEADER "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n<Document>\n"
  #define COORD "\t\t\t<gx:coord>%lf %lf %lf</gx:coord>\n"
  #define MARKER "\t<Placemark>\n\t\t<name>%s</name>\n\t\t<description><![CDATA[Latitude: %lf<br>Longitude:%lf]]></description>\n\t\t<styleUrl>%s</styleUrl>\n\t\t<Point>\n\t\t\t<coordinates>%lf,%lf</coordinates>\n\t\t</Point>\n\t</Placemark>\n"
  #define WHEN  "\t\t\t<when>%s</when>\n"
  #define EARTH_RADIUS 6371
  using namespace std;
  vector<double> lons, lats;
  vector<char *> times;

  const char * trackName;
%}
%option noyywrap outfile="gps.cc"
%s IGNORE PATH
trkBegin \<trk\>.*
trkEnd \<\/trk\>.*
trkPtTime <time\>.*
trkPtBegin \<trkpt.*
trkPtEnd \<\/trkpt\>.*
trkPtSpeed \<speed\>.*
trkPtEle \<ele\>.*

%%
  double lat,lon,prevLat = 1000,prevLon = 1000,dlon,dlat;
  int pointNr = 0,noTime=1,speedValuesCount = 0;
  char time[30]; 
  float speedsSum = 0, speed, maxAltitude = -20000, minAltitude = 10000,altitude,distance = 0,a;
  void printStyles();
  
\<gpx.* {
          printf(HEADER); 
          printStyles();
          BEGIN IGNORE;}
<IGNORE>{trkBegin} {
                    printf("\t<Placemark>\n\t\t<name>%s</name>\n\t\t<styleUrl>#line</styleUrl>\n\t\t<gx:Track>\n",trackName);
                    BEGIN PATH; 
                    }
<IGNORE>.|\n {}
<PATH>.|\n {}
<PATH>{trkPtBegin}  {
                  //https://andrew.hedges.name/experiments/haversine/ <- para calcular distância entre dois pontos
                  sscanf(yytext,"<trkpt lat=\"%lf\" lon=\"%lf\">",&lat, &lon);  
                  lons.push_back(lon); 
                  lats.push_back(lat);
                  pointNr++;

                  //Conversão para radianos
                  lat = lat * M_PI / 180;
                  lon = lon * M_PI / 180;
                  if(prevLon != 1000 && prevLat != 1000){
                    dlon = lon - prevLon;
                    dlat = lat - prevLat;
                    a = pow(sin(dlat/2),2) + cos(lat) * cos(prevLat) * pow(sin(dlon/2),2);
                    distance += EARTH_RADIUS * 2 * atan2( sqrt(a), sqrt(1-a) ) ;
                  }

                  prevLon = lon;
                  prevLat = lat;
                }
<PATH>{trkPtTime} {
                  char * timeToAdd;
                  sscanf(yytext,"<time>%[^<]</time>",time);
                  printf(WHEN,time);
                  timeToAdd = strdup(time);
                  times.push_back(timeToAdd);
                  noTime = 0;
                  }
<PATH>{trkPtSpeed} {
                sscanf(yytext,"<speed>%f</speed>",&speed);
                speedsSum += speed;
                speedValuesCount++;
              }
<PATH>{trkPtEle} {
                sscanf(yytext,"<ele>%f</ele>",&altitude);
                if(altitude > maxAltitude)
                  maxAltitude = altitude;
                if(altitude < minAltitude)
                  minAltitude = altitude;
                }
<PATH>{trkPtEnd}  {
                  if(noTime)
                    fprintf( stderr, "One or more time elements missing"); 
                  else
                    noTime = 1;
                  }
<PATH>{trkEnd}  {
                  for(int i = 0; i < lats.size();i++){
                    lon = lons.at(i);
                    lat = lats.at(i);
                    printf(COORD,lon,lat,0.0);
                  }
                  printf("\t\t</gx:Track>\n");
                  printf("\t\t<description><![CDATA[");


                  printf("Distância(aprox): %f km<br>",distance);

                  if(times.size() > 0){
                    printf("Início: %s<br>",times.at(0));
                    printf("Fim: %s<br>",times.at(times.size()-1));
                  }

                  if(speedValuesCount > 0)
                    printf("Velocidade média: %.4f km/h<br>",speedsSum/(float)speedValuesCount);
                  else
                    printf("Velocidade média: N/A<br>");

                  if(maxAltitude != -20000 && minAltitude != 10000){
                    printf("Altitude máxima: %f m<br>",maxAltitude);
                    printf("Altitude mínima: %f m<br>",minAltitude);
                  }
                  else{
                    printf("Altitude máxima: N/A<br>");
                    printf("Altitude mínima: N/A<br>");
                  }



                  printf("]]></description>\n");
                  printf("\t</Placemark>\n");

                  if(lons.size() > 0){
                    lon = lons.at(0);
                    lat = lats.at(0);
                    printf(MARKER,"Início",lat,lon,"#icon-green",lon,lat);

                    lon = lons.at(lons.size()-1);
                    lat = lats.at(lats.size()-1);
                    printf(MARKER,"Fim",lat,lon,"#icon-red",lon,lat);
                  }
                  printf("</Document>\n</kml>\n");
                }
%%
void printStyles(){
  //Ícone verde normal
  /* <Style id=\"icon-green-normal\">
      <IconStyle>
        <Icon>
          <href>http://www.gstatic.com/mapspro/images/stock/61-green-dot.png</href>
        </Icon>
      </IconStyle>
     </Style>   */
  printf("<Style id=\"icon-green-normal\">\n\t<IconStyle>\n\t\t<Icon>\n\t\t\t<href>http://www.gstatic.com/mapspro/images/stock/61-green-dot.png</href>\n\t\t</Icon>\n\t</IconStyle>\n</Style>\n");


  //Ícone verde selecionado
  /*<Style id="icon-green-highlight">
    <IconStyle>
      <Icon>
        <href>http://www.gstatic.com/mapspro/images/stock/61-green-dot.png</href>
      </Icon>
    </IconStyle>
    <LabelStyle>
        \t\t<scale>1.1</scale>
    </LabelStyle>
  </Style> */
  printf("<Style id=\"icon-green-highlight\">\n\t<IconStyle>\n\t\t<Icon>\n\t\t\t<href>http://www.gstatic.com/mapspro/images/stock/61-green-dot.png</href>\n\t\t</Icon>\n\t</IconStyle>\n\t<LabelStyle>\n\t\t<scale>1.1</scale>\n\t</LabelStyle>\n</Style>\n");


//Ícone verde
  /*<StyleMap id="icon-green">
      <Pair>
        <key>normal</key>
        <styleUrl>#icon-green-normal</styleUrl>
      </Pair>
      <Pair>
        <key>highlight</key>
        <styleUrl>#icon-green-highlight</styleUrl>
      </Pair>
    </StyleMap>*/
  printf("<StyleMap id=\"icon-green\">\n\t<Pair>\n\t\t<key>normal</key>\n\t\t<styleUrl>#icon-green-normal</styleUrl>\n\t</Pair>\n\t<Pair>\n\t\t<key>highlight</key>\n\t\t<styleUrl>#icon-green-highlight</styleUrl>\n\t</Pair>\n</StyleMap>\n");




    //Ícone vermelho normal
  /* <Style id=\"icon-red-normal\">
      <IconStyle>
        <Icon>
          <href>http://www.gstatic.com/mapspro/images/stock/123-red-dot.png</href>
        </Icon>
      </IconStyle>
     </Style>   */
  printf("<Style id=\"icon-red-normal\">\n\t<IconStyle>\n\t\t<Icon>\n\t\t\t<href>http://www.gstatic.com/mapspro/images/stock/123-red-dot.png</href>\n\t\t</Icon>\n\t</IconStyle>\n</Style>\n");


  //Ícone vermelho selecionado
  /*<Style id="icon-red-highlight">
    <IconStyle>
      <Icon>
        <href>http://www.gstatic.com/mapspro/images/stock/123-red-dot.png</href>
      </Icon>
    </IconStyle>
    <LabelStyle>
      <scale>1.1</scale>
    </LabelStyle>
  </Style> */
  printf("<Style id=\"icon-red-highlight\">\n\t<IconStyle>\n\t\t<Icon>\n\t\t\t<href>http://www.gstatic.com/mapspro/images/stock/123-red-dot.png</href>\n\t\t</Icon>\n\t</IconStyle>\n\t<LabelStyle>\n\t\t<scale>1.1</scale>\n\t</LabelStyle>\n</Style>\n");


//Ícone vermelho
  /*<StyleMap id="icon-red">
      <Pair>
        <key>normal</key>
        <styleUrl>#icon-red-normal</styleUrl>
      </Pair>
      <Pair>
        <key>highlight</key>
        <styleUrl>#icon-red-highlight</styleUrl>
      </Pair>
    </StyleMap>*/

  printf("<StyleMap id=\"icon-red\">\n\t<Pair>\n\t\t<key>normal</key>\n\t\t<styleUrl>#icon-red-normal</styleUrl>\n\t</Pair>\n\t<Pair>\n\t\t<key>highlight</key>\n\t\t<styleUrl>#icon-red-highlight</styleUrl>\n\t</Pair>\n</StyleMap>\n");


//Linha do trajeto
  /*<Style id="line">\n
      \t<LineStyle>\n
        \t\t<color>ffd18802</color>\n
        \t\t<width>5</width>\n
      \t</LineStyle>\n
  </Style>\n
  */
  printf("<Style id=\"line\">\n\t<LineStyle>\n\t\t<color>ffd18802</color>\n\t\t<width>5</width>\n\t</LineStyle>\n</Style>\n");

}

int main(int argc, const char* argv[]) {
  vector<double>::iterator itLons, itLats;
  int position;
  FILE *fr, *fw;
  if(argc>1){
    yyin=fopen(argv[1],"r");
    trackName = strndup(argv[1],strlen(argv[1])-4);
  }
  else
    trackName = "Trajeto";
  if(argc>2){
    fw =fopen(argv[2],"w"); 
    dup2(fileno(fw),1);
  }

  
  yylex();

  return 0;
}
