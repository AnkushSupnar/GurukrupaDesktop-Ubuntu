package hibernate.dao.dao;

import hibernate.entities.Counter;

import java.util.List;

public interface CounterDao {
    public List<Counter> getAllCounters();

    public Counter getCounterById(int id);

    public Counter getCounterByName(String name);

    public List<String>getAllCounterNames();

    public int saveCounter(Counter counter);

    public Character getCounterBillInitial(int id);

    public Counter getCounterByPerson(String person);


}
