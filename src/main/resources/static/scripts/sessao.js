    if(localStorage.getItem("usuariologado") == ""){
   	 window.location.href = "index.html";

   }else{

   document.getElementById("usuariologado").innerHTML = localStorage.getItem("usuariologado");

   }

   $("#sair").on('click', function(){
           localStorage.setItem("usuariologado", "");
            window.location.href = "index.html";
   });
