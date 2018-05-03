package p3.mvc.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelGerente extends JPanel implements ActionListener {
    private JLabel etiqueta1 = new JLabel("GERENTE");
    private JLabel etiqueta2;
    private JLabel texto = new JLabel("Error al cerrar sesion");
    private GuiInmobiliaria gui;
    private JPanel select = new JPanel(new GridLayout(3, 1));
    private ButtonGroup grupo = new ButtonGroup();

    JButton volver = new JButton("Desconectarse");
    JButton desbloquear = new JButton("Desbloquear usuarios");
    JButton comprobar = new JButton("Comprobar ofertas");

    PanelGerente(GuiInmobiliaria gui){
        this.gui = gui;
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        etiqueta2=new JLabel("Bienvenido " + gui.getControlador().getSistema().getGerente().getNombre());
        grupo.add(volver);
        grupo.add(desbloquear);
        grupo.add(comprobar);
        select.add(volver);
        select.add(desbloquear);
        select.add(comprobar);
        select.setVisible(true);
        texto.setVisible(false);

        layout.putConstraint(SpringLayout.WEST, etiqueta1, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, etiqueta2, 5, SpringLayout.WEST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 20, SpringLayout.NORTH, etiqueta1);
        layout.putConstraint(SpringLayout.WEST, select, 5, SpringLayout.WEST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, select, 20, SpringLayout.NORTH, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, texto, 8, SpringLayout.SOUTH, select);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto, 0, SpringLayout.HORIZONTAL_CENTER, select);
        this.add(etiqueta1);
        this.add(select);
        this.add(etiqueta2);
        this.add(texto);
        this.setVisible(true);

        volver.addActionListener(this);
        desbloquear.addActionListener(this);
        //comprobar.addActionListener(this);
    }

    public void actionPerformed(ActionEvent evento) {
        texto.setVisible(false);
        if( evento.getSource()==volver){
            gui.getControlador().logout();
        } else if(evento.getSource()==desbloquear){
            gui.getControlador().goDesbloquearUsuarios();
        } else if(evento.getSource()==comprobar){
            gui.getControlador().goComprobarOfertas();
        }
    }

    public void setError(String error) {
        texto.setVisible(true);
        texto.setForeground(java.awt.Color.red);
    }
}
