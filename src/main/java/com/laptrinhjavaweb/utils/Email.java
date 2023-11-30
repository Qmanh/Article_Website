package com.laptrinhjavaweb.utils;

public class Email {
    public static String writeEmailSendPassword(String password){
        String message= String.format("Hello,\n" +
                "New password for your account: "+ password +"\n"+
                "When you login. you should change a new password \n"+
                "Thanks, \n"+
                "QM Blog is sent this mail!"
        );
        return message;
    }
    public static String writeSubject(String username){
        return "Reset Password Username: "+username +" at QM Blog";
    }

}
