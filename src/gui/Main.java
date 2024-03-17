package gui;




//import java.sql.SQLException;
//import misc.DBCreate;

public class Main {

	
	    public static void main(String[] args)  {//tira exeption
	    	
	    	//cargar y crear tablas aux
	    	
			
	    	
	    	try {
				//DBCreate db=new DBCreate();//para restaurar base de datos
				//db.crearTablaTurnos();
				//db.crearTablaMedicos();
				//db.crearTablaMedicos();
				//db.cargarAdmin();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
	    	
	    	
	        PanelManager menu = new PanelManager();
	        menu.executeGUI();
	        
	        
	        /*
	        Connection c = DBManager.connect();
	        c.close();
	        */
	        
	    }
	
}
//TODO: en dao agregar manaejo de exepcion para cuando la base de datos no esta conectada o no depende