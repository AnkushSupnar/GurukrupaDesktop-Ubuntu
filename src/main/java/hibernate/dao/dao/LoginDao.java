package hibernate.dao.dao;

import hibernate.entities.Login;

import java.util.List;

public interface LoginDao {
    public List<Login> getAllLogin();
    public Login getLoginByName(String name) ;
    public Login getLoginByPerson(String person);
    public Login getLoginById( int id);
    public int validateLogin( String name,String password);
    public int saveLogin(Login login);
    public List<String>getAllUserNames();
}
