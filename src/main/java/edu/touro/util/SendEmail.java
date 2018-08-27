package edu.touro.util;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.touro.bean.TouroUser;
import edu.touro.constants.UserCreationConstants;

public class SendEmail {
	
	private static final Logger logger = LoggerFactory.getLogger(SendEmail.class);
	
	/*public static void main(String args[]) {
		SendEmail sendIt = new SendEmail();
		sendIt.send("Robert.jamesTest@touro.edu", "robert.james4@touro.edu", "Banner 9 Testing", UserCreationConstants.EMAIL_FOOTER);
	}*/
  
   public void send(String sender, String subject, StringBuffer content, TouroUser userBean) {

      String host = "owa1.touro.edu";
      Properties properties = System.getProperties();
      properties.setProperty("mail.smtp.host", host);
      Session session = Session.getDefaultInstance(properties);
      
      try {
          MimeMessage message = new MimeMessage(session);
          message.setFrom(new InternetAddress(sender));
          message.addRecipient(Message.RecipientType.TO, new InternetAddress(userBean.getEmailAddress()));
          message.setSubject(subject);
          message.setContent(content.toString(), "text/html");
          Transport.send(message);
          logger.info("Email sent Successfully to " + userBean.getEmailAddress());
		  
		} catch (MessagingException mex) {
			
			mex.printStackTrace();
		}
   }
   
   public StringBuffer messageBuilder(TouroUser userBean) {
	   StringBuffer emailMessage = new StringBuffer();
	   emailMessage.append(UserCreationConstants.EMAIL_MESSAGE);
	   emailMessage.append(UserCreationConstants.FIRST_NAME + userBean.getFirst_name() + UserCreationConstants.NEWLINE_CHAR);
	   emailMessage.append(UserCreationConstants.LAST_NAME + userBean.getLast_name() + UserCreationConstants.NEWLINE_CHAR);
	   emailMessage.append(UserCreationConstants.TOUROONE_ID + userBean.getTnumber() + UserCreationConstants.NEWLINE_CHAR);
	   emailMessage.append(UserCreationConstants.DAY_OF_BIRTH + userBean.getDob() + UserCreationConstants.NEWLINE_CHAR);
	   emailMessage.append(UserCreationConstants.LAST_SSN + userBean.getSsn() + UserCreationConstants.NEWLINE_CHAR);
	   emailMessage.append(UserCreationConstants.EMAIL_FOOTER + UserCreationConstants.NEWLINE_CHAR);
	   return emailMessage;
	   
   }
   
}