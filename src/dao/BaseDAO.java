package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public abstract class BaseDAO<T> implements BaseDAOinterface<T>{
Date d= new Date(0);
	
	protected abstract String insertSqlString(T entidad); // para la funcion de abajo convierte objeto en sql
	public void crearObjeto(T x) throws ObjectoDuplicadoException, DAOException {
        Connection c = DBManager.connect();
        
	        try {
	            Statement s = c.createStatement();
	            String sql = insertSqlString(x);
	            s.executeUpdate(sql);
	            c.commit();
	        } catch (SQLException e) {
	            try {
	            	if(e.getErrorCode() == 23505) {throw new ObjectoDuplicadoException(e.getMessage()+"(DAO> entrada duplicada)");
	            	}
	                c.rollback();
	                e.printStackTrace();
	                if(e.getErrorCode() != 23505) {throw new DAOException(e.getMessage()+"(DAO> ERROR AL CARGAR entidad)");}
	                
	                
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	                throw new DAOException(e1.getMessage());
	            }
	            
	        } finally {
	            
	        	try {
	                c.close();
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	                throw new DAOException(e1.getMessage()+"(DAO>LA CONEXION CON LA BASE DE DATOS NO FUE CERRADA CORRECTAMENTE)");
	                
	            }
	      }
	        
		
	}

	protected abstract String updateSqlString(T entidad);
	
	public void actualizaObjeto(T x) throws DAOException {
  
        Connection c = DBManager.connect();
	        try {
	            Statement s = c.createStatement();
	            String sql = updateSqlString(x);
	            s.executeUpdate(sql);
	            c.commit();
	        } catch (SQLException e) {
	            try {
	                e.printStackTrace();
	                c.rollback();
	                throw new DAOException(e.getMessage());
	            } catch (SQLException e1) {
	                e.printStackTrace();
	                throw new DAOException(e1.getMessage());
	            }
	        } finally {
	            try {
	                c.close();
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	                throw new DAOException(e1.getMessage()+"(DAO>LA CONEXION CON LA BASE DE DATOS NO FUE CERRADA CORRECTAMENTE)");
	            }
	      }
		
	}

	protected abstract String BusquedaSimpleSqlString(int t);
	protected abstract T resultsetToObject(ResultSet rs) throws SQLException;
	public T muestraObjeto(int x) throws DAOException {
		String sql = BusquedaSimpleSqlString(x);
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            
            return resultsetToObject(rs);

        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
                throw new DAOException(e.getMessage());
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new DAOException(e1.getMessage());
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new DAOException(e1.getMessage()+"(DAO>LA CONEXION CON LA BASE DE DATOS NO FUE CERRADA CORRECTAMENTE)");
            }
        }
        
	}
	
	protected abstract List<T> rsToList(ResultSet rs) throws SQLException;
	protected abstract String listaStringSql();
	public List<T> listaTodosLosObjetos() throws DAOException {
        String sql = listaStringSql();
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            
            return rsToList(rs);
            
        } catch (SQLException e) {
            try {
                c.rollback();
                throw new DAOException(e.getMessage());
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new DAOException(e1.getMessage());
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new DAOException(e1.getMessage()+"(DAO>LA CONEXION CON LA BASE DE DATOS NO FUE CERRADA CORRECTAMENTE)");
            }
        }
        
	}

	
	
	protected abstract String BorrarStringSql(int x);
	
	public void borraObjeto(int x) throws DAOException {
		String sql = BorrarStringSql(x);
		Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            s.executeUpdate(sql);
            c.commit();
        } 
        catch (SQLException e) {
            
        	try {
                c.rollback();
            } catch (SQLException ex) {
            	ex.printStackTrace();
            	throw new DAOException(ex.getMessage());
            }
            throw new DAOException(e.getMessage());
        }
        
        
        try {
            c.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
            throw new DAOException(e1.getMessage()+"(DAO>LA CONEXION CON LA BASE DE DATOS NO FUE CERRADA CORRECTAMENTE)");
            }
            
        
    }
}
