package com.google.sps.servlets;

import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/** Handles requests sent to the /hello URL. Try running a server and navigating to /hello! */
@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ArrayList<String> myList = new ArrayList<>();
    myList.add("I am excited to go get coffee right now.");
    myList.add("I have to work tommorow.");
    myList.add("I feel sleepy right now.");
    Gson gson = new Gson();
    String myJSON = gson.toJson(myList);
    response.setContentType("text/html;");
    response.getWriter().println(myJSON);
  }
}
