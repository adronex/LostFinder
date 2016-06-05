package by.lostFinder.security;

import by.lostFinder.entities.Account;
import by.lostFinder.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
class CustomUserDetailsService implements UserDetailsService {

	private final AccountRepository accountRepository;

	@Autowired
	public CustomUserDetailsService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByLoginIgnoreCase(username);
		if (account == null) {
			throw new UsernameNotFoundException(String.format("Account %s does not exist!", username));
		}
		return new AccountRepositoryAccountDetails(account);
	}

	private final static class AccountRepositoryAccountDetails extends Account implements UserDetails {

		private static final long serialVersionUID = 1L;

		private AccountRepositoryAccountDetails(Account account) {
			super(account.getLogin(), account.getPassword(), account.getEmail());
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return new ArrayList<>();//getPermissions();
		}

		@Override
		public String getUsername() {
			return getLogin();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

	}

}
