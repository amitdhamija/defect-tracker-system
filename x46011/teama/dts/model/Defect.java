package x46011.teama.dts.model;

import java.util.Date;

/**
 * The Defect model object
 *
 * @author Travis Cribbet
 * @author Amit Dhamija
 * @version 1.0
 * @revision 1.1        Added spaces
 * @revision 1.2        Organized members to match the table order for readability
 */

public class Defect {
	private Date date;
	private int id;
	private Person reporter;
	private String summary;
	private String details;
	private Person assignee;
	private Status status;
	private DefectPriority priority;
	
	public Defect(Person reporter, String summary
			, String details, Person assignee, Status status
			, DefectPriority priority){
		this.setReporter(reporter);
		this.setSummary(summary);
		this.setDetails(details);
		this.setAssignee(assignee);
		this.setStatus(status);
		this.setPriority(priority);
	}
	public Defect(Date date, int id, Person reporter, String summary
			, String details, Person assignee, Status status
			, DefectPriority priority){
		this.setDate(date);
		this.setId(id);
		this.setReporter(reporter);
		this.setSummary(summary);
		this.setDetails(details);
		this.setAssignee(assignee);
		this.setStatus(status);
		this.setPriority(priority);
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
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public DefectPriority getPriority() {
		return priority;
	}
	public void setPriority(DefectPriority priority) {
		this.priority = priority;
	}
}
