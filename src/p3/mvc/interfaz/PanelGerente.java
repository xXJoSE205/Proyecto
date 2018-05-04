package p3.mvc.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelGerente extends JPanel implements ActionListener {
    private JLabel texto = new JLabel("Error al cerrar sesion");
    private GuiInmobiliaria gui;

    JButton volver = new JButton("Desconectarse");
    JButton desbloquear = new JButton("Desbloquear usuarios");
    JButton comprobar = new JButton("Comprobar ofertas");

    PanelGerente(GuiInmobiliaria gui){
        this.gui = gui;
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        JLabel etiqueta2 = new JLabel("Bienvenido " + gui.getControlador().getSistema().getGerente().getNombre());
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(volver);
        grupo.add(desbloquear);
        grupo.add(comprobar);
        JPanel select = new JPanel(new GridLayout(3, 1));
        select.add(volver);
        select.add(desbloquear);
        select.add(comprobar);
        select.setVisible(true);
        texto.setVisible(false);

        JLabel etiqueta1 = new JLabel("GERENTE");
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta1, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta2, 0, SpringLayout.HORIZONTAL_CENTER, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 20, SpringLayout.NORTH, etiqueta1);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, select, 0, SpringLayout.HORIZONTAL_CENTER, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, select, 20, SpringLayout.NORTH, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, texto, 8, SpringLayout.SOUTH, select);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto, 0, SpringLayout.HORIZONTAL_CENTER, select);
        this.add(etiqueta1);
        this.add(select);
        this.add(etiqueta2);
        this.add(texto);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(800, 600));
        volver.addActionListener(this);
        desbloquear.addActionListener(this);
        comprobar.addActionListener(this);
    }

    public void actionPerformed(ActionEvent evento) {
        if(evento.getSource()==volver){
            texto.setVisible(false);
            gui.getControlador().logout();
        } else if(evento.getSource()==desbloquear){
            texto.setVisible(false);
            gui.getControlador().goUsuariosBloqueados();
        } else if(evento.getSource()==comprobar){
            texto.setVisible(false);
            gui.getControlador().goComprobarOfertas();
        }
    }

    public void setError(String error) {
        texto.setVisible(true);
        texto.setForeground(java.awt.Color.red);
    }

    public void creadaOK(String texto) {
        this.texto.setText(texto);
        this.texto.setVisible(true);
        this.texto.setForeground(java.awt.Color.red);
    }
}
