package gui;
import javax.swing.*;

import service.UserService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.color.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entidad.*;
import panels.*;
@SuppressWarnings("unused")
public class PanelManager implements ActionListener{
	//generalidades
	private JFrame frame;
	private JPanel nav;
	private JPanel vista;
	
	//conexion con usuario
	private User sesionUser;
	private UserService servicioUser;
	
	
	//menus
	JButton verMenu;
	JButton reporte1;
	JButton pacienteButton;
	JButton medicoButton;
	JButton administrarTurnobutton;
	//usuarios normales
	JButton consultarTurnosButton;
	//login
	private JPanel loginPanel;
		//Labels
	Label dniLabel;
	Label passLabel;
	Label errorLabel;
		//campos de texto
	private JTextField dniText;
	private JTextField passText;
		//boton login
	JButton loginbutton;
	JButton consultarGananciasTotales;
	private JButton logout;
	private JButton consultarTurnosPropiosButton;
	private JButton consultarTurnosDePacientesButton;
	 
	PanelManager(){
		this.frame = new JFrame("ejemplo hospital xyz");
	}
	
    private void createAndShowLogin() {
    	//init datos
    	this.servicioUser= new UserService();
    	
    	
    	
    	//init frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.setSize(1000,500);
        // panel de navegacion
    	this.nav= new JPanel();
        nav.setBackground(Color.lightGray);
        frame.add(nav,BorderLayout.NORTH);
        
        
        // panel central
        this.vista= new JPanel();
        this.vista.setBackground(new Color(0,91,234));
        
        frame.add(vista,BorderLayout.CENTER);
        
        showLogin();
        
        
        
        
    }
    //inicializar
    public void executeGUI() {
        createAndShowLogin();
    }
    
    public void showLogin() {
    	
    	//init de login
    	this.loginPanel = new JPanel();
    	loginPanel.setLayout(new GridLayout(3,2));
    	loginPanel.setSize(1000,800);
    	
    	//init de labels
    	this.dniLabel= new Label("DNI:");
    	this.passLabel= new Label("CONTRASEÑA:");
    	this.errorLabel= new Label("System OK!");
    	errorLabel.setForeground(Color.GREEN);
    	errorLabel.setBackground(Color.BLACK);
    	
    	//init textfield
    	this.dniText= new JTextField();
    	this.passText= new JTextField();
    	
    	//init de boton
    	this.loginbutton= new JButton("LOGIN");
    	loginbutton.addActionListener(this);
    	
    	//cargar en panel login
    	loginPanel.add(dniLabel);
    	loginPanel.add(dniText);
    	loginPanel.add(passLabel);
    	loginPanel.add(passText); 
    	loginPanel.add(errorLabel);
    	loginPanel.add(loginbutton);
    	
    	//agregar a vista
    	this.vista.add(loginPanel);
    	vista.revalidate();
    	vista.repaint();
    	
    }
    
    public void activarNav() {
    	
    	this.verMenu= new JButton("MENU");
    	verMenu.addActionListener(this);
    	nav.add(verMenu);
    	this.logout= new JButton("LogOut");
    	logout.addActionListener(this);
    	nav.add(logout);
    	nav.add(new Label("                  bienvenido: "+sesionUser.getNya()));
    	nav.revalidate();
    	nav.repaint();
    }
    
    public void mostrar(JPanel panel) {
    	vista.removeAll();
    	vista.add(panel);
    	vista.validate();
    	vista.repaint();
    }
    
    
    
    private void menu() {
    	vista.removeAll();
    	int tipo = sesionUser.getTipo_usuario();
    	if(tipo==1) {
    	this.reporte1= new JButton("Reporte Ganancia/Medico");
    	reporte1.addActionListener(this);
    	this.medicoButton= new JButton("Cargar Medico");
    	medicoButton.addActionListener(this);
    	this.pacienteButton= new JButton("Cargar Usuario/Paciente");
    	pacienteButton.addActionListener(this);
    	this.administrarTurnobutton= new JButton("Administrar turnos");
    	administrarTurnobutton.addActionListener(this);
    	this.consultarGananciasTotales= new JButton("Consultar Ganancias");
    	consultarGananciasTotales.addActionListener(this);
    	this.consultarTurnosButton= new JButton("Consultar Turnos");
    	consultarTurnosButton.addActionListener(this);
    	vista.add(reporte1);
    	vista.add(medicoButton);
    	vista.add(pacienteButton);
    	vista.add(administrarTurnobutton);
    	vista.add(consultarGananciasTotales);
	    vista.add(consultarTurnosButton);
    	}
    	if(sesionUser.getTipo_usuario()==2) {
    		this.consultarTurnosDePacientesButton= new JButton("Consultar Turnos pacientes");
    		consultarTurnosDePacientesButton.addActionListener(this);
        	vista.add(consultarTurnosDePacientesButton);
    	}
    	
    	this.consultarTurnosPropiosButton= new JButton("Consultar Turnos propios");
    	consultarTurnosPropiosButton.addActionListener(this);
        vista.add(consultarTurnosPropiosButton);
        
    	vista.validate();
    	vista.repaint();
    	
    }
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == verMenu) {
			menu();
		} 
		else if(e.getSource() == pacienteButton) {
			mostrar(new TablaUsuarioPanel());
		}
		else if(e.getSource() == consultarGananciasTotales) {
			mostrar(new ReporteMedicosEntreFechas());
		}
		else if(e.getSource() == logout) {
			this.vista.removeAll();
			this.nav.removeAll();
			executeGUI();
		
		}
		else if(e.getSource() == consultarTurnosDePacientesButton) {
			mostrar(new ListaDeConsultas(sesionUser,2));
		}
		else if(e.getSource() == consultarTurnosPropiosButton) {
			mostrar(new ListaDeConsultas(sesionUser,1));
		}
		
		else if(e.getSource() == consultarTurnosButton) {
			mostrar(new ListaDeConsultas(sesionUser,3));
		}
		else if(e.getSource() == medicoButton) {
			mostrar(new TablaMedicoPanel());
		}
		else if(e.getSource() == administrarTurnobutton) {
			
			mostrar(new PanelAdministrarTurnos());
		}
		else if(e.getSource() == reporte1) {
			
			mostrar(new ReporteGananciaMedico());
		}
		//login
		else if(e.getSource() == loginbutton) {
			//todo manejar exepcion por parseint
			try {
				int dni = Integer.parseInt( this.dniText.getText());
			
			
			System.out.println("logeando");
			try {
				this.sesionUser= servicioUser.muestraObjeto(dni);
				System.out.println("usuario+pass");
				System.out.println(sesionUser.getDni());
				System.out.println(sesionUser.getPass());

				if (this.sesionUser.getPass().equals(passText.getText())) {
					System.out.println("contraseña ok");
					menu();
					activarNav();
				}
				else {
					System.out.println("contra incorrecta");
					System.out.println(sesionUser.getPass());
					System.out.println(passText.getText());
					mostrarerror("Contraseña incorrecta");
					
				}

			} catch (Exception e2) {
				mostrarerror("usuario no existe");
				// TODO: handle exception
			}
			} catch (Exception e2) {
				mostrarerror("DNI no valido");
			}
			
		}
		
	}

	public void mostrarerror(String x) {
		
		this.errorLabel.setForeground(Color.red);
		this.errorLabel.setText(x);
		this.errorLabel.validate();
		this.errorLabel.repaint();
		this.vista.validate();
		this.vista.repaint();
		System.out.println(x);
		
	}
	

    
    
    
}