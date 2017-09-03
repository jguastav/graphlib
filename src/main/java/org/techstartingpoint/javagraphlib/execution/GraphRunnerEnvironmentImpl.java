
package org.techstartingpoint.javagraphlib.execution;

import com.onelake.api.error.OnelakeException;
import com.onelake.workflowexecutor.schema.repo.ComponentRepository;
import org.techstartingpoint.javagraphlib.api.GraphAPIService;
import org.techstartingpoint.javagraphlib.model.repo.YamlParser;

/**
 * Main execution context that manages running processes
 * Starts / Stops and manage running flows
 *
 * @author Jose Alberto Guastavino
 *
 */
public class GraphRunnerEnvironmentImpl {
	
	

	/**
	 * Runs an activity
	 * @param workflowFileName
	 * 
	 * @author Jose Alberto Guastavino
	 */
	public void run(String workflowFileName) throws Throwable {

        java.net.URL resource=getClass().getClassLoader().getResource("org/techstartingpoint/javagraphlib/model/repo/components.yml");
        String uriYaml=resource.toURI().getPath();

        ComponentRepository componentRepository=parseComponentRepository(uriYaml);


		GraphRunnerImpl runner=new GraphRunnerImpl(new GraphAPIService(),workflowFileName,componentRepository,this);
		runner.run();
	}

    private static ComponentRepository parseComponentRepository(String repoYmlFile) throws OnelakeException {
        YamlParser<ComponentRepository> componentRepositoryYamlReader = new YamlParser<>();
        return componentRepositoryYamlReader.readYaml(repoYmlFile, ComponentRepository.class);
    }




}
