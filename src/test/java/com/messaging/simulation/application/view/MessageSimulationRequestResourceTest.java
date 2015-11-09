package com.messaging.simulation.application.view;

import org.hibernate.validator.HibernateValidator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MessageSimulationRequestResourceTest {


    private LocalValidatorFactoryBean localValidatorFactory;

    @Before
    public void setUp() throws Exception {
        localValidatorFactory = new LocalValidatorFactoryBean();
        localValidatorFactory.setProviderClass(HibernateValidator.class);
        localValidatorFactory.afterPropertiesSet();

    }

    @Test
    public void shouldThrowValidationExceptionForEmptyUsername() throws Exception {
        //given
        MessageSimulationRequestResource messageSimulationRequestResource = new MessageSimulationRequestResource(null, "Sample text", 10);

        Set<ConstraintViolation<MessageSimulationRequestResource>> constraintViolations = localValidatorFactory.validate(messageSimulationRequestResource);

        assertThat(constraintViolations.size(), is(1));
        assertThat(constraintViolations.toString(), containsString("may not be empty"));
    }

    @Test
    public void shouldThrowValidationExceptionForEmptyText() throws Exception {
        //given
        MessageSimulationRequestResource messageSimulationRequestResource = new MessageSimulationRequestResource("Bill", null, 10);

        Set<ConstraintViolation<MessageSimulationRequestResource>> constraintViolations = localValidatorFactory.validate(messageSimulationRequestResource);

        assertThat(constraintViolations.size(), is(1));
        assertThat(constraintViolations.toString(), containsString("may not be empty"));
    }
}