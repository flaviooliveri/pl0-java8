package org.flavio.pl0;

import org.junit.Test;

import java.io.BufferedReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ApplicationContextTest {

    @Test
    public void test() throws Exception {
        String[] args= {getClass().getResource("/BIEN-00.PL0").getPath()};
        ApplicationContext.main(args);
        ApplicationContext.main(null);
        args[0] = getClass().getResource("/BIEN-00").getPath();
        ApplicationContext.main(args);
        args[0] = "thisfilenotexist";
        ApplicationContext.main(args);

    }

}