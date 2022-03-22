package com.fabhotels.user_transactions.account;

import com.fabhotels.user_transactions.user.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AccountController {

    AccountService accountService;

    @GetMapping("/account/details")
    private Optional<Account> getAccountDetails(@Validated @RequestParam Integer userId){
        return accountService.getAccountDetails(userId);
    }

    @PostMapping("/account/amount")
    private Status updateAmount(@Validated @RequestBody Integer user, Integer amount,Boolean add){
        return accountService.updateAmount(user,amount,add);
    }
}
