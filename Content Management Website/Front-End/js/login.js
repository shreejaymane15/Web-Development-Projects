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


const createUrl = (uri) =>{
    return "http://localhost:8080/cms"+uri;
};

const log = (message) =>{
    console.log(message);
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



const login = () => {
    debugger;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    const body = JSON.stringify({email, password});
    const url = createUrl('/user/login');
    
    const xhr = new XMLHttpRequest();
    if(email != "" && password != ""){

        xhr.onreadystatechange = function() {
            debugger;
            if (this.readyState === 4 && this.status === 200) {
                debugger;
                var response = JSON.parse(this.responseText);
                // var response = this.responseText;
                log(response);
                if(response.status===200){
                    showToast("success", response.message);
                    localStorage.setItem("token", response.token);
                    localStorage.setItem("user_id", response.user_id);
                    setTimeout(function() {
                        window.location.href = 'dashboard.html';
                    }, 3000);
                }else if(response.status === 500){
                    showToast("error", response.message);
                }    
            }else if(this.readyState === 4 && this.status != 200){
                debugger;
                showToast("error", "Failed To Login. <br> Please Try After Sometime");
            }
        }
    }else{
        showToast("error", "Please Fill Email And Password.");
    }
        
    xhr.open('POST', url);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(body);
};