package by.lostFinder.services.security;

import by.lostFinder.entities.account.Account;
import by.lostFinder.repositories.account.AccountRepository;
import by.lostFinder.services.SimpleServiceImpl;
import by.lostFinder.utils.ExceptionUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl extends SimpleServiceImpl<Account, AccountRepository> implements SecurityService {

    @Override
    public Account getCurrentAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            ExceptionUtils.throwNotAuthenticatedException(getClass());
        }
        Account currentAccount = repository.findByEmailIgnoreCase(authentication.getName());
        if (currentAccount == null) {
            ExceptionUtils.throwUsernameNotFoundException(getClass());
        }
        return currentAccount;
    }
}
