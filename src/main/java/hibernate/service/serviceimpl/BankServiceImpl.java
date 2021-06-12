package hibernate.service.serviceimpl;

import hibernate.dao.dao.BankDao;
import hibernate.dao.daoimpl.BankDaoImpl;
import hibernate.entities.Bank;
import hibernate.service.service.BankService;

import java.util.List;

public class BankServiceImpl implements BankService {
    BankDao dao;
    public BankServiceImpl()
    {
        this.dao = new BankDaoImpl();
    }
    @Override
    public Bank getBankById(int id) {
        return dao.getBankById(id);
    }

    @Override
    public List<Bank> getAllBanks() {
        return dao.getAllBanks();
    }

    @Override
    public Bank getBankByBankName(String bankname) {
        return dao.getBankByBankName(bankname);
    }

    @Override
    public List<String> getAllBankNames() {
        return dao.getAllBankNames();
    }

    @Override
    public double getBankBalance(int id) {
        return dao.getBankBalance(id);
    }

    @Override
    public int addBankBalance(int id, double balance) {
        return dao.addBankBalance(id,balance);
    }

    @Override
    public int reduceBankBalance(int id, double balance) {
        return dao.reduceBankBalance(id,balance);
    }

    @Override
    public int saveBank(Bank bank) {
        return dao.saveBank(bank);
    }

    @Override
    public int updateBank(Bank bank) {
        return dao.updateBank(bank);
    }
}
