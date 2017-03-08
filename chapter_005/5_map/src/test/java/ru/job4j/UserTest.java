package ru.job4j;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * UserTest class.
 *
 * @author Denis
 * @since 07.03.2017
 */
public class UserTest {
    /**
     * Test user.
     */
    @Test
    public void whenUserWithoutOverride() {
        Map<User, Object> map = new HashMap<>();
        User user = new User("Ivan", 1, new GregorianCalendar(1980, 5, 1));
        User anotherUser = new User("Ivan", 1, new GregorianCalendar(1980, 5, 1));
        map.put(user, 1);
        map.put(anotherUser, 1);
        System.out.println(map);
    }

    /**
     * Test user with override hashCode.
     */
    @Test
    public void whenUserWithOverrideHashCode() {
        Map<User, Object> map = new HashMap<>();
        User user = new UserHashCode("Sergei", 1, new GregorianCalendar(1980, 5, 1));
        User anotherUser = new UserHashCode("Sergei", 1, new GregorianCalendar(1980, 5, 1));
        map.put(user, 1);
        map.put(anotherUser, 2);
        System.out.println(map);
    }

    /**
     * Test user with override equals.
     */
    @Test
    public void whenUserWithOverrideEquals() {
        Map<User, Object> map = new HashMap<>();
        User user = new UserEquals("Egor", 1, new GregorianCalendar(1980, 5, 1));
        User anotherUser = new UserEquals("Egor", 1, new GregorianCalendar(1980, 5, 1));
        map.put(user, 1);
        map.put(anotherUser, 2);
        System.out.println(map);
        assertThat(user.equals(user), is(true));
        assertThat(user.equals(anotherUser), is(true));
        assertThat(user.equals("test"), is(false));
        assertThat(user.equals(null), is(false));
        assertThat(user.equals(new UserEquals("Andrei", 1,
                new GregorianCalendar(1980, 5, 1))), is(false));
        assertThat(user.equals(new UserEquals("Egor", 2,
                new GregorianCalendar(1980, 5, 1))), is(false));
        assertThat(user.equals(new UserEquals("Egor", 1,
                new GregorianCalendar(1, 1, 1))), is(false));
    }

    /**
     * Test user with override equals and hashCode.
     */
    @Test
    public void whenUserWithOverrideEqualsAndHashCode() {
        Map<User, Object> map = new HashMap<>();
        User user = new UserEqualsHashCode("Andrei", 1, new GregorianCalendar(1980, 5, 1));
        User anotherUser = new UserEqualsHashCode("Andrei", 1, new GregorianCalendar(1980, 5, 1));
        map.put(user, 1);
        map.put(anotherUser, 2);
        System.out.println(map);
        assertThat(user.equals(user), is(true));
        assertThat(user.equals(anotherUser), is(true));
        assertThat(user.equals("test"), is(false));
        assertThat(user.equals(null), is(false));
        assertThat(user.equals(new UserEqualsHashCode("Egor", 1,
                new GregorianCalendar(1980, 5, 1))), is(false));
        assertThat(user.equals(new UserEqualsHashCode("Andrei", 2,
                new GregorianCalendar(1980, 5, 1))), is(false));
        assertThat(user.equals(new UserEqualsHashCode("Andrei", 1,
                new GregorianCalendar(1, 1, 1))), is(false));
    }
}