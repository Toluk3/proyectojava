package dao;

import java.util.List;

import service.ServiceException;


public interface BaseDAOinterface<T> {


	
	
	

	void actualizaObjeto(T x) throws DAOException;

	T muestraObjeto(int x) throws DAOException;
	
	List<T> listaTodosLosObjetos() throws DAOException;

	

	void borraObjeto(int x) throws DAOException, ServiceException;



	void crearObjeto(T x) throws ObjectoDuplicadoException, DAOException;
	

	
	
}
