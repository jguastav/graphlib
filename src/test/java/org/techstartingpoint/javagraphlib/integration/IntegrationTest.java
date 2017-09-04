package org.techstartingpoint.javagraphlib.integration;

import com.onelake.api.error.OnelakeException;
import com.onelake.workflowexecutor.error.WorkflowErrorCode;
import org.junit.Before;
import org.junit.Test;
import org.techstartingpoint.javagraphlib.App;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    private String jobJson=null;

    @Before
    public void setup() {

    }


    @Test
    public void testIntegration1()  {
        String args[] = {
                "workflow.json"
        };
        try {
            App.main(args);
        } catch (OnelakeException e) {
            assertEquals(e.getErrorCode(),WorkflowErrorCode.NoStartNode);
        }
    }


    @Test
    public void testIntegrationWitStart()  {
        String args[] = {
                "workflow2WithStart.json"
        };
        try {
            App.main(args);
        } catch (OnelakeException e) {
            assertEquals(e.getErrorCode(),WorkflowErrorCode.UnusedPorts);
        }
    }

    @Test
    public void testIntegrationWitStartConnectingWriteAndJoin() throws OnelakeException {
        String args[] = {
                "workflow2WithStartConnectingWriteAndJoin.json"
        };
        App.main(args);
    }

    // TODO: GraphAPIService readJson  URISyntaxException, IOException
    // TODO: AbstractMainExecutor ClassNotFoundException InstantiationException  IllegalAccessException


}
