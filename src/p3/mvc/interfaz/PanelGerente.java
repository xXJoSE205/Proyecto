package p3.mvc.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase contiene la informacion del panel Gerente
 * Crea un panel en el que se muestran las distintas acciones que puede realizar el gerente, unos botones para
 * cerrar sesion(desconectarse), desbloquear usuarios y comprobar ofertas
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
class PanelGerente extends JPanel implements ActionListener {
    private final JLabel texto = new JLabel("Error al cerrar sesion");
    private final GuiInmobiliaria gui;
    private final JButton volver = new JButton("Desconectarse");
    private final JButton desbloquear = new JButton("Desbloquear usuarios");
    private final JButton comprobar = new JButton("Comprobar ofertas");

    /**
     * Constructor de PanelGerente
     * @param gui GUI de la inmobiliaria
     */
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
        JLabel etiqueta3 = new JLabel("Comisiones totales: "+gui.getControlador().getSistema().getTotalComisiones());

        JLabel etiqueta1 = new JLabel("GERENTE");
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta1, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta2, 0, SpringLayout.HORIZONTAL_CENTER, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 20, SpringLayout.NORTH, etiqueta1);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, select, 0, SpringLayout.HORIZONTAL_CENTER, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, select, 20, SpringLayout.NORTH, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, texto, 8, SpringLayout.SOUTH, select);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto, 0, SpringLayout.HORIZONTAL_CENTER, select);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta3, 0, SpringLayout.HORIZONTAL_CENTER, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, etiqueta3, 20, SpringLayout.SOUTH, texto);
        this.add(etiqueta1);
        this.add(select);
        this.add(etiqueta2);
        this.add(texto);
        this.add(etiqueta3);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(800, 600));
        volver.addActionListener(this);
        desbloquear.addActionListener(this);
        comprobar.addActionListener(this);
    }

    /**
     * Manejador de las acciones de los botones
     * @param evento Accion que se activa
     */
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

    /**
     * Establece el mensaje que se va a mostrar por pantalla
     * @param texto Cadena con el mensaje a mostrar
     */
    public void setError(String texto) {
        this.texto.setVisible(true);
        this.texto.setText(texto);
        this.texto.setForeground(java.awt.Color.red);
    }
}
