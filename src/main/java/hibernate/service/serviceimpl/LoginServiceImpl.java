package hibernate.service.serviceimpl;

import hibernate.dao.dao.LoginDao;
import hibernate.dao.daoimpl.LoginDaoImpl;
import hibernate.entities.Login;
import hibernate.service.service.LoginService;

import java.util.List;

public class LoginServiceImpl implements LoginService {
    private LoginDao dao;
    public LoginServiceImpl() {
        this.dao = new LoginDaoImpl();
    }

    @Override
    public List<Login> getAllLogin() {
        return dao.getAllLogin();
    }

    @Override
    public Login getLoginByName(String name) {
        return dao.getLoginByName(name);
    }

    @Override
    public Login getLoginByPerson(String person) {
        return dao.getLoginByPerson(person);
    }

    @Override
    public Login getLoginById(int id) {
        return dao.getLoginById(id);
    }

    @Override
    public int validateLogin(String name, String password) {
        return dao.validateLogin(name,password);
    }

    @Override
    public int saveLogin(Login login) {
        return dao.saveLogin(login);
    }

    @Override
    public List<String> getAllUserNames() {
        return dao.getAllUserNames();
    }
}
