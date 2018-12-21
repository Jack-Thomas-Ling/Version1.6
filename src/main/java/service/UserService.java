package service;

import model.RecentUser;
import model.User;
import model.UserDetailed;

import java.sql.Date;
import java.util.List;

public interface UserService {

    //检查是否登录成功
    boolean checkLogin(String username, String password);
    //返回总的用户数
    int returnSumUser();
    //根据用户名查找用户，返回的是一个用户对象
    User findUserByName(String username);
    //根据用户名，锁定该用户
    void lockUser(String username);
    //根据用户名判断用户是否被锁定，返回一个bool值
    boolean isLocked(String username);
    //根据用户名，为该用户添加积分，积分是int型的
    void addCredit(String username, int creditNum);
    //根据用户名 设置登录的日期和地址？
    void setLoginLogAndDate(String username, Date date, String addr);
    //用户注册
    void register(String username, String password);
    //根据用户的id查找用户，返回一个User对象
    User selectByUserId(int id);
    //根据用户的id，更新用户的头像地址

    void updateAddressOfPic(int userId,String address);
    //根据用户的id，返回一个用户信息对象
    UserDetailed findById(int userId);
    //返回最新注册的用户的一个list
    List<RecentUser> RecentUserData();
//获取所有的用户
    List<User> getAllUsers();

    //根据用户id获取用户详细信息；返回一个UserDetailed对象
    UserDetailed getDetailById(int id);

}
