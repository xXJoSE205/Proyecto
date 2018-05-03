package p3.mvc.interfaz;

import javax.swing.table.DefaultTableModel;

public class TablaMiModelo extends DefaultTableModel {
    public boolean isCellEditable (int row, int column){
        return false;
    }
}
