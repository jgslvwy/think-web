package user.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
@SuppressWarnings("deprecation")
public class BeanFactoryTest {

	@Test
	public void testSimpleTest(){
    	  BeanFactory fact = new  XmlBeanFactory(new ClassPathResource("beanTest.xml"));
    	  TestBean testBean  =  (TestBean)fact.getBean("bean");
    	   assertEquals("XXX",testBean.getTestStr());
    }
}
