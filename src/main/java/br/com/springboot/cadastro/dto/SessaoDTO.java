package br.com.springboot.cadastro.dto;

import java.util.ArrayList;
import java.util.List;

public class SessaoDTO {

    private String login;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    private List<String> roles;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }



}

