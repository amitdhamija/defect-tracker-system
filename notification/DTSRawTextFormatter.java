package notification;

import x46011.teama.dts.model.Defect;
import x46011.teama.dts.model.DefectPriority;
import x46011.teama.dts.model.Status;

/**
 * DTSRawTextFormatter formats defect notification emails into raw text
 * 
 * @author Kevin Alexander
 * @version 1.0
  */
public class DTSRawTextFormatter implements IDTSEmailFormatter
{

	@Override
	public String formatEmail(Defect defect) {
		
		int id = defect.getId();
		String summary = defect.getSummary();
		String description = defect.getDescription();
		String assignee = defect.getAssignee().getName();
		String reporter = defect.getSubmitter().getName();
		DefectPriority priority = defect.getPriority();
		Status status = defect.getStatus();
		java.util.Date reportedDate = defect.getDate();
		
		String message = "Information about bug # " + id + " has changed.\n\tPlease review the new information.\n";
		message += "\nSummary: " + summary + "\n";
		message += "\nReported on: " + reportedDate.toString();
		message += "\nAssignee: " + assignee;
		message += "\nReporter: " + reporter;
		message += "\nPriority: " + priority.toString() + "\t\tStatus: " + status.toString();
		message += "\nReporter: " + reporter;
		message += "\n\nDetails: " + description;
		
		return message;
	}
	
	@Override
	public String formatEmail(Defect oldDefect,Defect newDefect) {
		
		String message = "Information about bug # " + oldDefect.getId() + " has changed.\n\tPlease review the new information.\n";
		message += "\nSummary: " + oldDefect.getSummary() + "\n";
		message += "\nReported on: " + oldDefect.getDate().toString();
		message += "\nReporter: " + oldDefect.getSubmitter();

		if (oldDefect.getAssignee().getId()!=newDefect.getAssignee().getId())
			message += "\n\nPrevious Assignee: " + oldDefect.getAssignee().getName() + "\t\tNew Assignee: " + newDefect.getAssignee().getName();
		if (oldDefect.getPriority()!=newDefect.getPriority())
			message += "\n\nPrevious Priority: " + oldDefect.getPriority() + "\t\tNew Priority: " + newDefect.getPriority();
		if (oldDefect.getStatus()!=newDefect.getStatus())
			message += "\n\nPrevious Status: " + oldDefect.getStatus() + "\t\tNew Status: " + newDefect.getStatus();
		if (oldDefect.getDescription()!=newDefect.getDescription())
			message += "\n\nPrevious Details:\n\t" + oldDefect.getDescription() + "\n\nNew Details:\n\t" + newDefect.getDescription();
		
		return message;
	}

}
