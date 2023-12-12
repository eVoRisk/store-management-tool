package com.store.management.tool.service;

import com.store.management.tool.dto.UserDto;
import com.store.management.tool.model.User;

import java.util.List;

public interface UserService {

    UserDto add(UserDto userDto);

    User getById(Integer id);

    List<User> getAll();

    void update(UserDto userDto, Integer id);

    void deleteById(Integer id);
}
