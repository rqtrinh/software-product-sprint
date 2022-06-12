package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Information requested in the form
    String name = request.getParameter("name-input");
    String contactMethod = request.getParameter("contact-method");
    String contactInformation = request.getParameter("contact-information");

    // Print the value so you can see it in the server logs.
    System.out.println("Name: " + name);
    System.out.println("Contact Method: " + contactMethod);
    System.out.println("Contact Information: " + contactInformation);

    // Write the value to the response so the user can see it.
    response.getWriter().println("You submitted: " + name + ", " + 
                                contactMethod + ", " + contactInformation);
    // Redirect user back to main page
    response.sendRedirect("https://rtrinh-sps-summer22.appspot.com/");
  }
}
