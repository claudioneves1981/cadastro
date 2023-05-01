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

    function gerandoPdf(nome){

                  $.ajax({
        	    		method: "GET",
        	    		url: "gerandopdf",
        	    		data : "name=" + nome ,
        	    		success: function(response){
        	    		    $('.alert').removeClass("hide");
                            $('.alert').addClass("show");
                            $('.alert').addClass("showAlert");
                            $('.msg').text("PDF salvo com sucesso!!");
                            setTimeout(function(){
                                       $('.alert').addClass("hide");
                                       $('.alert').removeClass("show");

                            },5000);
     	    			}
     	    	  }).fail(function(xhr,status,errorThrown){
     	    	   $('.alert').removeClass("hide");
                   				      $('.alert').addClass("show");
                   				      $('.alert').addClass("showAlert");
                   				      $('.msg').text("Erro ao buscar usuario:");
                   				      setTimeout(function(){
                                         					$('.alert').addClass("hide");
                                         					$('.alert').removeClass("show");

                                        },5000);

     	          });

    }
   	
    function listarCadastros(){

   	 var nome = '';
   		if ($('#cadastroBusca').val() != ''){
   	    	 
   	    	 nome = $('#cadastroBusca').val();
   	         gerandoPdf(nome);

    		  
 	    }else if($('#nomeBusca').val()!=''){
    	  
    	  nome = $('#nomeBusca').val();
    	  gerandoPdf(nome);

  	    }else{
  	    $('.alert').removeClass("hide");
        $('.alert').addClass("show");
        $('.alert').addClass("showAlert");
        $('.msg').text("Campo não pode estar vazio");
            setTimeout(function(){
                 $('.alert').addClass("hide");
                 $('.alert').removeClass("show");
            },5000);
  	    }
   }
   
   function CriaPDF(codigo) {
   	
    	$.ajax({
   		    method: "GET",
   		    url: "cadastrobuscaruserid",
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

        $('.alert').removeClass("hide");
        $('.alert').addClass("show");
        $('.alert').addClass("showAlert");
        $('.msg').text("PDF gerado com Sucesso!!");

            setTimeout(function(){
                 $('.alert').addClass("hide");
                 $('.alert').removeClass("show");
            },5000);



   		    }
    	}).fail(function(xhr,status,errorThrown){
    	        $('.alert').removeClass("hide");
                $('.alert').addClass("show");
                $('.alert').addClass("showAlert");
                $('.msg').text("Erro ao buscar usuario por id:");
                    setTimeout(function(){
                         $('.alert').addClass("hide");
                         $('.alert').removeClass("show");
                    },5000);
    	});
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
	    			$('.alert').removeClass("hide");
                    $('.alert').addClass("show");
                    $('.alert').addClass("showAlert");
                    $('.msg').text(response);
                    setTimeout(function(){
                               $('.alert').addClass("hide");
                               $('.alert').removeClass("show");
                    },5000);
	    		}
	    	}).fail(function(xhr,status,errorThrown){
	    	     $('.alert').removeClass("hide");
                 $('.alert').addClass("show");
                 $('.alert').addClass("showAlert");
                 $('.msg').text("Erro ao deletar usuario por id:" + xhr.responseText);
                 setTimeout(function(){
                            $('.alert').addClass("hide");
                            $('.alert').removeClass("show");
                 },5000);
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
	    			$('.alert').removeClass("hide");
                    $('.alert').addClass("show");
                    $('.alert').addClass("showAlert");
                    $('.msg').text(response);
                    setTimeout(function(){
                               $('.alert').addClass("hide");
                               $('.alert').removeClass("show");
                    },5000);
	    		}
	    	}).fail(function(xhr,status,errorThrown){
	    	 $('.alert').removeClass("hide");
             $('.alert').addClass("show");
             $('.alert').addClass("showAlert");
             $('.msg').text("Erro ao deletar usuario por id:" + xhr.responseText);
             setTimeout(function(){
                        $('.alert').addClass("hide");
                        $('.alert').removeClass("show");
             },5000);
	    	});
		}
   }
   
