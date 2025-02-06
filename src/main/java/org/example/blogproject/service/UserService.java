package org.example.blogproject.service;

import lombok.RequiredArgsConstructor;
import org.example.blogproject.data.entity.User;
import org.example.blogproject.data.repository.UserRepository;
import org.example.blogproject.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // ✅ Spring Security가 사용자 인증 시 호출하는 메서드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) // 암호화된 비밀번호 사용
                .roles("USER") // 기본 역할 부여
                .build();
    }

    // ✅ 회원가입 메서드 (비밀번호 암호화)
    public void registerUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // 비밀번호 암호화
        user.setEmail(userDto.getEmail());

        userRepository.save(user);
    }
}
