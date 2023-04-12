/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.RoleDB;
import java.util.List;
import models.Role;

/**
 *
 * @author Xavier
 */
public class RoleService {
    public Role get(Integer id) throws Exception {
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(id);
        return role;
    }
    
    public List<Role> getAll() throws Exception {
        RoleDB roleDB = new RoleDB();
        List<Role> roles = roleDB.getAll();
        return roles;
    }
    
    public void insert(Role role) throws Exception {
        RoleDB roleDB = new RoleDB();
        roleDB.insert(role);
    }
    
    public void update(Role role) throws Exception {
        RoleDB roleDB = new RoleDB();
        roleDB.update(role);
    }
    
    public void delete(Role role) throws Exception {
        RoleDB roleDB = new RoleDB();
        roleDB.delete(role);
    }
}
