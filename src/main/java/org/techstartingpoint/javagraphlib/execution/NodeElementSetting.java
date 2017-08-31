
package org.techstartingpoint.javagraphlib.execution;


/**
 * Setting related to elements
 * @author Jose Alberto Guastavino
 *
 */
// TODO: review to delete it
public class NodeElementSetting {

	/**
	 * initial width of elements
	 */
	private int width;
	private int height;
	private int leftConnectorMargin;
	private int connectorDistance;
	private int rightConnectorMargin;
	private int topConnectorMargin;
	private int bottomConnectorMargin;
	private int textHeight;

	
    
    
    private NodeElementSetting() {}
	
	public NodeElementSetting(int width, int height,
			int topConnectorMargin, int rightConnectorMargin,int bottomConnectorMargin,int leftConnectorMargin, 
			int connectorDistance,  
			int textHeight) {
		this.width=width;
		this.height=height;
		this.topConnectorMargin=topConnectorMargin;
		this.rightConnectorMargin= rightConnectorMargin;
		this.bottomConnectorMargin=bottomConnectorMargin;
		this.leftConnectorMargin=leftConnectorMargin;
		this.connectorDistance=connectorDistance;
		this.textHeight= textHeight;
	
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getLeftConnectorMargin() {
		return leftConnectorMargin;
	}

	public void setLeftConnectorMargin(int leftConnectorMargin) {
		this.leftConnectorMargin = leftConnectorMargin;
	}

	public int getConnectorDistance() {
		return connectorDistance;
	}

	public void setConnectorDistance(int connectorDistance) {
		this.connectorDistance = connectorDistance;
	}

	public int getRightConnectorMargin() {
		return rightConnectorMargin;
	}

	public void setRightConnectorMargin(int rightConnectorMargin) {
		this.rightConnectorMargin = rightConnectorMargin;
	}

	public int getTextHeight() {
		return textHeight;
	}

	public void setTextHeight(int textHeight) {
		this.textHeight = textHeight;
	}

	public int getTopConnectorMargin() {
		return topConnectorMargin;
	}

	public void setTopConnectorMargin(int topConnectorMargin) {
		this.topConnectorMargin = topConnectorMargin;
	}

	public int getBottomConnectorMargin() {
		return bottomConnectorMargin;
	}

	public void setBottomConnectorMargin(int bottomConnectorMargin) {
		this.bottomConnectorMargin = bottomConnectorMargin;
	}

	
	
	
}
