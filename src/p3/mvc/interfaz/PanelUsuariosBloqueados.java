package p3.mvc.interfaz;

import p3.mvc.modelo.Demandante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PanelUsuariosBloqueados extends JPanel implements ActionListener{
    private String[] titulos = {"Nombre", "Apellidos", "NIF", "Tarjeta"};
    private Object filas [][] = {};
    private GuiInmobiliaria gui;
    private DefaultTableModel modeloDatos = new DefaultTableModel(filas, titulos){
        public boolean isCellEditable(int row, int colum){
            return false;
        }
    };
    private JButton volver = new JButton("Volver");
    private JButton desbloquear = new JButton("Desbloquear usuario");
    private List<Demandante> lista;
    private JTable tabla = new JTable(modeloDatos);
    private JLabel texto = new JLabel("");

    public PanelUsuariosBloqueados(GuiInmobiliaria gui){
        this.gui = gui;
        this.lista = gui.getControlador().getUsuariosBloqueados();
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        JTable tabla = new JTable(modeloDatos);
        JScrollPane scrollPane = new JScrollPane(tabla);
        if(this.lista==null){
            Object[] nuevaFila={"No hay usuarios bloqueados"};
        }else {
            for (Demandante d : lista) {
                Object[] nuevaFila = {d.getNombre(), d.getApellidos(), d.getNif(), d.getTarjeta()};
                modeloDatos.addRow(nuevaFila);
            }
        }

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(volver);
        grupo.add(desbloquear);
        JPanel select = new JPanel(new GridLayout(1, 2));
        select.add(volver);
        select.add(desbloquear);
        select.setVisible(true);
        texto.setVisible(false);

        JLabel etiqueta1 = new JLabel("Usuarios Bloqueados");
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
        this.setPreferredSize(new Dimension(600, 400));
        volver.addActionListener(this);
        desbloquear.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        texto.setVisible(false);
        if(e.getSource()==volver){
            gui.getControlador().volverOfertante();
        }else if(e.getSource()==desbloquear){
            gui.getControlador().goDesbloquearUsuarios(lista.get(tabla.getSelectedRow()));
        }
    }
}
