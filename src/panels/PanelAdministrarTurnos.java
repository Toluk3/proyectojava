package panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dao.DAOException;
import entidad.*;
import service.*;
import tableModels.TurnosTableModel;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@SuppressWarnings("serial")
public class PanelAdministrarTurnos extends JPanel implements ActionListener {
	//manejo de error
	private String ok; 
	private Label errorDisplay;
    //servicios
    private UserService serviciousuario;
    private MedicoService serviciomedico;
    private TurnoService servicioturno;
    //botones
    private JButton reservarButton;
    private JButton eliminarButton;
    //listas
    private List<Medico> listaMedico;
    private List<User> listaUsuarios;
	//combos
    private JComboBox<String> usuarioComboBox;
	private JComboBox<String> medicoComboBox;
	private JComboBox<Object> horaComboBox;
	private JComboBox<Object> fechaComboBox;
	private JComboBox<Integer> consultorioComboBox;
	//paneles
	private JPanel panelPrincipal;
	private JPanel sidePanel;
	private TurnosTableModel modelo;
	private JTable tablaTurnos;
	private JScrollPane scrollPaneParaTabla;
	
	
    
    public PanelAdministrarTurnos() {
    	this.serviciomedico= new MedicoService();
    	this.serviciousuario=new UserService();
    	this.servicioturno= new TurnoService();
    	armarPanel();
    }
    	//Establece el diseño del panel
    private void armarPanel() {
    	String error="";
    	this.setLayout(new FlowLayout());
    	this.panelPrincipal= new JPanel();
    	panelPrincipal.setLayout(new GridLayout(7,2));
        //Agrego display de errores
        this.ok="correcto!";//string de ejecucion exitosa
        this.errorDisplay= new Label();
		errorDisplay.setBackground(Color.BLACK);
		mostrarerror(ok);
		add(errorDisplay);
		this.sidePanel= new JPanel();
		sidePanel.setLayout(new GridLayout(2,1));
		this.sidePanel.add(errorDisplay);
		this.sidePanel.add(panelPrincipal);
		
		
		
        
        // Etiqueta para seleccionar un médico
        JLabel medicoLabel = new JLabel("Médico:");
        panelPrincipal.add(medicoLabel);
        // ComboBox para seleccionar un médico
        this.medicoComboBox = new JComboBox<>();
        try {
        	this.listaMedico = serviciomedico.listaTodosLosObjetos();
        	for (Medico elemento : this.listaMedico) {
        		String x = elemento.getNya();
                medicoComboBox.addItem(x);
                }
		} catch (Exception e) {
			error=error+"error carga medica";
		}
		panelPrincipal.add(medicoComboBox);

		// Etiqueta para seleccionar un usuario
		JLabel usuarioLabel = new JLabel("Usuario:");
		panelPrincipal.add(usuarioLabel);

		this.usuarioComboBox = new JComboBox<>();

		try {
		    this.listaUsuarios=serviciousuario.listaTodosLosObjetos();
		    for (User usuario : listaUsuarios) {
		    	String x= usuario.getNya();
		        usuarioComboBox.addItem(x);
		    }
		} catch (Exception e) {
			error=error+"error carga usuarios";
		}

		panelPrincipal.add(usuarioComboBox);

		
		
		
		
        // Etiqueta para ingresar fecha y hora
        fechaComboBox = new JComboBox<>();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        
        for (int i = 0; i < 60; i++) {
            fechaComboBox.addItem(dateFormat.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        // Agregar el JComboBox al contenido del JFrame
        panelPrincipal.add(new JLabel("fecha:"));
        panelPrincipal.add(fechaComboBox);

        // Campo de texto para ingresar fecha y hora (puedes utilizar JFormattedTextField)
        
        this.horaComboBox = new JComboBox<>();
        for (int hora = 8; hora <= 20; hora++) {
            for (int minuto = 0; minuto < 6; minuto += 3) {
                String horaStr = hora + ":" + minuto+"0";
                horaComboBox.addItem(horaStr);
            }
        }
        panelPrincipal.add(new JLabel("hora:"));
        panelPrincipal.add(horaComboBox);
        
        //campo consultorio
        JLabel nroConsultorio = new JLabel("nro_consultorio:");
		panelPrincipal.add(nroConsultorio);
        this.consultorioComboBox = new JComboBox<>();
        for (int i = 1; i <= 30; i++) {
            consultorioComboBox.addItem(i);
        }
        panelPrincipal.add(consultorioComboBox);
        
        
        
        
        // Botón para reservar turno
        reservarButton = new JButton("Reservar Turno");
        reservarButton.addActionListener(this);
        this.panelPrincipal.add(reservarButton);
        
        eliminarButton = new JButton("Eliminar Turno");
        eliminarButton.addActionListener(this);
        add(eliminarButton);
        
        if(error.length()==0) {mostrarerror(ok);} else {mostrarerror(error);}
        
        //agregar modelo de tabla
        modelo = new TurnosTableModel();
		tablaTurnos = new JTable(modelo);
		scrollPaneParaTabla = new JScrollPane(tablaTurnos);
		this.add(scrollPaneParaTabla);
		
		this.add(sidePanel);
        refresh();
        revalidate();
    	repaint();
        
    }
    

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==reservarButton) {
			User usu = listaUsuarios.get(usuarioComboBox.getSelectedIndex());
			Medico med = listaMedico.get(medicoComboBox.getSelectedIndex());
			String fecha = (String) fechaComboBox.getSelectedItem();
			String hora = (String) horaComboBox.getSelectedItem();
			Integer consultorio = (Integer) consultorioComboBox.getSelectedItem();
			if(verificarValidezDeTurno(consultorio,fecha,hora,med,usu)) {
				//guardar turno
				try {
					Turno tur= new Turno(usu.getDni(), med.getLegajoMedico(), fecha, hora, consultorio,generarIdNuevo());
					this.servicioturno.crearObjeto(tur);
				} catch (Exception e2) {
					e2.printStackTrace();
					mostrarerror("error base de datos al cargar turno");
				}
			}
			else {
				System.out.println("turno no valido");
			}
			refresh();
		}
		if (e.getSource()==eliminarButton) {
			int filaSeleccionada = this.tablaTurnos.getSelectedRow();
			if(filaSeleccionada != -1) {
				int x = (int) this.modelo.getValueAt(filaSeleccionada,5);
				
			
				this.modelo.getContenido().remove(filaSeleccionada);
			
				modelo.fireTableDataChanged();
			
				try {
					this.servicioturno.borraObjeto(x);
					System.out.println(x+"fue borrado (dato legajo)");
					mostrarerror(ok);
				} catch (ServiceException e1) {
				// TODO Auto-generated catch block
					mostrarerror("la base de datos fallo");
					e1.printStackTrace();
				}
			}else {System.out.println("no se selecciono ninguna fila");}
			refresh();
		}
		
		
	}
	
	
	
	
	
	

	
	
//refrescar tabla
	public void refresh() {
		List<Turno> lista;
		try {
			lista = this.servicioturno.listaTodosLosObjetos();
			for (Turno elem: lista) {
				System.out.println(elem.toString());
			}
			System.out.println(lista);
			modelo.setContenido(lista);
			modelo.fireTableDataChanged();
			
			revalidate();
			repaint();
			
		} catch (DAOException e1) {
			e1.printStackTrace();
			System.out.println("error al cargar medicos en servicio");
			mostrarerror("ERROR Base de datos");
		} 
	}
	
