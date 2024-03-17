package panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import dao.DAOException;
import service.*;
import tableModels.MedicoTableModel;
import entidad.*;

@SuppressWarnings({ "serial", "unused" })
public class TablaMedicoPanel extends AbstractCRUDpanel implements ActionListener {
	//tabla
	private JTable tablaMedicos;
	private MedicoTableModel modelo;
	private JScrollPane scrollPaneParaTabla;
	//servicio
	private MedicoService servicio;
	//campos
	private JTextField campoLegajo;
	private JTextField campoObras;
	private JTextField campoCostoXConsulta;
	//constructor
	public TablaMedicoPanel() {
		armarPanel();
		this.ok="Correcto!";
		this.servicio= new MedicoService();
		refresh();
	}
	private void armarPanel() {
		//campos
		this.campoLegajo = new JTextField();
		agregarCampoCarga("legajo:", campoLegajo);
		this.campoObras = new JTextField();
		agregarCampoCarga("obras:", campoObras);
		this.campoCostoXConsulta = new JTextField();
		agregarCampoCarga("Costo:", campoCostoXConsulta);
		//tabla
		modelo = new MedicoTableModel();
		tablaMedicos = new JTable(modelo);
		scrollPaneParaTabla = new JScrollPane(tablaMedicos);
		this.add(scrollPaneParaTabla);
	}
	
	

	@Override
	public void agregar() { 
			
			
			try {
				//seteo de verificadores
				
				String error = "Error: ";
				int costo=-1;
				int leg=-1;
				boolean verif= true;
				boolean verifleg= true;
				boolean verifcosto= true;
					//validacion de numeros
					try {
					costo = Integer.parseInt( this.campoCostoXConsulta.getText());
					leg = Integer.parseInt( this.campoLegajo.getText());
					try {
						if(!validarNum(leg)) {error=error+"error de legajo usuario no encontrado";}
						verifleg = (validarNum(leg)&&validarLeg(leg));
					} catch (Exception e2) {
						error=error+"error de legajo usuario no encontrado";
						verifleg = false;
					}
					if(!validarNum(leg)) {error=error+"error de legajo usuario fuera de rango";}
					
					
					
					
					
					
					
				
					verifcosto = validarNum(costo);
					if(!validarNum(costo)) {error=error+" costo fuera de rango,";}
				
					
					} catch (Exception e2) {
					error=error+" solo es posible numeros en leg. y costo,";
					verif= false;
					
					}
						
					//validacion de strings
					String obr = this.campoObras.getText();
					boolean verifnom = validarStr(obr);
					if(!validarStr(obr)) {error=error+"error obras usar a-z o ','";}		
					
					
						
					//si todo esta validado correctamente se ejecuta la carga
					if(verif && verifcosto && verifleg ) {
					//medico
						
						
						Medico x1 = new Medico(leg,obr.toLowerCase(),costo);
					//cambiar permiso de usuario
						UserService servUsu = new UserService(); // recordar que todo esto esta encerrado dentro de un try, en caso de que falle la base de datos
						
						
						
						User usu= servUsu.muestraObjeto(leg);
						if (usu.getTipo_usuario()==3)
							{usu.setTipo_usuario(2);}
						servUsu.actualizaObjeto(usu);
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
				mostrarerror("ERROR EN CARGA");
				// TODO: implementar validacion y ventana emergente
			}
			
			
			
			
			//desplegar ventana aparte para agregar medico work in progress!!!!!!!!!!!
			
		} 
		@Override
		public void borrar()  {
			
			int filaSeleccionada = this.tablaMedicos.getSelectedRow();
			if(filaSeleccionada != -1) {
				int x = (int) this.modelo.getValueAt(filaSeleccionada,1);
				System.out.println(x+"fue borrado (dato legajo)");
			
				this.modelo.getContenido().remove(filaSeleccionada);
			
				modelo.fireTableDataChanged();
			
				try {
					this.servicio.borraObjeto(x);//borro el estatus de medico
					UserService servUsu = new UserService(); 
					User usu= servUsu.muestraObjeto(x);
					if (usu.getTipo_usuario()==2)//remuevo permisos
						{usu.setTipo_usuario(3);
						servUsu.actualizaObjeto(usu);
						}
					
				} catch (DAOException | ServiceException e1) {
				// TODO Auto-generated catch block
				
					e1.printStackTrace();
				}
			}else {System.out.println("no se selecciono ninguna fila");}
			

		
	}

//refrescar lista
	@Override
	public void refresh() {
		List<Medico> lista;
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
	
//seccion de validaciones
	public boolean validarNum(int x) {
		if (x>0 && x<2000000000) {
			return true;
			
		}
		else {
			return false;
		}
	}
	
	public boolean validarStr(String cadena) {
	    
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
	    
	    
	
	public boolean validarLeg(int leg) {
		UserService x= new UserService();
		try {
			if (x.muestraObjeto(leg).getDni()>0) {
				return true;
			}else {
				return true;
			}
		} catch (DAOException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
}
