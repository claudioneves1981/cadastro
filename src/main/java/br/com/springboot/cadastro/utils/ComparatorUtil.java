package br.com.springboot.cadastro.utils;

import java.util.Comparator;

public class ComparatorUtil implements Comparator<String>{

    @Override
    public int compare(String login, String user) {
        return login.compareTo(user);
    }
}
