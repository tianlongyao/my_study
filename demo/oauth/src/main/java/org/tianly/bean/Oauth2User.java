package org.tianly.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Oauth2User {
    private String uerName;
    private String password;
    private String nickName;
    private String phone;
    private String email;
    private String avatar;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;

    private Set<SimpleGrantedAuthority> grantedAuthorities;

    private List<Map<String, Object>> roleList;

    private   List<Map<String, Object>> permissionList;
}
