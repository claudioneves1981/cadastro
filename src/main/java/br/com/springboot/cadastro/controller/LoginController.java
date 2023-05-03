package br.com.springboot.cadastro.controller;

import br.com.springboot.cadastro.dto.Login;
import br.com.springboot.cadastro.dto.Sessao;
import br.com.springboot.cadastro.model.Administrativo;
import br.com.springboot.cadastro.repository.AdministrativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class LoginController {

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private AdministrativoRepository repository;

    @PostMapping("/login")
    public Sessao logar(@RequestBody Login login){
        Administrativo user = repository.findByUsuario(login.getUsername());
        if(user!=null){
            boolean passwordOk = encoder.matches(login.getPassword(),user.getSenha());
            if(!passwordOk){
                throw new RuntimeException("Senha invalida para o login:" + login.getUsername());
            }
            Sessao sessao = new Sessao();
            sessao.setLogin(user.getUsuario());
            sessao.setRoles(user.getRoles());
            return sessao;
        }else{
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }
}
