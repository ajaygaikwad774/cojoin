package com.smn.el.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

	/*
	@Autowired
	private Environment env;

	
	 * @Bean public JavaMailSender getMailSender() { JavaMailSenderImpl mailSender =
	 * new JavaMailSenderImpl();
	 * 
	 * mailSender.setHost(env.getProperty("spring.mail.host"));
	 * mailSender.setPort(Integer.valueOf(env.getProperty("spring.mail.port")));
	 * mailSender.setUsername(env.getProperty("spring.mail.username"));
	 * mailSender.setPassword(env.getProperty("spring.mail.password"));
	 * 
	 * Properties javaMailProperties = new Properties();
	 * javaMailProperties.put("mail.smtp.starttls.enable", "true");
	 * javaMailProperties.put("mail.smtp.auth", "true");
	 * javaMailProperties.put("mail.transport.protocol", "smtp");
	 * javaMailProperties.put("mail.debug", "true");
	 * 
	 * mailSender.setJavaMailProperties(javaMailProperties); return mailSender; }
	 */
	
	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    
	    mailSender.setUsername("ajaygaikwad774@gmail.com");
	    mailSender.setPassword("Counter@774");
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}
}
