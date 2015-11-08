package com.messaging.simulation.application.controller.path;

public interface MessageSimulationPaths {
    String ROOT_PATH = "/simulation";
    String TEST_PATH = "/";
    String CHAT = "/chat";
    String ID = CHAT + "/{id}";
    String USER_NAME = "/chats/{username}";
}