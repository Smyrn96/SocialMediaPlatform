/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.csd.uoc.cs359.winter2018.warp;

import gr.csd.uoc.cs359.winter2018.warp.db.PostDB;
import gr.csd.uoc.cs359.winter2018.warp.db.UserDB;
import gr.csd.uoc.cs359.winter2018.warp.model.Post;
import gr.csd.uoc.cs359.winter2018.warp.model.User;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Michalis
 */
@WebServlet(name = "UserProfileServlet", urlPatterns = {"/UserProfileServlet"})
public class UserProfileServlet extends HttpServlet {

    String username="";
    String flag="";
    
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
       String[] array = text.split(",");
        
        username=array[0].substring(array[0].lastIndexOf("=")+1);
        flag=array[1].substring(array[1].lastIndexOf("=")+1);

        try (PrintWriter out = response.getWriter()) {
           
            List<Post> recentPosts =PostDB.getTop10RecentPostsOfUser(username);
            
             out.print("<input class=\"logout_button\" type=\"button\" value=\"Logout\" onclick=\"deleteCookie('usernameCookie'), deleteCookie('passwordCookie'),singlePageLogin()\">");
             out.print("<input class=\"home_button\" type=\"button\" value=\"Home\"onclick=\"ajaxRequestHome()\">");
             out.print("<input class=\"delete_prof_butt\" type=\"button\" value=\"DeleteProfile\"onclick=\"ajaxRequestDeleteProfile('"+username+"')\">");
             
             out.print("<h2>Profile: "+username+"</h2><br>");
             
             for (int j = 0; j < recentPosts.size(); j++) {
                out.println("<div class=\"box\">");
                 out.println("<a class=\"postid\"  href=\"javascript:ajaxRequestPostDetails('"+recentPosts.get(j).getPostID()+"');\">PostID: "+recentPosts.get(j).getPostID()+"</a><br>");
               if(!recentPosts.get(j).getImageURL().equals("")){
                out.print("<img class='resize' src='"+ recentPosts.get(j).getImageURL()+"' alt='image'/><br>");
               }
                out.println("<div class=\"small_box\"> <strong>Comment: </strong>"+recentPosts.get(j).getComment()+ "</div><br>");
                if(!recentPosts.get(j).getResourceURL().equals("")){
                    out.println("<a class=\"link\" href="+recentPosts.get(j).getResourceURL()+"> Visit the link: " +recentPosts.get(j).getResourceURL()+"</a><br><br>");
                 }else if(!recentPosts.get(j).getImageBase64().equals("")){
                    out.print("<img class='resize' src='"+ recentPosts.get(j).getImageBase64()+"' alt='image'/><br>");
               }
                 out.print("<div class=\"date\">Post Date: "+recentPosts.get(j).getCreatedAt()+"</div></div>");
                 if(flag.equals("true")){
                     out.print("<input class=\"delete_button\" type=\"button\" value=\"Delete\" onclick=\"ajaxRequestDeletePost('"+recentPosts.get(j).getPostID()+"')\">");
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
            Logger.getLogger(UserProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UserProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
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
