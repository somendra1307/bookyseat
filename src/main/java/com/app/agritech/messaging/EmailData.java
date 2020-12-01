package com.app.agritech.messaging;

import java.util.Map;

public class EmailData {

	String subject;

	String template;

	Map<String, String> templateValues;

	String[] to;

	String[] cc;

	String[] bcc;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Map<String, String> getTemplateValues() {
		return templateValues;
	}

	public void setTemplateValues(Map<String, String> templateValues) {
		this.templateValues = templateValues;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String[] getCc() {
		return cc;
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}

	public String[] getBcc() {
		return bcc;
	}

	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}

}
