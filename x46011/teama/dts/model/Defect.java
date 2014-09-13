package x46011.teama.dts.model;

import java.util.Date;

/**
 * The Defect model object
 * 
 * @author Travis Cribbet
 * @author Amit Dhamija
 * @version 1.0
 * @revision 1.1	Amit Dhamija: Added spaces
 * @revision 1.2	Amit Dhamija: Organized members to match the table order for readability
 * @revision 1.3	Amit Dhamija: Added default constructor
 */
public class Defect {
	
	private int id;
	private Date date;
	private User submitter;
	private User assignee;
	private DefectPriority priority;
	private DefectStatus status;
	private String summary;
	private String description;
	
	public Defect() {}
	
	public Defect(User submitter, User assignee, DefectPriority priority, DefectStatus status, String summary, String description) {
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
	
	public User getSubmitter() {
		return submitter;
	}
	
	public void setSubmitter(User submitter) {
		this.submitter = submitter;
	}
	
	public User getAssignee() {
		return assignee;
	}
	
	public void setAssignee(User assignee) {
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