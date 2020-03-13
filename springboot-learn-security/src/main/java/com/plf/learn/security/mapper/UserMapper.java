package com.plf.learn.security.mapper;

import com.plf.learn.security.entity.Permission;
import com.plf.learn.security.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Panlf
 * @date 2020/3/13
 */
@Mapper
public interface UserMapper {
    @Select("select * from sys_user where username = #{username}")
    public User findByUsername(String username);

    @Select("select permission.* " +
            "  from " +
            "  sys_user user" +
            "  inner join sys_user_role user_role on user.id=user_role.user_id" +
            "  inner join sys_role_permission role_permission on user_role.role_id=role_permission.role_id" +
            "  inner join sys_permission permission on role_permission.perm_id = permission.id" +
            "  where user.username=#{username}")
    public List<Permission> findPermissionByUsername(String username);
}
