function emailValidation(){
    var email = document.getElementById('email');
    var emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/ ;
    var emailError = document.getElementById('email-error');
    
    if(email.value.match(emailRegex)){
        emailError.innerHTML = "";
        return true;
    }else{
        emailError.innerHTML = `<p>Your Email Address is Invalid</p>`;
    }
}



function passwordValidation(){
    var password = document.getElementById('password');
    var passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,20}$/;
    var passwordError = document.getElementById('password-error');

    if(password.value.match(passwordRegex)){
        passwordError.innerHTML = "";
        return true;
    }else{
        passwordError.innerHTML = `<div>
                                   <p>Password Must Contain -></p>
                                   <p id="upper" class="invalid">  One Uppercase Letter </p>
                                   <p id="lower" class="invalid">  One Lowercase Letter </p>
                                   <p id="number" class="invalid">  One Number  </p>
                                   <p id="symbol" class="invalid">  One Symbol  </p>
                                   <p id="length" class="invalid">  Password length should be 8 to 20 characters </p>
                                   </div>`;

        //Validate Lower letters
        var lowerCaseLetters = /[a-z]/g;
        var lower = document.getElementById('lower');
        if(password.value.match(lowerCaseLetters)) {  
            lower.classList.remove("invalid");
            lower.classList.add("valid");
        } else {
            lower.classList.remove("valid");
            lower.classList.add("invalid");
        }

        // Validate Upper letters
        var upperCaseLetters = /[A-Z]/g;
        var upper = document.getElementById('upper');
        if(password.value.match(upperCaseLetters)) {  
            upper.classList.remove("invalid");
            upper.classList.add("valid");
        } else {
            upper.classList.remove("valid");
            upper.classList.add("invalid");
        }

        // Validate numbers
        var numbers = /[0-9]/g;
        var number = document.getElementById('number');
        if(password.value.match(numbers)) {  
            number.classList.remove("invalid");
            number.classList.add("valid");
        } else {
            number.classList.remove("valid");
            number.classList.add("invalid");
        }


        // Validate symbols
        var symbols = /[^A-Za-z 0-9]/g;
        var symbol = document.getElementById('symbol');
        if(password.value.match(symbols)) {  
            symbol.classList.remove("invalid");
            symbol.classList.add("valid");
        } else {
            symbol.classList.remove("valid");
            symbol.classList.add("invalid");
        }
        
        // Validate length
        var length = document.getElementById('length');
        if(password.value.length >= 8 && password.value.length <=20) {
            length.classList.remove("invalid");
            length.classList.add("valid");
        } else {
            length.classList.remove("valid");
            length.classList.add("invalid");
        }
    }
}


function login(){
    if(emailValidation() && passwordValidation()){
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function(response, error){
            if(this.readyState == 4 && this.status == 200){

            }
        }
        xhr.open("GET", "");
        xhr.send();
    }else{
        var submitError = document.getElementById('submit-error');
        submitError.innerHTML = `<p>Please Check Username and Password</P>`
    }
}