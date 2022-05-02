package com.itbatia.app.servlets;

import com.google.gson.Gson;
import com.itbatia.app.controller.UserController;
import com.itbatia.app.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserServlet extends HttpServlet {
    private final UserController userController = new UserController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer urlString = request.getRequestURL();
        Gson gsonUser = new Gson();
        PrintWriter writer = response.getWriter();

        int lastSlashIndex = urlString.lastIndexOf("/");
        String endPoint = urlString.substring(lastSlashIndex + 1);

        if (endPoint.equalsIgnoreCase("users")) {
            List<User> users = userController.getAllUsers();
            writer.println(gsonUser.toJson(users));
        } else {
            try {
                int userId = Integer.parseInt(endPoint);
                User user = userController.getUser(userId);
                String userString = gsonUser.toJson(user);
                if(userString.contains("null")) {
                    writer.println("User with id=" + userId + " doesn't exist");
                } else {
                    writer.println(userString);
                }
            } catch (Exception ex) {
                response.getWriter().println("Wrong type of parameter id");
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User newUser = new Gson().fromJson(request.getReader(), User.class);
            userController.createUser(newUser.getName(), newUser.getEvents());
            response.getWriter().println("User with name=" + newUser.getName() + " was successfully created.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer urlString = request.getRequestURL();

        try {
            int lastSlashIndex = urlString.lastIndexOf("/");
            int userId = Integer.parseInt(urlString.substring(lastSlashIndex + 1));
            User userToUpdate = new Gson().fromJson(request.getReader(), User.class);
            userToUpdate.setId(userId);
            userController.updateUser(userToUpdate);
            response.getWriter().println("User updated successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer urlString = request.getRequestURL();

        int lastIndexOfSlash = urlString.lastIndexOf("/");


        try {
            int userId = Integer.parseInt(urlString.substring(lastIndexOfSlash + 1));
            userController.deleteUser(userId);
            response.getWriter().println("User with id = " + userId + " was successfully deleted.");
        } catch (Exception ex) {
            ex.printStackTrace();
            response.getWriter().println("Wrong type of parameter ID");
        }
    }
}
