package com.acs.camel.transform.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class CamelModifyFileProcessorRouteTest extends CamelTestSupport {

    @Override
    public RouteBuilder createRouteBuilder() {

        return new CamelModifyFileProcessorRoute();

    }

    @Test
    public void processorTest() throws InterruptedException {

        String expectedValue = "123:Andre Carlos:11JAN1982\n" +
                "456:Luciana Siqueira:18APR1983\n";

        MockEndpoint mock = getMockEndpoint("mock:output");

        mock.expectedBodiesReceived(expectedValue);

        Thread.sleep(5000);

        File file = new File("data/output");

        assertTrue(file.isDirectory());

        assertMockEndpointsSatisfied();

    }

}