package com.fabhotels.user_transactions.account;

import com.fabhotels.user_transactions.user.User;
import com.fabhotels.user_transactions.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class AccountService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;

    public Optional<Account> getAccountDetails(Integer userId){
        AtomicReference<Optional<Account>> accountDetails = null;
        Optional<User> user = userRepository.findById(userId);

        user.ifPresent(u->{
            accountDetails.set(accountRepository.findById(u.getAccountId()));
        });

        return accountDetails.get();
    }

    public Status updateAmount(User user, Integer amount, Boolean add){
        AtomicReference<Status> status = new AtomicReference<>(Status.FAILURE);
        Optional<User> currUser = userRepository.findById(user.getId());

        currUser.ifPresent(u->{
            List<Account> accounts= accountRepository.findAll();

            for(Account account : accounts){
                if(account.getId()==u.getAccountId()){
                    account.setAmount(add? account.getAmount()+amount:account.getAmount()-amount);
                    accountRepository.save(account);
                    status.set(Status.SUCCESS);
                    break;
                }
            }
        });

        return status.get();
    }
}
