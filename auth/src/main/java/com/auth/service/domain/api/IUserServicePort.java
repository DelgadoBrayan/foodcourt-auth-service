package com.auth.service.domain.api;


import com.auth.service.domain.model.user.User;
public interface IUserServicePort {
    
    User saveUser(User user);
    User findUserByEmail(String email);
    User findUserById(Long userId);
}
