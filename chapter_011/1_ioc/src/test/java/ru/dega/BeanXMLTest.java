package ru.dega;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.dega.beans.BeanAggregation;
import ru.dega.beans.BeanAnnotation;
import ru.dega.beans.BeanXML;

import static org.junit.Assert.assertNotNull;

/**
 * BeanXMLTest class.
 *
 * @author Denis
 * @since 02.10.2017
 */
public class BeanXMLTest {
    /**
     * Test beans.
     */
    @Test
    public void whenLoadContextShouldGetBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        BeanXML beanXML = context.getBean(BeanXML.class);
        assertNotNull(beanXML);

        BeanAggregation beanAggregation = context.getBean(BeanAggregation.class);
        assertNotNull(beanAggregation);

        BeanAnnotation beanAnnotation = context.getBean(BeanAnnotation.class);
        assertNotNull(beanAnnotation);
    }
}