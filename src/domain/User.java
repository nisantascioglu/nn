package domain;


import java.util.List;

public class User {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String address;
    private String email;
    private String phoneNumber;
    private List<Account> accounts;
    public User(){}

    public User(Long id, String name, String username, String password, String address, String email, String phoneNumber, List<Account> accounts) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.accounts = accounts;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Account createAccount(String accountType, double initialBalance) {
        Account newAccount = new Account();
        newAccount.setAccountType(accountType);
        newAccount.setBalance(initialBalance);
        this.accounts.add(newAccount);
        return newAccount;
    }


}
