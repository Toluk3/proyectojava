package service;
import dao.BaseDAOinterface;
import dao.MedicoDaoH2impl;
import entidad.Medico;

public class MedicoService extends AbstractService<Medico, BaseDAOinterface<Medico>>{
	public MedicoService() {
		super(new MedicoDaoH2impl());
	    }

}


