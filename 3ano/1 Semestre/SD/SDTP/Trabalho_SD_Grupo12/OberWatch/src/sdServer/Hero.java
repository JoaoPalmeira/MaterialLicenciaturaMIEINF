package sdServer;

import java.io.*;
import java.net.*;
import java.util.*;

public class Hero {

    private int cod;
    // private String nome;
    // private ReentrantLock lock;

    public Hero(int cod/*, String nome*/) {
        this.cod = cod;
    }

    public int getCod() {
        return cod;
    }
}
