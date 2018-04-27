package p3.mvc.interfaz;

import p3.src.Inmueble;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BusquedaResultado extends JPanel implements ActionListener {
    private JLabel etiqueta1 = new JLabel("Resultados de la busqueda");
    private String[] titulos = {"Habitaciones", "Banos", "Dimensiones", "Ascensor", "Planta" };
    private Object filas [][] = {};
    private GuiSimple gui;
    private JPanel select = new JPanel(new GridLayout(1, 2));
    private ButtonGroup grupo = new ButtonGroup();
    private DefaultTableModel modeloDatos = new DefaultTableModel(filas, titulos);

    BusquedaResultado(GuiSimple gui, List<Inmueble> lista){
        this.gui = gui;
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        JTable tabla = new JTable(modeloDatos);

        for(Inmueble i:lista){
            Object[] nuevaFila = {i.getnHabitaciones(), i.getnBanos(), i.getDimensiones(), i.getAscensor(),i.getPlanta()};
            modeloDatos.addRow(nuevaFila);
        }

        JButton volver = new JButton("Volver");
        grupo.add(volver);
        select.add(volver);
        select.setVisible(true);

        layout.putConstraint(SpringLayout.WEST, etiqueta1, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, tabla, 5, SpringLayout.WEST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, tabla, 20, SpringLayout.NORTH, etiqueta1);
        layout.putConstraint(SpringLayout.WEST, select, 5, SpringLayout.WEST, tabla);
        layout.putConstraint(SpringLayout.NORTH, select, 20, SpringLayout.NORTH, tabla);

        this.add(etiqueta1);
        this.add(select);
        this.add(tabla);
        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent evento) {

    }

}
