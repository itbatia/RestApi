package com.itbatia.app.rest;

import com.google.gson.Gson;
import com.itbatia.app.model.Event;
import com.itbatia.app.service.EventService;

import javax.servlet.http.*;
import java.io.*;

import static com.itbatia.app.utils.RestControllerUtil.*;

public class EventRestControllerV1 extends HttpServlet {

    private final EventService eventService = new EventService();
    private final Gson GSON = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer url = request.getRequestURL();
        PrintWriter writer = response.getWriter();
        String endPoint = getEndpointFromUrl(url);

        if (endPoint.equalsIgnoreCase("events")) {
            writer.println(GSON.toJson(eventService.getAllEvents()));
        } else {
            try {
                int eventId = Integer.parseInt(endPoint);
                String eventString = GSON.toJson(eventService.getEvent(eventId));

                if (eventString.contains("null")) {
                    writer.println("Event with id=" + eventId + " doesn't exist");
                } else {
                    writer.println(eventString);
                }
            } catch (Exception e) {
                response.setStatus(400);
                writer.println("Wrong type of parameter id");
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        try {
            Event newEvent = GSON.fromJson(request.getReader(), Event.class);
            Event createdEvent = eventService.createEvent(newEvent.getActivity(), newEvent.getDate(), newEvent.getFile(), newEvent.getUserId());
            String json = GSON.toJson(createdEvent);
            response.setStatus(200);
            response.getWriter().println(json);
        } catch (Exception e) {
            response.setStatus(400);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        StringBuffer url = request.getRequestURL();
        try {
            int eventId = getIdFromUrl(url);
            Event eventToUpdate = GSON.fromJson(request.getReader(), Event.class);
            eventToUpdate.setId(eventId);
            eventService.updateEvent(eventToUpdate);
            response.getWriter().println("Event updated successfully!");
        } catch (Exception e) {
            response.setStatus(400);
            response.getWriter().println("The event with this id does not exist or the data entered is incorrect.");
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer url = request.getRequestURL();
        try {
            int eventId = getIdFromUrl(url);
            eventService.deleteEvent(eventId);
            response.setStatus(200);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.setStatus(400);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            response.setStatus(404);
        }
    }
}
