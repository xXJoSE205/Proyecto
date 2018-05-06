package p3.mvc.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelLogin extends JPanel implements ActionListener{
    private final JTextField nif = new JTextField(10);
    private final JPasswordField pswd = new JPasswordField(15);
    private final JRadioButton demandante = new JRadioButton("Demandante");
    private final JRadioButton ofertante = new JRadioButton("Ofertante");
    private final JRadioButton gerente = new JRadioButton("Gerente");
    private final JButton inSes = new JButton("Iniciar Sesion");
    private final JButton volver = new JButton("Volver");
    private final JLabel texto = new JLabel("");

    private final GuiInmobiliaria gui;

    PanelLogin(GuiInmobiliaria gui){
        this.gui = gui;

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        JPanel selectType = new JPanel(new GridLayout(3, 1));
        ButtonGroup grupo1 = new ButtonGroup();
        grupo1.add(demandante);
        grupo1.add(ofertante);
        grupo1.add(gerente);
        selectType.add(demandante);
        selectType.add(ofertante);
        selectType.add(gerente);
        selectType.setVisible(true);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(inSes);
        grupo.add(volver);
        JPanel selectLogin = new JPanel(new GridLayout(1, 2));
        selectLogin.add(inSes);
        selectLogin.add(volver);
        selectLogin.setVisible(true);
        texto.setVisible(false);

        JLabel etiqueta2 = new JLabel("Contrasena: ");
        JLabel etiqueta1 = new JLabel("NIF: ");
        layout.putConstraint(SpringLayout.EAST, etiqueta1, 0, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, nif, 0, SpringLayout.WEST, pswd);
        layout.putConstraint(SpringLayout.NORTH, nif, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.EAST, etiqueta2, -50, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 8, SpringLayout.SOUTH, etiqueta1);
        layout.putConstraint(SpringLayout.WEST, pswd, 5, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, pswd, 5, SpringLayout.SOUTH, nif);
        layout.putConstraint(SpringLayout.NORTH, selectType, 10, SpringLayout.SOUTH, etiqueta2);
        layout.putConstraint(SpringLayout.WEST, selectType, 0, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, texto, 8, SpringLayout.SOUTH, selectType);
        layout.putConstraint(SpringLayout.WEST, texto, 0, SpringLayout.WEST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, selectLogin, 10, SpringLayout.SOUTH, texto);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, selectLogin, 0, SpringLayout.HORIZONTAL_CENTER, this);

        this.add(etiqueta1);
        this.add(etiqueta2);
        this.add(nif);
        this.add(pswd);
        this.add(texto);
        this.add(selectLogin);
        this.add(selectType);

        this.setVisible(true);
        this.setPreferredSize(new Dimension(800, 600));

        inSes.addActionListener(this);
        volver.addActionListener(this);
    }

    public void actionPerformed(ActionEvent evento){
        String option = "";
        if(evento.getSource()==inSes) {
            texto.setVisible(false);
            if (demandante.isSelected()) {
                option = demandante.getText();
            } else if (ofertante.isSelected()) {
                option = ofertante.getText();
            } else if (gerente.isSelected()) {
                option = gerente.getText();
            }
            gui.getControlador().login(nif.getText(), new String(pswd.getPassword()), option);
            pswd.setText("");
        }else if(evento.getSource()==volver){
            pswd.setText("");
            texto.setVisible(false);
            gui.getControlador().volverLogin();
        }
    }

    public void setError(String texto) {
        this.texto.setText(texto);
        this.texto.setVisible(true);
        this.texto.setForeground(java.awt.Color.red);
    }

    public void text (){
        this.texto.setVisible(false);
    }
}
