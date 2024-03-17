package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidad.Medico;

public class MedicoDaoH2impl extends BaseDAO<Medico> {

	@Override
	protected String insertSqlString(Medico entidad) {
		int legajo = entidad.getLegajoMedico();
        String obras = entidad.getObras();
        int costo= entidad.getCosto();
        
		return "INSERT INTO medicos (legajomedico,obras,costo) VALUES ('" + legajo + "', '" + obras + "', '"+costo+"')";
	}

	@Override
	protected String updateSqlString(Medico entidad) {
		int legajo = entidad.getLegajoMedico();
        String obras = entidad.getObras();
        int costo= entidad.getCosto();
		return "UPDATE medicos set (legajomedico,obras,costo) VALUES ('" + legajo + "', '" + obras + "', '"+costo+"')";
	}
/**************************************************************************************************************/
	@Override/**/
	protected String BusquedaSimpleSqlString(int t) {
		
		return "SELECT * FROM medicos WHERE legajoMedico = '" + t + "'";
	}

	@Override
	protected Medico resultsetToObject(ResultSet rs) throws SQLException {
		if (rs.next()) {
        	int leg = rs.getInt("legajoMedico");
            String obras = rs.getString("obras");
            int costo = rs.getInt("costo");
            Medico u = new Medico(leg, obras, costo);
            return u;
        }else {return null;}
		
	}
/**************************************************************************************************************/
	@Override
	protected String listaStringSql() {
		
	       
		return "SELECT * FROM medicos";
	}
	@Override
	protected List<Medico> rsToList(ResultSet rs) throws SQLException {
		List<Medico> resultado = new ArrayList<>();
		while (rs.next()) {
        	int leg = rs.getInt("legajoMedico");
            String obras = rs.getString("obras");
            int costo = rs.getInt("costo");
            Medico u = new Medico(leg, obras, costo);
            resultado.add(u);
        }
		return resultado;
	}

	
	
/**************************************************************************************************************/	
	@Override
	protected String BorrarStringSql(int x) {
		return "DELETE FROM medicos WHERE legajoMedico = '" + x + "'";
	}

	
	
}
/*	




	@Override
	public Medico muestraMedico(int legajoMedico) throws DAOException {
		String sql = "SELECT * FROM medicos WHERE legajoMedico = '" + legajoMedico + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            
            if (rs.next()) {
                int leg = rs.getInt("legajoMedico");
                String obras = rs.getString("obras");
                
                int costo = rs.getInt("costo");
                Medico u = new Medico(leg, obras, costo);
                return u;
            }

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
        return null;
	}

	@Override
	public List<Medico> listaTodosLosMedicos() throws DAOException {
		List<Medico> resultado = new ArrayList<>();
        String sql = "SELECT * FROM medicos";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            
            while (rs.next()) {
            	int leg = rs.getInt("legajoMedico");
                String obras = rs.getString("obras");
                int costo = rs.getInt("costo");
                Medico u = new Medico(leg, obras, costo);
                resultado.add(u);

            }
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
        return resultado;
	}

	
	
	@Override
	public void borraMedico(int legajo) throws DAOException {
		String sql = "DELETE FROM medicos WHERE legajoMedico = '" + legajo + "'";
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
*/
