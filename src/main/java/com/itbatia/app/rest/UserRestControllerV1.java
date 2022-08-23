package com.itbatia.app.rest;

import com.google.gson.Gson;
import com.itbatia.app.model.User;
import com.itbatia.app.service.UserService;

import javax.servlet.http.*;
import java.io.*;

import static com.itbatia.app.utils.RestControllerUtil.*;

public class UserRestControllerV1 extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer url = request.getRequestURL();
        PrintWriter writer = response.getWriter();
        String endPoint = getEndpointFromUrl(url);

        if (endPoint.equalsIgnoreCase("users")) {
            writer.println(new Gson().toJson(userService.getAllUsers()));
        } else {
            try {
                int userId = Integer.parseInt(endPoint);
                String userString = new Gson().toJson(userService.getUser(userId));

                if (userString.contains("null")) {
                    writer.println("User with id=" + userId + " doesn't exist");
                } else {
                    writer.println(userString);
                }
            } catch (Exception ex) {
                writer.println("Wrong type of parameter id");
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        try {
            User newUser = new Gson().fromJson(request.getReader(), User.class);
            userService.createUser(newUser.getName(), newUser.getEvents());
            response.getWriter().println("User with name=" + newUser.getName() + " was successfully created.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        StringBuffer url = request.getRequestURL();
        try {
            int userId = getIdFromUrl(url);
            User userToUpdate = new Gson().fromJson(request.getReader(), User.class);
            userToUpdate.setId(userId);
            userService.updateUser(userToUpdate);
            response.getWriter().println("User updated successfully!");
        } catch (Exception e) {
            response.getWriter().println("The user with this id doesn't exist or the data entered is incorrect.");
            e.printStackTrace();
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer url = request.getRequestURL();
        try {
            int userId = getIdFromUrl(url);
            userService.deleteUser(userId);
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
