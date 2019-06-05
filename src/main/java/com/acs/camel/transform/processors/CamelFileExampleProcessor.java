package com.acs.camel.transform.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CamelFileExampleProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {

        System.out.println("Exchange in processor is: " + exchange.getIn().getBody());

        GenericFile<File> file = (GenericFile<File>) exchange.getIn().getBody();

        String fileLine = null;
        String newValue = "";

        if (file != null) {

            FileReader fileReader = new FileReader(file.getFile());

            BufferedReader reader = new BufferedReader(fileReader);

            while ((fileLine = reader.readLine()) != null) {

                System.out.println("Read line is " + fileLine);

                newValue = newValue.concat(fileLine.replaceAll(",",":")).concat("\n");

            }

            exchange.getIn().setBody(newValue);
        }

    }

}
