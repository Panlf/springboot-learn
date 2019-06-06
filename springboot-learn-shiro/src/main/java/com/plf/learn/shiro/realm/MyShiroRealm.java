package com.plf.learn.shiro.realm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.plf.learn.shiro.bean.User;
import com.plf.learn.shiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Panlf
 * @date 2019/6/5
 */
public class MyShiroRealm extends AuthorizingRealm {


    @Autowired
    private UserService userService;

    /**
     * 认证、登录
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1、把AuthenticationToken强转成UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        //2、从UsernamePasswordToken中获取username
        String username = upToken.getUsername();

        //3、调用数据库方法，从数据库中查询username对应的用户记录
        User user = userService.findByUsername(username);

        if(user == null){
            return null;
        }


        //4、根据用户情况，来构建AuthenticationInfo对象并返回
        //以下信息是从数据库中获取的
        //1） principal 认证的实体信息  可以是username 也可以是数据表对应的用户的实体类对象
        Object principal = user;

        //2） credentials 密码
        Object credentials = user.getPassword();

        //3） realmName 当前realm对象的name 调用父类的getName()方法即可
        String realmName = getName();

        //4）盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,
                credentials,credentialsSalt,realmName);

        return info;
    }


    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //从PrincipalCollection中获取登录用户的信息
        User user = (User) principals.getPrimaryPrincipal();

        //利用登录的用户信息来确定当前用户的角色和权限
        //从数据库中获取到用户的权限
        Set<String> roles = new HashSet<>();
        roles.addAll(Arrays.asList(user.getRoles().trim().split(",")));

        //创建SimpleAuthorizationInfo 并设置其roles属性
        SimpleAuthorizationInfo info =new SimpleAuthorizationInfo(roles);


        Set<String> perms = new HashSet<String>();
        perms.addAll(Arrays.asList(user.getPermissions().trim().split(",")));
        info.addStringPermissions(perms);

        //返回SimpleAuthorizationInfo对象
        return info;
    }

}
