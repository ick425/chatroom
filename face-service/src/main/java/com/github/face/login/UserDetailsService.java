package com.github.face.login;

import com.github.face.login.entity.CustomUser;
import com.github.face.user.entity.SysUser;
import com.github.face.user.service.SysUserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.github.face.constants.SystemConstant.USER_STATUS_0;

/**
 * 登录认证
 *
 * @author wo
 */
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


    @Resource
    private SysUserService sysUserService;

    /**
     * 访问数据库查询账户信息
     *
     * @param username 手机号
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名进行查询
        SysUser sysUser = sysUserService.getUserByUserName(username);
        if (null == sysUser) {
            throw new UsernameNotFoundException("用户名不存在！");
        }

        if (Objects.equals(sysUser.getStatus(), USER_STATUS_0)) {
            throw new RuntimeException("账号已停用");
        }

        //根据userid查询用户操作权限数据
//        List<String> userPermsList = sysMenuService.findUserPermsByUserId(sysUser.getId());
        List<String> userPermsList = new ArrayList<>();
        //创建list集合，封装最终权限数据
        List<SimpleGrantedAuthority> authList = new ArrayList<>();
        //查询list集合遍历
        for (String perm : userPermsList) {
            authList.add(new SimpleGrantedAuthority(perm.trim()));
        }
        // return null;
        return new CustomUser(sysUser, authList);
    }
}
