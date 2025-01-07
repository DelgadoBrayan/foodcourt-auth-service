package com.auth.service.domain.spi;


import com.auth.service.domain.model.user.User;
public interface IUserPersistencePort {
    
    User saveUser(User user);
    User findUserByEmail(String email);
    User findUserById(Long userId);
}
