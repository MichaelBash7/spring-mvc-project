package ru.itsjava.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domain.User;
import ru.itsjava.repository.PetRepository;
import ru.itsjava.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PetRepository petRepository;

    @Transactional(readOnly = true)
    @Override
    public void printAllUsers() {
        System.out.println("Все пользователи: ");
        List<User> users = userRepository.findAll();
        System.out.println(users.toString());
    }


    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
//        petRepository.deletePetByUser(user);
        userRepository.delete(user);

    }
}
