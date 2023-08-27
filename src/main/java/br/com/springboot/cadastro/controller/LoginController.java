package br.com.springboot.cadastro.controller;


import br.com.springboot.cadastro.dto.AdministrativoDTO;
import br.com.springboot.cadastro.dto.LoginDTO;
import br.com.springboot.cadastro.dto.SessaoDTO;
import br.com.springboot.cadastro.service.AdministrativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("signin")
public class LoginController {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AdministrativoService administrativoService;


    @PostMapping
    @ResponseBody
    public ResponseEntity<SessaoDTO> logar(@RequestBody LoginDTO login){
        AdministrativoDTO user = administrativoService.buscarPorUsuario(login.getUsername());

        if(user!=null){
            boolean passwordOk = encoder.matches(login.getPassword(), user.getSenha());
            if(!passwordOk){
                throw new RuntimeException("Senha invalida para o login:" + login.getUsername());
            }
            SessaoDTO sessaoDTO = new SessaoDTO();
            sessaoDTO.setLogin(user.getUsuario());
            sessaoDTO.setRoles(user.getRoles());
            return ResponseEntity.ok(sessaoDTO);
        }else{
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }


}
