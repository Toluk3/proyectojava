package service;
import dao.*;
import entidad.Turno;

public class TurnoService extends AbstractService<Turno, BaseDAOinterface<Turno>> {

	public TurnoService() {
		super(new TurnoDAOH2impl());
	    }


	
	
	
}
