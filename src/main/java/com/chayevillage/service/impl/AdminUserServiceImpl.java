package com.chayevillage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chayevillage.common.BusinessException;
import com.chayevillage.entity.AdminUser;
import com.chayevillage.mapper.AdminUserMapper;
import com.chayevillage.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final AdminUserMapper adminUserMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AdminUser getByUsername(String username) {
        LambdaQueryWrapper<AdminUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminUser::getUsername, username);
        AdminUser user = adminUserMapper.selectOne(wrapper);
        if (user == null) {
            throw new BusinessException(401, "用户名或密码错误");
        }
        return user;
    }

    @Override
    public AdminUser getById(Long id) {
        AdminUser user = adminUserMapper.selectById(id);
        if (user == null) {
            throw new BusinessException(404, "管理员不存在");
        }
        return user;
    }

    @Override
    public boolean updatePassword(Long id, String oldPassword, String newPassword) {
        AdminUser user = getById(id);
        if (!passwordEncoder.matches(oldPassword, user.getPasswordHash())) {
            throw new BusinessException(400, "原密码错误");
        }
        user.setPasswordHash(passwordEncoder.encode(newPassword));
        return adminUserMapper.updateById(user) > 0;
    }
}
