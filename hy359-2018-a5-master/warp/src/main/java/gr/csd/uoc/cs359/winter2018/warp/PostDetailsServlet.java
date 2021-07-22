/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.csd.uoc.cs359.winter2018.warp;

import gr.csd.uoc.cs359.winter2018.warp.db.CommentDB;
import gr.csd.uoc.cs359.winter2018.warp.db.PostDB;
import gr.csd.uoc.cs359.winter2018.warp.model.Comment;
import gr.csd.uoc.cs359.winter2018.warp.model.Post;
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
 * @author User
 */
@WebServlet(name = "PostDetailsServlet", urlPatterns = {"/PostDetailsServlet"})
public class PostDetailsServlet extends HttpServlet {

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
            String []array;
            
            array=text.split(",");
            String postid=array[0].substring(array[0].lastIndexOf("=")+1);
            String username = array[1].substring(array[1].lastIndexOf("=") + 1);
        
       // String postid=request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        int postID = Integer.parseInt(postid);
        List<Comment> comments = CommentDB.getComments(postID);

        Post post=PostDB.getPost(postID);     
        
        try (PrintWriter out = response.getWriter()) {
            
            out.print("<input class=\"logout_button\" type=\"button\" value=\"Logout\" onclick=\"deleteCookie('usernameCookie'), deleteCookie('passwordCookie'),singlePageLogin()\">");
            out.print("<input class=\"home_button\" type=\"button\" value=\"Home\"onclick=\"ajaxRequestHome()\">");
            
            out.print("<h2>Post "+post.getPostID()+" Info</h2><br>");
            
            out.print("<script src=\"js/ajaxRequestFile.js\"></script>");
            out.println("<div class=\"box\">"); 
            out.println("<a class=\"profile\" href=\"javascript:ajaxRequestSingleUserPosts('"+post.getUserName()+"');\">" + post.getUserName() + "</a><br><br>"); 
            if(!post.getImageURL().equals("")){
                out.print("<img class='resize' src='"+ post.getImageURL()+"' alt='image'/><br>");
               }
            if(!post.getImageBase64().equals("")){
                out.print("<img class='resize' src='"+ post.getImageBase64()+"' alt='image'/><br>");
               }
             out.println("<div class=\"small_box\"> <strong>Comment: </strong>"+post.getComment()+ "</div><br>");
             if(!post.getResourceURL().equals("")){
                    out.println("<a class=\"link\" href="+post.getResourceURL()+"> Visit the link: " +post.getResourceURL()+"</a><br><br>");
                 }
              out.println("<h4>Latitude: "+post.getLatitude()+"</h5>");
              out.println("<h4>Longitude: "+post.getLongitude()+"</h5><br>");
              for(int i=0 ; i < comments.size(); i++){
                   out.println("<div class=\"comment_box\"> <strong>"+comments.get(i).getUserName()+"</strong> : "+comments.get(i).getComment()+"</div><br>");
                   if(comments.get(i).getUserName().equals(username)){
                        
                        out.print("<input class=\"delete_cmnt\" type=\"button\" value=\"delcomnt\"onclick=\"ajaxRequestDeleteComment('"+comments.get(i).getID()+"')\">");
                   }
              }
              out.print("<div class=\"date\">Post Date: "+post.getCreatedAt()+"</div></div>");
              
              
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
            Logger.getLogger(PostDetailsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PostDetailsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
