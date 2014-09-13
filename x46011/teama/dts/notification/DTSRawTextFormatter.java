package x46011.teama.dts.notification;

import x46011.teama.dts.model.Defect;
import x46011.teama.dts.model.DefectPriority;
import x46011.teama.dts.model.DefectStatus;

/**
 * DTSRawTextFormatter formats defect x46011.teama.dts.notification emails into raw text
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
		String submitter = defect.getSubmitter().getName();
		DefectPriority priority = defect.getPriority();
		DefectStatus status = defect.getStatus();
		java.util.Date submittedDate = defect.getDate();
		
		String message = "Information about Defect # " + id + " has changed.\nPlease review the new information.\n";
		message += "\nSummary: " + summary + "\n";
		message += "\nSubmitted on: " + submittedDate.toString();
		message += "\nSubmitter: " + submitter;
		message += "\nAssignee: " + assignee;
		message += "\nPriority: " + priority.toString() + "\t\tStatus: " + status.toString();
		message += "\nSubmitter: " + submitter;
		message += "\n\nDescription: " + description;
		
		return message;
	}
	
	@Override
	public String formatEmail(Defect oldDefect,Defect newDefect) {
		
		String message = "Information about Defect # " + oldDefect.getId() + " has changed.\nPlease review the new information.\n";
		message += "\nSummary: " + oldDefect.getSummary() + "\n";
		message += "\nSubmitted on: " + oldDefect.getDate().toString();
		message += "\nSubmitter: " + oldDefect.getSubmitter();

		if (oldDefect.getAssignee().getId()!=newDefect.getAssignee().getId())
			message += "\n\nPrevious Assignee: " + oldDefect.getAssignee().getName() + "\t\tNew Assignee: " + newDefect.getAssignee().getName();
		if (oldDefect.getPriority()!=newDefect.getPriority())
			message += "\n\nPrevious Priority: " + oldDefect.getPriority() + "\t\tNew Priority: " + newDefect.getPriority();
		if (oldDefect.getStatus()!=newDefect.getStatus())
			message += "\n\nPrevious Status: " + oldDefect.getStatus() + "\t\tNew Status: " + newDefect.getStatus();
		if (oldDefect.getDescription()!=newDefect.getDescription())
			message += "\n\nPrevious Description:\n\t" + oldDefect.getDescription() + "\n\nNew Description:\n\t" + newDefect.getDescription();
		
		return message;
	}

}
