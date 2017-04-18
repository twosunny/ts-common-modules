package woosun.common.jta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import woosun.common.jta.entity.Account;
import woosun.common.jta.entity.Other;
import woosun.common.jta.repository.AccountRepository;
import woosun.common.jta.repository.OtherRepository;

@Service
public class OtherService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private OtherRepository otherRepository;
	
	@Transactional
	public void equalDBTransactionTest(Other other1, Other other2){
		otherRepository.save(other1);
		String err = null;
		err.equals(""); // error!
		otherRepository.save(other2);
		
	}
	
	@Transactional
	public void notEqualDBTransactionTest(Account account, Other other){
		otherRepository.save(other);
		String err = null;
		err.equals(""); // error!
		accountRepository.save(account);
	}
}
