package p3.mvc.interfaz;

import p3.mvc.modelo.Inmueble;
import p3.mvc.modelo.Ofertante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelVerInmuebles extends JPanel implements ActionListener{
    private String[] titulos = {"Habitaciones", "Banos", "Dimensiones", "Ascensor", "Planta" };
    private Object filas [][] = {};
    private GuiInmobiliaria gui;
    private DefaultTableModel modeloDatos = new DefaultTableModel(filas, titulos){
        public boolean isCellEditable(int row, int colum){
            return false;
        }
    };
    private JButton volver = new JButton("Volver");
    private JButton crear = new JButton("Crear Oferta");
    private List<Inmueble> lista;
    private JTable tabla = new JTable(modeloDatos);
    private JLabel texto = new JLabel("");

    public PanelVerInmuebles(GuiInmobiliaria gui){
        this.gui = gui;
        this.lista = ((Ofertante)gui.getControlador().getCliente()).getInmuebles();
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        JTable tabla = new JTable(modeloDatos);
        JScrollPane scrollPane = new JScrollPane(tabla);
        for(Inmueble i:lista){
            Object[] nuevaFila = {i.getnHabitaciones(), i.getnBanos(), i.getDimensiones(), i.getAscensor(), i.getPlanta()};
            modeloDatos.addRow(nuevaFila);
        }

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(volver);
        JPanel select = new JPanel(new GridLayout(1, 2));
        select.add(volver);
        select.setVisible(true);
        texto.setVisible(false);

        JLabel etiqueta1 = new JLabel("Tus Inmuebles");
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta1, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollPane, 0, SpringLayout.HORIZONTAL_CENTER, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, scrollPane, 20, SpringLayout.NORTH, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, texto, 5, SpringLayout.SOUTH, scrollPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto, 0, SpringLayout.HORIZONTAL_CENTER, scrollPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, select, 0, SpringLayout.HORIZONTAL_CENTER, texto);
        layout.putConstraint(SpringLayout.NORTH, select, 5, SpringLayout.SOUTH, texto);

        this.add(etiqueta1);
        this.add(select);
        this.add(tabla);
        this.add(texto);
        this.add(scrollPane);
        this.setVisible(true);

        volver.addActionListener(this);
        crear.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        texto.setVisible(false);
        if(e.getSource()==volver){
            gui.getControlador().volverOfertante();
        }else if(e.getSource()==crear){
            gui.getControlador().goCrearOferta(lista.get(tabla.getSelectedRow()));
        }
    }
}
