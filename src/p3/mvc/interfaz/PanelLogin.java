package p3.mvc.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelLogin extends JPanel implements ActionListener{
    private final JTextField nif = new JTextField(10);
    private final JPasswordField pswd = new JPasswordField(15);
    private ButtonGroup grupo = new ButtonGroup();
    private JRadioButton demandante = new JRadioButton("Demandante");
    private JRadioButton ofertante = new JRadioButton("Ofertante");
    private JRadioButton gerente = new JRadioButton("Gerente");
    private JButton inSes = new JButton("Iniciar Sesion");
    private JButton volver = new JButton("Volver");
    private JLabel texto = new JLabel("");

    private GuiInmobiliaria gui;

    PanelLogin(GuiInmobiliaria gui){
        this.gui = gui;

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        JPanel selectType = new JPanel(new GridLayout(3, 1));
        grupo.add(demandante);
        grupo.add(ofertante);
        grupo.add(gerente);
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
        layout.putConstraint(SpringLayout.WEST, etiqueta2, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 8, SpringLayout.SOUTH, etiqueta1);
        layout.putConstraint(SpringLayout.WEST, pswd, 5, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, pswd, 5, SpringLayout.SOUTH, nif);
        layout.putConstraint(SpringLayout.NORTH, selectType, 10, SpringLayout.SOUTH, etiqueta2);
        layout.putConstraint(SpringLayout.WEST, selectType, 0, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, texto, 8, SpringLayout.SOUTH, selectType);
        layout.putConstraint(SpringLayout.WEST, texto, 0, SpringLayout.WEST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, selectLogin, 10, SpringLayout.SOUTH, texto);
        layout.putConstraint(SpringLayout.WEST, selectLogin, 0, SpringLayout.WEST, etiqueta2);

        this.add(etiqueta1);
        this.add(etiqueta2);
        this.add(nif);
        this.add(pswd);
        this.add(texto);
        this.add(selectLogin);
        this.add(selectType);

        this.setVisible(true);
        this.setPreferredSize(new Dimension(600, 400));

        inSes.addActionListener(this);
        volver.addActionListener(this);
    }

    public void actionPerformed(ActionEvent evento){
        String option = "";
        texto.setVisible(false);
        if(evento.getSource()==inSes) {
            if (demandante.isSelected()) {
                option = demandante.getText();
            } else if (ofertante.isSelected()) {
                option = ofertante.getText();
            } else if (gerente.isSelected()) {
                option = gerente.getText();
            }
            gui.getControlador().login(nif.getText(), new String(pswd.getPassword()), option);
        }else if(evento.getSource()==volver){
            gui.getControlador().volverLogin();
        }
    }

    public void setError(String texto) {
        this.texto.setText(texto);
        this.texto.setVisible(true);
        this.texto.setForeground(java.awt.Color.red);
    }
}
