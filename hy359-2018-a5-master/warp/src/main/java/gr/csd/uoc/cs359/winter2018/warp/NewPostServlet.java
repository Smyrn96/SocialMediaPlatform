/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.csd.uoc.cs359.winter2018.warp;

import gr.csd.uoc.cs359.winter2018.warp.db.PostDB;
import gr.csd.uoc.cs359.winter2018.warp.model.Post;
import gr.csd.uoc.cs359.winter2018.warp.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @author user
 */
@WebServlet(name = "NewPostServlet", urlPatterns = {"/NewPostServlet"})
public class NewPostServlet extends HttpServlet {

    String userName = "";
    String comment = "";
    String resourceURL = "";
    String imageURL = "";
    String imageBase64 = "";
    String latitude = "";
    String longitude = "";
    String createdAt = "";
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
        
        String text=request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        String []array;

        array=text.split(",");
      
        userName = array[0].substring(array[0].lastIndexOf("=") + 1);
        comment = array[1].substring(array[1].lastIndexOf("=") + 1);
        resourceURL = array[2].substring(array[2].lastIndexOf("=") + 1);
        imageURL = array[3].substring(array[3].lastIndexOf("=") + 1);
        imageBase64 = array[4].substring(array[4].lastIndexOf("=") + 1);
        latitude = array[5].substring(array[5].lastIndexOf("=") + 1);
        longitude = array[6].substring(array[6].lastIndexOf("=") + 1);
        try (PrintWriter out = response.getWriter()) {
            
            
            Post post = new Post();
            post.setUserName(userName);
            post.setComment(comment);
            post.setResourceURL(resourceURL);
            post.setImageURL(imageURL);
            post.setImageBase64(imageBase64);
            post.setLatitude(latitude);
            post.setLongitude(longitude);
                //get time
                DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                Date dateobj = new Date();
                createdAt = df.format(dateobj);
            post.setCreatedAt(createdAt);
                
             // Check that everything is ok
            post.checkFields();   

            PostDB.addPost(post);
            out.print("<input class=\"logout_button\" type=\"button\" value=\"Logout\" onclick=\"deleteCookie('usernameCookie'), deleteCookie('passwordCookie'),singlePageLogin()\">");
            out.print("<input class=\"user_info_button\" type=\"button\" value=\"User Info\" onclick=\"ajaxRequestUserInfo()\">");
            out.print("<input class=\"users_list_button\" type=\"button\" value=\"Users List\"onclick=\"ajaxRequestPrintUsers()\">");
            out.print("<input class=\"my_profile_button\" type=\"button\" value=\"My Profile\"onclick=\"ajaxRequestSingleUserPosts('"+userName+"')\">");
                    
            out.print("<h2>Post Uploaded Successfully</h2><br>");
            
            List<Post> posts =PostDB.getTop10RecentPosts();
            out.print("<h2>Updated Posts List</h2><br>");
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
                 out.print("<div class=\"date\">Post Date: "+posts.get(j).getCreatedAt()+"</div></div>");
            }
                    
        } catch (Exception ex) {
            Logger.getLogger(NewPostServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
