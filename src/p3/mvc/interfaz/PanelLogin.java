package p3.mvc.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelLogin extends JPanel implements ActionListener{
    private JLabel etiqueta1 = new JLabel("NIF: ");
    private final JTextField nif = new JTextField(10);
    private JLabel etiqueta2 = new JLabel("Contrasena: ");
    private final JPasswordField pswd = new JPasswordField(15);
    private JPanel selectType = new JPanel(new GridLayout(3, 1));
    private JPanel selectLogin = new JPanel(new GridLayout(1, 2));
    private JRadioButton demandante = new JRadioButton("Demandante");
    private JRadioButton ofertante = new JRadioButton("Ofertante");
    private JRadioButton gerente = new JRadioButton("Gerente");
    private ButtonGroup grupo = new ButtonGroup();
    private JButton inSes = new JButton("Iniciar Sesion");
    private JButton volver = new JButton("Volver");

    private GuiInmobiliaria gui;

    PanelLogin(GuiInmobiliaria gui){
        this.gui = gui;

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        selectType.add(demandante);
        selectType.add(ofertante);
        selectType.add(gerente);
        selectType.setVisible(true);

        grupo.add(inSes);
        grupo.add(volver);
        selectLogin.add(inSes);
        selectLogin.add(volver);
        selectLogin.setVisible(true);

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
        layout.putConstraint(SpringLayout.NORTH, selectLogin, 10, SpringLayout.SOUTH, selectType);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, selectLogin, 0, SpringLayout.HORIZONTAL_CENTER, pswd);

        this.add(etiqueta1);
        this.add(etiqueta2);
        this.add(nif);
        this.add(pswd);
        this.add(selectLogin);
        this.add(selectType);
        this.setVisible(true);

        inSes.addActionListener(this);
    }

    public void actionPerformed(ActionEvent evento){
        String option = "";
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

    public void setError(String error) {
        pswd.setText(error);
        pswd.setForeground(java.awt.Color.red);
    }
}
