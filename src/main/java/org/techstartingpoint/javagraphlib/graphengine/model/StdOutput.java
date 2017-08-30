package org.techstartingpoint.javagraphlib.graphengine.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
/**
 * Class to expose output of running excomponents
 * messages to outpout can be sent by calling sendMessage()
 * @author Jose Alberto Guastavino
 *
 */
public class StdOutput {
	
	
	private ConcurrentLinkedQueue<PrintItem> outputList=new ConcurrentLinkedQueue<PrintItem>();

	public ConcurrentLinkedQueue<PrintItem> getOutputList() {
		return outputList;
	}

	public void sendMessage(String fluxId,String message) {
		outputList.add(new PrintItem(fluxId,message));
	} 
	
	
	public List<String> getStringMessages() {
			List<String> result=new ArrayList<String>();
			while (!getOutputList().isEmpty()) {
				result.add(getOutputList().poll().toString());
			}
			return result;
	}
}
