/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.UserDAO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nelio_saturnino
 */
public class UserServices {
    private UserDAO dao;
    
    public UserServices (){
        dao = new UserDAO();
    }
    
    public void validateName(String name, Map<String, String> errors){
        if (name == null || "".equals(name) || name.length() > 40)
            errors.put("name", "Name must have between 1 and 40 characters.");
    }
    
    public void validateEmail(String email, Map<String, String> errors){
        String emailRegex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        
        if (!email.matches(emailRegex))
            errors.put("email", "Not a valid e-mail address");
        else if (dao.getUserByEmail(email) != null)
            errors.put("email", "A user already exists with this e-mail address");
    }
    
    public void validateEmail(String email,int id, Map<String, String> errors){
        validateEmail(email, errors);
        
        if(errors.containsValue("A user already exists with this e-mail address")){
            User user2 = dao.getUserByEmail(email);

            if (user2 != null && user2.getId() != id) {
                errors.put("email", "Another user already exists with this e-mail address");
            }
            else
                errors.remove("email");
        }
    }
    
    public void validatePassword(String password, Map<String, String> errors){
        if (password == null || password.length() < 8 || password.length() > 40) {
            errors.put("password", "Password must have between 8 and 20 characters.");
        }
    }
    
    public User getUserById(int id){
        return dao.getUserById(id);
    }
    
    public User getUserByEmail(String email){
        return dao.getUserByEmail(email);
    }
    
    public void addUser(User user){
        dao.addUser(user);
    }
    
    public void updateUser(User user){
        dao.updateUser(user);
    }
    
    public void deleteUser(int id){
        dao.deleteUser(id);
    }
    
    public List<User> getAllUsers(){
        return dao.getAllUsers();
    }
}
