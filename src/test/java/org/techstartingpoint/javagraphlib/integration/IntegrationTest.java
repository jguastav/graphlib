package org.techstartingpoint.javagraphlib.integration;

import com.onelake.api.error.OnelakeException;
import org.junit.Before;
import org.junit.Test;
import org.techstartingpoint.javagraphlib.App;

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
    public void testIntegrationWitStart() throws OnelakeException {
        String args[] = {
                "workflow2WithStart.json"
        };
        App.main(args);
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
