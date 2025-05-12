package org.voduybao.bookstorebackend.controllers.v1;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.voduybao.bookstorebackend.dtos.AuthenDto;
import org.voduybao.bookstorebackend.services.AuthenService;
import org.voduybao.bookstorebackend.tools.response.http.Result;

@Slf4j
@RestController
@Validated
@RequestMapping("/v1/authentication")
@RequiredArgsConstructor
public class AuthController {
    @Setter(onMethod_ = @Autowired)
    private AuthenService authenService;

    @PostMapping("/register")
    public Result register(@RequestBody @Validated AuthenDto.RegisterRequest request) {
        authenService.reigster(request);
        return Result.success();
    }

    @PostMapping("/sign-in")
    public Result signIn(@RequestBody @Validated AuthenDto.LoginRequest request,
                         HttpServletResponse response) {
        var reponse = authenService.login(request, response);
        return Result.content(reponse);
    }

    @PostMapping("/refresh-token")
    public Result refreshToken(@CookieValue(name = "refreshToken") String refreshToken,
                               HttpServletResponse response) {
        var reponse = authenService.refresh(refreshToken, response);
        return Result.content(reponse);
    }

    @PostMapping("/sign-out")
    public Result signOut(@RequestBody @Validated AuthenDto.LogoutRequest request,
                          HttpServletResponse response) {
        authenService.logout(request, response);
        return Result.success();
    }

    @GetMapping("/socail-login")
    public Result socailAuth(@RequestParam("login_type") String loginType,
                             HttpServletResponse response) {
        var reponse = authenService.socailAuthType(loginType, response);
        return Result.content(reponse);
    }

    @GetMapping("/callback")
    public Result callback(@RequestParam("code") String code,
                           @RequestParam("login_type") String loginType) {
        System.out.println("call back");
        return Result.success();
    }

}