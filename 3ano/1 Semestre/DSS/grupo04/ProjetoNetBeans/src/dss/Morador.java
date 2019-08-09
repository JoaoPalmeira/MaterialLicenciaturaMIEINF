/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dss;

import java.util.ArrayList;

/**
 *
 * @author sofia
 */
public class Morador {
    
    //Variáveis de instância
    
    private String _email;
    private String _password;
    private int _nIF;
    private final ArrayList<Despesa> _despesas = new ArrayList<>();
    private final ArrayList<Pagamento> _pagamentos = new ArrayList<>();
}
