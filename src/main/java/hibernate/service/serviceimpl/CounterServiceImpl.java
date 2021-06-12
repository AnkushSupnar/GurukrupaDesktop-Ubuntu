package hibernate.service.serviceimpl;

import hibernate.dao.dao.CounterDao;
import hibernate.dao.daoimpl.CounterdaoImpl;
import hibernate.entities.Counter;
import hibernate.service.service.CounterService;

import java.util.List;

public class CounterServiceImpl implements CounterService {
    private CounterDao dao;
    public CounterServiceImpl()
    {
        this.dao = new CounterdaoImpl();
    }
    @Override
    public List<Counter> getAllCounters() {
        return dao.getAllCounters();
    }

    @Override
    public Counter getCounterById(int id) {
        return dao.getCounterById(id);
    }

    @Override
    public Counter getCounterByName(String name) {
        return dao.getCounterByName(name);
    }

    @Override
    public List<String> getAllCounterNames() {
        return dao.getAllCounterNames();
    }

    @Override
    public int saveCounter(Counter counter) {
        return dao.saveCounter(counter);
    }

    @Override
    public Character getCounterBillInitial(int id) {
        return dao.getCounterBillInitial(id);
    }

    @Override
    public Counter getCounterByPerson(String person) {
        return dao.getCounterByPerson(person);
    }
}
