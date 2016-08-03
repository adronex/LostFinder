package by.lostFinder.security;

import by.lostFinder.entities.Account;
import by.lostFinder.repositories.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmailIgnoreCase(username);
        if (account == null) {
            throw new UsernameNotFoundException(String.format("Account %s does not exist!", username));
        }
        return account;
    }

}
