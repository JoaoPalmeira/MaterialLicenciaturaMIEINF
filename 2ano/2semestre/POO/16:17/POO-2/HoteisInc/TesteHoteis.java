
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TesteHoteis
{
    public static void main(String args[])
    {
        HoteisInc hi = new HoteisInc();
        Hotel h1 = new HotelStandard("h1","Ibis","Braga",3.4,true,50);
        Hotel h2 = new HotelStandard("h2","Ibis","Porto",4.2,true,60);
        try
        {
            hi.adiciona(h1);
            hi.adiciona(h2);
            hi.adiciona(h1);
        }
        catch(HotelExisteException e)
        {
            System.out.println("Hotel Existe: "+e.getMessage());
        }
    }
}
