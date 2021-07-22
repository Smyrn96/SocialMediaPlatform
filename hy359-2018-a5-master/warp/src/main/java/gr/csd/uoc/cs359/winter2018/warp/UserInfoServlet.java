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
 * @author internet
 */
@WebServlet(name = "UserInfoServlet", urlPatterns = {"/UserInfoServlet"})
public class UserInfoServlet extends HttpServlet {

    String username="";
    String flagUpdate="";
    
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
        flagUpdate=array[1].substring(array[1].lastIndexOf("=")+1);
        
        User tempUser=UserDB.getUser(username);
        
        try (PrintWriter out = response.getWriter()) {
            
            
            if(flagUpdate.equals("false")){
                
                out.print("<input class=\"logout_button\" type=\"button\" value=\"Logout\" onclick=\"deleteCookie('usernameCookie'), deleteCookie('passwordCookie'),singlePageLogin()\">");
                out.print("<input class=\"home_button\" type=\"button\" value=\"Home\"onclick=\"ajaxRequestHome('"+username+"')\">");
                out.print("<br><h5>");
                out.println("Username: " + tempUser.getUserName() + "<br>");
                out.println("Email: " + tempUser.getEmail() + "<br>");
                out.println("Password: "
                    + "<input type=\"password\" placeholder=\"Password\" id=\"password\" "
                    + "value=" + tempUser.getPassword() + ">"
                    + "<br>"
                    + "First Name: "
                    + "<input type=\"text\" placeholder=\"First Name\" id=\"firstname\" "
                    + "value=" + tempUser.getFirstName() + " required>"
                    + "<br>"
                    + "Last Name: "
                    + "<input type=\"text\" placeholder=\"Last Name\"  id=\"lastname\" value=" + tempUser.getLastName() + " "
                    + "required>"
                    + "<br>"
                    + "BirthDate: "
                    + "<input type=\"date\" name=\"birthdate\" id=\"birthday\" value=" + tempUser.getBirthDate() + " required>"
                    + "<br>"
                    + "Occupation: "
                    + "<input type=\"text\" placeholder=\"Occupation\" id=\"job\" value=" + tempUser.getOccupation() + " "
                    + " required>"
                    + "<br>"
                    + "Country: " + tempUser.getCountry() + "<br>"
                    + "<select name=\"country\" id=\"country\" "
                    + "                        <option value=\"Afganistan\">Afghanistan</option>\n"
                    + "                        <option value=\"Albania\">Albania</option>\n"
                    + "                        <option value=\"Algeria\">Algeria</option>\n"
                    + "                        <option value=\"American Samoa\">American Samoa</option>\n"
                    + "                        <option value=\"Andorra\">Andorra</option>\n"
                    + "                        <option value=\"Angola\">Angola</option>\n"
                    + "                        <option value=\"Anguilla\">Anguilla</option>\n"
                    + "                        <option value=\"Antigua &amp; Barbuda\">Antigua &amp; Barbuda</option>\n"
                    + "                        <option value=\"Argentina\">Argentina</option>\n"
                    + "                        <option value=\"Armenia\">Armenia</option>\n"
                    + "                        <option value=\"Aruba\">Aruba</option>\n"
                    + "                        <option value=\"Australia\">Australia</option>\n"
                    + "                        <option value=\"Austria\">Austria</option>\n"
                    + "                        <option value=\"Azerbaijan\">Azerbaijan</option>\n"
                    + "                        <option value=\"Bahamas\">Bahamas</option>\n"
                    + "                        <option value=\"Bahrain\">Bahrain</option>\n"
                    + "                        <option value=\"Bangladesh\">Bangladesh</option>\n"
                    + "                        <option value=\"Barbados\">Barbados</option>\n"
                    + "                        <option value=\"Belarus\">Belarus</option>\n"
                    + "                        <option value=\"Belgium\">Belgium</option>\n"
                    + "                        <option value=\"Belize\">Belize</option>\n"
                    + "                        <option value=\"Benin\">Benin</option>\n"
                    + "                        <option value=\"Bermuda\">Bermuda</option>\n"
                    + "                        <option value=\"Bhutan\">Bhutan</option>\n"
                    + "                        <option value=\"Bolivia\">Bolivia</option>\n"
                    + "                        <option value=\"Bonaire\">Bonaire</option>\n"
                    + "                        <option value=\"Bosnia &amp; Herzegovina\">Bosnia &amp; Herzegovina</option>\n"
                    + "                        <option value=\"Botswana\">Botswana</option>\n"
                    + "                        <option value=\"Brazil\">Brazil</option>\n"
                    + "                        <option value=\"British Indian Ocean Ter\">British Indian Ocean Ter</option>\n"
                    + "                        <option value=\"Brunei\">Brunei</option>\n"
                    + "                        <option value=\"Bulgaria\">Bulgaria</option>\n"
                    + "                        <option value=\"Burkina Faso\">Burkina Faso</option>\n"
                    + "                        <option value=\"Burundi\">Burundi</option>\n"
                    + "                        <option value=\"Cambodia\">Cambodia</option>\n"
                    + "                        <option value=\"Cameroon\">Cameroon</option>\n"
                    + "                        <option value=\"Canada\">Canada</option>\n"
                    + "                        <option value=\"Canary Islands\">Canary Islands</option>\n"
                    + "                        <option value=\"Cape Verde\">Cape Verde</option>\n"
                    + "                        <option value=\"Cayman Islands\">Cayman Islands</option>\n"
                    + "                        <option value=\"Central African Republic\">Central African Republic</option>\n"
                    + "                        <option value=\"Chad\">Chad</option>\n"
                    + "                        <option value=\"Channel Islands\">Channel Islands</option>\n"
                    + "                        <option value=\"Chile\">Chile</option>\n"
                    + "                        <option value=\"China\">China</option>\n"
                    + "                        <option value=\"Christmas Island\">Christmas Island</option>\n"
                    + "                        <option value=\"Cocos Island\">Cocos Island</option>\n"
                    + "                        <option value=\"Colombia\">Colombia</option>\n"
                    + "                        <option value=\"Comoros\">Comoros</option>\n"
                    + "                        <option value=\"Congo\">Congo</option>\n"
                    + "                        <option value=\"Cook Islands\">Cook Islands</option>\n"
                    + "                        <option value=\"Costa Rica\">Costa Rica</option>\n"
                    + "                        <option value=\"Cote DIvoire\">Cote D'Ivoire</option>\n"
                    + "                        <option value=\"Croatia\">Croatia</option>\n"
                    + "                        <option value=\"Cuba\">Cuba</option>\n"
                    + "                        <option value=\"Curaco\">Curacao</option>\n"
                    + "                        <option value=\"Cyprus\">Cyprus</option>\n"
                    + "                        <option value=\"Czech Republic\">Czech Republic</option>\n"
                    + "                        <option value=\"Denmark\">Denmark</option>\n"
                    + "                        <option value=\"Djibouti\">Djibouti</option>\n"
                    + "                        <option value=\"Dominica\">Dominica</option>\n"
                    + "                        <option value=\"Dominican Republic\">Dominican Republic</option>\n"
                    + "                        <option value=\"East Timor\">East Timor</option>\n"
                    + "                        <option value=\"Ecuador\">Ecuador</option>\n"
                    + "                        <option value=\"Egypt\">Egypt</option>\n"
                    + "                        <option value=\"El Salvador\">El Salvador</option>\n"
                    + "                        <option value=\"Equatorial Guinea\">Equatorial Guinea</option>\n"
                    + "                        <option value=\"Eritrea\">Eritrea</option>\n"
                    + "                        <option value=\"Estonia\">Estonia</option>\n"
                    + "                        <option value=\"Ethiopia\">Ethiopia</option>\n"
                    + "                        <option value=\"Falkland Islands\">Falkland Islands</option>\n"
                    + "                        <option value=\"Faroe Islands\">Faroe Islands</option>\n"
                    + "                        <option value=\"Fiji\">Fiji</option>\n"
                    + "                        <option value=\"Finland\">Finland</option>\n"
                    + "                        <option value=\"France\">France</option>\n"
                    + "                        <option value=\"French Guiana\">French Guiana</option>\n"
                    + "                        <option value=\"French Polynesia\">French Polynesia</option>\n"
                    + "                        <option value=\"French Southern Ter\">French Southern Ter</option>\n"
                    + "                        <option value=\"Gabon\">Gabon</option>\n"
                    + "                        <option value=\"Gambia\">Gambia</option>\n"
                    + "                        <option value=\"Georgia\">Georgia</option>\n"
                    + "                        <option value=\"Germany\">Germany</option>\n"
                    + "                        <option value=\"Ghana\">Ghana</option>\n"
                    + "                        <option value=\"Gibraltar\">Gibraltar</option>\n"
                    + "                        <option value=\"Great Britain\">Great Britain</option>\n"
                    + "                        <option value=\"Greece\" selected=\"selected\">Greece</option>\n"
                    + "                        <option value=\"Greenland\">Greenland</option>\n"
                    + "                        <option value=\"Grenada\">Grenada</option>\n"
                    + "                        <option value=\"Guadeloupe\">Guadeloupe</option>\n"
                    + "                        <option value=\"Guam\">Guam</option>\n"
                    + "                        <option value=\"Guatemala\">Guatemala</option>\n"
                    + "                        <option value=\"Guinea\">Guinea</option>\n"
                    + "                        <option value=\"Guyana\">Guyana</option>\n"
                    + "                        <option value=\"Haiti\">Haiti</option>\n"
                    + "                        <option value=\"Hawaii\">Hawaii</option>\n"
                    + "                        <option value=\"Honduras\">Honduras</option>\n"
                    + "                        <option value=\"Hong Kong\">Hong Kong</option>\n"
                    + "                        <option value=\"Hungary\">Hungary</option>\n"
                    + "                        <option value=\"Iceland\">Iceland</option>\n"
                    + "                        <option value=\"India\">India</option>\n"
                    + "                        <option value=\"Indonesia\">Indonesia</option>\n"
                    + "                        <option value=\"Iran\">Iran</option>\n"
                    + "                        <option value=\"Iraq\">Iraq</option>\n"
                    + "                        <option value=\"Ireland\">Ireland</option>\n"
                    + "                        <option value=\"Isle of Man\">Isle of Man</option>\n"
                    + "                        <option value=\"Israel\">Israel</option>\n"
                    + "                        <option value=\"Italy\">Italy</option>\n"
                    + "                        <option value=\"Jamaica\">Jamaica</option>\n"
                    + "                        <option value=\"Japan\">Japan</option>\n"
                    + "                        <option value=\"Jordan\">Jordan</option>\n"
                    + "                        <option value=\"Kazakhstan\">Kazakhstan</option>\n"
                    + "                        <option value=\"Kenya\">Kenya</option>\n"
                    + "                        <option value=\"Kiribati\">Kiribati</option>\n"
                    + "                        <option value=\"Korea North\">Korea North</option>\n"
                    + "                        <option value=\"Korea Sout\">Korea South</option>\n"
                    + "                        <option value=\"Kuwait\">Kuwait</option>\n"
                    + "                        <option value=\"Kyrgyzstan\">Kyrgyzstan</option>\n"
                    + "                        <option value=\"Laos\">Laos</option>\n"
                    + "                        <option value=\"Latvia\">Latvia</option>\n"
                    + "                        <option value=\"Lebanon\">Lebanon</option>\n"
                    + "                        <option value=\"Lesotho\">Lesotho</option>\n"
                    + "                        <option value=\"Liberia\">Liberia</option>\n"
                    + "                        <option value=\"Libya\">Libya</option>\n"
                    + "                        <option value=\"Liechtenstein\">Liechtenstein</option>\n"
                    + "                        <option value=\"Lithuania\">Lithuania</option>\n"
                    + "                        <option value=\"Luxembourg\">Luxembourg</option>\n"
                    + "                        <option value=\"Macau\">Macau</option>\n"
                    + "                        <option value=\"Macedonia\">Macedonia</option>\n"
                    + "                        <option value=\"Madagascar\">Madagascar</option>\n"
                    + "                        <option value=\"Malaysia\">Malaysia</option>\n"
                    + "                        <option value=\"Malawi\">Malawi</option>\n"
                    + "                        <option value=\"Maldives\">Maldives</option>\n"
                    + "                        <option value=\"Mali\">Mali</option>\n"
                    + "                        <option value=\"Malta\">Malta</option>\n"
                    + "                        <option value=\"Marshall Islands\">Marshall Islands</option>\n"
                    + "                        <option value=\"Martinique\">Martinique</option>\n"
                    + "                        <option value=\"Mauritania\">Mauritania</option>\n"
                    + "                        <option value=\"Mauritius\">Mauritius</option>\n"
                    + "                        <option value=\"Mayotte\">Mayotte</option>\n"
                    + "                        <option value=\"Mexico\">Mexico</option>\n"
                    + "                        <option value=\"Midway Islands\">Midway Islands</option>\n"
                    + "                        <option value=\"Moldova\">Moldova</option>\n"
                    + "                        <option value=\"Monaco\">Monaco</option>\n"
                    + "                        <option value=\"Mongolia\">Mongolia</option>\n"
                    + "                        <option value=\"Montserrat\">Montserrat</option>\n"
                    + "                        <option value=\"Morocco\">Morocco</option>\n"
                    + "                        <option value=\"Mozambique\">Mozambique</option>\n"
                    + "                        <option value=\"Myanmar\">Myanmar</option>\n"
                    + "                        <option value=\"Nambia\">Nambia</option>\n"
                    + "                        <option value=\"Nauru\">Nauru</option>\n"
                    + "                        <option value=\"Nepal\">Nepal</option>\n"
                    + "                        <option value=\"Netherland Antilles\">Netherland Antilles</option>\n"
                    + "                        <option value=\"Netherlands\">Netherlands (Holland, Europe)</option>\n"
                    + "                        <option value=\"Nevis\">Nevis</option>\n"
                    + "                        <option value=\"New Caledonia\">New Caledonia</option>\n"
                    + "                        <option value=\"New Zealand\">New Zealand</option>\n"
                    + "                        <option value=\"Nicaragua\">Nicaragua</option>\n"
                    + "                        <option value=\"Niger\">Niger</option>\n"
                    + "                        <option value=\"Nigeria\">Nigeria</option>\n"
                    + "                        <option value=\"Niue\">Niue</option>\n"
                    + "                        <option value=\"Norfolk Island\">Norfolk Island</option>\n"
                    + "                        <option value=\"Norway\">Norway</option>\n"
                    + "                        <option value=\"Oman\">Oman</option>\n"
                    + "                        <option value=\"Pakistan\">Pakistan</option>\n"
                    + "                        <option value=\"Palau Island\">Palau Island</option>\n"
                    + "                        <option value=\"Palestine\">Palestine</option>\n"
                    + "                        <option value=\"Panama\">Panama</option>\n"
                    + "                        <option value=\"Papua New Guinea\">Papua New Guinea</option>\n"
                    + "                        <option value=\"Paraguay\">Paraguay</option>\n"
                    + "                        <option value=\"Peru\">Peru</option>\n"
                    + "                        <option value=\"Phillipines\">Philippines</option>\n"
                    + "                        <option value=\"Pitcairn Island\">Pitcairn Island</option>\n"
                    + "                        <option value=\"Poland\">Poland</option>\n"
                    + "                        <option value=\"Portugal\">Portugal</option>\n"
                    + "                        <option value=\"Puerto Rico\">Puerto Rico</option>\n"
                    + "                        <option value=\"Qatar\">Qatar</option>\n"
                    + "                        <option value=\"Republic of Montenegro\">Republic of Montenegro</option>\n"
                    + "                        <option value=\"Republic of Serbia\">Republic of Serbia</option>\n"
                    + "                        <option value=\"Reunion\">Reunion</option>\n"
                    + "                        <option value=\"Romania\">Romania</option>\n"
                    + "                        <option value=\"Russia\">Russia</option>\n"
                    + "                        <option value=\"Rwanda\">Rwanda</option>\n"
                    + "                        <option value=\"St Barthelemy\">St Barthelemy</option>\n"
                    + "                        <option value=\"St Eustatius\">St Eustatius</option>\n"
                    + "                        <option value=\"St Helena\">St Helena</option>\n"
                    + "                        <option value=\"St Kitts-Nevis\">St Kitts-Nevis</option>\n"
                    + "                        <option value=\"St Lucia\">St Lucia</option>\n"
                    + "                        <option value=\"St Maarten\">St Maarten</option>\n"
                    + "                        <option value=\"St Pierre &amp; Miquelon\">St Pierre &amp; Miquelon</option>\n"
                    + "                        <option value=\"St Vincent &amp; Grenadines\">St Vincent &amp; Grenadines</option>\n"
                    + "                        <option value=\"Saipan\">Saipan</option>\n"
                    + "                        <option value=\"Samoa\">Samoa</option>\n"
                    + "                        <option value=\"Samoa American\">Samoa American</option>\n"
                    + "                        <option value=\"San Marino\">San Marino</option>\n"
                    + "                        <option value=\"Sao Tome &amp; Principe\">Sao Tome &amp; Principe</option>\n"
                    + "                        <option value=\"Saudi Arabia\">Saudi Arabia</option>\n"
                    + "                        <option value=\"Senegal\">Senegal</option>\n"
                    + "                        <option value=\"Serbia\">Serbia</option>\n"
                    + "                        <option value=\"Seychelles\">Seychelles</option>\n"
                    + "                        <option value=\"Sierra Leone\">Sierra Leone</option>\n"
                    + "                        <option value=\"Singapore\">Singapore</option>\n"
                    + "                        <option value=\"Slovakia\">Slovakia</option>\n"
                    + "                        <option value=\"Slovenia\">Slovenia</option>\n"
                    + "                        <option value=\"Solomon Islands\">Solomon Islands</option>\n"
                    + "                        <option value=\"Somalia\">Somalia</option>\n"
                    + "                        <option value=\"South Africa\">South Africa</option>\n"
                    + "                        <option value=\"Spain\">Spain</option>\n"
                    + "                        <option value=\"Sri Lanka\">Sri Lanka</option>\n"
                    + "                        <option value=\"Sudan\">Sudan</option>\n"
                    + "                        <option value=\"Suriname\">Suriname</option>\n"
                    + "                        <option value=\"Swaziland\">Swaziland</option>\n"
                    + "                        <option value=\"Sweden\">Sweden</option>\n"
                    + "                        <option value=\"Switzerland\">Switzerland</option>\n"
                    + "                        <option value=\"Syria\">Syria</option>\n"
                    + "                        <option value=\"Tahiti\">Tahiti</option>\n"
                    + "                        <option value=\"Taiwan\">Taiwan</option>\n"
                    + "                        <option value=\"Tajikistan\">Tajikistan</option>\n"
                    + "                        <option value=\"Tanzania\">Tanzania</option>\n"
                    + "                        <option value=\"Thailand\">Thailand</option>\n"
                    + "                        <option value=\"Togo\">Togo</option>\n"
                    + "                        <option value=\"Tokelau\">Tokelau</option>\n"
                    + "                        <option value=\"Tonga\">Tonga</option>\n"
                    + "                        <option value=\"Trinidad &amp; Tobago\">Trinidad &amp; Tobago</option>\n"
                    + "                        <option value=\"Tunisia\">Tunisia</option>\n"
                    + "                        <option value=\"Turkey\">Turkey</option>\n"
                    + "                        <option value=\"Turkmenistan\">Turkmenistan</option>\n"
                    + "                        <option value=\"Turks &amp; Caicos Is\">Turks &amp; Caicos Is</option>\n"
                    + "                        <option value=\"Tuvalu\">Tuvalu</option>\n"
                    + "                        <option value=\"Uganda\">Uganda</option>\n"
                    + "                        <option value=\"Ukraine\">Ukraine</option>\n"
                    + "                        <option value=\"United Arab Erimates\">United Arab Emirates</option>\n"
                    + "                        <option value=\"United Kingdom\">United Kingdom</option>\n"
                    + "                        <option value=\"United States of America\">United States of America</option>\n"
                    + "                        <option value=\"Uraguay\">Uruguay</option>\n"
                    + "                        <option value=\"Uzbekistan\">Uzbekistan</option>\n"
                    + "                        <option value=\"Vanuatu\">Vanuatu</option>\n"
                    + "                        <option value=\"Vatican City State\">Vatican City State</option>\n"
                    + "                        <option value=\"Venezuela\">Venezuela</option>\n"
                    + "                        <option value=\"Vietnam\">Vietnam</option>\n"
                    + "                        <option value=\"Virgin Islands (Brit)\">Virgin Islands (Brit)</option>\n"
                    + "                        <option value=\"Virgin Islands (USA)\">Virgin Islands (USA)</option>\n"
                    + "                        <option value=\"Wake Island\">Wake Island</option>\n"
                    + "                        <option value=\"Wallis &amp; Futana Is\">Wallis &amp; Futana Is</option>\n"
                    + "                        <option value=\"Yemen\">Yemen</option>\n"
                    + "                        <option value=\"Zaire\">Zaire</option>\n"
                    + "                        <option value=\"Zambia\">Zambia</option>\n"
                    + "                        <option value=\"Zimbabwe\">Zimbabwe</option>\n"
                    + "                    </select>"
                    + "<br>"
                    + "City: "
                    + "<input type=\"text\" placeholder=\"City\" id=\"city\" value=" + tempUser.getTown() + " required>"
                    + "<br>"
                    + "Address: "
                    + "<input type=\"text\" placeholder=\"Address\" id=\"address\" value=" + tempUser.getAddress() + ">"
                    + "<br>"
                    + "Interests: "
                    + "<input type=\"text\" placeholder=\"Interests\" id=\"interests\"value=" + tempUser.getInterests() + ">"
                    + "<br>"
                    + "General Info: "
                    + "<input type=\"text\" placeholder=\"General Info\" id=\"generalinfo\"value=" + tempUser.getInfo()+">"
                    + "<br>"
                    + "Gender: " + tempUser.getGender()
                    + "<br>"
                    + "<input type=\"radio\" name=\"gender\" value=\"male\">Male\n"
                    + "<input type=\"radio\" name=\"gender\" value=\"female\">Female\n"
                    + "<input type=\"radio\" name=\"gender\" value=\"other\">Other"
                    + "<br><br>"
                        + "<input type=\"button\" value=\"Update changes\" onclick=\"updateCredentials()\" > </h5>");
            }else{
                String password = array[2].substring(array[2].lastIndexOf("=") + 1);
                String firstName = array[3].substring(array[3].lastIndexOf("=") + 1);
                String lastName = array[4].substring(array[4].lastIndexOf("=") + 1);
                String city = array[5].substring(array[5].lastIndexOf("=") + 1);
                String country = array[6].substring(array[6].lastIndexOf("=") + 1);
                String address = array[7].substring(array[7].lastIndexOf("=") + 1);
                String birthday = array[8].substring(array[8].lastIndexOf("=") + 1);
                String job = array[9].substring(array[9].lastIndexOf("=") + 1);
                String interests = array[10].substring(array[10].lastIndexOf("=") + 1);
                String generalInfo = array[11].substring(array[11].lastIndexOf("=") + 1);
                String gender = array[12].substring(array[12].lastIndexOf("=") + 1);
                
                
                tempUser.setPassword(password);
                tempUser.setFirstName(firstName);
                tempUser.setLastName(lastName);
                tempUser.setCountry(country);
                tempUser.setTown(city);
                tempUser.setAddress(address);
                tempUser.setBirthDate(birthday);
                tempUser.setOccupation(job);
                tempUser.setInterests(interests);
                tempUser.setInfo(generalInfo);
                tempUser.setGender(gender);
                
                UserDB.updateUser(tempUser);
               out.print("<input class=\"logout_button\" type=\"button\" value=\"Logout\" onclick=\"deleteCookie('usernameCookie'), deleteCookie('passwordCookie'),singlePageLogin()\">");
               out.print("<input class=\"home_button\" type=\"button\" value=\"Home\"onclick=\"ajaxRequestHome('"+username+"')\">");
               out.print("<br><h2>");
               out.print("Credentials succesfully updated!</h2>");
               
                    out.print("<h5>Username : "+ username +"<br>");
                    out.print("Email : "+ tempUser.getEmail() +"<br>");
                    out.print("First name : "+ firstName +"<br>");
                    out.print("Last name : "+ lastName +"<br>");
                    out.print("Country : "+ country +"<br>");
                    out.print("City : "+ city +"<br>");
                    out.print("Address : "+ address +"<br>");
                    out.print("Birthdate : "+ birthday +"<br>");
                    out.print("Occupation : "+ job +"<br>");
                    out.print("Gender : "+ gender +"<br>");
                    out.print("Interests : "+ interests +"<br>");
                    out.print("General Info : "+ generalInfo +"<br></h5>");
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
            Logger.getLogger(UserInfoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UserInfoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
