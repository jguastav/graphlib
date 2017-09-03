
package org.techstartingpoint.javagraphlib.execution;

import com.onelake.api.error.OnelakeException;
import com.onelake.workflowexecutor.error.WorkflowErrorCode;
import com.onelake.workflowexecutor.schema.repo.ComponentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.techstartingpoint.javagraphlib.api.GraphAPIService;
import org.techstartingpoint.javagraphlib.model.repo.YamlParser;

import java.net.URISyntaxException;

/**
 * Main execution context that manages running processes
 * Starts / Stops and manage running flows
 *
 * @author Jose Alberto Guastavino
 *
 */
public class GraphRunnerEnvironmentImpl {

    private static final Logger logger = LoggerFactory.getLogger(GraphRunnerEnvironmentImpl.class.getName());


	/**
	 * Runs a workflow defined in json file ,
     * yaml file and java components implementation
     *
	 * @param workflowFileName
	 * 
	 * @author Jose Alberto Guastavino
	 */
	public void run(String workflowFileName) throws OnelakeException {
        final String yamlFileName="org/techstartingpoint/javagraphlib/model/repo/components.yml";
        java.net.URL resource=
                getClass().getClassLoader().getResource(yamlFileName);
        String uriYaml;
        try {
            uriYaml = resource.toURI().getPath();
        } catch (URISyntaxException e) {
            logger.error(WorkflowErrorCode.UnableToReadFile.getMessage());
            throw OnelakeException.build(WorkflowErrorCode.UnableToReadFile, e).set("fileName", yamlFileName).set("class", this.getClass());
        }

        ComponentRepository componentRepository=parseComponentRepository(uriYaml);


		GraphRunnerImpl runner=
                new GraphRunnerImpl(
                        new GraphAPIService(),
                        workflowFileName,
                        componentRepository,
                        this);
		runner.run();
	}

    private static ComponentRepository parseComponentRepository(String repoYmlFile) throws OnelakeException {
        YamlParser<ComponentRepository> componentRepositoryYamlReader = new YamlParser<>();
        return componentRepositoryYamlReader.readYaml(repoYmlFile, ComponentRepository.class);
    }




}
