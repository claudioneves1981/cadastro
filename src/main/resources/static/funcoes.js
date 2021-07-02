if(sessionStorage.getItem("usuariologado") == ""){
   	 window.location.href = "index.html";
   	
   }else{
   
   document.getElementById("usuariologado").innerHTML = sessionStorage.getItem("usuariologado");

   }
   
   function sair(){
   	sessionStorage.setItem("usuariologado", "");
   	window.location.href = "index.html";
   }
   
   	function coletaDados(){
   	   var ids = document.getElementsByClassName('custom-control-input');
   	   coletaIDs(ids);         
   	}  
   	        
   	function coletaIDs(dados){
   	   var array_dados = dados; 
   	   var newArray = [];
   	   for(var x = 0; x <= array_dados.length; x++){     
   	        if(typeof array_dados[x] == 'object'){
   	          if(array_dados[x].checked){
   	        	  CriaPDF(array_dados[x].id)
   	              newArray.push(array_dados[x].id)          
   	          }          
   	        }
   	   }
 		}
   	
    function listarCadastros(){
   	 
   		if ($('#cadastroBusca').val() != ''){
   	    	 
   	    	 nome = $('#cadastroBusca').val();
   	 
    		  $.ajax({
    	    		method: "GET",
    	    		url: "cadastrobuscarPorCadastro",
    	    		data : "name=" + nome ,
    	    		success: function(response){
    	    			var style = "<style>";
    	    	        style = style + "table {width:100%;font: 8px Calibri;}";
    	    	        style = style + "table, tr, th, td {border: solid 1px #DDD; border-collapse: collapse;";
    	    	        style = style + "padding: 2px 3px;text-align: center;}";
    	    	        style = style + "</style>";
    	    			var win = window.open('', '', 'height=700,width=700');
	    		        win.document.write('<html><head>');
	    		        win.document.write('<title>Cadastro</title>');   // <title> CABEÇALHO DO PDF.                                   // INCLUI UM ESTILO NA TAB HEAD
	    		        win.document.write('</head>');
	    		        win.document.write('<body>');
	    		 		win.document.write(style);
	    		        win.document.write('<table id="tabelalista">');
	    		        win.document.write(' <thead>'+
   										'<tr>'+
   										'<th scope="col">ID</th>'+
   										'<th scope="col">Nome</th>'+
  										    '<th scope="col">Data Nasc</th>'+
   										'<th scope="col"> Idade </th>'+
											'<th scope="col"> Endereço</th>'+
											'<th scope="col"> Número</th>'+
											'<th scope="col"> Bairro</th>'+
											'<th scope="col"> CEP</th>'+
											'<th scope="col"> Cidade</th>'+
											'<th scope="col"> Tel 1</th>'+
   										'<th scope="col"> Tel 2r</th>'+
   										'<th scope="col">Quantos Moram</th>'+
   										'<th scope ="col">Estuda</th>'+
   										'<th scope ="col">Casa Propria</th>'+
   										'<th scope ="col">Numero Nis</th>'+
   										'</tr>'+
 											'</thead>');
	    		        win.document.write('<tbody>');
    	    			for (var i = 0; i < response.length; i++){
    	    				var estuda;
    	    				if((response[i].estuda) == true){
    	    					estuda = 'sim';
    	    				}else{
    	    					estuda = 'não';
    	    				}
    	    				var casapropria;
    	    				if((response[i].casapropria)== true){
    	    					casapropria = 'sim';
    	    				}else{
    	    					casapropria = 'não';
    	    				}
    	    				
    	    				win.document.write('<tr id="'+response[i].codigo+'"><td>'+response[i].codigo+'</td>'+
        	   						'<td>'+response[i].nome+'</td><td>'+response[i].datanasc+'</td><td>'+response[i].idade+'</td>'+
        	   						'<td>'+response[i].endereco+'</td><td>'+response[i].numero+'</td><td>'+response[i].bairro+'</td>'+
        	   						'<td>'+response[i].cep+'</td><td>'+response[i].cidade+'</td><td>'+response[i].telefone1+'</td></td>'+
        	   						'<td>'+response[i].telefone2+'</td><td>'+response[i].quantosmoram+'</td><td>'+estuda+'</td>'+
        	   						'<td>'+casapropria+'</td><td>'+response[i].numeronis+'</td></tr>');     	    			
    	    			}
	    		        win.document.write('</tbody');
    		 			win.document.write('</body>');
						win.document.write('</html>');
						win.document.close(); 	                                         // FECHA A JANELA
		       			win.print(); 
 	    			}
 	    	}).fail(function(xhr,status,errorThrown){
 	    		alert("Erro ao buscar usuario:" + xhr.responseText);
 	    	}); 
    		  
 	    }else if($('#nomeBusca').val()!=''){
    	  
    	nome = $('#nomeBusca').val();   		  
  		  $.ajax({
  	    		method: "GET",
  	    		url: "cadastrobuscarPorNome",
  	    		data : "name=" + nome,
  	    		success: function(response){
  	    			var style = "<style>";
  	    	        style = style + "table {width:100%;font: 8px Calibri;}";
  	    	        style = style + "table, tr, th, td {border: solid 1px #DDD; border-collapse: collapse;";
  	    	        style = style + "padding: 2px 3px;text-align: center;}";
  	    	        style = style + "</style>";
  	    			var win = window.open('', '', 'height=700,width=700');
	    		        win.document.write('<html><head>');
	    		        win.document.write('<title>Cadastro</title>');   // <title> CABEÇALHO DO PDF.                                   // INCLUI UM ESTILO NA TAB HEAD
	    		        win.document.write('</head>');
	    		        win.document.write('<body>');
	    		 		win.document.write(style);
	    		        win.document.write('<table id="tabelalista">');
	    		        win.document.write(' <thead>'+
 										   '<tr>'+
 										   '<th scope="col">ID</th>'+
 										   '<th scope="col">Nome</th>'+
										   '<th scope="col">Data Nasc</th>'+
 										   '<th scope="col"> Idade </th>'+
										   '<th scope="col"> Endereço</th>'+
										   '<th scope="col"> Número</th>'+
										   '<th scope="col"> Bairro</th>'+
									       '<th scope="col"> CEP</th>'+
										   '<th scope="col"> Cidade</th>'+
										   '<th scope="col"> Tel 1</th>'+
 										   '<th scope="col"> Tel 2r</th>'+
 										   '<th scope="col">Quantos Moram</th>'+
 										   '<th scope ="col">Estuda</th>'+
 										   '<th scope ="col">Casa Propria</th>'+
 										   '<th scope ="col">Numero Nis</th>'+
 										   '</tr>'+
										   '</thead>');
	    		        win.document.write('<tbody>');
  	    			//win.document.write($('#tabelalista > tbody > tr').remove());
  	    			for (var i = 0; i < response.length; i++){
  	    				var estuda;
  	    				if((response[i].estuda) == true){
  	    					estuda = 'sim';
  	    				}else{
  	    					estuda = 'não';
  	    				}
  	    				var casapropria;
  	    				if((response[i].casapropria)== true){
  	    					casapropria = 'sim';
  	    				}else{
  	    					casapropria = 'não';
  	    				}
  	    				
  	    				win.document.write('<tr id="'+response[i].codigo+'"><td>'+response[i].codigo+'</td>'+
      	   						'<td>'+response[i].nome+'</td><td>'+response[i].datanasc+'</td><td>'+response[i].idade+'</td>'+
      	   						'<td>'+response[i].endereco+'</td><td>'+response[i].numero+'</td><td>'+response[i].bairro+'</td>'+
      	   						'<td>'+response[i].cep+'</td><td>'+response[i].cidade+'</td><td>'+response[i].telefone1+'</td></td>'+
      	   						'<td>'+response[i].telefone2+'</td><td>'+response[i].quantosmoram+'</td><td>'+estuda+'</td>'+
      	   						'<td>'+casapropria+'</td><td>'+response[i].numeronis+'</td></tr>');     	    			
  	    			}
	    		        win.document.write('</tbody');
  		 			win.document.write('</body>');
						win.document.write('</html>');
						win.document.close(); 	                                         // FECHA A JANELA
		       			win.print(); 
	    			}
	    	}).fail(function(xhr,status,errorThrown){
	    		alert("Erro ao buscar usuario:" + xhr.responseText);
	    	}); 
  		  
  	  }
 }
   
   function CriaPDF(codigo) {
   	
   	$.ajax({
   		method: "GET",
   		url: "cadastrobuscaruserId",
   		data : "iduser=" + codigo,
   		success: function(response){
   		$("#codigo").val(response.codigo);
			$("#nome").val(response.nome);
  			$("#datanasc").val(response.datanasc);
  			$("#idade").val(response.idade);
			$("#endereco").val(response.endereco);
			$("#numero").val(response.numero);
			$("#bairro").val(response.bairro);
			$("#cep").val(response.cep);
			$("#cidade").val(response.cidade);
			$("#estado").val(response.estado);
			$("#telefone1").val(response.telefone1);
			$("#telefone2").val(response.telefone2);
			//$(".estuda:checked").val(response.estuda);
			$("#quantosmoram").val(response.quantosmoram);
			//$(".casapropria:checked").val(response.casapropria);
			$("#numeronis").val(response.numeronis);
			
			var minhaTabela = document.getElementById('formCadastro').innerHTML;
	        // CRIA UM OBJETO WINDOW
	        var win = window.open('', '', 'height=700,width=700');
	        win.document.write('<html><head>');
	        win.document.write('<title>'+response.nome+'</title>');   // <title> CABEÇALHO DO PDF.                                   // INCLUI UM ESTILO NA TAB HEAD
	        win.document.write('</head>');
	        win.document.write('<body>');
	        win.document.write('<h3>Dados Pessoais</h3>');
	        win.document.write(' <label for="inputEmail4"  >ID: </label>')
	        win.document.write(response.codigo);
	        win.document.write(' <label for="inputEmail4"  >Nome: </label>')
	        win.document.write(response.nome);
	        win.document.write('<br><label for="inputEmail4"  >Data Nascimento: </label>')
	        win.document.write(response.datanasc);
	        win.document.write('<label for="inputEmail4"  >Idade: </label>')
	        win.document.write(response.idade);
	        win.document.write('<br>')
	        win.document.write('<h3>Endereço</h3>')
	        win.document.write(' <label for="inputEmail4"  >Endereço: </label>')
	        win.document.write(response.endereco);
	        win.document.write(' <label for="inputEmail4"  >Número: </label>')
	        win.document.write(response.numero);
	        win.document.write(' <label for="inputEmail4"  >Bairro: </label>')
	        win.document.write(response.bairro);
	        win.document.write(' <label for="inputEmail4"  >CEP: </label>')
	        win.document.write(response.cep);
	        win.document.write(' <label for="inputEmail4"  >Cidade: </label>')
	        win.document.write(response.cidade);
	        win.document.write('<br><label for="inputEmail4"  >Estado: </label>')
	        win.document.write(response.estado);
	        win.document.write('<br><label for="inputEmail4"  >Telefone 1: </label>')
	        win.document.write(response.telefone1);
	        win.document.write(' <label for="inputEmail4"  >Telefone 2: </label>')
	        win.document.write(response.telefone2);
	        win.document.write('<br>')
	        win.document.write('<h3>Outros</h3>')
	       	win.document.write(' <label for="inputEmail4"  >estuda: </label>')
	       	if (response.estuda == false){
	       	win.document.write('Não');	
	       	}else{
	       	win.document.write('Sim');
	       	}
	        win.document.write(' <label for="inputEmail4">Quantos Moram: </label>')
	        win.document.write(response.quantosmoram);
	    	win.document.write(' <label for="inputEmail4">Casa Propria: </label>')
	       	if (response.casapropria == false){
	       	win.document.write('Não');	
	       	}else{
	       	win.document.write('Sim');
	       	}
	        win.document.write('<label for="inputEmail4">Numero Nis: </label>')
	        win.document.write(response.numeronis);                       // O CONTEUDO DA TABELA DENTRO DA TAG BODY
	        win.document.write('</body></html>');
	        win.document.close(); 	                                         // FECHA A JANELA
	        win.print();   
   		}
   	}).fail(function(xhr,status,errorThrown){
   		alert("Erro ao buscar usuario por id:" + xhr.responseText);
   	}); 
   	    	                                                         // IMPRIME O CONTEUDO
   }
   
   function mascara(t, mask){
   	 var i = t.value.length;
   	 var saida = mask.substring(1,0);
   	 var texto = mask.substring(i)
   	 if (texto.substring(0,1) != saida){
   	 t.value += texto.substring(0,1);
   	 }
   }
   
   function calculaIdade(){
     	if ($("#datanasc").val() != '') {
		    var dataInput = new Date($("#datanasc").val());
		    if (!isNaN(dataInput)) {
		      var dataAtual = new Date();
		      var diferenca = dataAtual.getFullYear() -
		                      dataInput.getFullYear();
		      $("#idade").val(diferenca);
		      return true;
		    }
		  }
		  $("#idade").val('Data inválida');
		  return false;  
		}
   
   function botaoDeletarDaTela(){
   	var codigo= $('#codigo').val();
   	if(codigo!= null && codigo.trim()!=''){
   	deleteCadastro(codigo);
   	}
   	document.getElementById('formCadastro').reset();
   }
   
   function deleteCadastro(codigo){
	
	if(confirm('Deseja realmente deletar?')){
   	
   	$.ajax({
	    		method: "DELETE",
	    		url: "cadastrodelete",
	    		data : "iduser=" + codigo,
	    		success: function(response){
	    			$('#'+codigo).remove();
	    			alert(response);
	    		}
	    	}).fail(function(xhr,status,errorThrown){
	    		alert("Erro ao deletar usuario por id:" + xhr.responseText);
	    	}); 
		}
   }
   
   function botaoDeletarDaTelaAdministrativo(){
   	var codigo= $('#idadm').val();
   	if(codigo!= null && codigo.trim()!=''){
   	deleteCadastro(codigo);
   	}
   	document.getElementById('formAdministrativo').reset();
   }
   
   function deleteAdministrativo(codigo){
	
	if(confirm('Deseja realmente deletar?')){
   	
   	$.ajax({
	    		method: "DELETE",
	    		url: "administrativodelete",
	    		data : "iduser=" + codigo,
	    		success: function(response){
	    			$('#'+codigo).remove();
	    			alert(response);
	    		}
	    	}).fail(function(xhr,status,errorThrown){
	    		alert("Erro ao deletar usuario por id:" + xhr.responseText);
	    	}); 
		}
   }
   
