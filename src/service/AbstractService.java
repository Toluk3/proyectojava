package service;
import java.util.List;

import dao.*;

public abstract class AbstractService<T,DAO extends BaseDAOinterface<T>> implements BaseDAOinterface<T> {
		protected DAO dao;
	
		public AbstractService(DAO dao) {
			this.dao=dao;
		}
		public void crearObjeto(T x) throws DAOException {

			try {
	            dao.crearObjeto(x);
	        } catch (ObjectoDuplicadoException | DAOException e) {
	            throw new DAOException(e);
	        }

		}
		public void actualizaObjeto(T x) throws DAOException {

			try {
				
				dao.actualizaObjeto(x);
				
	        }
			catch (DAOException e) {
				
	            e.printStackTrace();
	            throw new DAOException(e.getMessage());
	        }


		}

		public T muestraObjeto(int dni) throws DAOException {

			try {

				return dao.muestraObjeto(dni);

	        }
			catch (DAOException e) {

	            e.printStackTrace();
	            throw new DAOException(e.getMessage());
	        }

		}
		public List<T> listaTodosLosObjetos() throws DAOException{
			try {
				return dao.listaTodosLosObjetos();


			}catch(DAOException e){
				e.printStackTrace();
				throw new DAOException(e.getMessage());
			}
		}
		public void borraObjeto(int x) throws ServiceException {
		try {
			dao.borraObjeto(x);

		}catch(DAOException e) {
			e.printStackTrace();

			throw new ServiceException(e.getMessage());
								}
		}
	
}
