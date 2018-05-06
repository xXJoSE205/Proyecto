package p3.mvc.interfaz;

import p3.mvc.modelo.Demandante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class PanelUsuariosBloqueados extends JPanel implements ActionListener{
    private final String[] titulos = {"Nombre", "Apellidos", "NIF", "Tarjeta"};
    private final Object[][] filas  = {};
    private final GuiInmobiliaria gui;
    private final DefaultTableModel modeloDatos = new DefaultTableModel(filas, titulos){
        public boolean isCellEditable(int row, int colum){
            return false;
        }
    };
    private final JButton volver = new JButton("Volver");
    private final JButton desbloquear = new JButton("Desbloquear usuario");
    private final List<Demandante> lista;
    private final JTable tabla = new JTable(modeloDatos);
    private final JLabel texto = new JLabel("");

    public PanelUsuariosBloqueados(GuiInmobiliaria gui){
        this.gui = gui;
        this.lista = gui.getControlador().getUsuariosBloqueados();
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        JScrollPane scrollPane = new JScrollPane(tabla);
        if(this.lista==null){
            Object[] nuevaFila={"No hay usuarios bloqueados"};
        }else {
            for (Demandante d : lista) {
                Object[] nuevaFila = {d.getNombre(), d.getApellidos(), d.getNif(), d.getTarjeta()};
                modeloDatos.addRow(nuevaFila);
            }
        }
        tabla.setPreferredScrollableViewportSize(new Dimension(600, 350));

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

        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setColumnSelectionAllowed(false);
        tabla.getSelectionModel().getLeadSelectionIndex();
        tabla.setRowSelectionAllowed(true);

        this.add(etiqueta1);
        this.add(select);
        this.add(texto);
        this.add(scrollPane);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(800, 600));
        volver.addActionListener(this);
        desbloquear.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==volver){
            texto.setVisible(false);
            gui.getControlador().volverGerente();
        }else if(e.getSource()==desbloquear){
            texto.setVisible(false);
            try {
                gui.getControlador().goDesbloquearUsuarios(lista.get(tabla.getSelectedRow()));
            }catch (Exception exception){
                texto.setText("Selecciona usario para desbloquear");
                texto.setVisible(true);
                texto.setForeground(Color.red);
            }
        }
    }
}
