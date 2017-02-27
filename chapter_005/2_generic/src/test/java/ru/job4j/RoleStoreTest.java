package ru.job4j;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * RoleStoreTest class.
 *
 * @author Denis
 * @since 26.02.2017
 */
public class RoleStoreTest {
    /**
     * Add.
     */
    @Test
    public void whenSuccessfullyAddedRoleInCollectionThenReturnTrue() {
        Store<Role> store = new RoleStore(10);
        Role role = new Role("Admin", "1");

        boolean actual = store.add(role);

        assertThat(actual, is(true));
    }

    /**
     * Remove.
     */
    @Test
    public void whenSuccessfullyRemoveRoleFromCollectionThenReturnTrue() {
        Store<Role> store = new RoleStore(10);
        Role role = new Role("Admin", "1");
        Role role1 = new Role("Guest", "2");

        store.add(role);
        store.add(role1);
        boolean actual = store.remove("2");

        assertThat(actual, is(true));
    }

    /**
     * Update.
     */
    @Test
    public void whenSuccessfullyUpdateRoleInCollectionThenReturnOldUser() {
        Store<Role> store = new RoleStore(10);
        Role role = new Role("Admin", "1");
        Role role1 = new Role("Guest", "2");

        store.add(role);
        Base actual = store.update(role1, "1");

        assertThat(actual.toString(), is("Admin (id=1)"));
    }
}