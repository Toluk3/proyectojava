package entidad;
import dao.DAOException;
import service.*;

public class Medico extends User{
	private int legajoMedico; //legajo medico es igual al dni usuario
	private String obras;
	private int costo;
	
	
 //TODO:mejorar excepciones
	
	public Medico(int legajoMedico, String obras, int costo)  {
		
        super(legajoMedico, obtenerUsuario(legajoMedico).getPass(), obtenerUsuario(legajoMedico).getNya(), obtenerUsuario(legajoMedico).getFecha_nac(), obtenerUsuario(legajoMedico).getObra(), obtenerUsuario(legajoMedico).getTipo_usuario());
        this.legajoMedico = legajoMedico;
        this.obras = obras;
        this.costo = costo;
    }
	
	public int getLegajoMedico() {
		return legajoMedico;
	}
	public void setLegajoMedico(int legajoMedico) {
		this.legajoMedico = legajoMedico;
	}
	
	public String getObras() {
		return obras;
	}
	public void setObras(String obras) {
		this.obras = obras;
	}
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}
	@Override
	public String toString() {
		return "Medico [legajoMedico=" + legajoMedico + ", obras=" + obras + ", Costo="
				+ costo + "]";
	}
	
	
	

	
	
	private static User obtenerUsuario(int legajoMedico) {
        UserService serv = new UserService();
        try {
			return serv.muestraObjeto(legajoMedico);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			User x = new User(404, "error", "error", "error", "error", 404);
			return x;
		}
    }
	
	
	
}

