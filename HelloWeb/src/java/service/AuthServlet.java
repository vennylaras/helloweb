/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.TokenDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import org.json.simple.JSONObject;

/**
 *
 * @author Venny
 */
@WebServlet(name = "AuthServlet", urlPatterns = {"/api/auth"})
public class AuthServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AuthServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AuthServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        JSONObject jo = new JSONObject();
        try(PrintWriter out = response.getWriter()){
            response.setStatus(405);
            jo.put("status", 405);
            jo.put("message", "Method not allowed!");
            out.println(jo.toString());
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get parameters from request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDAO user_dao = new UserDAO();
        
        // get user by username from DB  
        User user = user_dao.find(username);
        response.setContentType("application/json");
        JSONObject jo = new JSONObject();
        try(PrintWriter out = response.getWriter()){
            if (user == null) {
                jo.put("status", 401);
                jo.put("message", "User not found!");
            } else {            
                if (!user.getPassword().equals(password)) {
                    jo.put("status", 401);
                    jo.put("message", "Incorrect password!");
                } else {
                    jo.put("status", 200);
                    jo.put("message", "Hello " + user.getFirstName() + " " + user.getLastName() + "!");
                }
            }
            out.println(jo.toString());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
