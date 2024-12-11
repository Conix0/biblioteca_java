package com.biblioteca.VisualG;

import javax.swing.SwingUtilities;

public class Main {
       public static void main(String[] args) {
    
    SwingUtilities.invokeLater(() -> {
        visual ventana = new visual();
        ventana.setVisible(true);
    });
 }

}  


