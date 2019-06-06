package com.acs.camel.transform.routes.process;

import org.apache.camel.builder.RouteBuilder;

public class CamelModifyDirectProcessorRoute extends RouteBuilder {

    public void configure() throws Exception {

        from("direct:processorInput")
                .log("Receive message processorInput is ${body} and headers: ${headers}")
                .process((exchange) -> {
                    try {
                        String oldValue = (String) exchange.getIn().getBody();

                        String newValue = oldValue.replaceAll(",", ":");

                        exchange.getIn().setBody(newValue);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                })
                .log("Message after process is ${body} and headers: ${headers}")
                .to("file:data/output?fileName=output.txt")
                .to("mock:output");

    }

}
