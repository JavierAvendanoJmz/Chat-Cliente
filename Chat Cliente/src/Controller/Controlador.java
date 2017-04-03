/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author j4v13
 */
public class Controlador extends Thread {
    
    Socket cliente;
    OutputStream output;
    ObjectOutputStream writer;
    InputStream input;
    ObjectInputStream reader;
    JTextArea txtChat;

    public Controlador(JTextArea txtChat) throws IOException {    
        this.txtChat = txtChat;
    }    
    
    public void enviarMensaje(String s) throws IOException {
        writer.writeObject(s);
    }

    @Override
    public void run() {        
        try {   
            cliente = new Socket("127.0.0.1",3000);
            output = cliente.getOutputStream();
            writer = new ObjectOutputStream(output);  
            input = cliente.getInputStream();
            reader = new ObjectInputStream(input);
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(true) {
            String s;
            try {
                s = (String)reader.readObject();
                txtChat.append(s+"\n");
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
    }
    
}
