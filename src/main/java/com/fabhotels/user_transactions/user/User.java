package com.fabhotels.user_transactions.user;

import com.fabhotels.user_transactions.account.Account;
import com.fabhotels.user_transactions.account.AccountService;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    private String password;

    private Boolean loggedIn;


    @Id
    private Integer accountId;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.loggedIn = false;

        Account account = new Account(0);
        AccountService accountService = new AccountService();
        accountService.accountRepository.save(account);
        this.accountId = account.getId();
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return (Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(name,user.name)) ||
                (Objects.equals(email, user.email) && Objects.equals(password, user.password)) ||
                (Objects.equals(id,user.id));
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name,loggedIn);
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email=" + email +
                ", loggedIn=" + loggedIn +
                '}';
    }
}




