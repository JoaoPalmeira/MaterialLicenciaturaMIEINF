/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 * @author joaopalmeira
 */
public class Main_warmup {
    public static void main(String args[]) {
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		try {
			System.out.print("> ");
			while((line=input.readLine())!=null){
				System.out.println("Echo: " + line);
				System.out.print("> ");
			}
		} catch (IOException e) {
			System.err.println("Error reading line from System.in!");
			e.printStackTrace();
		}
	}
    
}