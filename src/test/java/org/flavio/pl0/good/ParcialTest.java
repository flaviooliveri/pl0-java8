package org.flavio.pl0.good;

import org.flavio.pl0.ApplicationContext;
import org.junit.Test;

public class ParcialTest  {

    @Test
    public void test() {
        String[] args= {getClass().getResource("/PARCIAL.PL0").getPath()};
        ApplicationContext.main(args);
    }

}