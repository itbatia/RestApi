package com.itbatia.app.servlets;

import com.google.gson.Gson;
import com.itbatia.app.controller.FileController;
import com.itbatia.app.model.File;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;

public class FileServlet extends HttpServlet {
    private final FileController fileController = new FileController();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer urlString = request.getRequestURL();
        PrintWriter writer = response.getWriter();
        Gson gsonFile = new Gson();

        int lastSlashIndex = urlString.lastIndexOf("/");
        String endPoint = urlString.substring(lastSlashIndex + 1);

        if (endPoint.equalsIgnoreCase("files")) {
            writer.println(gsonFile.toJson(fileController.getAllFiles()));
        } else {
            try {
                int fileId = Integer.parseInt(endPoint);
                String file = gsonFile.toJson(fileController.getFile(fileId));

                if (file.contains("null")) {
                    writer.println("File with id=" + fileId + " doesn't exist");
                } else {
                    writer.println(file);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                writer.println("Wrong type of parameter id.");
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            File file = new Gson().fromJson(request.getReader(), File.class);
            fileController.createFile(file.getName(), file.getContent());
            response.getWriter().println("File created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer urlString = request.getRequestURL();
        try {
            int lastSlashIndex = urlString.lastIndexOf("/");
            String endPoint = urlString.substring(lastSlashIndex + 1);
            int fileId = Integer.parseInt(endPoint);

            File fileToUpdate = new Gson().fromJson(request.getReader(), File.class);
            fileToUpdate.setId(fileId);
            fileController.updateFile(fileToUpdate);
            response.getWriter().println("File updated successfully!");
        } catch (Exception e) {
            response.getWriter().println("File with such index doesn't exist or incorrect data was entered.");
            e.printStackTrace();
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer urlString = request.getRequestURL();
        try {
            int lastSlashIndex = urlString.lastIndexOf("/");
            int fileId = Integer.parseInt(urlString.substring(lastSlashIndex + 1));
            fileController.deleteFile(fileId);
            response.getWriter().println("File with id=" + fileId + " has been successfully deleted!");
        } catch (Exception ex) {
            ex.printStackTrace();
            response.getWriter().println("Wrong type of parameter id");
        }
    }
}