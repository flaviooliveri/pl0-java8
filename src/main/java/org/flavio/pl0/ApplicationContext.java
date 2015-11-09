package org.flavio.pl0;

import org.flavio.pl0.generator.CodeGenerator;
import org.flavio.pl0.generator.ContentInitializer;

import java.io.Reader;

public class ApplicationContext {

    private Parser parser;

    public void initialize(Reader reader) {
        TokenEvaluator tokenEvaluator = new TokenEvaluator();
        Scanner scanner = new Scanner(reader, tokenEvaluator);
        IDTable table = new IDTable();
        ContentInitializer contentInitializer = new ContentInitializer();
        CodeGenerator codeGenerator = new CodeGenerator(contentInitializer);
        this.parser = new Parser(scanner, table, codeGenerator);
    }

    public Parser getParser() {
        return parser;
    }

}
