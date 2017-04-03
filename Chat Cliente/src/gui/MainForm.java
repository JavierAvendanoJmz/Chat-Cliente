/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Controller.Controlador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author j4v13
 */
public class MainForm extends JFrame {
    
    Controlador controlador;
    JTextField txtUsuario;
    JTextArea txtChat;
    JTextField txtMensaje;
    JButton btnEnviar;

    public MainForm() {        
        super("Mensajes");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        super.setSize(480, 640);        
        
        super.add(pnlUsuario(), BorderLayout.NORTH);
        super.add(pnlCentro(), BorderLayout.CENTER);
        super.add(pnlMensaje(), BorderLayout.SOUTH);
        try {
            controlador = new Controlador(txtChat);
        } catch (IOException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        controlador.start();
        super.setVisible(true);
    }        
    
    private JPanel pnlUsuario() {
        JPanel pnlUsuario = new JPanel();
        pnlUsuario.setLayout(new FlowLayout());
        pnlUsuario.setBorder(BorderFactory.createEmptyBorder(15,15,0,15));
        txtUsuario = new JTextField(20);
        txtUsuario.setText("Anonimo");
        pnlUsuario.add(new JLabel("Nombre de usuario:"));
        pnlUsuario.add(txtUsuario);
        return pnlUsuario;
    }
    
    private JPanel pnlCentro() {
        JPanel pnlCentro = new JPanel();
        pnlCentro.setLayout(new BoxLayout(pnlCentro, BoxLayout.PAGE_AXIS));
        pnlCentro.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        UIManager.put("TextField.inactiveBackground",Color.WHITE);
        txtChat = new JTextArea();
        txtChat.setEditable(false);
        pnlCentro.add(txtChat);        
        return pnlCentro;
    }
    
    private JPanel pnlMensaje() {
        JPanel pnlMensaje = new JPanel();
        pnlMensaje.setLayout(new FlowLayout());
        pnlMensaje.setBorder(BorderFactory.createEmptyBorder(0,15,15,15));
        txtMensaje = new JTextField(25);
        btnEnviar = new JButton("Enviar");
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    String mensaje = new String();
                    java.util.Date fecha = new Date();
                    mensaje += fecha;
                    mensaje += " \""+txtUsuario.getText()+"\" ";
                    mensaje += ": "+txtMensaje.getText();
                    controlador.enviarMensaje(mensaje);
                } catch (IOException ex) {
                    Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        pnlMensaje.add(txtMensaje);
        pnlMensaje.add(btnEnviar);
        return pnlMensaje;
    }
}
