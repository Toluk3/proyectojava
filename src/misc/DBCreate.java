package misc;

import java.sql.Connection;


import java.sql.SQLException;
import java.sql.Statement;

import dao.DBManager;

public class DBCreate {
		
	public void crearTablaUsuario() throws SQLException {
		try {
		Connection c = DBManager.connect();
        Statement s = c.createStatement();
        String sql2 = "drop table users1";
        String sql = "CREATE TABLE USERS1"
        		+ "    (dni int not null,"
        		+ "    pass varchar(50) NOT NULL,"
        		+ "    nya varchar(60) NOT NULL,"
        		+ "    fecha_nac varchar(11) NOT NULL,"
        		+ "    obra varchar(30) NULL,"
        		+ "    tipo_user int NOT NULL,"
        		+ "		PRIMARY KEY (dni))";
        
        s.executeUpdate(sql2);
        s.executeUpdate(sql);
        
        
        c.commit();
        
        
		}catch(SQLException e){
			
			System.out.println("no se creo la tabla:"+e.getMessage());
			
			
		}
	}
	
	public void crearTablaMedicos() throws SQLException {
		try {
		Connection c = DBManager.connect();
        Statement s = c.createStatement();
        //String sql = "drop table MEDICOS";
        String sql2 = "CREATE TABLE MEDICOS"
        		+ "    (legajomedico int not null,"
        		+ "     obras varchar(100) NOT NULL,"
        		+ "     costo int NOT NULL,"
        		+ "		PRIMARY KEY (legajomedico))";
        
        //s.executeUpdate(sql);
        s.executeUpdate(sql2);
        c.commit();
        
        //legajomedico,obras,costo
		}catch(SQLException e){
			
			System.out.println("no se creo la tabla:"+e.getMessage());
			
			
		}
	}
	
		
		public void cargarAdmin() throws SQLException {
			try {
			Connection c = DBManager.connect();
	        Statement s = c.createStatement();
	        String sql = "insert into USERS1 (dni,pass,nya,fecha_nac,obra,tipo_user) values ('1','admin','admin','00/00/0000','TEST','1')";
	        
	        s.executeUpdate(sql);
	        c.commit();
	        
	        
			}catch(SQLException e){
				
				System.out.println("no se cargo admin:"+e.getMessage());
				
			}
			
	}
		public void crearTablaTurnos() throws SQLException {
			try {
				//dnipaciente,legajomedico,fecha,hora,nroconsultorio,idturno
			Connection c = DBManager.connect();
	        Statement s = c.createStatement();
	        String sql2 = "drop TABLE turnos";
	        String sql = "CREATE TABLE turnos"
	        		+ "    (dnipaciente int not null,"
	        		+ "    legajomedico int NOT NULL,"
	        		+ "    fecha varchar(50) NOT NULL,"
	        		+ "    hora varchar(60) NOT NULL,"
	        		+ "    nroconsultorio int NOT NULL,"
	        		+ "    idturno int NOT NULL,"
	        		+ "		PRIMARY KEY (idturno))";
	        		
	        s.executeUpdate(sql2);
	        s.executeUpdate(sql);
	        c.commit();
	        
	        
			}catch(SQLException e){
				
				System.out.println("no se creo la tabla:"+e.getMessage());
				
				
			}
		}
		
		public void eliminarTablaTurno() throws SQLException {
			try {
				//dnipaciente,legajomedico,fecha,hora,nroconsultorio,idturno
			Connection c = DBManager.connect();
	        Statement s = c.createStatement();
	        
	        String sql = "drop table turnos";
	        		
	        
	        s.executeUpdate(sql);
	        c.commit();
	        
	        
			}catch(SQLException e){
				
				System.out.println("no se creo la tabla:"+e.getMessage());
				
				
			}
		}
		
		
		
		
		
}
