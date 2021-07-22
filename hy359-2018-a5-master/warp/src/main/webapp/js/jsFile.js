function confirmPassword(){
        var original_password=document.getElementById("original_password");
        var confirm_password=document.getElementById("confirm_password");
        var message=document.getElementById("error_message");
        var errorColor="#fe0000";
        if(original_password.value!=confirm_password.value){
            message.style.fontSize="medium";
            message.style.color=errorColor;
            message.innerHTML="Password doesnt match!";
        }else{
            message.innerHTML="";
        }
    }   
