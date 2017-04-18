package woosun.common.jta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import woosun.common.jta.entity.Account;
import woosun.common.jta.repository.AccountRepository;

@Service
public class AcountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	
	public Account join(Account account) {
		
		Account	result = accountRepository.save(account);
		return result;
	}
	
}
