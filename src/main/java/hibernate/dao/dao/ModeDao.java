package hibernate.dao.dao;

import hibernate.entities.Mode;

import java.time.LocalDate;
import java.util.List;

public interface ModeDao {
    public Mode getModeById(long id);

    public List<Mode> getAllModes();

    public List<Mode> getModeByCustomer(long customerid);

    public List<Mode> getModeByLogin(int loginid);

    public List<Mode> getDateWiseModes(LocalDate date);

    public Mode saveMode(Mode mode);

    public Mode updateMode(Mode mode);


}
