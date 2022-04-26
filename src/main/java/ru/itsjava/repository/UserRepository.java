package ru.itsjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    void deleteAllByPet(Pet pet);
    Optional<User> findUserByNameAndPet(String name, Pet pet);
}
