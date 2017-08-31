
package org.techstartingpoint.javagraphlib.commons;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * General used utility methods
 * 
 * @author Jose Alberto Guastavino
 *
 */
public class GraphUtils {

	public static String getNowId() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(new Date());
	}
	
}
