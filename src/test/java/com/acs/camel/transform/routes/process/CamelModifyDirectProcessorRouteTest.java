package com.acs.camel.transform.routes.process;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class CamelModifyDirectProcessorRouteTest extends CamelTestSupport {

    @Override
    public RouteBuilder createRouteBuilder() {

        return new CamelModifyDirectProcessorRoute();

    }

    @Test
    public void testDirectProcessor() throws InterruptedException {

        String inputValue = "1234,Andre Carlos,Teste\n578,Luciana,Teste2\n";

        String outputExpected = inputValue.replaceAll(",", ":");

        String output = (String)template.requestBody("direct:processorInput", inputValue);

        assertEquals(output, outputExpected);

    }

    @Test
    public void processorDirectUsingMock() throws InterruptedException {

        String inputValue = "1234,Andre Carlos,Teste\n578,Luciana,Teste2\n";

        String outputExpected = inputValue.replaceAll(",", ":");

        MockEndpoint mock = getMockEndpoint("mock:output");

        mock.expectedBodiesReceived(outputExpected);

        template.requestBody("direct:processorInput", inputValue);

        assertMockEndpointsSatisfied();

    }

}