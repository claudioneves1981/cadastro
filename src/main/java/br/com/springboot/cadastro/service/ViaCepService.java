package br.com.springboot.cadastro.service;

import br.com.springboot.cadastro.dto.EnderecoDTO;
import br.com.springboot.cadastro.model.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@FeignClient(name = "viacep", url = "http://viacep.com.br/ws")
@RestController
public interface ViaCepService {

    @RequestMapping(method = RequestMethod.GET, value = "/{cep}/json/")
    Endereco consultarCep(@PathVariable("cep") String cep);

}