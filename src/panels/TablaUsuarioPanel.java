package panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import dao.DAOException;
import service.*;
import tableModels.UsuarioTableModel;
import entidad.*;

@SuppressWarnings({ "serial", "unused" })
public class TablaUsuarioPanel extends AbstractCRUDpanel implements ActionListener {

	private JTable tablaMedicos;
	private UsuarioTableModel modelo;

	private JScrollPane scrollPaneParaTabla;
	private UserService servicio;
	
	//Campos
	private JTextField campoDni;
	private JTextField campoPass;
	private JTextField campoNYA;
	private JTextField campoFechaNac;
	private JTextField campoObra;

	public TablaUsuarioPanel() {
		this.servicio= new UserService();
		armarPanel();
		this.ok="sin errores!";
		refresh();
	}
	
	
	private void armarPanel() {
		//dni
		this.campoDni = new JTextField();
		JPanel comboCampoDni = new JPanel();
		agregarCampoCarga("DNI:",this.campoDni);
		//pass
		this.campoPass = new JTextField();
		agregarCampoCarga("pass:",this.campoPass);
		
		//nombre completo
		this.campoNYA = new JTextField();
		agregarCampoCarga("Nombre Completo:",this.campoNYA);

		//campoFechaNac
		this.campoFechaNac = new JTextField();
		agregarCampoCarga("fecha nac:",this.campoFechaNac);
		//obra social
		this.campoObra = new JTextField();
		agregarCampoCarga("obra social:",this.campoObra);
		
		//tabla usuarios
		modelo = new UsuarioTableModel();
		tablaMedicos = new JTable(modelo);
		scrollPaneParaTabla = new JScrollPane(tablaMedicos);
		this.add(scrollPaneParaTabla);
			
		
	}
		@Override
		public void agregar() {
			System.out.println("iniciando validacion de carga");
			
			try {
				//seteo de verificadores
				
				String error = "Error: ";
				int dni=-1;
				
				boolean verif= true;
					//validacion de numeros
					try {
						dni = Integer.parseInt( this.campoDni.getText());
					} catch (Exception e2) {
						verif = false;
						error=error+"dni no valido,";
					}
						//cargar demas datos a variables
					String pass = this.campoPass.getText();
					String nYA = this.campoNYA.getText();
					String fechaNac = this.campoFechaNac.getText();
					String obra = this.campoObra.getText();
					if (obra.length()==0) {
						obra="sin obra";
					}
						//validar
					if(!validarStr(nYA)) {error=error+"nombre no valido,";verif=false;}
					if(!validarStr(obra)) {error=error+"obra no valida,";verif=false;}
					if(!validarNac(fechaNac)) {error=error+"fecha no valida (dd/mm/yyyy),";verif=false;}
					
						
					//si todo esta validado correctamente se ejecuta la carga
					if(verif) {
					//medico
						User x1 = new User(dni, pass, nYA.toLowerCase(), fechaNac, obra.toLowerCase(), 3);
					//cargar datos en tabla
						modelo.getContenido().add(x1);//TODO: cambiar el metodo de obtencion para que se parezca al de refresh
						modelo.fireTableDataChanged();
					//persistir en DB
						this.servicio.crearObjeto(x1);
						refresh();
						mostrarerror(ok);
						}
						else {
							mostrarerror(error);
						}
		
				
			} catch (DAOException e2) {
				
				e2.printStackTrace();
				System.out.println("error de carga");
				mostrarerror("ERROR de carga"); 
				
			}
		}
			
		@Override
		public void borrar() {
			
			int filaSeleccionada = this.tablaMedicos.getSelectedRow();
			if(filaSeleccionada != -1) {
				int x = (int) this.modelo.getValueAt(filaSeleccionada,0);
				System.out.println(x+"fue borrado (dato legajo)");
			
				this.modelo.getContenido().remove(filaSeleccionada);
			
				modelo.fireTableDataChanged();
			
				try {
					
					if (this.servicio.muestraObjeto(x).getTipo_usuario()==2) {
						MedicoService serv2 = new MedicoService();
						serv2.borraObjeto(x);
					}
					this.servicio.borraObjeto(x);
				} catch (DAOException | ServiceException e1) {
				// TODO Auto-generated catch block
				
					e1.printStackTrace();
				}
			}else {System.out.println("no se selecciono ninguna fila");}
			
		} 
	
	

	@Override
	public void refresh() {
		List<User> lista;
		try {
			lista = this.servicio.listaTodosLosObjetos();
			System.out.println(lista);
			modelo.setContenido(lista);
			modelo.fireTableDataChanged();
			mostrarerror(ok);
			
		} catch (DAOException e1) {
			e1.printStackTrace();
			System.out.println("error al cargar medicos en servicio");
			mostrarerror("ERROR Base de datos");
		} 
	}
// validadores auxiliares------------------------------------------------------------------------------------------------	
	public boolean validarNum(int x) {
		if (x>0 && x<2000000000) {
			return true;
			
		}
		else {
			return false;
		}

		
	}
	public static boolean validarStr(String cadena) {
	    
		if (cadena.length()==0) {return false;}
	    
		for (int i = 0; i < cadena.length(); i++) {
	        char caracter = cadena.charAt(i);
	        if (!Character.isLetter(caracter) ) {
	        	if(caracter != ',') {
	        		if(caracter != ' ') {
			        	return false;}
	        	}
	        	
	        }
	        
	    }
	    
	    return true;
	}
	
	public boolean validarNac(String nac) {
		if (nac.length()==10) {
			String[] nac2=nac.split("/");
			try {
				int dia = Integer.parseInt(nac2[0]);
				int mes = Integer.parseInt(nac2[1]);
				int anio = Integer.parseInt(nac2[2]);
				if (dia>0 && dia<32 && mes>0 && mes<13 && anio>1900 && anio<2023) {
					return true;
				}
			} catch (Exception e) {
				return false;
			}
			
		}
			
			return false;
	} 
	
	
	
	

	
	
	

	
	
	
}