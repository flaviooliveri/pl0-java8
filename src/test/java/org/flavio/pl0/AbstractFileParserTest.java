package org.flavio.pl0;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class AbstractFileParserTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    protected Parser parser;

    protected void setUp(String path) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(getClass().getResource(path).toURI()))) {
            ApplicationContext applicationContext = new ApplicationContext();
            applicationContext.initialize(reader);
            parser = applicationContext.getParser();
            parser.scan();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}