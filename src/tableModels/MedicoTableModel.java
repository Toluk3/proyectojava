package tableModels;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;
import java.util.List;
import entidad.*;

@SuppressWarnings({ "unused", "serial" })
public class MedicoTableModel extends AbstractTableModel {
	private static final int columna_nombre = 0;
	private static final int columna_legajoMed = 1;
	private static final int columna_obras = 2;
	private static final int columna_costo = 3;
	
	private String[] nombresColumnas = {"Nombre","Legajo", "Obras", "Costo"};
	@SuppressWarnings("rawtypes")
	private Class[] tiposColumnas = {String.class,Integer.class, String.class, Integer.class};
	private List<Medico> contenido;
	
	
	public MedicoTableModel() {
		contenido = new ArrayList<Medico>();
	}
	public int getColumnCount() {
		return nombresColumnas.length;
	}

	public int getRowCount() {
		return contenido.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Medico m = contenido.get(rowIndex);
		
		Object result = null;
		switch(columnIndex) {
		case columna_nombre:
			result = m.getNya();
			break;
		case columna_legajoMed:
			result = m.getLegajoMedico();
			break;
		case columna_obras:
			result = m.getObras();
			break;
		case columna_costo:
			result = m.getCosto();
			break;
		default:
			result = new String("");
		}
		
		return result;
	}

	
	public String getColumnName(int col) {
        return nombresColumnas[col];
    }

	
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Class getColumnClass(int col) {
        return tiposColumnas[col];
    }
    
    
    
    
  
    public List<Medico> getContenido() {
    	return contenido;
    }
    
    public void setContenido(List<Medico> contenido) {
    	this.contenido = contenido;
    }
}
