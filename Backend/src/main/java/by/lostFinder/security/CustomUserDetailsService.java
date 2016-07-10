package by.lostFinder.security;

import by.lostFinder.entities.Account;
import by.lostFinder.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findByAuthUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(String.format("Account %s does not exist!", username));
        }
        return account;
    }

}
