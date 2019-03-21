package com.controller;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.MailDTO;

@Controller
public class MailController {
	
	@Autowired
	private JavaMailSenderImpl mailSender;

	@RequestMapping(value = "/sendMail")
	public String sendMail(@RequestAttribute("mailInfo") MailDTO mailInfo, Model m) {
		System.out.println(mailInfo);
		final MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				helper.setFrom(mailInfo.getFormNickName());
				helper.setTo(mailInfo.getUseremail());
				helper.setSubject(mailInfo.getMailtitle());
				helper.setText(mailInfo.getMesg(), true);
			}
		};
		mailSender.send(preparator);
		m.addAttribute("mesg", mailInfo.getRandomMessage());
		return mailInfo.getNextPage();
	}
}
