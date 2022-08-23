package com.itbatia.app.rest;

import com.google.gson.Gson;
import com.itbatia.app.model.File;
import com.itbatia.app.service.FileService;

import javax.servlet.http.*;
import java.io.*;

import static com.itbatia.app.utils.RestControllerUtil.*;

public class FileRestControllerV1 extends HttpServlet {

    private final FileService fileService = new FileService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer url = request.getRequestURL();
        PrintWriter writer = response.getWriter();
        String endPoint = getEndpointFromUrl(url);

        if (endPoint.equalsIgnoreCase("files")) {
            writer.println(new Gson().toJson(fileService.getAllFiles()));
        } else {
            try {
                int fileId = Integer.parseInt(endPoint);
                String fileString = new Gson().toJson(fileService.getFile(fileId));

                if (fileString.contains("null")) {
                    writer.println("File with id=" + fileId + " doesn't exist");
                } else {
                    writer.println(fileString);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                writer.println("Wrong type of parameter id.");
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        try {
            File file = new Gson().fromJson(request.getReader(), File.class);
            fileService.createFile(file.getName(), file.getContent());
            response.getWriter().println("File created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        StringBuffer url = request.getRequestURL();
        try {
            int fileId = getIdFromUrl(url);
            File fileToUpdate = new Gson().fromJson(request.getReader(), File.class);
            fileToUpdate.setId(fileId);
            fileService.updateFile(fileToUpdate);
            response.getWriter().println("File updated successfully!");
        } catch (Exception e) {
            response.getWriter().println("The file with this id doesn't exist or the data entered is incorrect.");
            e.printStackTrace();
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        StringBuffer url = request.getRequestURL();
        try {
            int fileId = getIdFromUrl(url);
            fileService.deleteFile(fileId);
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