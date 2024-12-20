package com.movie.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        // OAuth2 로그인 성공 후 사용자 정보 가져오기
        String clientId = userRequest.getClientRegistration().getRegistrationId(); // google, facebook, apple
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");

        // 사용자 정보 저장 로직 추가 (DB 연동)
        // 예: userRepository.findByEmailOrCreate(email, name, clientId);

        // 반환된 OAuth2User 수정 가능
        return oauth2User;
    }
}