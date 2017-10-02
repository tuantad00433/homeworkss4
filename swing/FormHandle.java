/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ADMIN-PC
 */
public class FormHandle {
    String patternMail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    String patternName = "^[\\p{L} .'-]+$";
    public HashMap<String,String> validateLogin(String name, String mail){
        HashMap<String,String> errors = new HashMap<>();
    
        Pattern mailPt = Pattern.compile(patternMail);
        Pattern namePt = Pattern.compile(patternName);
        Matcher nameMatch = namePt.matcher(name);
        Matcher mailMatch = mailPt.matcher(mail);
        if(!nameMatch.matches()){
            errors.put("txtName","Tên bỏ trống hoặc không đúng định dạng");
        }
        if (!mailMatch.matches()) {
            errors.put("txtMail", "Email bỏ trống hoặc không đúng định dạng");
        }
        return errors;
    }
}
