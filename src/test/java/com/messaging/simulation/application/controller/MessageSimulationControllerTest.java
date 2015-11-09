package com.messaging.simulation.application.controller;

import com.messaging.simulation.application.service.MessageSimulationService;
import com.messaging.simulation.application.view.MessageSimulationRequestResource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MessageSimulationControllerTest extends AbstractControllerTest{

    @Autowired
    private MessageSimulationService messageSimulationService;

    @Before
    public void setUp(){
        super.setUp();
        messageSimulationService.deleteAll();
    }

    @Test
    public void shouldReturnTestMessage() throws Exception {
        //given
        String uri = "/simulation/";

        //when
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();

        //then
        assertThat(result.getResponse().getStatus(), is(200));
        assertThat(content, is("Hello World Spring Boot"));
    }

    @Test
    public void shouldCreateMessage() throws Exception {
        //given
        String uri = "/simulation/chat";

        MessageSimulationRequestResource messageSimulationRequestResource =
                new MessageSimulationRequestResource("Bill", "hello world", 60);

        String inputJson = super.mapToJson(messageSimulationRequestResource);

        //when
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

        //then
        assertThat(result.getResponse().getStatus(), is(201));
    }

    @Test
    public void shouldFindMessageById() throws Exception {
        //given
        MessageSimulationRequestResource messageSimulationRequestResource =
                new MessageSimulationRequestResource("Bill", "hello world", 60);

        String inputJson = super.mapToJson(messageSimulationRequestResource);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/simulation/chat")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

        String createdMessageResponse = result.getResponse().getContentAsString();

        //bruteforce to get random GUID
        String id = createdMessageResponse.substring(7, createdMessageResponse.length() - 2);

        assertThat(result.getResponse().getStatus(), is(201));

        //when
        MvcResult searchedMessage = mvc.perform(MockMvcRequestBuilders.get("/simulation/chat/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

        String searchResponse = searchedMessage.getResponse().getContentAsString();

        assertThat(searchedMessage.getResponse().getStatus(), is(200));
        assertThat(searchResponse, containsString("{\"username\":\"Bill\",\"text\":\"hello world\",\"expiration_date\":"));
    }

    @Test
    public void shouldFindMessageByUsername() throws Exception {
        //given
        MessageSimulationRequestResource messageSimulationRequestResource =
                new MessageSimulationRequestResource("Bill", "hello world", 60);

        String inputJson = super.mapToJson(messageSimulationRequestResource);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/simulation/chat")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

        String createdMessageResponse = result.getResponse().getContentAsString();

        //bruteforce to get random GUID
        String id = createdMessageResponse.substring(7, createdMessageResponse.length() - 2);

        assertThat(result.getResponse().getStatus(), is(201));

        //when
        MvcResult searchedMessage = mvc.perform(MockMvcRequestBuilders.get("/simulation/chats/{username}", "Bill")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

        String searchResponse = searchedMessage.getResponse().getContentAsString();

        assertThat(searchedMessage.getResponse().getStatus(), is(200));
        assertThat(searchResponse, is("[{\"id\":\"" + id + "\",\"text\":\"hello world\"}]"));
    }
}