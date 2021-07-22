/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.csd.uoc.cs359.winter2018.warp;
import gr.csd.uoc.cs359.winter2018.warp.db.PostDB;
import gr.csd.uoc.cs359.winter2018.warp.db.UserDB;
import gr.csd.uoc.cs359.winter2018.warp.model.Post;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    String username="";
    String password="";
    
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
            throws ServletException, IOException, ClassNotFoundException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        
        String text=request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        String[] array = text.split(",");
       
        username=array[0].substring(array[0].lastIndexOf("=")+1);
        password=array[1].substring(array[1].lastIndexOf("=")+1);
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
        
        int flag=0;
        int i=0;
        
        if(UserDB.getUser(username)!=null && UserDB.getUser(username).getPassword().equals(password)){
            flag=1;
                Cookie a1 = new Cookie("usernameCookie", username);
                Cookie a2 = new Cookie("passwordCookie", password);
                a1.setMaxAge(10*60);
                a2.setMaxAge(10*60);
                response.addCookie(a1);
                response.addCookie(a2);
        }
        
        try (PrintWriter out = response.getWriter()) {
            
            if(flag==1){
                
                out.print("<input class=\"logout_button\" type=\"button\" value=\"Logout\" onclick=\"deleteCookie('usernameCookie'), deleteCookie('passwordCookie'),singlePageLogin()\">");
                out.print("<input class=\"user_info_button\" type=\"button\" value=\"User Info\" onclick=\"ajaxRequestUserInfo()\">");
                 out.print("<input class=\"users_list_button\" type=\"button\" value=\"Users List\"onclick=\"ajaxRequestPrintUsers()\">");
                out.print("<input class=\"my_profile_button\" type=\"button\" value=\"My Profile\"onclick=\"ajaxRequestSingleUserPosts('"+username+"')\">");


                out.print("<strong><h3> Welcome Back "+username+"! You are signed-in!<h3></strong><br>");

                out.print("<textarea rows=\"4\" cols=\"50\" maxlength=\"60\" placeholder=\"Say something!\" id=\"new_post\"></textarea>");
                out.print("<textarea rows=\"4\" cols=\"50\" maxlength=\"500\" placeholder=\"Post a Link!\" pattern=\"https://.*\" id=\"new_URL\"></textarea>");
                out.print("<textarea rows=\"4\" cols=\"50\" maxlength=\"500\" placeholder=\"Post an image!\" id=\"new_img\"></textarea><br>");
                out.print("<input class=\"new_post_button\" type=\"button\" value=\"Share\"onclick=\"ajaxRequestNewPost('"+username+"')\">");


              List<Post> posts =PostDB.getTop10RecentPosts();
            
            
             out.print("<h2>Posts List</h2><br>");
             for (int j = 0; j < posts.size(); j++) {
                out.print("<script src=\"js/ajaxRequestFile.js\"></script>");
                out.println("<div class=\"box\">");
                out.println("<a class=\"profile\" href=\"javascript:ajaxRequestSingleUserPosts('"+posts.get(j).getUserName()+"');\">" + posts.get(j).getUserName() + "</a><br><br>");
                 out.println("<a class=\"postid\"  href=\"javascript:ajaxRequestPostDetails('"+posts.get(j).getPostID()+"','"+username+"');\">PostID: "+posts.get(j).getPostID()+"</a><br>");
                if(!posts.get(j).getImageURL().equals("")){
                out.print("<img class='resize' src='"+ posts.get(j).getImageURL()+"' alt='image'/><br>");
                }else if(!posts.get(j).getImageBase64().equals("")){
                    out.print("<img class='resize' src='"+ posts.get(j).getImageBase64()+"' alt='image'/><br>");
                }
                 
                out.println("<div class=\"small_box\"> <strong>Comment: </strong>"+posts.get(j).getComment()+ "</div><br>");
                if(!posts.get(j).getResourceURL().equals("")){
                    out.println("<a class=\"link\" href="+posts.get(j).getResourceURL()+"> Visit the link: " +posts.get(j).getResourceURL()+"</a><br><br>");
                 }
                out.print("<textarea rows=\"4\" cols=\"20\" maxlength=\"35\" placeholder=\"write your comment!\" id=\"new_cmnt"+posts.get(j).getPostID()+"\"></textarea><br>");
                out.print("<input class=\"comment_button\" type=\"button\" value=\"comment\"onclick=\"ajaxRequestNewComment('"+username+"','"+posts.get(j).getPostID()+"')\">");
                
                 out.print("<div class=\"date\">Post Date: "+posts.get(j).getCreatedAt()+"</div></div>");
               
              
            } 
              
            }else{
                out.print("<link href=\"css/signIn.css\" rel=\"stylesheet\" type=\"text/css\">"+"<script src=\"js/face.js\"></script>"+ "<script src=\"js/singlePageApplication.js\"></script>"+" <div id=\"main2\">\n" +
"<header>\n" +
"<h1>Log In!</h1>\n" +
"</header>\n" +
"<nav>"+"<h2>Please fill the sign in form</h2> <br> <strong> You should register first!</strong>\n" +
                        "<input class=\"register_button\" type=\"button\" value=\"Register\" onclick=\"singlePageRegister()\">"+
"	</nav>\n" +
"		Username:<br>\n" +
"      		<input type=\"text\" id=\"username\" name=\"username\" pattern=\"[A-Za-z]{8,}\" title=\"Eight letter username\" required >\n" +
"      		<br>\n" +
"      		<p>Username autocomplete</p>\n" +
"      		<input type=\"radio\" id=\"radioPhoto\" onclick=\"showCamera()\">\n" +
"      		<br><br>\n" +
"      		 Password:<br>\n" +
"      		<input id=\"original_password\" type=\"password\" name=\"Users Password\" pattern=\"(?=.*\\d)(?=.[A-Za-z])(?=.*[#,%,$]).{8,10}\" title=\"Latin characters 8 to 10,one number and one of the following characters:(#,%,$)\" required>\n" +
"      		<br><br>\n" +
"      		<input class=\"sumbit_button\" type=\"submit\" value=\"Submit\" onclick=\"ajaxRequestLogIn()\">\n" +
"      	<video id=\"video\" width=\"540\" height=\"380\" autoplay></video>\n" +
"    	<input type=\"button\" id=\"snap\" value =\"Take Photo\">\n" +
"    	<canvas id=\"canvas\" width=\"540\" height=\"380\"></canvas>\n" +
"    	<input type=\"button\" id=\"upload\" value =\"\" style=\"display:none\">\n" +
"       <input type=\"button\" id=\"search\" value =\"Search Image\">\n" +
"    	<script>\n" +
"        faceRec.init();\n" +
"    	</script>\n" +
"    <footer>\n" +
"        <p>©Copyright 2018 by Emmanouil Smyrnakis. All rights reversed.</p>\n" +
"    </footer>\n" +
"    </div> <br>");
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
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
