package org.techstartingpoint.javagraphlib.execution;


import com.onelake.api.error.OnelakeException;

/**
 * method of the runner implemtation that excomponents can use
 * @author Jose Alberto Guastavino
 *
 */
public interface GraphRunner {
	
	/**
	 * method to send messages to other objects. 
	 * This method is called each time an component calls setOuput(index,message) or initially for async connectors
	 * It sends the message to all target connected elements
	 * 
	 * @param fluxId
	 * @param outputIndex
	 * @param value
	 * @
	 * 
	 * @author Jose Alberto Guastavino
	 */
	void broadcast(String fluxId, int outputIndex, Object value) ;



}
