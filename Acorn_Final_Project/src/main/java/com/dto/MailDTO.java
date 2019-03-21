package com.dto;

public class MailDTO {
	private String mesg;
	private String mailtitle;
	private String mailbody;
	private String useremail;
	private String nextPage;
	private String formNickName;
	private String randomMessage;

	public MailDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MailDTO(String mesg, String mailtitle, String mailbody, String useremail, String nextPage) {
		super();
		this.mesg = mesg;
		this.mailtitle = mailtitle;
		this.mailbody = mailbody;
		this.useremail = useremail;
		this.nextPage = nextPage;
	}

	public String getMesg() {
		return mesg;
	}

	public void setMesg(String mesg) {
		this.mesg = mesg;
	}

	public String getMailtitle() {
		return mailtitle;
	}

	public void setMailtitle(String mailtitle) {
		this.mailtitle = mailtitle;
	}

	public String getMailbody() {
		return mailbody;
	}

	public void setMailbody(String mailbody) {
		this.mailbody = mailbody;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}
	

	public String getRandomMessage() {
		return randomMessage;
	}

	public void setRandomMessage(String randomMessage) {
		this.randomMessage = randomMessage;
	}
	
	public String getFormNickName() {
		return formNickName;
	}

	public void setFormNickName(String formNickName) {
		this.formNickName = formNickName;
	}

	@Override
	public String toString() {
		return "MailDTO [mesg=" + mesg + ", mailtitle=" + mailtitle + ", mailbody=" + mailbody + ", useremail="
				+ useremail + ", nextPage=" + nextPage + ", formNickName=" + formNickName + ", randomMessage="
				+ randomMessage + "]";
	}

}
