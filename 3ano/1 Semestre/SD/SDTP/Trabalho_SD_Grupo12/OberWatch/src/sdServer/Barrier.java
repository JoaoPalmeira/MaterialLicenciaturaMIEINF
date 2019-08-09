package sdServer;

import java.util.*;
import java.net.*;
import java.io.*;

public class Barrier {

    int emEspera = 0;
    int tamanhoGrupo;

    public Barrier(int tam) {
        tamanhoGrupo = tam;
    }

    public synchronized void barrier() throws InterruptedException {
        emEspera++;
        if (emEspera < tamanhoGrupo) {
            wait();
        } else {
            emEspera = 0;
            notifyAll();
        }
    }
}
