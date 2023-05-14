package controllers;


import helpers.HashUtils;
import models.User;
import play.mvc.Controller;

public class PublicContentBase extends Controller {

    public static void register(){
        render();
    }

    public static void processRegister(String username, String password, String passwordCheck, String type){
        //The following blocks verify if either the username is null, empty or contains characters that are not either letters or numbers
        //If any of the aforementioned conditions occur then render an html with an error message
        //Note, probably we could reuse the original register.html file and just pass the error as there is code to handle it, but since I am not profficient with this framework I will just go with an easier solution which is duplicating the register.html file and putting an error message on the new one.
        if (username == null || username.length()==0) {
            registerError();
        } else {
            for (char c : username.toCharArray()) {
                if (!Character.isLetterOrDigit(c)) {
                    registerError();
                }
            }
            User u = new User(username, HashUtils.getMd5(password), type, -1);
            u.save();
            registerComplete();
        }
    }

    public static void registerComplete(){
        render();
    }

    public static void registerError(){
        render();
    }
}
