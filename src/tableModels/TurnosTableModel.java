package tableModels;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;
import java.util.List;
import entidad.*;

@SuppressWarnings({ "unused", "serial" })
public class TurnosTableModel extends AbstractTableModel {

	private static final int columna_legajoMedico = 0;
	private static final int columna_fecha = 1;
	private static final int columna_hora = 2;
	private static final int columna_nroConsultorio = 3;
	private static final int columna_dniPaciente = 4;
	private static final int columna_iDTurno = 5;
	
	
	private String[] nombresColumnas = {"legajoMedico", "fecha","hora","nroConsultorio","dniPaciente","iDTurno"};
	@SuppressWarnings("rawtypes")
	private Class[] tiposColumnas = {Integer.class, String.class,String.class,String.class,String.class, Integer.class};
	private List<Turno> contenido;
	
	
	public TurnosTableModel() {
		contenido = new ArrayList<Turno>();
	}
	public int getColumnCount() {
		return nombresColumnas.length;
	}

	public int getRowCount() {
		return contenido.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Turno m = contenido.get(rowIndex);
		
		Object result = null;
		switch(columnIndex) {
		case columna_legajoMedico:
			result = m.getLegajoMedico();
			break;
		case columna_fecha:
			result = m.getFecha();
			break;
		case columna_hora:
			result = m.getHora();
			break;
		case columna_nroConsultorio:
			result = m.getNroConsultorio();
			break;
		case columna_dniPaciente:
			result = m.getDniPaciente();
			break;
		case columna_iDTurno:
			result = m.getIDTurno();
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
    

    public List<Turno> getContenido() {
    	return contenido;
    }
    
    public void setContenido(List<Turno> contenido) {
    	this.contenido = contenido;
    }
}

