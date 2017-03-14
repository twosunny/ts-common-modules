package woosun.common.mail.service;

public interface MailService {
	
	public void sendMail(String mailTo, String mailFrom, String subject, String contents);

}
