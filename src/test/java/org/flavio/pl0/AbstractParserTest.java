package org.flavio.pl0;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;

public abstract class AbstractParserTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    protected Parser parser;

    protected void setUp(String source) {
        StringReader reader = new StringReader(source);
        Scanner scanner = new Scanner(reader);
        parser = new Parser(scanner);
        parser.scan();
    }

}