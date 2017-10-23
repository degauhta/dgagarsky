package ru.dega.beans;

import org.springframework.stereotype.Component;

/**
 * BeanAggregation class.
 *
 * @author Denis
 * @since 02.10.2017
 */
@Component
public class BeanAggregation {
    /**
     * BeanXML.
     */
    private BeanXML beanXML;

    /**
     * Instantiates a new Bean aggregation.
     *
     * @param beanXML the bean xml
     */
    public BeanAggregation(BeanXML beanXML) {
        this.beanXML = beanXML;
    }
}