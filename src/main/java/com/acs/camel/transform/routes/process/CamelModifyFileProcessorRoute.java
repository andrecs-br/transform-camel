package com.acs.camel.transform.routes.process;

import com.acs.camel.transform.processors.CamelFileExampleProcessor;
import org.apache.camel.builder.RouteBuilder;

public class CamelModifyFileProcessorRoute extends RouteBuilder {

    public void configure() throws Exception {

        from("file:data/input?noop=true")
                .log("Read file is ${body} and headers: ${headers}")
                .process(new CamelFileExampleProcessor())
                .to("file:data/output?fileName=output.txt")
                .to("mock:output");

    }

}
