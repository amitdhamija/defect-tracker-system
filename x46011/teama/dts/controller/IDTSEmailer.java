package x46011.teama.dts.controller;

import java.util.Set;

/**
 * IDTSEmailer provides an interface for senders of defect notification emails
 * 
 * @author Kevin Alexander
 * @version 1.0
  */
public interface IDTSEmailer {
	
	public void sendMail( String formattedEmail, Set<String> addressees );
	public void sendMail( String formattedEmail, String addressee );
		
}
