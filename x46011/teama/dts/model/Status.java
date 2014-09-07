package x46011.teama.dts.model; 

import x46011.teama.dts.model.DefectStatus;


public class Status {
	private int ID;
	private DefectStatus Status;
	public Status(int Id, DefectStatus status)
	{
		setId(Id);
		setStatus(status);
	}
	public int getId() {
		return ID;
	}
	public void setId(int iD) {
		ID = iD;
	}
	public DefectStatus getStatus() {
		return Status;
	}
	public void setStatus(DefectStatus status) {
		Status = status;
	}
}
