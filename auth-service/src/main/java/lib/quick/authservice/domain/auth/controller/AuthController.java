package lib.quick.authservice.domain.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lib.quick.authservice.domain.auth.controller.dto.request.UserJoinRequest;
import lib.quick.authservice.domain.auth.controller.dto.request.UserLoginRequest;
import lib.quick.authservice.domain.auth.controller.dto.response.RefreshTokenResponse;
import lib.quick.authservice.domain.auth.controller.dto.response.UserLoginResponse;
import lib.quick.authservice.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/join")
    public ResponseEntity<Void> joinMember(@RequestBody @Valid UserJoinRequest userJoinRequest){
        authService.joinMember(userJoinRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> loginMember(@RequestBody @Valid UserLoginRequest userLoginRequest){
        return ResponseEntity.ok(authService.loginMember(userLoginRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refreshToken(HttpServletRequest request){
        String accessToken = request.getHeader("Access-Token");
        String refreshToken = request.getHeader("Refresh-Token");

        return ResponseEntity.ok(authService.refreshToken(accessToken, refreshToken));
    }
}
