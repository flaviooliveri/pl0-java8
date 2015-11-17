package org.flavio.pl0;

import org.flavio.pl0.generator.CodeGenerator;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class AbstractFileParserTest {

    protected Parser parser;
    protected CodeGenerator generator;

    protected void setUp(String path) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(getClass().getResource(path).toURI()))) {
            ApplicationContext applicationContext = new ApplicationContext();
            applicationContext.initialize(reader);
            parser = applicationContext.getParser();
            generator = applicationContext.getCodeGenerator();
            parser.compile(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}