package x46011.teama.dts.notification;

import x46011.teama.dts.model.Defect;

/**
 * IDTSEmailer provides an interface for formatters of defect x46011.teama.dts.notification emails
 * 
 * @author Kevin Alexander
 * @version 1.0
  */
public interface IDTSEmailFormatter {

	public String formatEmail(Defect defect);
	public String formatEmail(Defect oldDefect,Defect newDefect);

}
