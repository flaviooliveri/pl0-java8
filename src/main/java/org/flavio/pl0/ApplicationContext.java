package org.flavio.pl0;

import org.flavio.pl0.generator.CodeGenerator;
import org.flavio.pl0.generator.ContentInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ApplicationContext {

    private final static Logger log = LoggerFactory.getLogger(ApplicationContext.class);

    private Parser parser;
    private CodeGenerator codeGenerator;

    public void initialize(Reader reader) {
        TokenEvaluator tokenEvaluator = new TokenEvaluator();
        Scanner scanner = new Scanner(reader, tokenEvaluator);
        IDTable table = new IDTable();
        ContentInitializer contentInitializer = new ContentInitializer();
        this.codeGenerator = new CodeGenerator(contentInitializer);
        this.parser = new Parser(scanner, table, codeGenerator);
    }

    public Parser getParser() {
        return parser;
    }

    public CodeGenerator getCodeGenerator() {
        return codeGenerator;
    }

    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            log.error("Usage: pl0c <source file>\n");
            return;
        }
        String path = args[0];
        File f = new File(path);
        if (!f.exists()) {
            log.error("file not found: " + path);
            return;
        }
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            ApplicationContext applicationContext = new ApplicationContext();
            applicationContext.initialize(reader);
            Parser parser = applicationContext.getParser();
            if (path.toLowerCase().endsWith(".pl0")) {
                path = path.substring(0, path.length() - 4);
            } else {
                log.error("Only files with \".PLO\" extension accepted");
                return;
            }
            parser.compile(path);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
