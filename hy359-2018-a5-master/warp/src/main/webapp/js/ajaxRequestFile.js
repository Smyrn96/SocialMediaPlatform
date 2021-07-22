/* global df, dateobj, L */

"use strict";

var flag=0;

function checkUsername(){
    var username=document.getElementById('username').value;
    ajaxRequest("username="+ username,"usernameMessage");
}
function checkEmail(){
    var email=document.getElementById('email').value;
    ajaxRequest("email="+ email,"emailMessage");
}
function checkPassword() {
    var password = document.getElementById('original_password').value;
    ajaxRequest("password=" + password, "passwordMessage");
}
function checkConfirmPassword() {
    var confirmPassword = document.getElementById('confirm_password').value;
    ajaxRequest("confrimpassword=" + confirmPassword, "confirmPasswordMessage");
}
function checkFirstName() {
    var firstname = document.getElementById('firstname').value;
    ajaxRequest("firstname=" + firstname, "firstNameMessage");
}
function checkLastName() {
    var lastname = document.getElementById('lastname').value;
    ajaxRequest("lastname=" + lastname, "lastNameMessage");
}
function checkCity() {
    var city = document.getElementById("Users_city").value;
    ajaxRequest("city=" + city, "cityMessage");
}
function checkJob() {
    var job = document.getElementById('job').value;
    ajaxRequest("job=" + job, "jobMessage");
}
function checkInterests() {
    var interests = document.getElementById('interests').value;
    ajaxRequest("interests=" + interests, "interestsMessage");
}
function checkGeneralInfo() {
    var generalinfo = document.getElementById('generalinfo').value;
    ajaxRequest("generalinfo=" + generalinfo, "generalInfoMessage");
}

function ajaxRequest(variable,div){
    
    var xhr = new XMLHttpRequest();
    var temp;
    xhr.open('POST', 'RegisterUserServlet', true);
    
     xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById(div).innerHTML = xhr.responseText;
            temp=xhr.responseText;
            if(temp===""){
                flag=1;
            }
    
        }else if(xhr.status!=200){
            alert('Request has failed.Status: '+xhr.status);
        }
    };
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(variable);
}

function ajaxRequestNewUser(){
    
    var username;
    var email;
    var password;
    var confirmPassword;
    var firstName;
    var lastName;
    var country;
    var city;
    var address;
    var birthday;
    var gender;
    var job;
    var interests;
    var generalinfo;
    
    username=document.getElementById("username").value;
    email=document.getElementById("email").value;
    password=document.getElementById("original_password").value;
    confirmPassword=document.getElementById("confirm_password").value;
    firstName=document.getElementById("firstname").value;
    lastName=document.getElementById("lastname").value;
    country=document.getElementById("Users_country").value;
    city=document.getElementById("Users_city").value;
    address=document.getElementById("Users_address").value;
    birthday=document.getElementById("birthday").value;
    job=document.getElementById("job").value;
    interests=document.getElementById("interests").value;
    generalinfo=document.getElementById("generalinfo").value;
    
   if(document.getElementById("Male").checked){
       gender=document.getElementById("Male").value;
   }else if(document.getElementById("Female").checked){
       gender=document.getElementById("Female").value;
   }else if(document.getElementById("Other").checked){
       gender=document.getElementById("Other").value;
   }
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'NewUserServlet', true);
    
     xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('main').innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    
    xhr.send("username=" + username + ",email=" + email + ",password=" + password + ",confirmPassword=" + confirmPassword + ",firstName=" + firstName + ",lastName=" + lastName + ",country=" + country + ",city=" + city + ",address=" + address + ",birthday=" + birthday + ",gender=" + gender + ",job=" + job + ",interests=" + interests + ",generalinfo=" + generalinfo + ",flag=" + flag);
    
}
function ajaxRequestNewComment(username, postID){
   var comment=document.getElementById("new_cmnt"+postID).value;
   
   if(comment===""){
       alert('Please fill the text area to comment!');
   }else{
       
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'NewCommentServlet', true);
    
     xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('main').innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    
    xhr.send("username="+ username +",comment="+ comment+ ",postID="+postID);
   }
}


function ajaxRequestNewPost(username){
    var comment = "";
    var resourceURL = "";
    var imageURL = "";
    var imageBase64 = "";
    var latitude = "";
    var longitude = "";
    var createdAt = "";
   
   comment=document.getElementById("new_post").value;
   resourceURL=document.getElementById("new_URL").value;
   imageURL=document.getElementById("new_img").value;
   
   if(comment===""){
       alert('Please fill the text area to post!');
   }else{
       
    
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'NewPostServlet', true);
    
     xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('main').innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    
    xhr.send("username=" + username + ",comment=" + comment + ",resourceURL=" + resourceURL + ",imageURL=" + imageURL + ",imageBase64=" + imageBase64 + ",latitude=" + latitude + ",longitude=" + longitude+ ",createdAt=" + createdAt);
   }
}


function ajaxRequestLogIn(){
     var username;
     var password;
     
     username=document.getElementById("username").value;
     password=document.getElementById("original_password").value;
     
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'LoginServlet', true);
    
     xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('main').innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };

    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send('username=' + username + ',password=' + password);
    
}

