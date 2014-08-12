/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserServices;

/**
 *
 * @author nelio_saturnino
 */
public class ListUsersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserServices services;

    public ListUsersServlet() {
        super();
        services = new UserServices();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("users", services.getAllUsers());

        RequestDispatcher view = request.getRequestDispatcher("/listUsers.jsp");
        view.forward(request, response);
    }
}
