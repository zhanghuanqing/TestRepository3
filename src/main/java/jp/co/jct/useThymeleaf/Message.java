package jp.co.jct.useThymeleaf;

import java.util.Calendar;

public class Message {
	private Long id;

	// 编写不能为空的提示语
	private String text;

	// 编写不能为空的提示语
	private String summary;

	private Calendar created = Calendar.getInstance();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Calendar getCreated() {
		return created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}

	// get set

}
