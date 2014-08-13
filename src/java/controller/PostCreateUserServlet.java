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
public class PostCreateUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    UserServices services;

    public PostCreateUserServlet() {
        super();
        services = new UserServices();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String _name = request.getParameter("name");
        String _email = request.getParameter("email");
        String _password = request.getParameter("password");
        String[] _phones = request.getParameterValues("phone");
        List<String> phones = new ArrayList<>();
        phones.addAll(Arrays.asList(_phones));

        User user = new User();
        Map<String, String> errors = new HashMap<>();

        user.setName(_name);
        user.setEmail(_email);
        user.setPassword(_password);
        user.setPhones(phones);

        services.validateName(_name, errors);
        services.validateEmail(_email, errors);
        services.validatePassword(_password, errors);

        request.setAttribute("errors", errors);
        request.setAttribute("user", user);

        if (errors.isEmpty()) {
            services.addUser(user);
            response.sendRedirect("ListUsersServlet");
        } else {
            RequestDispatcher view = request.getRequestDispatcher("/createUser.jsp");
            view.forward(request, response);
        }
    }
}
