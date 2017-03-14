package woosun.common.mail.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
  @Autowired
  private JavaMailSender javaMailSender;

  @Override
  public void sendMail(String mailTo, String mailFrom, String subject, String contents){
    
    try {
    	MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
	    messageHelper.setTo(mailTo);
	    messageHelper.setFrom(mailFrom);
	
	    messageHelper.setSubject(subject);
	    messageHelper.setText(contents, true);
	    javaMailSender.send(message);

    } catch (Exception e) {
    	e.printStackTrace();
    }

  }

}
