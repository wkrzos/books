package com.example.books.service.implementation;

import com.example.books.dto.UserDTO;
import com.example.books.entity.User;
import com.example.books.exception.UserNotFound;
import com.example.books.mapper.UserMapper;
import com.example.books.repository.UserRepository;
import com.example.books.service.contract.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO create(UserDTO dto) {
        User user = userMapper.toEntity(dto);

        user = userRepository.save(user);

        log.info("Created user: {}", user);

        return userMapper.toDTO(user);
    }

    @Override
    public void delete(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound(id));

        userRepository.delete(user);

        log.info("Deleted user: {}", user);
    }

    @Override
    public UserDTO update(Integer id, UserDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound(id));

        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setEmail(dto.email());
        user.setName(dto.name());
        user.setSurname(dto.surname());

        userRepository.save(user);

        log.info("Updated user: {}", user);

        return userMapper.toDTO(user);
    }

    @Override
    public UserDTO getById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound(id));

        log.info("Retrieved user by id: {}", user);

        return userMapper.toDTO(user);
    }

    public UserDTO getByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFound(username));

        log.info("Retrieved user by username: {}", user);

        return userMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();

        log.info("Retrieved all users: {}", users);

        return users.stream()
                .map(userMapper::toDTO)
                .toList();
    }
}
