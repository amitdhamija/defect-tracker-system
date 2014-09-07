package x46011.teama.dts.model;

import java.util.Date;

/**
 * The Defect model object
 * 
 * @author Travis Cribbet
 * @author Amit Dhamija
 * @version 1.0
 * @revision 1.1	Added spaces
 * @revision 1.2	Organized members to match the table order for readability
 * @revision 1.3	Added default constructor
 */
public class Defect {
	
	private int id;
	private Date date;
	private Person submitter;
	private Person assignee;
	private DefectPriority priority;
	private DefectStatus status;
	private String summary;
	private String description;
	
	public Defect() {}
	
	public Defect(int id, Date date, Person submitter, Person assignee, DefectPriority priority, DefectStatus status, String summary, String description) {
		this.id = id;
		this.date = date;
		this.submitter = submitter;
		this.assignee = assignee;
		this.priority = priority;
		this.status = status;
		this.summary = summary;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Person getSubmitter() {
		return submitter;
	}
	
	public void setSubmitter(Person submitter) {
		this.submitter = submitter;
	}
	
	public Person getAssignee() {
		return assignee;
	}
	
	public void setAssignee(Person assignee) {
		this.assignee = assignee;
	}
	
	public DefectPriority getPriority() {
		return priority;
	}
	
	public void setPriority(DefectPriority priority) {
		this.priority = priority;
	}
	
	public DefectStatus getStatus() {
		return status;
	}
	
	public void setStatus(DefectStatus status) {
		this.status = status;
	}
	
	public String getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}