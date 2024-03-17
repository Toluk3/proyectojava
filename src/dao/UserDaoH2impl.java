package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidad.User;


public class UserDaoH2impl extends BaseDAO<User> {
	@Override
	protected String insertSqlString(User UnUser) {
		int dni=UnUser.getDni();
		String pass=UnUser.getPass();
		String nya = UnUser.getNya();
		String fecha_nac=UnUser.getFecha_nac();
		String obra=UnUser.getObra();
		int tipo_user=UnUser.getTipo_usuario();
		return ("INSERT INTO USERS1 (dni,pass,nya,fecha_nac,obra,tipo_user) VALUES ('" 
		+ dni + "', '" + pass + "', '" + nya + "', '"+fecha_nac+"', '"+obra+"', '"+tipo_user+"')");
	}
	@Override
	protected String updateSqlString(User entidad) {
		int dni=entidad.getDni();
		String pass=entidad.getPass();
		String nya = entidad.getNya();
		String fecha_nac=entidad.getFecha_nac();
		String obra=entidad.getObra();
		int tipo_user=entidad.getTipo_usuario();
		return ("UPDATE USERS1 SET pass = '" + pass 
				+ "', nya = '" + nya 
				+ "', fecha_nac = '" + fecha_nac 
				+ "', obra = '" + obra 
				+ "', tipo_user = '" + tipo_user 
				+ "' WHERE dni =" + dni);
	}
	@Override
	protected String BusquedaSimpleSqlString(int t) {
		return "SELECT * FROM USERS1 WHERE dni = '" + t + "'";
	}
	@Override
	protected User resultsetToObject(ResultSet rs) throws SQLException {
		if(rs.next()) {
		int dni = rs.getInt("dni");
        String pass = rs.getString("pass");
        String nya = rs.getString("nya");
        String fecha_nac = rs.getString("fecha_nac");
        String obra = rs.getString("obra");
        int tipo_user = rs.getInt("tipo_user");
        User u = new User(dni, pass, nya, fecha_nac,obra,tipo_user);
        return u;}
		return null;
	}
	@Override
	protected List<User> rsToList(ResultSet rs) throws SQLException{
		List<User> resultado = new ArrayList<>();
        while (rs.next()) {
        	int dni = rs.getInt("dni");
            String pass = rs.getString("pass");
            String nya = rs.getString("nya");
            String fecha_nac = rs.getString("fecha_nac");
            String obra = rs.getString("obra");
            int tipo_user = rs.getInt("tipo_user");
            User u = new User(dni, pass, nya, fecha_nac,obra,tipo_user);
            resultado.add(u);
        }   
		return resultado;	
	}
	@Override
	protected String listaStringSql() {
		return "SELECT * FROM USERS1";
	}
	@Override
	protected String BorrarStringSql(int dni) {
		return "DELETE FROM USERS1 WHERE dni = '" + dni + "'";
	}
}

