package com.itbatia.app.servlets;

import com.google.gson.Gson;
import com.itbatia.app.controller.EventController;
import com.itbatia.app.model.Event;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;

public class EventServlet extends HttpServlet {
    private final EventController eventController = new EventController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer urlString = request.getRequestURL();
        Gson gsonEvent = new Gson();
        PrintWriter writer = response.getWriter();

        int lastSlashIndex = urlString.lastIndexOf("/");
        String endPoint = urlString.substring(lastSlashIndex + 1);

        if (endPoint.equalsIgnoreCase("events")) {
            List<Event> events = eventController.getAllEvents();
            writer.println(gsonEvent.toJson(events));
        } else {
            try {
                int eventId = Integer.parseInt(endPoint);
                Event event = eventController.getEvent(eventId);
                String eventString = gsonEvent.toJson(event);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Event newEvent = new Gson().fromJson(request.getReader(), Event.class);
            eventController.createEvent(newEvent.getActivity(), newEvent.getDate(), newEvent.getFile(), newEvent.getUserId());
            response.getWriter().println("Event created successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer urlString = request.getRequestURL();
        try {
            int lastSlashIndex = urlString.lastIndexOf("/");
            int eventId = Integer.parseInt(urlString.substring(lastSlashIndex + 1));
            Event eventToUpdate = new Gson().fromJson(request.getReader(), Event.class);
            eventToUpdate.setId(eventId);
            eventController.updateEvent(eventToUpdate);
            response.getWriter().println("Event updated successfully!");
        } catch (Exception ex) {
            response.getWriter().println("Event with such index doesn't exist or incorrect data was entered.");
            ex.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer urlString = request.getRequestURL();
        try {
            int lastSlashIndex = urlString.lastIndexOf("/");
            int eventId = Integer.parseInt(urlString.substring(lastSlashIndex + 1));
            eventController.deleteEvent(eventId);
            response.getWriter().println("Event with id=" + eventId + " has been successfully deleted!");
        } catch (Exception ex) {
            ex.printStackTrace();
            response.getWriter().println("Wrong type of parameter id");
        }
    }
}
