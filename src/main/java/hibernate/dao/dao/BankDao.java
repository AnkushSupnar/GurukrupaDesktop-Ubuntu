package hibernate.dao.dao;

import hibernate.entities.Bank;

import java.util.List;

public interface BankDao {
    public Bank getBankById(int id);
    public List<Bank> getAllBanks();
    public Bank getBankByBankName(String bankname);
    public List<String>getAllBankNames();
    public double getBankBalance(int id);
    public int addBankBalance(int id,double balance);
    public int reduceBankBalance(int id,double balance);
    public int saveBank( Bank bank);
    public int updateBank(Bank bank);

}
