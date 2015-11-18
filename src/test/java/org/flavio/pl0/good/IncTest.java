package org.flavio.pl0.good;

import org.flavio.pl0.ApplicationContext;
import org.flavio.pl0.Parser;
import org.flavio.pl0.generator.CodeGenerator;
import org.junit.Test;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;

public class IncTest{

    @Test
    public void test() {
        String[] args= {getClass().getResource("/INC.PL0").getPath()};
        ApplicationContext.main(args);
    }

}