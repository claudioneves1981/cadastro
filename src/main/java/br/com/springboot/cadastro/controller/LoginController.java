package br.com.springboot.cadastro.controller;


import br.com.springboot.cadastro.dto.AdministrativoDTO;
import br.com.springboot.cadastro.dto.LoginDTO;
import br.com.springboot.cadastro.dto.SessaoDTO;
import br.com.springboot.cadastro.model.Administrativo;
import br.com.springboot.cadastro.service.AdministrativoService;
import br.com.springboot.cadastro.utils.ComparatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<SessaoDTO> logar(@RequestBody LoginDTO login){
        AdministrativoDTO user = administrativoService.buscarPorUsuario(login.getUsername());
        if(user!=null){
            int passwordOk = comparatorUtil.compare(login.getPassword(), user.getSenha());
            if(passwordOk != 0){
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
