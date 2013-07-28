package ua.kiev.naiv.drinkit.cocktail.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.kiev.naiv.drinkit.cocktail.common.Operator;
import ua.kiev.naiv.drinkit.springconfig.TestConfig;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 28.07.13
 * Time: 16:31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class AspectTest {

    @Autowired
    Operator operator;

    @Test
    public void test() throws InterruptedException {
        operator.operate();
    }
}
