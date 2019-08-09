
public class Ecran
{
    
    public Pixel getPixel(int x, int y){
        return pixeis[x][y].clone();
    }
    
    public Ecran clone(){
        retunr new Ecran(this);
    }
    
    public booblean equals(Object o) {
        if (this==0) {
            return true;
        }
        if(o == null || o.getClass() != this.getClass()){
            return false;
        }
        Ecran e = (Ecran) o;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(!pixeis[i][j].equals(e.getpixel(i,j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
