package br.com.springboot.cadastro.controller;

import br.com.springboot.cadastro.dto.Login;
import br.com.springboot.cadastro.dto.Sessao;
import br.com.springboot.cadastro.model.Administrativo;
import br.com.springboot.cadastro.repository.AdministrativoRepository;
import br.com.springboot.cadastro.service.AdministrativoService;
import br.com.springboot.cadastro.utils.ComparatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
public class LoginController {




    //@Autowired
    //private PasswordEncoder encoder;

    private final ComparatorUtil comparatorUtil = new ComparatorUtil();

    @Autowired
    private AdministrativoService administrativoService;


    @PostMapping
    @ResponseBody
    public ResponseEntity<Sessao> logar(@RequestBody Login login){
        Administrativo user = administrativoService.buscarPorUsuario(login.getUsername());
        if(user!=null){
            int passwordOk = comparatorUtil.compare(login.getPassword(), user.getSenha());
            if(passwordOk != 0){
                throw new RuntimeException("Senha invalida para o login:" + login.getUsername());
            }
            Sessao sessao = new Sessao();
            sessao.setLogin(user.getUsuario());
            sessao.setAdministrativo(user.getAdministrativo());
            return ResponseEntity.ok(sessao);
        }else{
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }


}
