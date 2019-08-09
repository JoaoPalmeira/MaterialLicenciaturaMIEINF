import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

class Cliente {
	public String username;
	public String pass;
	public boolean pedidopendente;

	public Cliente(String username, String pass) {
		this.username = username;
		this.pass = pass;
		this.pedidopendente=false;
	}
}