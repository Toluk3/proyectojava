package entidad;

public class Turno {
	private int dniPaciente;
	private int legajoMedico;
	private String fecha;
	private String hora;
	private int nroConsultorio;
	private int iDTurno;
	
	public Turno(int dniPaciente, int legajoMedico, String fecha, String hora, int nroConsultorio, int iDTurno) {
		this.dniPaciente = dniPaciente;
		this.legajoMedico = legajoMedico;
		this.fecha = fecha;
		this.hora = hora;
		this.nroConsultorio = nroConsultorio;
		this.iDTurno = iDTurno;
	}
	
	
	public int getLegajoMedico() {
		return legajoMedico;
	}
	public void setLegajoMedico(int legajoMedico) {
		this.legajoMedico = legajoMedico;
	}
	public int getDniPaciente() {
		return dniPaciente;
	}
	public void setDniPaciente(int dniPaciente) {
		this.dniPaciente = dniPaciente;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public int getNroConsultorio() {
		return nroConsultorio;
	}
	public void setNroConsultorio(int nroConsultorio) {
		this.nroConsultorio = nroConsultorio;
	}
	public int getIDTurno() {
		return iDTurno;
	}
	public void setIDTurno(int iDTurno) {
		this.iDTurno = iDTurno;
	}
	@Override
	public String toString() {
		return "Turno [dniPaciente=" + dniPaciente + ", legajoMedico=" + legajoMedico + ", fecha=" + fecha + ", hora="
				+ hora + ", nroConsultorio=" + nroConsultorio + ", iDTurno=" + iDTurno + "]";
	}
	
	
	
	
	
	
	
	
	
}
