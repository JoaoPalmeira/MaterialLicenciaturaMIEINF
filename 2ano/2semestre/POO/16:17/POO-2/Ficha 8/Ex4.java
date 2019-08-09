import java.lang.String;
public class Ex4
{
    public static int main(String grande, String pequena)
    {
        int index = grande.indexOf(pequena);
        int conta = 0;
        while (index != -1)
        {
            conta++;
            grande = grande.substring(index + 1);
            index = grande.indexOf(pequena);
        }
        return conta;
    }
}
