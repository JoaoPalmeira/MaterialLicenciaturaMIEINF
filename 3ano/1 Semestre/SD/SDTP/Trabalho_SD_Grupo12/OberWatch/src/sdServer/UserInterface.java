/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdServer;

import java.io.*;
import java.util.*;
public interface UserInterface {
    public void menuInicial(PrintWriter out, BufferedReader in);
    public void menuConta(PrintWriter out, BufferedReader in);
    public void menuHeroi(PrintWriter out, BufferedReader in);
    public void menuJogo(PrintWriter out, BufferedReader in,  ArrayList<String> blue, ArrayList<String> orange);
    public void menuJogoHeroi(PrintWriter out, BufferedReader in, HashMap<String,Integer> blue, HashMap<String,Integer> orange );
    
    
}
