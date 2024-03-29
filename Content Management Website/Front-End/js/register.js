const showPassword = () =>{
    var pass = document.getElementById("password");
    var eye1 = document.getElementById("eye1");
    var eye2 = document.getElementById("eye2");

    if(pass.type === 'password'){
        pass.type = 'text';
        eye1.style.display = 'none';
        eye2.style.display = 'block';
    }else{
        pass.type = 'password';
        eye1.style.display = 'block';
        eye2.style.display = 'none';
    }
};


const showRePassword = () =>{
    var pass = document.getElementById("re-password");
    var eye3 = document.getElementById("eye3");
    var eye4 = document.getElementById("eye4");

    if(pass.type === 'password'){
        pass.type = 'text';
        eye3.style.display = 'none';
        eye4.style.display = 'block';
    }else{
        pass.type = 'password';
        eye3.style.display = 'block';
        eye4.style.display = 'none';
    }
};

const showToast = (type, message) => {
    debugger;
    var toast = document.getElementById('toast');
    toast.classList.add('show');
    toast.style.display = "block";
    
    if(type === "success"){
        toast.style.backgroundColor = "#07bc0c";
    }else if(type === "error"){
        toast.style.backgroundColor = "#e74c3c";
    }else if(type === "warning"){
        toast.style.backgroundColor = "#f1c40f";
    }
    toast.innerHTML = `${message}`;

    setTimeout(function() {
        toast.style.display = "none";
        toast.classList.remove('show');
    }, 3000);
};


const reqFieldsValidation = () => {
    var name = document.getElementById("name").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var repassword = document.getElementById("re-password").value;
    if(name === '' || email === '' || password === '' || repassword === ''){
        showToast("error", "Please fill all the required fileds");
    }else{
        return true;
    }
};


const emailValidation = () => {
    var email = document.getElementById('email');
    var emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/ ;
    
    if(email.value.match(emailRegex)){
        return true;
    }else{
        showToast("error", "Invalid email id");
    }
};



const passwordValidation = () => {
    var password = document.getElementById('password');
    var passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,20}$/;
    var passwordError = document.getElementById('password-error');

    if(password.value.match(passwordRegex)){
        passwordError.innerHTML = "";
        return true;
    }else{
        passwordError.innerHTML = `<div class="password-error">
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
};

const repassValidation = () => {
    var password = document.getElementById("password").value;
    var repassword = document.getElementById("re-password").value;
    if(password === repassword){
        return true;
    }else{
        showToast("error", "Password and Re-enter password should be same");
    }
};


const createUrl = (uri) =>{
    return "http://localhost:8080/cms"+uri;
}

const log = (message) =>{
    console.log(message);
}


const register = () => {
    debugger;
    var passwordError = document.getElementById('password-error');
    passwordError.innerHTML = "";
    if(reqFieldsValidation() && emailValidation() && passwordValidation()  && repassValidation()){
        var name = document.getElementById("name").value;
        var email = document.getElementById("email").value;
        var password = document.getElementById("password").value;

        const body = JSON.stringify({name, email, password});
        const url = createUrl('/user/register');

        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            debugger;
            if (this.readyState === 4 && this.status === 200) {
                debugger;
                var response = JSON.parse(this.responseText);
                // var response = this.responseText;
                log(response);
                if(response.status === 200){
                    showToast("success", response.message);
                    setTimeout(function() {
                        window.location.href = 'login.html';
                    }, 3000);
                }else if( response.status === 500){
                    showToast("error", response.message);
                }
            }else if(this.readyState === 4 && this.status === 0){
                debugger;
                showToast("error", "Failed To Register. <br> Please Try After Sometime");
            }
        };
        xhr.open('POST', url);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.send(body);
    }
};

