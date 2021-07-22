/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.csd.uoc.cs359.winter2018.warp;

import gr.csd.uoc.cs359.winter2018.warp.db.PostDB;
import gr.csd.uoc.cs359.winter2018.warp.model.Post;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
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
@WebServlet(name = "HomeServlet", urlPatterns = {"/HomeServlet"})
public class HomeServlet extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, URISyntaxException {
        response.setContentType("text/html;charset=UTF-8");
         
        String username=request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        try (PrintWriter out = response.getWriter()) {
           
             out.print("<input class=\"logout_button\" type=\"button\" value=\"Logout\" onclick=\"deleteCookie('usernameCookie'), deleteCookie('passwordCookie'),singlePageLogin()\">");
                out.print("<input class=\"user_info_button\" type=\"button\" value=\"User Info\" onclick=\"ajaxRequestUserInfo()\">");
                out.print("<input class=\"users_list_button\" type=\"button\" value=\"Users List\"onclick=\"ajaxRequestPrintUsers()\">");
                out.print("<input class=\"my_profile_button\" type=\"button\" value=\"My Profile\"onclick=\"ajaxRequestSingleUserPosts('"+username+"')\">");
                out.print("<strong><h3> Welcome Back !<h3></strong><br>");

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
                out.println("<a class=\"postid\"  href=\"javascript:ajaxRequestPostDetails('"+posts.get(j).getPostID()+"');\">PostID: "+posts.get(j).getPostID()+"</a><br>");
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
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
