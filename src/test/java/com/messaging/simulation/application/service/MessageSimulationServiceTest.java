package com.messaging.simulation.application.service;

import com.messaging.simulation.domain.MessageSimulation;
import com.messaging.simulation.domain.repository.MessageSimulationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MessageSimulationServiceTest {

    private MessageSimulationService messageSimulationService;

    @Mock
    private MessageSimulationRepository messageSimulationRepository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        messageSimulationService = new MessageSimulationService(messageSimulationRepository);
    }

    @Test
    public void shouldFindById() throws Exception {
        //given
        MessageSimulation messageSimulation = new MessageSimulation(
                "e03a4571-f4a5-46d9-9365-86988f64c0da",
                "Bob",
                "This is a message",
                "2015-11-08 12:00:00");

        when(messageSimulationRepository.findOne("e03a4571-f4a5-46d9-9365-86988f64c0da"))
                .thenReturn(messageSimulation);

        //when
        MessageSimulation returnedMessageSimulation = messageSimulationService.findById("e03a4571-f4a5-46d9-9365-86988f64c0da");

        //then
        assertThat(returnedMessageSimulation.getUsername(), is("Bob"));
        assertThat(returnedMessageSimulation.getText(), is("This is a message"));
        assertThat(returnedMessageSimulation.getExpiration_date(), is("2015-11-08 12:00:00"));
    }

    @Test
    public void shouldFindByUsername() throws Exception {
        //given
        MessageSimulation messageOne = new MessageSimulation(
                "e03a4571-f4a5-46d9-9365-86988f64c0da",
                "Bob",
                "This is a message",
                "2015-11-08 12:00:00");
        MessageSimulation messageTwo = new MessageSimulation(
                "f04a4571-f4a5-46d9-9365-86988f64c0da",
                "Bob",
                "This is a message also",
                "2015-11-08 12:10:00");

        List<MessageSimulation> value = Arrays.asList(messageOne, messageTwo);

        when(messageSimulationRepository.findByUsername("Bob"))
                .thenReturn(value);

        //when
        List<MessageSimulation> messageSimulationList = messageSimulationService.findByUsername("Bob");

        //then
        assertThat(messageSimulationList.get(0).getId(), is("e03a4571-f4a5-46d9-9365-86988f64c0da"));
        assertThat(messageSimulationList.get(1).getId(), is("f04a4571-f4a5-46d9-9365-86988f64c0da"));
    }
}