function colocarEmEdicaoAdm(codigo){
		
   	$.ajax({
	    		method: "GET",
	    		url: "administrativobuscaruserid",
	    		data : "iduser=" + codigo,
	    		success: function(response){
	    		    $("#idadm").val(response.codigo);
   				    $("#nomeusuario").val(response.nome);
	   			    $("#usuario").val(response.usuario);
	   			    $("#senha").val(response.senha);
	    		    $("#cadastrar").remove();
	    		    $("#novo").after(' '+'<button id="cadastrar" type="button" class="input-block btn-secondary" onclick="salvarAdministrativo()">Cadastrar</button>');

				
	    	    }
	}).fail(function(xhr,status,errorThrown){
	             $('.alert').removeClass("hide");
                 $('.alert').addClass("show");
                 $('.alert').addClass("showAlert");
                 $('.msg').text("Erro ao buscar usuario por id:" + xhr.responseText);
                 setTimeout(function(){
                            $('.alert').addClass("hide");
                            $('.alert').removeClass("show");
                },5000);
	});
}

  function novoAdministrativo(){
	document.getElementById('formAdministrativo').reset()
	$("#cadastrar").remove();
	$("#novo").after('<button id="cadastrar" type="button" class="input-block btn-secondary" onclick="salvarAdministrativo()">Cadastrar</button>');
  }
  
  function novoUsuario(){
		document.getElementById('formCadastro').reset()
		$("#salvar").remove();
		$("#botoes").append('<button id="salvar" type="button" class="input-block btn-primary" onclick="salvarUsuario()">Salvar</button>');
  }
   