function colocarEmEdicaoAdm(codigo){
		
   	$.ajax({
	    		method: "GET",
	    		url: "administrativobuscaruserId",
	    		data : "iduser=" + codigo,
	    		success: function(response){
	    		$("#idadm").val(response.codigo);
   				$("#nomeusuario").val(response.nome);
	   			$("#usuario").val(response.usuario);
	   			$("#senha").val(response.senha);
	    		$("#cadastrar").remove();
	    		$("#novo").after(' '+'<button id="cadastrar" type="button" class="btn btn-secondary" onclick="salvarAdministrativo()">Cadastrar</button>');

				
	    	  //  $("#modalPesquisar").modal('hide');
	    		}
	    	}).fail(function(xhr,status,errorThrown){
	    		alert("Erro ao buscar usuario por id:" + xhr.responseText);
	    	}); 
}

  function novoAdministrativo(){
	document.getElementById('formAdministrativo').reset()
	$("#cadastrar").remove();
	$("#novo").after('<button id="cadastrar" type="button" class="btn btn-secondary" onclick="validaDuplicadosAdministrativo()">Cadastrar</button>');
  }
  
  function novoUsuario(){
		document.getElementById('formCadastro').reset()
		$("#salvar").remove();
		$("#botoes").append('<button id="salvar" type="button" class="btn btn-primary" onclick="validaDuplicados()">Salvar</button>');
	  }
   
