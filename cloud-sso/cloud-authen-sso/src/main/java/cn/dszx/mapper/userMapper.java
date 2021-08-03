package cn.dszx.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface userMapper{
    @Select("select * from sys_user where username=#{username}")
    Map<String,Object> getUser(@Param("username")String name);
    @Select("SELECT me.`permission` FROM  sys_user u \n" +
            "LEFT JOIN sys_user_role sro ON u.`id` = sro.`user_id`\n" +
            "LEFT JOIN sys_role_menu rome ON rome.`role_id` = sro.`role_id`\n" +
            "LEFT JOIN sys_menu me ON rome.`menu_id` = me.`id`\n" +
            "WHERE u.id = #{id}")
    List<String> getAuthen(@Param("id")Long id);
}