	//display de errores
	public void mostrarerror(String x) {
		if(x==this.ok) {
			errorDisplay.setForeground(Color.green);
			errorDisplay.setSize(100,20);
		}
		else {
			errorDisplay.setForeground(Color.RED);
			errorDisplay.setSize(800,50);
		}
		
		errorDisplay.setText(x);
		errorDisplay.validate();
		errorDisplay.repaint();
		System.out.println(x);
		
	}
	
//verificar que el turno sea valido antes de cargar
	public boolean verificarValidezDeTurno(int consultorio, String fecha, String Hora,Medico med,User usu) {
		List<Turno> lista = null;
		if(med.getLegajoMedico()==usu.getDni()) {
			mostrarerror("el medico y el paciente no pueden ser iguales");
			return false;}
		try {
			lista = this.servicioturno.listaTodosLosObjetos();
		} catch (DAOException e) {
			mostrarerror("error al cargar turnos");
			e.printStackTrace();
			return false;
		}
		String error="";
		Integer veriflvl=0;
		boolean loc1=verificarDisponibilidadConsultorio(lista, fecha,consultorio);
		boolean loc2=verificarQueElMedicoNoEsteEnOtroConsultorio(lista, fecha, consultorio, med.getLegajoMedico());
		boolean loc3=elMedicoEstaEnElConsultorio(lista, fecha, consultorio, med.getLegajoMedico());
		boolean disp1=verificarQueElMedicoEsteDisponible(lista, fecha, Hora, med);
		boolean disp2=verificarQueElUsuarioEsteDisponible(lista, fecha, Hora, usu);
		
		
		System.out.println("consultorio libre>"+loc1);
		System.out.println("medico no esta en otro consultorio>"+loc2);
		System.out.println("medico en cosultorio>"+loc3);
		System.out.println("med disp>"+disp1);
		System.out.println("pax disp>"+disp2);
		//loc es de localizacion
		if((loc1&&loc2) || (loc2&&loc3))//si el consultorio esta libre y el medico no esta en ningun otro consultorio
										//o si el medico no esta en otro consultorio y el medico esta en el consultorio
			{	
			veriflvl=1;
			System.out.println("localizacion correcta");
			if(disp1&&disp2) {
				System.out.println("disponibilidad de ambas partes");
				mostrarerror(ok);
				return true;
			}		
		}
		//seccion de error
		if(veriflvl==0) {
			if(!loc1) 
			{error=error+"consultorio ocupado, ";}
			if(!loc2) 
			{error=error+"medico en otro consultorio";}
		}
		if(!disp1)
		{error=error+"medico ocupado, ";}
		if(!disp2)
		{error=error+"paciente ocupado";}
		mostrarerror(error);
		//no se pudo validar
		return false;
	}
	