function colocarEmEdicao(codigo){
		
   	$.ajax({
	    		method: "GET",
	    		url: "cadastrobuscaruserid",
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
	    		    $("#botoes").append(' '+'<button id="salvar" type="button" class="input-block btn-primary" onclick="salvarUsuario()">Salvar</button>');
                }


	}).fail(function(xhr,status,errorThrown){
	 $('.alert').removeClass("hide");
     $('.alert').addClass("show");
     $('.alert').addClass("showAlert");
     $('.msg').text("Erro ao buscar usuario por id:" + xhr.responseText);
     setTimeout(function(){
               $('.alert').addClass("hide");
               $('.alert').removeClass("show");
     },5000);
	});
   	
   	$('#myTab li:nth-child(3) button').tab('show');
}

  function ordenarPorCidade(){
   	   	
	   $.ajax({
	  		method: "GET",
	  		url: "ordenarporcidade",
	  		success: function(response){
	  			$('#selecionarCidade > option').remove();
	  			for (var i = 0; i < response.length; i++){
	 				$('#selecionarCidade').append('<option value="'+i+'">'+response[i].cidade+'</option>');
	 			}
	  		}
	   }).fail(function(xhr,status,errorThrown){
	    $('.alert').removeClass("hide");
        $('.alert').addClass("show");
        $('.alert').addClass("showAlert");
        $('.msg').text("Erro ao buscar cidade:" + xhr.responseText);
        setTimeout(function(){
                   $('.alert').addClass("hide");
                   $('.alert').removeClass("show");
        },5000);
	   });
   	
  }

   function pesquisarCadastro(){
       	
 	      var nome = $('#cadastroBusca').val();
 	  
 		  $.ajax({
 	    		method: "GET",
 	    		url: "buscarporcadastro",
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
   	   						'<td><div><a class="nav input-block btn-secondary" href="#" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile" role="tab" aria-controls="profile" aria-selected="true" onclick="colocarEmEdicao('+response[i].codigo+')">Editar</a></div></td>'+
   	   						'<td><button type="button" class="input-block btn-danger" onclick="deleteCadastro('+response[i].codigo+')">Delete</button></td>'+
   	   						'<td><button type="button" class="input-block btn-primary" onclick="CriaPDF('+response[i].codigo+')">Imprimir/PDF</button></td></tr>'
   	   					);
   	    			}
 	    			$('#quantidade').remove();
 	    			$('#registrosencontrados').append('<label id = "quantidade">'+response.length+'</label>');
 	    		}
 	      }).fail(function(xhr,status,errorThrown){
 	       $('.alert').removeClass("hide");
           $('.alert').addClass("show");
           $('.alert').addClass("showAlert");
           $('.msg').text("Erro ao buscar usuario:" + xhr.responseText);
           setTimeout(function(){
                         $('.alert').addClass("hide");
                         $('.alert').removeClass("show");
           },5000);
 	      });
     
   }
   
   function pesquisarAdministrativo(){
   	
   	      var nome ="";
   	      var resposta = "";
   		  $.ajax({
   	    		method: "GET",
   	    		url: "buscarporadministrativo",
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
     	   						'<td><button type="button" class="input-block btn-secondary" onclick="colocarEmEdicaoAdm('+response[i].codigo+')">Ver</button></td>'+
     	   						'<td><button type="button" class="input-block btn-danger" onclick="deleteAdministrativo('+response[i].codigo+')">Delete</button></td></tr>'
     	   				);
     	   			}
   	    			$('#quantidadeadm').remove();
   	    			$('#registrosadministrativo').append('<label id = "quantidadeadm">'+response.length+'</label>');
   	    		}
   	      }).fail(function(xhr,status,errorThrown){
   	       $('.alert').removeClass("hide");
           $('.alert').addClass("show");
           $('.alert').addClass("showAlert");
           $('.msg').text("Erro ao buscar usuario: " + xhr.responseText);
           setTimeout(function(){
                     $('.alert').addClass("hide");
                     $('.alert').removeClass("show");
           },5000);
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
  		url: "buscarporcadastro",
  		data :"name="+nome,
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
                        '<td><div><a class="nav input-block btn-secondary" href="#" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile" role="tab" aria-controls="profile" aria-selected="true" onclick="colocarEmEdicao('+response[i].codigo+')">Editar</a></div></td>'+
  						'<td><button type="button" class="input-block btn-danger" onclick="deleteCadastro('+response[i].codigo+')">Delete</button></td>'+
  						'<td><button type="button" class="input-block btn-primary" onclick="CriaPDF('+response[i].codigo+')">Imprimir/PDF</button></td></tr>'
  				);
   			}
 			$('#quantidade').remove();
 			$('#registrosencontrados').append('<label id = "quantidade">'+response.length+'</label>');
  		}
  	}).fail(function(xhr,status,errorThrown){
  	    $('.alert').removeClass("hide");
        $('.alert').addClass("show");
        $('.alert').addClass("showAlert");
        $('.msg').text("Erro ao buscar usuario :" + xhr.responseText);
        setTimeout(function(){
                   $('.alert').addClass("hide");
                   $('.alert').removeClass("show");
        },5000);
  	});
		
  }
   
   function pesquisarNomes(cidade){
   	
   	var nome = $('#nomeBusca').val();
   	
   	if(nome !="" && cidade !=""){
   		
   		$.ajax({
	    		method: "GET",
	    		url: "cadastrobuscarporparametros",
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
                                '<td><div><a class="nav input-block btn-secondary" href="#" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile" role="tab" aria-controls="profile" aria-selected="true" onclick="colocarEmEdicao('+response[i].codigo+')">Editar</a></div></td>'+
	       						'<td><button type="button" class="input-block btn-danger" onclick="deleteCadastro('+response[i].codigo+')">Delete</button></td>'+
	       						'<td><button type="button" class="input-block btn-primary" onclick="CriaPDF('+response[i].codigo+')">Imprimir/PDF</button></td></tr>'
	       				);
	        		}
	    			$('#quantidade').remove();
	    			$('#registrosencontrados').append('<label id = "quantidade">'+response.length+'</label>');
	    		}
	    }).fail(function(xhr,status,errorThrown){
	         $('.alert').removeClass("hide");
             $('.alert').addClass("show");
             $('.alert').addClass("showAlert");
             $('.msg').text("Erro ao buscar usuario:" + xhr.responseText);
             setTimeout(function(){
                $('.alert').addClass("hide");
                $('.alert').removeClass("show");
             },5000);
	    });
   		
    }
     
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
   			 $('.alert').removeClass("hide");
             $('.alert').addClass("show");
             $('.alert').addClass("showAlert");
             $('.msg').text(response);
             setTimeout(function(){
                      $('.alert').addClass("hide");
                      $('.alert').removeClass("show");
             },5000);
   			}else{
   			 $('.alert').removeClass("hide");
             $('.alert').addClass("show");
             $('.alert').addClass("showAlert");
             $('.msg').text("Operação cancelada:");
             setTimeout(function(){
                       $('.alert').addClass("hide");
                       $('.alert').removeClass("show");
             },5000);
   			}
    	}
   	}).fail(function(xhr,status,errorThrown){
   	 $('.alert').removeClass("hide");
     $('.alert').addClass("show");
     $('.alert').addClass("showAlert");
     $('.msg').text("Erro ao salvar:");
     setTimeout(function(){
                $('.alert').addClass("hide");
                $('.alert').removeClass("show");
     },5000);
   	});
   	

 }
  function salvarAdministrativo(){

       	   // var nome = $("#nomeCad").val();
       	    var nome = $('input:text[name=nomeCad]').val();
       	    var usuario = $('input:text[name=usuarioCad]').val();
       	    //var usuario = $("#usuarioCad").val();
       	    var senha = $('input:password[name=senhaCad]').val();
 		    //var senha = $("#senhaCad").val();
 		    var senhaConfirma = $('input:password[name=senhaConfirma]').val();
 			//var senhaConfirma = $("#senhaConfirma").val();

     	    var administrativo = "";
            $('input:radio[name=administrativoCad]').each(function() {
                if ($(this).is(':checked')){
                 administrativo = $(this).val();
                }
            })

     		if (nome == "" || usuario == "" || senha == "" || senhaConfirma == "" || administrativo == ""){


 				$('.alert').removeClass("hide");
 				$('.alert').addClass("show");
 				$('.alert').addClass("showAlert");
 				$('.msg').text('Atenção: Há campos vazios no formulário, preencha para continuar');

 				setTimeout(function(){
 					$('.alert').addClass("hide");
 					$('.alert').removeClass("show");

 				},5000);

 			} else if (nome.length < 3){
 				$('.alert').removeClass("hide");
 				$('.alert').addClass("show");
 				$('.alert').addClass("showAlert");
 				$('.msg').text('Atenção: Nome muito curto');
 				$('input:text[name=nomeCad]').focus();

 				setTimeout(function(){
 					$('.alert').addClass("hide");
 					$('.alert').removeClass("show");

 				},5000);

 			}else if(senha.length < 8 || senha.indexOf('@') == -1 || senha.indexOf('#') == -1){

 				$('.alert').removeClass("hide");
 				$('.alert').addClass("show");
 				$('.alert').addClass("showAlert");
 				$('.msg').text('Atenção: Senha deve ser maior que 8 caracteres e conter caracteres especiais "@" e "#" ');
 				$('input:password[name=senhaCad]').focus();

 				setTimeout(function(){
 					$('.alert').addClass("hide");
 					$('.alert').removeClass("show");

 				},5000);

 			} else if(senha != senhaConfirma){

 			$('.alert').removeClass("hide");
 				$('.alert').addClass("show");
 				$('.alert').addClass("showAlert");
 				$('.msg').text('Atenção: Senha deve ser igual ');
 				$('input:password[name=senhaConfirma]').focus();

 				setTimeout(function(){
 					$('.alert').addClass("hide");
 					$('.alert').removeClass("show");

 				},5000);

 			}else{

            	$.ajax({
       		        method: "POST",
       		        url: "administrativosalvar",
       		        data : JSON.stringify({nome : nome, usuario : usuario, senha : senha, administrativo : administrativo}),
       		        contentType: "application/json; charset=utf-8",
       		        success: function(response){

       		            var resposta = confirm("Você Deseja cadastrar esse Usuario?");
       			        if (resposta == true){
       			          $('.alert').removeClass("hide");
 				          $('.alert').addClass("show");
 				          $('.alert').addClass("showAlert");
 				          $('.msg').text(response);
 				          setTimeout(function(){
                 					$('.alert').addClass("hide");
                 					$('.alert').removeClass("show");

                         },5000);
                         //window.location.href = "index.html";

       			        }else{
       			        $('.alert').removeClass("hide");
 				        $('.alert').addClass("show");
 				        $('.alert').addClass("showAlert");
 				        $('.msg').text('Operação Cancelada ');
 				            setTimeout(function(){
                 					$('.alert').addClass("hide");
                 					$('.alert').removeClass("show");

                            },5000);

       			        }


        	        }
       	        }).fail(function(xhr,status,errorThrown){
       	        $('.alert').removeClass("hide");
 				$('.alert').addClass("show");
 				$('.alert').addClass("showAlert");
 				$('.msg').text('Erro ao salvar ');
 				    setTimeout(function(){
                 					$('.alert').addClass("hide");
                 					$('.alert').removeClass("show");

                    },5000);
       	        });
       	    }

       	$('.close-btn').click(function(){

 			$('.alert').addClass("hide");
 			$('.alert').removeClass("show");


 		});

 }

 $("#logar").on('click', function(){
         var usuario = $('input:text[name=usuario]').val();
       	// var usuario = $("#usuario").val();
       	 var senha = $('input:password[name=senha]').val();
     	// var usuario = $("#usuario").val();
 		 //var senha  =  $("#senha").val();
     	 var administrativo;
     	$('input:radio[name=administrativoLogin]').each(function() {
             if ($(this).is(':checked')){
                 administrativo = $(this).val();
              //console.log(administrativo)
             }
        })

     if(usuario == "" || senha == "") {
 		        $('.alert').removeClass("hide");
 				$('.alert').addClass("show");
 				$('.alert').addClass("showAlert");
 				$('.msg').text('Todos os Campos São Obrigatorios');
 				setTimeout(function(){
                 					$('.alert').addClass("hide");
                 					$('.alert').removeClass("show");

                },5000);
 	}else if(administrativo == "false"){
 		//administrativo = true;
 		$.ajax({
 			url: "login",
 			method: "POST",
 			data: JSON.stringify({usuario : usuario, senha : senha, administrativo : administrativo}),
       		contentType: "application/json; charset=utf-8"
 		}).done(function(retorno){
 				    if(retorno == true){
 					    window.localStorage.setItem("usuariologado", "Olá "+usuario);
 					    window.location.href = "logado.html";
 				    }else{
 				         $('.alert').removeClass("hide");
 				         $('.alert').addClass("show");
 				         $('.alert').addClass("showAlert");
 				         $('.msg').text('Usuario , Senha ou Privilégio incorreto');
 				         setTimeout(function(){
                          					$('.alert').addClass("hide");
                          					$('.alert').removeClass("show");

                         },5000);
 				    }

 		});

 		$("#usuario").val("");
 		$("#senha").val("");

 	}else{

 		$.ajax({
 			url: "loginadmin",
 			method: "POST",
 			data: JSON.stringify({usuario : usuario, senha : senha, administrativo : administrativo}),
       		contentType: "application/json; charset=utf-8"
 		}).done(function(retorno){
 		        console.log(retorno);
 				 if(retorno == true){
                           window.localStorage.setItem("usuariologado", "Olá "+usuario);
                           window.location.href = "logadoadmin.html";
                 }else{
 					  $('.alert').removeClass("hide");
 				      $('.alert').addClass("show");
 				      $('.alert').addClass("showAlert");
 				      $('.msg').text('Usuario , Senha ou Privilégio incorreto');
 				      setTimeout(function(){
                       					$('.alert').addClass("hide");
                       					$('.alert').removeClass("show");

                      },5000);
 				 }

 		});


 		$("#usuario").val("");
 		$("#senha").val("");
 	}
 });

 $("#confirma").on('click', function(){
    salvarAdministrativo();
 });