function colocarEmEdicao(codigo){
		
   	$.ajax({
	    		method: "GET",
	    		url: "cadastrobuscaruserId",
	    		data : "iduser=" + codigo,
	    		success: function(response){
	    		$("#codigo").val(response.codigo);
   				$("#nome").val(response.nome);
	   			$("#datanasc").val(response.datanasc);
	   			$("#idade").val(response.idade);
				$("#endereco").val(response.endereco);
				$("#numero").val(response.numero);
				$("#bairro").val(response.bairro);
				$("#cep").val(response.cep);
				$("#cidade").val(response.cidade);
				$("#estado").val(response.estado);
				$("#telefone1").val(response.telefone1);
				$("#telefone2").val(response.telefone2);
				$("#quantosmoram").val(response.quantosmoram);
				$("#numeronis").val(response.numeronis);
				$("#salvar").remove();
	    		$("#botoes").append(' '+'<button id="salvar" type="button" class="btn btn-primary" onclick="salvarUsuario()">Salvar</button>');

	    	  //  $("#modalPesquisar").modal('hide');
	    		}
	    	}).fail(function(xhr,status,errorThrown){
	    		alert("Erro ao buscar usuario por id:" + xhr.responseText);
	    	}); 
   	
   	$('#myTab li:nth-child(3) button').tab('show');
}

  function ordenarPorCidade(){
   	   	
	   $.ajax({
	  		method: "GET",
	  		url: "ordenarPorCidade",
	  		success: function(response){
	  			$('#selecionarCidade > option').remove();
	  			for (var i = 0; i < response.length; i++){
	 				$('#selecionarCidade').append('<option value="'+i+'">'+response[i]+'</option>');
	 			}
	  		}
	  	}).fail(function(xhr,status,errorThrown){
	  		alert("Erro ao buscar cidade:" + xhr.responseText);
	  	}); 
   	
   }

   function pesquisarCadastro(){
       	
 	  var nome = $('#cadastroBusca').val();
 	  
 		  $.ajax({
 	    		method: "GET",
 	    		url: "cadastrobuscarPorCadastro",
 	    		data : "name=" + nome ,
 	    		success: function(response){
 	    			$('#tabelaresultados > tbody > tr').remove();
 	    			for (var i = 0; i < response.length; i++){
 	    				$('#tabelaresultados > tbody').append('<tr id="'+response[i].codigo+'">'+
   	   						'<td><div class="custom-control custom-checkbox"><input type="checkbox" class="custom-control-input" id="'+response[i].codigo+'"></div>'+response[i].codigo+'</td>'+ 
   	   						'<td>'+response[i].nome+'</td>'+
   	   						'<td>'+response[i].datanasc+'</td><td>'+response[i].idade+'</td>'+
   	   						'<td>'+response[i].endereco+'</td><td>'+response[i].numero+'</td>'+
   	   						'<td>'+response[i].bairro+'</td><td>'+response[i].cep+'</td>'+
   	   						'<td>'+response[i].cidade+'</td><td>'+response[i].telefone1+'</td>'+
   	   						'<td><button type="button" class="btn btn-secondary" onclick="colocarEmEdicao('+response[i].codigo+')">Ver</button></td>'+
   	   						'<td><button type="button" class="btn btn-danger" onclick="deleteCadastro('+response[i].codigo+')">Delete</button></td>'+
   	   						'<td><button type="button" class="btn btn-primary" onclick="CriaPDF('+response[i].codigo+')">Imprimir/PDF</button></td></tr>');
   	    			}
 	    			$('#quantidade').remove();
 	    			$('#registrosencontrados').append('<label id = "quantidade">'+response.length+'</label>');
 	    		}
 	    	}).fail(function(xhr,status,errorThrown){
 	    		alert("Erro ao buscar usuario:" + xhr.responseText);
 	    	}); 
     
   }
   
   function pesquisarAdministrativo(){
   	
   	  var nome ="";
   	  var resposta = "";
   		  $.ajax({
   	    		method: "GET",
   	    		url: "administrativobuscarPorAdministrativo",
   	    		data : "name=" + nome ,
   	    		success: function(response){
   	    			$('#tabelaadministrativo > tbody > tr').remove();
   	    			for (var i = 0; i < response.length; i++){
   	    				
   	    				if(response[i].administrativo == true){
   	    					resposta = "Sim"
   	    				}else{
   	    					resposta = "Não"
   	    				}
   	    				
   	    				$('#tabelaadministrativo > tbody').append('<tr id="'+response[i].codigo+'">'+
     	   						'<td>'+response[i].codigo+'</td>'+ 
     	   						'<td>'+response[i].nome+'</td>'+
     	   						'<td>'+response[i].usuario+'</td>'+
     	   						'<td>'+resposta+'</td>'+
     	   						'<td><button type="button" class="btn btn-secondary" onclick="colocarEmEdicaoAdm('+response[i].codigo+')">Ver</button></td>'+
     	   						'<td><button type="button" class="btn btn-danger" onclick="deleteAdministrativo('+response[i].codigo+')">Delete</button></td></tr>');      	    			}
   	    			$('#quantidadeadm').remove();
   	    			$('#registrosadministrativo').append('<label id = "quantidadeadm">'+response.length+'</label>');
   	    		}
   	    	}).fail(function(xhr,status,errorThrown){
   	    		alert("Erro ao buscar usuario:" + xhr.responseText);
   	    	}); 
       
     }
   
   function selecionaCidade(){
   	
   	ordenarPorCidade();
   	
   	$("#selecionarCidade").click(function () {
 	        $("#selecionarCidade").each(function () {
 	          $(this).find("option").each(function () {
 	            if ($(this).attr("selected")) {
					 var cidade = $(this).text();
					 pesquisarNomes(cidade);
 	             $(this).removeAttr("selected");
 	            }
 	          });
 	        });
 	        $("#selecionarCidade").find("option:selected").attr("selected", true);
 	})	
 	
   }
   
  function pesquisarSomentePeloNome(){
	   
	   var nome = $('#nomeBusca').val();

	   $.ajax({
  		method: "GET",
  		url: "cadastrobuscarPorNome",
  		data :"name="+nome,
  		contentType: "application/json; charset=utf-8",
  		success: function(response){
  			$('#tabelaresultados > tbody > tr').remove();
  			for (var i = 0; i < response.length; i++){
 				$('#tabelaresultados > tbody').append('<tr id="'+response[i].codigo+'">'+
  						'<td><div class="custom-control custom-checkbox"><input type="checkbox" class="custom-control-input" id="'+response[i].codigo+'"></div>'+response[i].codigo+'</td>'+ 
  						'<td>'+response[i].nome+'</td>'+
  						'<td>'+response[i].datanasc+'</td><td>'+response[i].idade+'</td>'+
  						'<td>'+response[i].endereco+'</td><td>'+response[i].numero+'</td>'+
  						'<td>'+response[i].bairro+'</td><td>'+response[i].cep+'</td>'+
  						'<td>'+response[i].cidade+'</td><td>'+response[i].telefone1+'</td>'+
  						'<td><button type="button" class="btn btn-secondary" onclick="colocarEmEdicao('+response[i].codigo+')">Ver</button></td>'+
  						'<td><button type="button" class="btn btn-danger" onclick="deleteCadastro('+response[i].codigo+')">Delete</button></td>'+
  						'<td><button type="button" class="btn btn-primary" onclick="CriaPDF('+response[i].codigo+')">Imprimir/PDF</button></td></tr>');
   			}
 			$('#quantidade').remove();
 			$('#registrosencontrados').append('<label id = "quantidade">'+response.length+'</label>');
  		}
  	}).fail(function(xhr,status,errorThrown){
  		alert("Erro ao buscar usuario:" + xhr.responseText);
  	}); 
		
  }
   
   function pesquisarNomes(cidade){
   	
   	var nome = $('#nomeBusca').val();
   	
   	if(nome !="" && cidade !=""){
   		
   		$.ajax({
	    		method: "GET",
	    		url: "cadastrobuscarporParametros",
	    		data :"name="+nome+"&cidade="+cidade,
	    		contentType: "application/json; charset=utf-8",
	    		success: function(response){
	    			$('#tabelaresultados > tbody > tr').remove();
	    			for (var i = 0; i < response.length; i++){
	    				$('#tabelaresultados > tbody').append('<tr id="'+response[i].codigo+'">'+
	       						'<td><div class="custom-control custom-checkbox"><input type="checkbox" class="custom-control-input" id="'+response[i].codigo+'"></div>'+response[i].codigo+'</td>'+ 
	       						'<td>'+response[i].nome+'</td>'+
	       						'<td>'+response[i].datanasc+'</td><td>'+response[i].idade+'</td>'+
	       						'<td>'+response[i].endereco+'</td><td>'+response[i].numero+'</td>'+
	       						'<td>'+response[i].bairro+'</td><td>'+response[i].cep+'</td>'+
	       						'<td>'+response[i].cidade+'</td><td>'+response[i].telefone1+'</td>'+
	       						'<td><button type="button" class="btn btn-secondary" onclick="colocarEmEdicao('+response[i].codigo+')">Ver</button></td>'+
	       						'<td><button type="button" class="btn btn-danger" onclick="deleteCadastro('+response[i].codigo+')">Delete</button></td>'+
	       						'<td><button type="button" class="btn btn-primary" onclick="CriaPDF('+response[i].codigo+')">Imprimir/PDF</button></td></tr>');
	        			}
	    			$('#quantidade').remove();
	    			$('#registrosencontrados').append('<label id = "quantidade">'+response.length+'</label>');
	    		}
	    	}).fail(function(xhr,status,errorThrown){
	    		alert("Erro ao buscar usuario:" + xhr.responseText);
	    	}); 
   		
   	}
     
     }
     
   function validaDuplicados(){
   	
   	var nome = $("#nome").val();

   	  $.ajax({
	    		method: "GET",
	    		url: "validaDuplicados",
	    		data : "name=" + nome ,
 	 			success: function(response){
	    		if(response > 0){
		    		alert("Cadastro Duplicado");
	    		}else{
	    			salvarUsuario();
	    		}
	    		
	    		}
	    	}).fail(function(xhr,status,errorThrown){
	    		alert("Erro ao validar cadastro:" + xhr.responseText);
	    	}); 
 	
     }
   
