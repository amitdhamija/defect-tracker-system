package x46011.teama.dts.defect;


import java.util.Date;

import x46011.teama.dts.entities.Person;


public class Defect {
	private Date date;
	private int id;
	private Person reporter;
	private String summary;
	private String details;
	private Person assignee;
	private DefectStatusType status;
	private int priority;
	
	public Defect(Date date, int id, Person reporter, String summary
			, String details, Person assignee, DefectStatusType status
			, int priority){
		this.setDate(date);
		this.setId(id);
		this.setReporter(reporter);
		this.setSummary(summary);
		this.setDetails(details);
		this.setAssignee(assignee);
		this.setStatus(status);
		this.setPriority(priority);
	}
	
	public Defect( Person reporter ){
		this.setDate( new Date() );
		this.setId(-1);
		this.setStatus(DefectStatusType.OPEN);
		this.setPriority(3);
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Person getReporter() {
		return reporter;
	}
	public void setReporter(Person reporter) {
		this.reporter = reporter;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Person getAssignee() {
		return assignee;
	}
	public void setAssignee(Person assignee) {
		this.assignee = assignee;
	}
	public DefectStatusType getStatus() {
		return status;
	}
	public void setStatus(DefectStatusType status) {
		this.status = status;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	
}
