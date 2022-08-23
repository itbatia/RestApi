package com.itbatia.app.rest;

import com.google.gson.Gson;
import com.itbatia.app.model.Event;
import com.itbatia.app.service.EventService;

import javax.servlet.http.*;
import java.io.*;

import static com.itbatia.app.utils.RestControllerUtil.*;

public class EventRestControllerV1 extends HttpServlet {

    private final EventService eventService = new EventService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer url = request.getRequestURL();
        PrintWriter writer = response.getWriter();
        String endPoint = getEndpointFromUrl(url);

        if (endPoint.equalsIgnoreCase("events")) {
            writer.println(new Gson().toJson(eventService.getAllEvents()));
        } else {
            try {
                int eventId = Integer.parseInt(endPoint);
                String eventString = new Gson().toJson(eventService.getEvent(eventId));

                if (eventString.contains("null")) {
                    writer.println("Event with id=" + eventId + " doesn't exist");
                } else {
                    writer.println(eventString);
                }
            } catch (Exception e) {
                writer.println("Wrong type of parameter id");
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        try {
            Event newEvent = new Gson().fromJson(request.getReader(), Event.class);
            eventService.createEvent(newEvent.getActivity(), newEvent.getDate(), newEvent.getFile(), newEvent.getUserId());
            response.getWriter().println("Event created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        StringBuffer url = request.getRequestURL();
        try {
            int eventId = getIdFromUrl(url);
            Event eventToUpdate = new Gson().fromJson(request.getReader(), Event.class);
            eventToUpdate.setId(eventId);
            eventService.updateEvent(eventToUpdate);
            response.getWriter().println("Event updated successfully!");
        } catch (Exception e) {
            response.getWriter().println("The event with this id doesn't exist or the data entered is incorrect.");
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
