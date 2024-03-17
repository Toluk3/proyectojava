package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidad.Turno;


public class TurnoDAOH2impl extends BaseDAO<Turno> {

	@Override
	protected String insertSqlString(Turno UnTurno) {
		int dniPaciente=UnTurno.getDniPaciente();
		int legajoMedico=UnTurno.getLegajoMedico();
		String fecha = UnTurno.getFecha();
		String hora=UnTurno.getHora();
		int nroConsultorio=UnTurno.getNroConsultorio();
		int iDTurno=UnTurno.getIDTurno();
		return ("INSERT INTO TURNOS (dnipaciente,legajomedico,fecha,hora,nroconsultorio,idturno) VALUES ('" 
				+ dniPaciente + "', '" + legajoMedico + "', '" + fecha + "', '"+hora+"', '"+nroConsultorio+"', '"+iDTurno+"')");
	}
	@Override
	protected String updateSqlString(Turno UnTurno) {
		int dniPaciente=UnTurno.getDniPaciente();
		int legajoMedico=UnTurno.getLegajoMedico();
		String fecha = UnTurno.getFecha();
		String hora=UnTurno.getHora();
		int nroConsultorio=UnTurno.getNroConsultorio();
		int iDTurno=UnTurno.getIDTurno();
		String str="UPDATE TURNOS SET dnipaciente = '" + dniPaciente 
				+ "', legajomedico = '" + legajoMedico 
				+ "', fecha = '" + fecha 
				+ "', hora = '" + hora 
				+ "', nroconsultorio = '" + nroConsultorio 
				+ "' WHERE idturno = '" + iDTurno + "'";

		return str;
	}
	@Override
	protected String BusquedaSimpleSqlString(int iDTurno) {
		return "SELECT * FROM turnos WHERE idturno = '" + iDTurno + "'";
	}
	@Override
	protected Turno resultsetToObject(ResultSet rs) throws SQLException {
		if (rs.next()) {
			int iDTurno=rs.getInt("idturno");
			int dniPaciente=rs.getInt("dnipaciente");
    		int legajoMedico=rs.getInt("legajomedico");
    		String fecha = rs.getString("fecha");
    		String hora= rs.getString("hora");
    		int nroConsultorio=rs.getInt("nroconsultorio");
        Turno u = new Turno(dniPaciente, legajoMedico, fecha, hora,nroConsultorio,iDTurno);
        return u;}
		else {return null;}
	}
	
	@Override
	protected List<Turno> rsToList(ResultSet rs) throws SQLException{
		List<Turno> resultado = new ArrayList<>();
        while (rs.next()) {
        	int dniPaciente=rs.getInt("dnipaciente");
    		int legajoMedico=rs.getInt("legajomedico");
    		String fecha = rs.getString("fecha");
    		String hora= rs.getString("hora");
    		int nroConsultorio=rs.getInt("nroconsultorio");
    		int iDTurno=rs.getInt("idturno");
            Turno u = new Turno(dniPaciente, legajoMedico, fecha, hora,nroConsultorio,iDTurno);
            resultado.add(u);
        }   
		return resultado;	
	}
	@Override
	protected String listaStringSql() {
		return "SELECT * FROM turnos";
	}
	@Override
	protected String BorrarStringSql(int iDTurno) {
		return "DELETE FROM turnos WHERE idturno = '" + iDTurno + "'";
	}
}
	/*
	

	}

	

	}

	@Override
	public List<Turno> listaTodosLosTurnos() throws DAOException {
		List<Turno> resultado = new ArrayList<>();
        String sql = "SELECT * FROM turnos";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            
            while (rs.next()) {
            	int dniPaciente=rs.getInt("dnipaciente");
        		int legajoMedico=rs.getInt("legajomedico");
        		String fecha = rs.getString("fecha");
        		String hora= rs.getString("hora");
        		int nroConsultorio=rs.getInt("nroconsultorio");
        		int iDTurno=rs.getInt("idturno");

                Turno u = new Turno(dniPaciente, legajoMedico, fecha, hora,nroConsultorio,iDTurno);
                
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
	public void borraTurno(int iDTurno) throws DAOException {
		String sql = "DELETE FROM turnos WHERE idturno = '" + iDTurno + "'";
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
	public List<Turno> listaTodosLosTurnosMedico(int legajo) throws DAOException {
		List<Turno> resultado = new ArrayList<>();
        String sql = "SELECT * FROM turnos where legajomedico= '"+legajo + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            
            while (rs.next()) {
            	int dniPaciente=rs.getInt("dnipaciente");
        		int legajoMedico=rs.getInt("legajomedico");
        		String fecha = rs.getString("fecha");
        		String hora= rs.getString("hora");
        		int nroConsultorio=rs.getInt("nroconsultorio");
        		int iDTurno=rs.getInt("idturno");

                Turno u = new Turno(dniPaciente, legajoMedico, fecha, hora,nroConsultorio,iDTurno);
                
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
	
	
	public List<Turno> listaTodosLosTurnosPaciente(int dnipac) throws DAOException {
		List<Turno> resultado = new ArrayList<>();
        String sql = "SELECT * FROM turnos where dnipaciente= '"+dnipac + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            
            while (rs.next()) {
            	int dniPaciente=rs.getInt("dnipaciente");
        		int legajoMedico=rs.getInt("legajomedico");
        		String fecha = rs.getString("fecha");
        		String hora= rs.getString("hora");
        		int nroConsultorio=rs.getInt("nroconsultorio");
        		int iDTurno=rs.getInt("idturno");

                Turno u = new Turno(dniPaciente, legajoMedico, fecha, hora,nroConsultorio,iDTurno);
                
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
	
	
	public void borraTurnosPac(int dni) throws DAOException {
		String sql = "DELETE FROM turnos WHERE dnipaciente = '" + dni + "'";
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
	
	public void borraTurnosMed(int leg) throws DAOException {
		String sql = "DELETE FROM turnos WHERE legajomedico = '" + leg + "'";
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