	public boolean verificarQueElMedicoEsteDisponible(List<Turno> lista, String fecha, String Hora,Medico med) {
		for(Turno turn :lista) {
			if(turn.getFecha().equals(fecha) && turn.getHora().equals(Hora) && turn.getLegajoMedico()==med.getLegajoMedico() ) { 
				return false;
		}
		}
		return true;
	}
	public boolean verificarQueElUsuarioEsteDisponible(List<Turno> lista, String fecha, String Hora,User use) {
		for(Turno turn :lista) {
			if(turn.getFecha().equals(fecha) && turn.getHora().equals(Hora) && turn.getDniPaciente()==use.getDni() ) { 
					return false;
			}
		}
		return true;
	}
	
	public boolean verificarDisponibilidadConsultorio(List<Turno> lista, String fecha, int consultorio) {
		for(Turno turn :lista) {
			if(turn.getFecha().equals(fecha)) {
				if(turn.getNroConsultorio()==consultorio) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean elMedicoEstaEnElConsultorio(List<Turno> lista, String fecha, int consultorio, int legajo) {
		for(Turno turn :lista) {
			if (turn.getLegajoMedico()==legajo) {
				if(turn.getFecha().equals(fecha)) {
					if(turn.getNroConsultorio()==consultorio) {
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean verificarQueElMedicoNoEsteEnOtroConsultorio(List<Turno> lista, String fecha, int consultorio, int legajo) {
		for(Turno turn :lista) {
			if (turn.getLegajoMedico()==legajo) {
				if(turn.getFecha().equals(fecha)) {
					if(turn.getNroConsultorio()!=consultorio) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
public int generarIdNuevo() throws DAOException {
	int aux=0;
	List<Turno> x =this.servicioturno.listaTodosLosObjetos();
	for(Turno turn : x)
	{
		if(turn.getIDTurno()>aux) {aux=turn.getIDTurno();}
	}
	return aux+1;
}
	
    
}
