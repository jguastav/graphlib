
package com.onelake.workflowexecutor.executions;


import com.onelake.api.error.OnelakeException;

/**
 * Interface to allow add activitioes
 * @author Jose Alberto Guastavino
 *
 */
public interface GraphRunnerLauncher {
	void run() throws OnelakeException;
}