function ajaxRequestPrintUsers(username) {

    var xhr = new XMLHttpRequest();

    xhr.open('POST', 'PrintUsersServlet');

    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('main').innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    
    xhr.send(username);
}
function ajaxRequestPrintPosts() {

    var xhr = new XMLHttpRequest();

    xhr.open('POST', 'PrintPostsServlet');

    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('main').innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    
    xhr.send();
}

function deleteCookie(name) {
    document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

window.onload = function () {
    if (document.cookie.length !== 0) {
        
        var text = document.cookie.split(";");
        var array = text[0].split("=");
        var username = array[1];
        
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'LoadServlet');

        xhr.onload = function () {
            if (xhr.status === 200) {
                document.getElementById('main').innerHTML = xhr.responseText;
            } else if (xhr.status !== 200) {
                alert('Request failed. Returned status of ' + xhr.status);
            }
        };

        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.send(username);
    }
};

var flagUpdate="false";

function updateCredentials() {
    flagUpdate = "true";
    ajaxRequestUpdateCredentials();
}


function ajaxRequestUserInfo() {

    var username;

    if (document.cookie.length !== 0) {
        var text = document.cookie.split(";");
        var array = text[0].split("=");
        var username = array[1];
    } else {
        username = "";
    }

    var xhr = new XMLHttpRequest();

    xhr.open('POST', 'UserInfoServlet');

    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('main').innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

    xhr.send('username=' + username + ',flagUpdate=' + flagUpdate);
    flagUpdate = "false";
}


function ajaxRequestUpdateCredentials() {

    var username;
    var password = document.getElementById('password').value;
    var firstName = document.getElementById('firstname').value;
    var lastName = document.getElementById('lastname').value;
    var city = document.getElementById('city').value;
    var country = document.getElementById('country').value;
    var address = document.getElementById('address').value;
    var birthday = document.getElementById('birthday').value;
    var job = document.getElementById('job').value;
    var interests = document.getElementById('interests').value;
    var generalInfo = document.getElementById('generalinfo').value;
    var gender;
    var radios = document.getElementsByName('gender');
    for (var i = 0; i < radios.length; i++) {
        if (radios[i].checked) {
            gender = radios[i].value;

        }
    }
    if (document.cookie.length !== 0) {
        var text = document.cookie.split(";");
        var array = text[0].split("=");
        var username = array[1];
    } else {
        username = "";
    }

    var xhr = new XMLHttpRequest();

    xhr.open('POST', 'UserInfoServlet');

    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('main').innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

    xhr.send('username=' + username + ',flagUpdate=' + flagUpdate + ',password=' + password + ',firstName=' + firstName
            + ',lastName=' + lastName + ',city=' + city + ',country=' + country + ',address=' + address
            + ',birthday=' + birthday + ',job=' + job + ',interests=' + interests + ',generalInfo=' + generalInfo
            + ',gender=' + gender);
    
    flagUpdate = "false";
}

function ajaxRequestUsers() {

    var xhr = new XMLHttpRequest();

    xhr.open('POST', 'UsersServlet');

    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('ajaxContent2').innerHTML = xhr.responseText;
            var x = document.getElementById('ajaxContent2');
            if (x.style.display === "none") {
                x.style.display = "block";
            } else {
                x.style.display = "none";
            }
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    
    xhr.send();
}


function ajaxRequestSingleUserPosts(username) {
     var flag=false;
     
     if(document.cookie.length !== 0){
        var text = document.cookie.split(";");
        var array = text[0].split("=");
        var userName = array[1];
         if(userName===username){
             flag=true;
         }
     }
    
     var xhr = new XMLHttpRequest();

    xhr.open('POST', 'UserProfileServlet');

    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('main').innerHTML = xhr.responseText;          
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    
     xhr.send('username=' + username + ',flag=' + flag);
    
}
function ajaxRequestDeletePost(postID) {
    
    
     var xhr = new XMLHttpRequest();

    xhr.open('POST', 'DeletePostServlet');
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('main').innerHTML = xhr.responseText;          
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    
    xhr.send(postID);
    
}
function ajaxRequestDeleteComment(comID) {
    
    
     var xhr = new XMLHttpRequest();

    xhr.open('POST', 'DeleteCommentServlet');
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('main').innerHTML = xhr.responseText;          
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    
    xhr.send(comID);
    
}
function ajaxRequestDeleteProfile(username) {
     var xhr = new XMLHttpRequest();
     confirm("Are you sure to delete profile?");
    xhr.open('POST', 'DeleteProfileServlet');
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('main').innerHTML = xhr.responseText;          
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    
    xhr.send(username);
    
}
function ajaxRequestHome() {
    
    if (document.cookie.length !== 0) {
        
        var text = document.cookie.split(";");
        var array = text[0].split("=");
        var username = array[1];
    
     var xhr = new XMLHttpRequest();

    xhr.open('POST', 'HomeServlet');

    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('main').innerHTML = xhr.responseText;          
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    
    xhr.send(username);
    }
}


function ajaxRequestPostDetails(postID, username) {

    var xhr = new XMLHttpRequest();

    xhr.open('POST', 'PostDetailsServlet');

    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('main').innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    
    xhr.send("postID="+postID+",username="+username);
}

