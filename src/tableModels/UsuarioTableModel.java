package tableModels;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;
import java.util.List;
import entidad.*;

@SuppressWarnings({ "unused", "serial" })
public class UsuarioTableModel extends AbstractTableModel {
	private int dni;
	private String pass;
	private String nya;
	private String fecha_nac;
	private String obra;
	
	private static final int columna_dni = 0;
	private static final int columna_pass = 1;
	private static final int columna_nya = 2;
	private static final int columna_fecha_nac = 3;
	private static final int columna_obra = 4;
	private static final int columna_tipo_usuario = 5;
	
	
	private String[] nombresColumnas = {"dni", "pass", "nya","fecha_nac","obra","permiso"};
	@SuppressWarnings("rawtypes")
	private Class[] tiposColumnas = {Integer.class, String.class,String.class,String.class,String.class, Integer.class};
	private List<User> contenido;
	
	
	public UsuarioTableModel() {
		contenido = new ArrayList<User>();
	}
	public int getColumnCount() {
		return nombresColumnas.length;
	}

	public int getRowCount() {
		return contenido.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		
		User m = contenido.get(rowIndex);
		
		Object result = null;
		switch(columnIndex) {
		case columna_dni:
			result = m.getDni();
			break;
		case columna_pass:
			result = m.getPass();
			break;
		case columna_nya:
			result = m.getNya();
			break;
		case columna_fecha_nac:
			result = m.getFecha_nac();
			break;
		case columna_obra:
			result = m.getObra();
			break;
		case columna_tipo_usuario:
			result = m.getTipo_usuario();
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
    
    
    
    
  
    public List<User> getContenido() {
    	return contenido;
    }
    
    public void setContenido(List<User> contenido) {
    	this.contenido = contenido;
    }
}
