package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.DAOException;

import java.awt.*;
import java.util.List;

import service.*;
import entidad.Turno;
import entidad.User;

@SuppressWarnings("serial")
public class ListaDeConsultas extends JPanel {
	private List<Turno> listaTurnos;
	private TurnoService servicioTurno;
	private MedicoService servicioMedico;
	private User sesion;
	private DefaultTableModel tableModel;
	private UserService servicioUsuario;

	public ListaDeConsultas(User sesion, int modo) {//modo 1 paciente, modo 2 medico, modo 3 general 
		setLayout(new BorderLayout());
		try {
        this.sesion=sesion;
		this.servicioMedico=new MedicoService();
		this.servicioTurno=new TurnoService();
		this.servicioUsuario=new UserService();
		this.listaTurnos= servicioTurno.listaTodosLosObjetos();
		this.tableModel = new DefaultTableModel();
        tableModel.addColumn("Fecha");
        tableModel.addColumn("Hora");
        tableModel.addColumn("Consultorio");
        tableModel.addColumn("Médico");
        tableModel.addColumn("Paciente");
        
		if (modo == 1) {
			for (Turno turno : listaTurnos) {
	        	if(turno.getDniPaciente()==this.sesion.getDni()){
	        		cargarTurnoEnTabla(turno);
	        		}
				}
			
		}else if (modo == 2) {
        for (Turno turno : listaTurnos) {
        	if(turno.getLegajoMedico()==this.sesion.getDni()){
        		cargarTurnoEnTabla(turno);
        		}
        	}
        }
        else if (modo == 3) {
        	for (Turno turno : listaTurnos) {
                cargarTurnoEnTabla(turno);
        	}
        }

        
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
		}catch (DAOException e) {
			e.printStackTrace();
			this.add(new Label("error base de datos"));
		}


        }
	private void cargarTurnoEnTabla(Turno turno) throws DAOException {
		String fecha = turno.getFecha();
        String hora = turno.getHora();
        int consultorio = turno.getNroConsultorio();
        String medicoNombreApellido = servicioMedico.muestraObjeto(turno.getLegajoMedico()).getNya();
        String pacienteNombreApellido = servicioUsuario.muestraObjeto(turno.getDniPaciente()).getNya();
        tableModel.addRow(new Object[]{fecha, hora, consultorio, medicoNombreApellido,pacienteNombreApellido});
	}
	
}
