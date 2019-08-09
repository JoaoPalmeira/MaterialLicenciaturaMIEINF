import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class QueryEngineImpl implements Interface {

public GrupoArtigo ParseDoc(String args) {
      long nartigos=0;
      long npalavras=0;
      long idartigo=0;
      String titulocopia=null;
      String timecopia=null;
      long idrevisaocopia=0;
      String usernamecopia=null;
      long idccopia=0;
      boolean title = false;
      boolean idtitle = false;
      boolean timestamp = false;
      boolean page = false;
      boolean revision = false;
      boolean contributor = false;
      boolean username = false;
      boolean idcontributor = false;
      boolean idrevision = false;
      boolean text = false;
      try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            factory.setProperty(XMLInputFactory.IS_COALESCING, true);
            XMLEventReader eventReader =factory.createXMLEventReader(new FileReader(args));
            while(eventReader.hasNext()){
               XMLEvent event = eventReader.nextEvent();
               Revisao nova = new Revisao();
               Contribuidor contribuidor= new Contribuidor();
               switch(event.getEventType()){
                  case XMLStreamConstants.START_ELEMENT:
                     StartElement startElement = event.asStartElement();
                     String qName = startElement.getName().getLocalPart();
                    if (qName.equalsIgnoreCase("contributor")) {
                        contributor = true;
                        revision=false;
                    }else if(qName.equalsIgnoreCase("username")){
                            username=true;
                    }else if (qName.equalsIgnoreCase("page")) {
                        contributor=false;
                        revision=false;
                    } else if (qName.equalsIgnoreCase("title")) {
                        title = true;
                    }else if(qName.equalsIgnoreCase("id")&& !revision && !contributor){
                         idtitle=true;                
                     } else if (qName.equalsIgnoreCase("text")) {
                        text = true;
                         
                     } else if (qName.equalsIgnoreCase("revision")) {
                        revision = true;
                        
                     }else if(qName.equalsIgnoreCase("id")&& revision && !contributor){
                         idrevision=true;
                     } else if (qName.equalsIgnoreCase("timestamp")) {
                        timestamp = true;
                     
                     }else if(qName.equalsIgnoreCase("id")&& !revision && contributor){
                         
                         idcontributor=true;
                      }
                     break;
                  case XMLStreamConstants.CHARACTERS:
                     Characters characters = event.asCharacters();
                     if(title){
                        //System.out.println("Title: " 
                        //+ characters.getData());
                        titulocopia = characters.getData();
                        title = false;
                     }
                     if(idtitle){
                        //System.out.println("IdTitle: " 
                        //+ characters.getData());
                        nartigos++;
                        idartigo = Long.parseLong(characters.getData());
                        idtitle = false;
                     }
                     if(timestamp){
                        //System.out.println("Timestamp: " 
                        //+ characters.getData());
                        timecopia=characters.getData();
                        timestamp = false;
                     }
                      if(idrevision){
                        //System.out.println("IdRevision: " 
                        //+ characters.getData());
                        idrevisaocopia = Long.parseLong(characters.getData());
                        idrevision = false;
                     }
                      if(username){
                        //System.out.println("Username: " 
                        //+ characters.getData());
                        usernamecopia= characters.getData();
                        username = false;
                     }
                      if(idcontributor){
                        //System.out.println("IdContributor: " 
                        //+ characters.getData());
                        idccopia = Long.parseLong(characters.getData());
                        idcontributor = false;
                     }
                      if(text){
                        //  System.out.println("Npalavars: " 
                        //+ npalavras);
                          npalavras=contaPal(characters.getData());
                        text = false;
                     }
                    
                     break;
                  case  XMLStreamConstants.END_ELEMENT:
                     EndElement endElement = event.asEndElement();
                     if(endElement.getName().getLocalPart().equalsIgnoreCase("page")){
                        //System.out.println("----------------");
                        //System.out.println(idartigo);
                        nova.setTitulo(titulocopia);
                        nova.setTimestamp(timecopia);
                        nova.setIdrevisao(idrevisaocopia);
                        contribuidor.setNomecontribuidor(usernamecopia);
                        contribuidor.setIdcontribuidor(idccopia);
                        
                        nova.setNumpalavras(npalavras);
                        nova.setContribuidor(contribuidor);
                        this.grupo.addGrupotit(idartigo,nova);
                     }
                     break;
               
               }
            }
         } catch (FileNotFoundException e) {
            e.printStackTrace();
         } catch (XMLStreamException e) {
            e.printStackTrace();
      }
      
    this.grupo.addNumero(nartigos);
    return grupo;
    }

    public static int contaPal(String s){
    int res= 0;
    boolean word = false;
    int endOfLine = s.length() - 1;
    for (int i = 0; i < s.length(); i++) {
        if (!Character.isWhitespace(s.charAt(i)) && i != endOfLine) {
            word = true;
        } else if (Character.isWhitespace(s.charAt(i)) && word) {
            res++;
            word = false;
        } else if (!Character.isWhitespace(s.charAt(i)) && i == endOfLine) {
            res++;
        }
    }
    return res;
    } 

    public void init() {
         grupo=new GrupoArtigo();
    }

    public void load(int nsnaps, ArrayList<String> snaps_paths) {
        for(String s : snaps_paths){
        this.grupo= ParseDoc(s);
        }
        
    }
    
    public long all_articles() {
        
        return grupo.clone().getNartigos();
    }

    
    public long unique_articles() {
        //Integer i = this.grupo.getGrupotit().size();
        return grupo.clone().getg_artigo().size();
    }
    
    
    public long all_revisions() {
        return this.grupo.clone().getg_revisao().values().stream().count();

    }

    
    public ArrayList<Long> top_10_contributors() {
            ArrayList<Long> res = new ArrayList();
            List<Long> ax = new ArrayList();
            Map<Long,Integer> fim = new TreeMap();
            this.grupo.clone().getg_contribuidor().entrySet().forEach((j) -> {
                ax.add(j.getValue().getIdcontribuidor());
         });
            Set<Long> aux = new HashSet();
            for( int i =0; i<ax.size() ; i++){
                aux.add(ax.get(i));
            }
            Iterator<Long> it = aux.iterator();
            while (it.hasNext()) {
                Long g = it.next();
                fim.put(g,Collections.frequency(ax,g));
    }
            Integer anda=0;
            while(anda<10){
            long indice = -1, valor = Integer.MIN_VALUE;
                for (Map.Entry<Long, Integer> entry : fim.entrySet()) {
                    if (valor < entry.getValue() && !res.contains(entry.getKey())) {
                    indice = entry.getKey();
                    valor = entry.getValue();
                    }
                }
                res.add(indice);
                anda++;
            }
            
        return res;
    }
    
    public String contributor_name(long contributor_id){
        String res= null;
        for(Map.Entry<Long,Contribuidor> j : this.grupo.clone().getg_contribuidor().entrySet()){
                    if(j.getValue().getIdcontribuidor()==contributor_id){
                        res=j.getValue().clone().getNomecontribuidor();
                        break;
                    }
                }              
        return res;
    }
    
    public ArrayList<Long> top_20_largest_articles() {
            Comparator<Revisao> cmp = new Comparator<Revisao>(){
            public int compare(Revisao a, Revisao b){
                return (int) (b.getNbytes() - a.getNbytes());
            }
        };
            
            
        List<Revisao> tmp;     
        tmp = this.grupo.getg_revisao().values().stream()
              .sorted(cmp)
              .collect(Collectors.toList());
        
        ArrayList<Long> res=new ArrayList();
        for(Revisao c: tmp){
          res.add(c.getIdartigo());
        }
 
        ArrayList<Long> resas=new ArrayList();
        for(Long id: res){
            if(!resas.contains(id)){
            resas.add(id);
            }
        }
        
        ArrayList<Long> s =new ArrayList();
        for(int i=0; i<20;i++){
            s.add(resas.get(i));
        }
         
        return s;  
        
    }
    
    public String article_title(long article_id) {
        String result=null;
        for(Map.Entry<Long,Revisao> j : this.grupo.clone().getg_revisao().entrySet()){
                if(j.getValue().getIdartigo()==article_id){
                    result = j.getValue().clone().getTitulo();
                    break;
                    }
                }
        return result;
    }
    
    public ArrayList<Long> top_N_articles_with_more_words(int n){
             Comparator<Revisao> cmp = new Comparator<Revisao>(){
            public int compare(Revisao a, Revisao b){
                return (int) (b.getNumpalavras() - a.getNumpalavras());
            }
            };
        
            
            
            
        List<Revisao> tmp;     
        tmp = this.grupo.getg_revisao().values().stream()
              .sorted(cmp)
              .collect(Collectors.toList());
        
        ArrayList<Long> res=new ArrayList();
        for(Revisao c: tmp){
          res.add(c.getIdartigo());
        }
 
        ArrayList<Long> resas=new ArrayList();
        for(Long id: res){
            if(!resas.contains(id)){
            resas.add(id);
            }
        }
        
        ArrayList<Long> s =new ArrayList();
        for(int i=0; i<n;i++){
            s.add(resas.get(i));
        }
         
        return s;
       
    }
    
    
    public ArrayList<String> titles_with_prefix(String prefix) {
        ArrayList<String> res = new ArrayList();
        Map<Long,String> aux = new TreeMap();
        for (Map.Entry<Long,Revisao> j : this.grupo.clone().getg_revisao().entrySet()) {
                 if(j.getValue().getTitulo().startsWith(prefix)){
                     if(!res.contains(j.getValue().getTitulo())){
                         res.add(j.getValue().clone().getTitulo());
                     }
                 }
             } 
        Collections.sort(res);
        return res;
    }
    
    
    public String article_timestamp(long article_id, long revision_id) {
         String result=null;
        for(Map.Entry<Long,Revisao> j : this.grupo.clone().getg_revisao().entrySet()){
                if( (j.getKey()==revision_id) && (j.getValue().getIdartigo()==article_id) ){
                    result = j.getValue().clone().getTimestamp();
                    break;
                    }
                }
        return result;
    }
    
    public void clean() {
        this.grupo.getg_artigo().clear();
        this.grupo.getg_revisao().clear();
        this.grupo.getg_contribuidor().clear();
    } 
}
