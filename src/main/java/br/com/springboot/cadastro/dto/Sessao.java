package br.com.springboot.cadastro.dto;

import java.util.ArrayList;
import java.util.List;

public class Sessao {

    private String login;
    private Boolean administrativo;


    public Boolean getAdministrativo() {
        return administrativo;
    }

    public void setAdministrativo(Boolean administrativo) {
        this.administrativo = administrativo;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }



}

