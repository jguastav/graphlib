package com.onelake.workflowexecutor.integration;

import com.onelake.api.error.OnelakeException;
import com.onelake.workflowexecutor.error.WorkflowErrorCode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.onelake.workflowexecutor.App;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    private String jobJson=null;

    @Before
    public void setup() {

    }


    @Test
    public void testIntegration1() throws OnelakeException {
        String args[] = {
                "workflow.json"
        };
            App.main(args);
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
