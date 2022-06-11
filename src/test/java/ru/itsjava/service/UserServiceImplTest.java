package ru.itsjava.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.NoSuchElementException;


@DataJpaTest
@Import(UserServiceImpl.class)
public class UserServiceImplTest {
    public static final Pet DEFAULT_PET = new Pet(1L, "Lizard");
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserService userService;

    @Test
    public void shouldHaveCorrectGetAllUsers(){
        var expectedUsers = entityManager
                .createQuery("select distinct u from User u join fetch u.pet", User.class)
                .getResultList();
        var actualUsers = userService.getAllUsers();

        Assertions.assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void shouldHaveCorrectPrintAllUsers(){
        var expectedUsers = entityManager
                .createQuery("select distinct u from User u join fetch u.pet", User.class)
                .getResultList().toString();
        var actualUsers = userService.getAllUsers().toString();

        Assertions.assertEquals(expectedUsers, actualUsers);
    }
    @Test
    public void shouldHaveCorrectCreateUser(){
        var kirill = new User(5L, "Petr", 55, DEFAULT_PET);
        userService.createUser(kirill);
        var actualUser = userService.getUserById(5L);

        Assertions.assertEquals(kirill, actualUser);
    }

    @Test
    public void shouldHaveCorrectUpdateUser(){
        var user = userService.getUserById(1L);
        user.setName("Kirill");
        userService.updateUser(user);
        var actualUser = userService.getUserById(1L);

        Assertions.assertEquals("Kirill", actualUser.getName());
    }
    @Test
    public void shouldHaveCorrectDeleteUser(){
        NoSuchElementException thrownException = Assertions.assertThrows(NoSuchElementException.class, ()-> {
            var oleg = new User(6L, "Oleg", 13, DEFAULT_PET);
            userService.deleteUser(oleg);
            var deletedUser = userService.getUserById(6L);
            Assertions.assertNull(deletedUser);
        });

        Assertions.assertEquals("No value present", thrownException.getMessage());
    }
}

