package com.qiangqiang.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/24
 * \* Time: 14:29
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //设置账号有个role1的角色
        List<GrantedAuthority> role = AuthorityUtils.commaSeparatedStringToAuthorityList("role1");
        return new User("user",new BCryptPasswordEncoder().encode("123456"),role);
    }
}