package ru.job4j;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * UserStoreTest class.
 *
 * @author Denis
 * @since 26.02.2017
 */
public class UserStoreTest {
    /**
     * Add.
     */
    @Test
    public void whenSuccessfullyAddedUserInCollectionThenReturnTrue() {
        Store<User> store = new UserStore(10);
        User user = new User("Denis", "1");

        boolean actual = store.add(user);

        assertThat(actual, is(true));
    }

    /**
     * Remove.
     */
    @Test
    public void whenSuccessfullyRemoveUserFromCollectionThenReturnTrue() {
        Store<User> store = new UserStore(10);
        User user = new User("Denis1", "1");
        User user2 = new User("Denis2", "2");

        store.add(user);
        store.add(user2);
        boolean actual = store.remove("2");

        assertThat(actual, is(true));
    }

    /**
     * Update.
     */
    @Test
    public void whenSuccessfullyUpdateUserInCollectionThenReturnOldUser() {
        Store<User> store = new UserStore(10);
        User user = new User("Denis1", "1");
        User user2 = new User("Denis2", "2");

        store.add(user);
        Base actual = store.update(user2, "1");

        assertThat(actual.toString(), is("Denis1 (id=1)"));
    }
}