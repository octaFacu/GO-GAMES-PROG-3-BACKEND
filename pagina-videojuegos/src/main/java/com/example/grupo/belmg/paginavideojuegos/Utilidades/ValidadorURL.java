package com.example.grupo.belmg.paginavideojuegos.Utilidades;

import java.net.URL;

public class ValidadorURL {

    /*public boolean validarURL(String url){

        try{
            (new URL(url)).openStream().close();

            return true;

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }*/

    public boolean validarURL(String url){

        if(url.length() < 9){
            return false;
        }else if(url.substring(0,8).equals("https://")){
            return true;
        }else {
            return false;
        }
    }

}
