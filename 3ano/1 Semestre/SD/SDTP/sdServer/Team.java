package sdServer;


import java.io.*;
import java.net.*;
import java.util.*;

public class Team{
	private HashMap<String, Integer> comp = new HashMap<String,Integer>();
	private int[] arr = new int[30];
	private ArrayList<String> eq;
	public Team(Collection<String> players){
		eq = new ArrayList<String>(players);
		for(int i = 0; i<30; i++) arr[i] = 0;
	}

	public synchronized int select(String u, int s){
		if(int[s] == 0){
			int[s] = 1;
			comp.put(u,s);
			return 1; // Heroi selecionado com sucesso
			
		}
		else return 0; //Heroi ja selecionado por outro jogador da equipa
	}
	
}