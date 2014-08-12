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
import model.User;
import model.UserServices;

/**
 *
 * @author nelio_saturnino
 */
public class GetUpdateUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    UserServices services;

    public GetUpdateUserServlet() {
        super();
        services = new UserServices();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String _id = request.getParameter("id");
        int id = Integer.parseInt(_id);

        User user = services.getUserById(id);
        request.setAttribute("user", user);

        RequestDispatcher view = request.getRequestDispatcher("/updateUser.jsp");
        view.forward(request, response);
    }
}
