/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class PostUpdateUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    UserServices services;

    public PostUpdateUserServlet() {
        super();
        services = new UserServices();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String _id = request.getParameter("id");
        String _name = request.getParameter("name");
        String _email = request.getParameter("email");
        String _password = request.getParameter("password");

        String[] _phones = request.getParameterValues("phone");
        List<String> phones = new ArrayList<>();
        phones.addAll(Arrays.asList(_phones));

        Map<String, String> errors = new HashMap<>();
        request.setAttribute("errors", errors);

        int id;
        try {
            id = Integer.parseInt(_id);
        } catch (NumberFormatException ex) {
            errors.put("id", "Id inválido");
            RequestDispatcher view = request.getRequestDispatcher("/updateUser.jsp");
            view.forward(request, response);
            return;
        }

        User user = services.getUserById(id);

        user.setName(_name);
        user.setEmail(_email);
        user.setPassword(_password);
        user.setPhones(phones);

        services.validateName(_name, errors);
        services.validateEmail(_email, id, errors);
        services.validatePassword(_password, errors);

        request.setAttribute("user", user);

        if (errors.isEmpty()) {
            services.updateUser(user);
            response.sendRedirect("ListUsersServlet");
        } else {
            RequestDispatcher view = request.getRequestDispatcher("/updateUser.jsp");
            view.forward(request, response);
        }
    }
}
