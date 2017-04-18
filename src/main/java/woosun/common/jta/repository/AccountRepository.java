package woosun.common.jta.repository;

import org.springframework.data.repository.CrudRepository;

import woosun.common.jta.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

}
