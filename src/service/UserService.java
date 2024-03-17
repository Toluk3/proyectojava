package service;
import dao.UserDaoH2impl;
import dao.BaseDAOinterface;
import entidad.User;

public class UserService extends AbstractService<User, BaseDAOinterface<User>> {

public UserService() {
		super(new UserDaoH2impl());
	    }

}
