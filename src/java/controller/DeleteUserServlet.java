/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.UserServices;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nelio_saturnino
 */
public class DeleteUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserServices services;

    public DeleteUserServlet() {
        super();
        services = new UserServices();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        services.deleteUser(id);

        response.sendRedirect("ListUsersServlet");
    }
}
