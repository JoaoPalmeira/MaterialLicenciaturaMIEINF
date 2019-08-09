/*
 * Main
 * ruicouto in 14/dez/2015
 */
package main;

import main.business.SGT;
import main.presentation.MainWindow;

/**
 * Classe principal que inicializa dados (SGT) e abre janela principal
 * @author ruicouto
 */
public class Main {
    public static void main(String[] args) {
        SGT sgt = new SGT();
        
        MainWindow mw = new MainWindow(sgt);
        
        mw.setVisible(true);
    }
}
