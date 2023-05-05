package br.com.springboot.cadastro.controller;

import br.com.springboot.cadastro.model.Administrativo;
import br.com.springboot.cadastro.service.AdministrativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cadastroadministrativo")
public class CadastroAdministrativoController {

    @Autowired
    private AdministrativoService administrativoService;

    @PostMapping(value = "/salvar")
    @ResponseBody
    public ResponseEntity<Administrativo> salvar(@RequestBody Administrativo administrativo) {
        administrativoService.inserir(administrativo);
        return ResponseEntity.ok(administrativo);
    }
}
