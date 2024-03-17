package entidad;

public class User {
	
	protected int dni;
	protected String pass;
	protected String nya;
	protected String fecha_nac;
	protected String obra;
	protected int tipo_usuario;
	
	//1 admin, 2 medico, 3 paciente
	
	
	
	
	public User(int dni,String pass,String nya,String fecha_nac,String obra,int tipo){
		this.dni=dni;
		this.pass=pass;
		this.nya=nya;
		this.fecha_nac=fecha_nac;
		this.tipo_usuario=tipo;
		this.obra=obra;
		
	}
	
	
	//geters seters
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getNya() {
		return nya;
	}
	public void setNya(String nya) {
		this.nya = nya;
	}
	public String getFecha_nac() {
		return fecha_nac;
	}
	public void setFecha_nac(String fecha_nac) {
		this.fecha_nac = fecha_nac;
	}
	public String getObra() {
		return obra;
	}
	public void setObra(String obra) {
		this.obra = obra;
	}
	public int getTipo_usuario() {
		return tipo_usuario;
	}
	public void setTipo_usuario(int tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}
	
	
}

/*	tabla sql h2
 * CREATE TABLE USERS1
    (dni int not null,
    pass varchar(50) NOT NULL,
    nya varchar(60) NOT NULL,
    fecha_nac varchar(11) NOT NULL,
    obra varchar(30) NULL,
    tipo_user int NOT NULL)
    
    ADMIN>
    insert into USERS1 (dni,pass,nya,fecha_nac,obra,tipo_user) values ('1','admin','admin','00/00/0000','TEST','1')
 */




