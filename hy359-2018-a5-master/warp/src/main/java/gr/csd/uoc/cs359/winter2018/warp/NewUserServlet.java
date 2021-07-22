/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.csd.uoc.cs359.winter2018.warp;

import gr.csd.uoc.cs359.winter2018.warp.db.UserDB;
import gr.csd.uoc.cs359.winter2018.warp.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "NewUserServlet", urlPatterns = {"/NewUserServlet"})
public class NewUserServlet extends HttpServlet {

    String username="";
    String email="";
    String password="";
    String confirmPassword="";
    String firstName="";
    String lastName="";
    String country="";
    String city="";
    String address="";
    String birthday="";
    String gender="";
    String job="";
    String interests="";
    String generalinfo="";
    String flag="";
    ServletUtilities a=new ServletUtilities();
    
    
    
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
            throws ServletException, IOException, ClassNotFoundException, Exception{
            response.setContentType("text/html;charset=UTF-8");
            String text=request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            String []array;
            
            array=text.split(",");
            username=array[0].substring(array[0].lastIndexOf("=")+1);
            email = array[1].substring(array[1].lastIndexOf("=") + 1);
            password = array[2].substring(array[2].lastIndexOf("=") + 1);
            String passwordToHash = password;
            String generatedPassword = null;
            try {
                // Create MessageDigest instance for MD5
                MessageDigest md = MessageDigest.getInstance("MD5");
                //Add password bytes to digest
                md.update(passwordToHash.getBytes());
                //Get the hash's bytes
                byte[] bytes = md.digest();
                //This bytes[] has bytes in decimal format;
                //Convert it to hexadecimal format
                StringBuilder sb = new StringBuilder();
                for(int i=0; i< bytes.length ;i++){
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                //Get complete hashed password in hex format
                generatedPassword = sb.toString();
            }
            catch (NoSuchAlgorithmException e) {
                
            }
            password = generatedPassword;
            confirmPassword = array[3].substring(array[3].lastIndexOf("=") + 1);
            firstName = array[4].substring(array[4].lastIndexOf("=") + 1);
            lastName = array[5].substring(array[5].lastIndexOf("=") + 1);
            country = array[6].substring(array[6].lastIndexOf("=") + 1);
            city = array[7].substring(array[7].lastIndexOf("=") + 1);
            address = array[8].substring(array[8].lastIndexOf("=") + 1);
            birthday = array[9].substring(array[9].lastIndexOf("=") + 1);
            gender= array[10].substring(array[10].lastIndexOf("=") + 1);
            job = array[11].substring(array[11].lastIndexOf("=") + 1);
            interests = a.stringFilter(array[12].substring(array[12].lastIndexOf("=") + 1));
            generalinfo = a.stringFilter(array[13].substring(array[13].lastIndexOf("=") + 1));
            flag = array[14].substring(array[14].lastIndexOf("=") + 1);
            
            try (PrintWriter out = response.getWriter()) {
                
                
                if(username.equals("") || email.equals("") || password.equals("") || confirmPassword.equals("") || firstName.equals("") || lastName.equals("") || city.equals("") || birthday.equals("") || job.equals("")){
                    out.print("<strong> Please fill the required gaps");
                }else{
                    User user = new User(username, email, password, firstName, lastName, birthday, job, country, city);
                    user.setAddress(address);
                    user.setGender(gender);
                    user.setInfo(generalinfo);
                    user.setInterests(interests);
                    
                    if(UserDB.checkValidUserName(username) && UserDB.checkValidEmail(email) && flag.equals("1")){
                        UserDB.addUser(user);
                        
                        out.print("<input class=\"logout_button\" type=\"button\" value=\"Logout\" onclick=\"deleteCookie('usernameCookie'), deleteCookie('passwordCookie'),singlePageLogin()\">");
                        out.print("<input class=\"user_info_button\" type=\"button\" value=\"User Info\" onclick=\"ajaxRequestUserInfo()\">");
                        out.print("<input class=\"users_list_button\" type=\"button\" value=\"Users List\"onclick=\"ajaxRequestPrintUsers()\">");
                        out.print("<input class=\"home_button\" type=\"button\" value=\"Home\"onclick=\"ajaxRequestHome()\">");
                        
                        out.print("<h2>Registration Complete! New user :</h2><br>");
                        out.print("<h5>Username : "+ username +"<br>");
                        out.print("Email : "+ email +"<br>");
                        out.print("First name : "+ firstName +"<br>");
                        out.print("Last name : "+ lastName +"<br>");
                        out.print("Country : "+ country +"<br>");
                        out.print("City : "+ city +"<br>");
                        out.print("Address : "+ address +"<br>");
                        out.print("Birthdate : "+ birthday +"<br>");
                        out.print("Occupation : "+ job +"<br>");
                        out.print("Gender : "+ gender +"<br>");
                        out.print("Interests : "+ interests +"<br>");
                        out.print("General Info : "+ generalinfo +"<br></h5>");
                    }else{
                        out.print("<h2>User already exists!</h2><br>");
                    }
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
            Logger.getLogger(NewUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(NewUserServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(NewUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(NewUserServlet.class.getName()).log(Level.SEVERE, null, ex);
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
