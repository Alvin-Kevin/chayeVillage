package com.chayevillage.controller.admin;

import com.chayevillage.common.Result;
import com.chayevillage.dto.request.ChangePasswordRequest;
import com.chayevillage.dto.request.LoginRequest;
import com.chayevillage.dto.response.LoginResponse;
import com.chayevillage.entity.AdminUser;
import com.chayevillage.security.JwtTokenProvider;
import com.chayevillage.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/admin/auth")
@RequiredArgsConstructor
public class AdminAuthController {

    private final AdminUserService adminUserService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public Result<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        AdminUser user = adminUserService.getByUsername(request.getUsername());
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            return Result.error(400, "用户名或密码错误");
        }
        String token = jwtTokenProvider.generateToken(user.getId(), user.getUsername());
        LoginResponse.UserInfo userInfo = new LoginResponse.UserInfo(
                user.getId(),
                user.getUsername(),
                user.getNickname(),
                user.getAvatarUrl()
        );
        LoginResponse response = new LoginResponse(token, userInfo);
        return Result.success(response);
    }

    @PutMapping("/password")
    public Result<?> changePassword(@Valid @RequestBody ChangePasswordRequest request) {
        adminUserService.updatePassword(1L, request.getOldPassword(), request.getNewPassword());
        return Result.success();
    }
}
