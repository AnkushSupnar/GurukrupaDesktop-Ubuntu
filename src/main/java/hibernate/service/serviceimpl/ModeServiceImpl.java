package hibernate.service.serviceimpl;

import hibernate.dao.dao.ModeDao;
import hibernate.entities.Mode;
import hibernate.service.service.ModeService;

import java.time.LocalDate;
import java.util.List;

public class ModeServiceImpl implements ModeService {
    private ModeDao dao;
    public ModeServiceImpl(){
        this.dao=new ModeServiceImpl();
    }

    @Override
    public Mode getModeById(long id) {
        return dao.getModeById(id);
    }

    @Override
    public List<Mode> getAllModes() {
        return dao.getAllModes();
    }

    @Override
    public List<Mode> getModeByCustomer(long customerid) {
        return dao.getModeByCustomer(customerid);
    }

    @Override
    public List<Mode> getModeByLogin(int loginid) {
        return dao.getModeByLogin(loginid);
    }

    @Override
    public List<Mode> getDateWiseModes(LocalDate date) {
        return dao.getDateWiseModes(date);
    }

    @Override
    public Mode saveMode(Mode mode) {
        return dao.saveMode(mode);
    }

    @Override
    public Mode updateMode(Mode mode) {
        return dao.updateMode(mode);
    }
}
