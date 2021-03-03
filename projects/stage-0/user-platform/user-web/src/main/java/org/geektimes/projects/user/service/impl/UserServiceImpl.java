package org.geektimes.projects.user.service.impl;

import org.geektimes.infrastructure.SpiContainer;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.UserRepository;
import org.geektimes.projects.user.service.UserService;

/**
 *
 *
 * @author YuI
 * @date 2021/3/4 1:07 
 * @since v1-SNAPSHOT
 **/
public class UserServiceImpl implements UserService {

    @Override
    public boolean register(User user) {
        UserRepository repository = SpiContainer.getDefaultObject(UserRepository.class);

        if (repository.save(user)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean deregister(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User queryUserById(Long id) {
        return null;
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
        return null;
    }
}