function validaDuplicadosAdministrativo(){
   	
   	var nome = $("#nomeusuario").val();

   	  $.ajax({
	    		method: "GET",
	    		url: "validaDuplicadosAdministrativo",
	    		data : "name=" + nome ,
 	 			success: function(response){
	    		if(response > 0){
		    		alert("Cadastro Duplicado");
	    		}else{
	    			salvarAdministrativo();
	    		}
	    		
	    		}
	    	}).fail(function(xhr,status,errorThrown){
	    		alert("Erro ao validar cadastro:" + xhr.responseText);
	    	}); 
 	
     }

     
 function salvarUsuario(){
   	
   	var codigo= $("#codigo").val();
   	var nome = $("#nome").val();
	   	var datanasc = $("#datanasc").val();
	   	var idade = $("#idade").val();
		var endereco = $("#endereco").val();
		var numero = $("#numero").val();
		var bairro = $("#bairro").val();
		var cep = $("#cep").val();
		var cidade = $("#cidade").val();
		var estado = $("#estado").val();
		var telefone1 = $("#telefone1").val();
		var telefone2 = $("#telefone2").val();
		var quantosmoram = $("#quantosmoram").val();
		
		var estuda = "";
        $('input:radio[name=estuda]').each(function() {
            if ($(this).is(':checked'))
                estuda = $(this).val();
        })
		var numeronis = $("#numeronis").val();
        
       var casapropria ="";
		$('input:radio[name=casapropria]').each(function() {
           if ($(this).is(':checked'))
               casapropria = $(this).val();
       })
    

		if(nome == null || nome != null && nome.trim()==''){
   		$("#nome").focus();
   		alert('informe o nome');
   		return;
   	}
   	
   	if(datanasc == null){
   		$("#datnasc").focus();
   		alert('informe a data de nascimento');
   		return;
   	}
   	
   	$.ajax({
   		method: "POST",
   		url: "cadastrosalvar",
   		data : JSON.stringify({codigo: codigo, nome : nome, datanasc : datanasc, idade : idade, endereco : endereco, numero : numero, bairro : bairro, cep : cep, cidade : cidade, estado : estado, telefone1 : telefone1, telefone2: telefone2, estuda : estuda, quantosmoram : quantosmoram, casapropria : casapropria, numeronis : numeronis}),
   		contentType: "application/json; charset=utf-8",
   		success: function(response){    		
   
   		var resposta = confirm("Você Deseja cadastrar esse registro?");
   			if (resposta == true){
   			$("#codigo").val(response.codigo);
   			alert("Salvo com Sucesso!")
   			}else{
   			alert("Registro Cancelado com Sucesso")
   			}
   		
   			
   	}
   	}).fail(function(xhr,status,errorThrown){
   		alert("Erro ao Salvar:" + xhr.responseText);
   	});
   	
   }
 
 function salvarAdministrativo(){
 	
 	var codigo= $("#idadm").val();
 	var nome = $("#nomeusuario").val();
	var usuario = $("#usuario").val();
	var senha = $("#senha").val();
		
	var administrativo;
      $('input:radio[name=administrativo]').each(function() {
          if ($(this).is(':checked')){
              administrativo = $(this).val();
          }
      })
		
  

		if(nome == null || nome != null && nome.trim()==''){
 		$("#nomeusuario").focus();
 		alert('informe o nome');
 		return;
 	}
 	
 	if(usuario == null){
 		$("#usuario").focus();
 		alert('informe o usuario');
 		return;
 	}
 	
 	if(senha == null){
 		$("#senha").focus();
 		alert('informe a senha');
 		return;
 	}
 	
 	$.ajax({
 		method: "POST",
 		url: "administrativosalvar",
 		data : JSON.stringify({codigo: codigo, nome : nome, usuario : usuario, senha : senha, administrativo : administrativo}),
 		contentType: "application/json; charset=utf-8",
 		success: function(response){    		
 
 		var resposta = confirm("Você Deseja cadastrar esse registro?");
 			if (resposta == true){
 			$("#idadm").val(response.codigo);
 			alert("Salvo com Sucesso!")
 			}else{
 			alert("Registro Cancelado com Sucesso")
 			}
 		
 			
 	}
 	}).fail(function(xhr,status,errorThrown){
 		alert("Erro ao Salvar:" + xhr.responseText);
 	});
 	
 }