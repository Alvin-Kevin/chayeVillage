package com.chayevillage.service;

import com.chayevillage.entity.AdminUser;

public interface AdminUserService {

    AdminUser getByUsername(String username);

    AdminUser getById(Long id);

    boolean updatePassword(Long id, String oldPassword, String newPassword);
}
