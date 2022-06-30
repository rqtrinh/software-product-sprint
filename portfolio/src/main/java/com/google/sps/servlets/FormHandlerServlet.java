package com.google.sps.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.gson.Gson;

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
 
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Contact");
    FullEntity contactEntity =
    Entity.newBuilder(keyFactory.newKey())
        .set("name", name)
        .set("contactMethod", contactMethod)
        .set("contactInformation", contactInformation)
        .build();
    datastore.put(contactEntity);

    //Format string for getWriter 
    String submit = String.format("You submitted: %s, %s, %s", name, contactMethod, contactInformation);

    // Write the value to the response so the user can see it.
    response.getWriter().println(submit);
    // Redirect user back to main page
    response.sendRedirect("https://rtrinh-sps-summer22.appspot.com/");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    Query<Entity> query =
      Query.newEntityQueryBuilder().setKind("Contact").setOrderBy(OrderBy.desc("name")).build();
    QueryResults<Entity> results = datastore.run(query);

    List<String> contacts = new ArrayList<>();
    while (results.hasNext()) {
      Entity entity = results.next();

      String contactName = entity.getString("name");
      contacts.add(contactName);
    }

    Gson gson = new Gson();

    response.setContentType("text/html;");
    response.getWriter().println(gson.toJson(contacts));

  }
}
