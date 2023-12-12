package com.store.management.tool.service;

import com.store.management.tool.dto.UserDto;
import com.store.management.tool.exception.NotFoundException;
import com.store.management.tool.model.User;
import com.store.management.tool.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto add(UserDto userDto) {
        userRepository.save(modelMapper.map(userDto, User.class));

        return userDto;
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(format("User with id: %s not found", id)));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void update(UserDto userDto, Integer id) {
        Optional<com.store.management.tool.model.User> user = userRepository.findById(id);
        user.ifPresent(value -> modelMapper.map(userDto, value));

        userRepository.save(user
                .orElseThrow(() -> new NotFoundException(format("User with id: %s not found", id))));
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
