/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.csd.uoc.cs359.winter2018.warp;

import gr.csd.uoc.cs359.winter2018.warp.db.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "RegisterUserServlet", urlPatterns = {"/RegisterUserServlet"})
public class RegisterUserServlet extends HttpServlet {

    String username="";
    String email="";
    String password="";
    String confirmPassword="";
    String firstname="";
    String city="";
    String lastname="";
    String job="";
    String interests="";
    String generalinfo="";
    
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
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        
        String text=request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        String []array ;
        array=text.split("=");
        
        /*array[0] is the type of the variable and array[1] is its value*/
        
        if(array[0].equals("username")){
            username=array[1];
        }else if(array[0].equals("email")){
            email=array[1];
        }else if(array[0].equals("password")){
            password=array[1];
        }else if(array[0].equals("confirmpassword")){
            confirmPassword=array[1];
        }else if(array[0].equals("firstname")){
            firstname=array[1];
        }else if(array[0].equals("lastname")){
            lastname=array[1];
        }else if(array[0].equals("city")){
            city=array[1];
        }else if(array[0].equals("job")){
            job=array[1];
        }else if(array[0].equals("interests")){
            interests=array[1];
        }else{
            generalinfo=array[1];
        }
          
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            if(array[0].equals("username") && !UserDB.checkValidUserName(username)){
                out.print("This particular username is used!");
            }
            
            if(array[0].equals("username") && !username.matches("[A-Za-z]{8,}")){
                out.print("Status code 400 Bad request: ");
                out.print(array[0]);
            }
             if(array[0].equals("email") && !UserDB.checkValidEmail(email)){
                out.print("This particular email is used!");
            }
            if(array[0].equals("email") && !email.matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")){
                out.print("Status code 400 Bad request: ");
                out.print(array[0]);
            }
            if(array[0].equals("password") && !password.matches("^([a-zA-Z0-9%$#]{8,10})$")){
                out.print("Status code 400 Bad request: ");
                out.print(array[0]);
            }
            if(array[0].equals("confirmpassword") && !confirmPassword.matches("^([a-zA-Z0-9%$#]{8,10})$")){
                out.print("Status code 400 Bad request: ");
                out.print(array[0]);
            }
            if(array[0].equals("city") && !city.matches("[A-Za-z]{2,20}")){
                out.print("Status code 400 Bad request: ");
                out.print(array[0]);
            }
            if(array[0].equals("firstname") && !firstname.matches("[A-Za-z]{3,15}")){
                out.print("Status code 400 Bad request: ");
                out.print(array[0]);
            }
            if(array[0].equals("lastname") && !lastname.matches("[A-Za-z]{3,15}")){
                out.print("Status code 400 Bad request: ");
                out.print(array[0]);
            }
            if(array[0].equals("job") && !job.matches("[A-Za-z]{3,15}")){
                out.print("Status code 400 Bad request: ");
                out.print(array[0]);
            }
            if(array[0].equals("interests") && interests.length()>100){
                out.print("Status code 400 Bad request: ");
                out.print(array[0]);
            }
            if(array[0].equals("generalinfo") && generalinfo.length()>500){
                out.print("Status code 400 Bad request: ");
                out.print(array[0]);
            }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterUserServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterUserServlet.class.getName()).log(Level.SEVERE, null, ex);
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
