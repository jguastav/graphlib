package org.techstartingpoint.javagraphlib.execution;

import java.util.Date;


/**
 * Unit of information to be shown in output in app
 * @author Jose Alberto Guastavino
 *
 */
public class PrintItem {
	private String fluxId;
	private Date timeStamp;
	private String message;
	
	
	public PrintItem(String fluxId,String message) {
		this.fluxId=fluxId;
		this.message=message;
		this.timeStamp=new Date();
	}
	

	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}



	public String getFluxId() {
		return fluxId;
	}


	public void setFluxId(String fluxId) {
		this.fluxId = fluxId;
	}


	@Override
	public String toString() {
		return "OutputElement [fluxId=" + fluxId + ", timeStamp=" + timeStamp
				+ ", message=" + message + "]\n";
	}
	
	

}
