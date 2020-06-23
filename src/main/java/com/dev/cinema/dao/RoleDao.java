package com.dev.cinema.dao;

import com.dev.cinema.model.Role;

public interface RoleDao extends GenericDao<Role> {

    Role getRoleByName(String roleName);
}
