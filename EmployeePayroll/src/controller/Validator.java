/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author MSI-GF63
 */
public class Validator {
    
    public static boolean LoginValidation(String uName, String passwd){
        if(uName.isEmpty()){
            JOptionPane.showMessageDialog(null, "Username field is empty", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(passwd.isEmpty()){
            JOptionPane.showMessageDialog(null, "Password field is empty", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            return  true;
        }
        return false;
    }

    public static boolean SignupValidation(String un, String pw, String rpw) {
        if (un.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (pw.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (rpw.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Re-type password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!pw.equals(rpw)) {
            JOptionPane.showMessageDialog(null, "Passwords doesn't match", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            return true;
        }
        return false;
    }
    
    public static boolean  ResetPasswordValidation(String EID, String newPasswd, String newPasswdRe){
        if(EID.isEmpty() == true){
            JOptionPane.showMessageDialog(null, "Please enter employee ID", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(newPasswd.isEmpty() == true){
            JOptionPane.showMessageDialog(null, "Please enter new password", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(newPasswdRe.isEmpty() == true){
            JOptionPane.showMessageDialog(null, "Please enter new password again", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(!newPasswd.equals(newPasswdRe)){
            JOptionPane.showMessageDialog(null, "Passwords doesn't match", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            return  true;
        }
        return false;
    }

    public static boolean RegUpdValidation(String fName, String lName, String dobDate, String email, String contact, String address1, String address2, String houseNo, String postalCode, String department, String designation, String dateHiredDate, String basicSal, String jobTitle, String status) {

        if (fName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "First name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (lName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Last name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (dobDate.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Date of birth cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Email cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (contact.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Contact cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (houseNo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "House number cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (address2.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Address line 2 cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (postalCode.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Postal code cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (department.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Department cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (designation.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Designation cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (dateHiredDate.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Haired date cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (basicSal.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Basic salary cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (jobTitle.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Job title cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (status.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Status cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (fName.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "First name cannot have numbers", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (lName.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "Last name cannot have numbers", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!contact.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "Contact number cannot have charcters", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (contact.length() < 10 || contact.length() > 10) {
            JOptionPane.showMessageDialog(null, "Contact number must have 10 digits", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!postalCode.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "Postal code cannot have charcters", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (postalCode.length() < 5 || postalCode.length() > 5) {
            JOptionPane.showMessageDialog(null, "Postal code must have 5 digits", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (department.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "Department name cannot have numbers", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (designation.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "Designation cannot have numbers", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!basicSal.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "Basic salary cannot have charcters", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (jobTitle.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "Job title cannot have numbers", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (status.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "Status cannot have numbers", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            return true;
        }
        return false;
    }

    public static boolean emailValidation(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.find()) {
            JOptionPane.showMessageDialog(null, "Invalid email address", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            return true;
        }
        return false;
    }

    public static boolean allowanceValidation(String ot, String medical, String bonus, String other) {
        if (ot.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Overtime value cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (medical.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Medical value cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (bonus.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Bonus value cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (other.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Other value cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!ot.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "Overtime value cannot have characters", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!medical.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "Medical value cannot have characters", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!bonus.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "Bonus value cannot have characters", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!other.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "Other value cannot have characters", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            return true;
        }
        return false;
    }
    
    
    
    
}
