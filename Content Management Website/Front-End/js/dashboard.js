document.addEventListener("mousedown",function(event){
    debugger;
    var profileContent = document.getElementById("profile-content");

    if (event.target.id !== "profile-img" || event.target.id !== "logoutBtn" ) {
        if(profileContent.classList.contains("show")) {
            profileContent.classList.remove("show");
        }
    }
})



document.getElementById("logoutBtn").addEventListener("mousedown", function () {
    // Handle logout functionality here
    window.location.href = "login.html";
    console.log("Logout clicked");
});




function toggleMenu(){
    debugger;
    var menu = document.getElementById("menu");
    var sidebar = document.getElementById("sidebar");
    if(sidebar.style.display == "none"){
        sidebar.style.display = "block";
    }else{
        sidebar.style.display = "none";
    }
}


function profileDropDown(){
    
    var profile = document.getElementById("profile-content");
    
    if(!profile.classList.contains("show")){
        profile.classList.add("show");
    }else{
        profile.classList.remove("show");
    }
    
}

