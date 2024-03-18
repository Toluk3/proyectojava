package panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import entidad.Medico;
import entidad.Turno;
import service.MedicoService;
import service.TurnoService;

@SuppressWarnings("serial")
public class ReporteMedicosEntreFechas extends JPanel implements ActionListener {

	//servicios
	private MedicoService servmed;
	private TurnoService servtur;
	//swing
	
    private JTextField fechaInicioTextField;
    private JTextField fechaFinTextField;
    private JButton calcular;
	private String ok;
	private JLabel errorDisplay;
	private JPanel panelForm;
	//listas
	private List<Medico> listaMedica;
	private List<Turno> listaTurnos;
	private DefaultTableModel tableModel;
	private JPanel paneltabla;
	

	public ReporteMedicosEntreFechas() {
        //cargar listas
		this.paneltabla=new JPanel();
		this.paneltabla.setLayout(new FlowLayout());
		this.panelForm=new JPanel();
		this.panelForm.setLayout(new GridLayout(4,2));
    	this.errorDisplay=new JLabel();
    	this.ok="ok!";
    	this.errorDisplay.setBackground(Color.BLACK);
        this.errorDisplay.setForeground(Color.green);
    	
    	
    	// Configuraci�n del panel y sus componentes aqu�
    	
    	
    	this.setLayout(new FlowLayout());
        this.servmed=new MedicoService();
        this.servtur=new TurnoService();

        fechaInicioTextField = new JTextField(10);
        fechaFinTextField = new JTextField(10);
        calcular = new JButton("Calcular Turnos");      
        try {
			this.listaMedica=servmed.listaTodosLosObjetos();
			this.listaTurnos=servtur.listaTodosLosObjetos();
			generarTabla("10/10/1000", "10/10/1000");//genero tabla vacia
			
		} catch (Exception e) {
			e.printStackTrace();
			mostrarerror("error de base de datos");
		}

        this.panelForm.add(new JLabel("Fecha Inicio (dd/MM/yyyy):"));
        this.panelForm.add(fechaInicioTextField);
        this.panelForm.add(new JLabel("Fecha Fin (dd/MM/yyyy):"));
        this.panelForm.add(fechaFinTextField);
        calcular.addActionListener(this);
        this.panelForm.add(calcular);
        this.panelForm.add(new JLabel());
        this.panelForm.add(errorDisplay);
        
        add(panelForm);
        add(paneltabla);
        
        
        this.revalidate();
        this.repaint();
    }
    
    
    @Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==calcular) {
			System.out.println(fechaInicioTextField.getText());
			System.out.println(fechaFinTextField.getText());
			if (validarFecha(fechaInicioTextField.getText()) && validarFecha(fechaFinTextField.getText()) ) {
				generarTabla(fechaInicioTextField.getText(), fechaFinTextField.getText());
				mostrarerror(ok);
				
			} else {
				mostrarerror("rango de fechas incorrecto");
			}
			
			
		}
		
	}
    
    private int calcularSumaTurnos(int leg, String fechaInicio, String fechaFin) {
    	int total=0;
    	
    	String[] auxFechainit= fechaInicio.split("/");
    	String[] auxFechafin= fechaFin.split("/");
    	
    	int anoInicio= Integer.parseInt(auxFechainit[2]);
		int mesInicio= Integer.parseInt(auxFechainit[1]);
		int diaInicio= Integer.parseInt(auxFechainit[0]);
		int anoFin= Integer.parseInt(auxFechafin[2]);
		int mesFin= Integer.parseInt(auxFechafin[1]);
		int diaFin= Integer.parseInt(auxFechafin[0]);
		
    	for (Turno tur:listaTurnos) {
    		System.out.println(tur.getFecha());
    		String[] auxFecha=tur.getFecha().split("/");
    		int anoTurno= Integer.parseInt(auxFecha[2]);
    		int mesTurno= Integer.parseInt(auxFecha[1]);
    		int diaTurno= Integer.parseInt(auxFecha[0]);
    		System.out.println(fechaFinTextField.getText());
    		if(tur.getLegajoMedico()==leg) {
    			if(estaDentroDelRango(anoTurno, mesTurno, diaTurno, anoInicio, mesInicio, diaInicio, anoFin, mesFin, diaFin))
		                    total=total+1;
    		}
    	}
        return total; 
    }
    private boolean estaDentroDelRango(int anoTurno, int mesTurno, int diaTurno, int anoInicio, int mesInicio, int diaInicio, int anoFin, int mesFin, int diaFin) {
        if (anoTurno < anoInicio || anoTurno > anoFin) {return false;}
        if (anoTurno == anoInicio && mesTurno < mesInicio) {return false;}
        if (anoTurno == anoInicio && mesTurno == mesInicio && diaTurno < diaInicio) {return false;}
        if (anoTurno == anoFin && mesTurno > mesFin) {return false;}
        if (anoTurno == anoFin && mesTurno == mesFin && diaTurno > diaFin) {return false;}
        return true;
    }

    
    public void mostrarerror(String x) {
		
		if(x==this.ok) {
			errorDisplay.setForeground(Color.green);
		}
		else {
			errorDisplay.setForeground(Color.RED);
		}
		errorDisplay.setText(x);
		errorDisplay.validate();
		errorDisplay.repaint();
		System.out.println(x);
		
	}
    
    public boolean validarFecha(String nac) {
		if (nac.length()==10) {
			String[] nac2=nac.split("/");
			try {
				int dia = Integer.parseInt(nac2[0]);
				int mes = Integer.parseInt(nac2[1]);
				int anio = Integer.parseInt(nac2[2]);
				if (dia>0 && dia<32 && mes>0 && mes<13 && anio>1900 && anio<2500) {
					return true;
				}
			} catch (Exception e) {
				return false;
			}
			
		}
			
			return false;
	} 

	public void generarTabla(String fechaInicioTextField, String fechaFinTextField) {
	this.paneltabla.removeAll();
	this.tableModel = new DefaultTableModel();
    tableModel.addColumn("Medico");
    tableModel.addColumn("Turnos");
    tableModel.addColumn("ganancias");
    
    for (Medico med : listaMedica) {
        int x = calcularSumaTurnos(med.getLegajoMedico(), fechaInicioTextField, fechaFinTextField);
        tableModel.addRow(new Object[]{med.getNya(), x, x*med.getCosto()});
    }

    JTable table = new JTable(tableModel);

    JScrollPane scrollPane = new JScrollPane(table);
    
    this.paneltabla.add(scrollPane);
    revalidate();
    repaint();
	}
}
