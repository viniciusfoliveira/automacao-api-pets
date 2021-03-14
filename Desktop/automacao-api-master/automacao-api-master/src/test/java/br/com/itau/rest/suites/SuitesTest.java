package br.com.itau.rest.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.itau.rest.test.OrderTest;
import br.com.itau.rest.test.PetTest;
import br.com.itau.rest.test.UserTest;


@RunWith(Suite.class)
@SuiteClasses({ PetTest.class, UserTest.class, OrderTest.class})
public class SuitesTest {

}
