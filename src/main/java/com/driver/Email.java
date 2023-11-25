package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        if(oldPassword.equals(password) && isValid(newPassword)){
            password = newPassword;
            System.out.println("Password changed successfully!");
        }else{
            System.out.println("Password change failed. Please check the criteria.");
        }
    }

    public boolean isValid(String newPassword){
        if(newPassword.length() < 8){
            return false;
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecialCh = false;

        for(int ch : newPassword.toCharArray()){
            if(Character.isUpperCase(ch)){
                hasUpper = true;
            } else if (Character.isLowerCase(ch)) {
                hasLower = true;
            }else if(Character.isDigit(ch)){
                hasDigit = true;
            }else{
                hasSpecialCh = true;
            }
        }

        return hasUpper && hasLower && hasDigit && hasSpecialCh;
    }
}
