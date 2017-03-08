package ru.job4j;

import java.util.Calendar;

/**
 * User class.
 *
 * @author Denis
 * @since 07.03.2017
 */
public class User {
    /**
     * Name.
     */
    private String name;

    /**
     * Number of children.
     */
    private int children;

    /**
     * Birthday.
     */
    private Calendar birthday;

    /**
     * MAin constructor.
     *
     * @param name     name
     * @param children children
     * @param birthday birthday
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    /**
     * Get name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get children.
     *
     * @return children
     */
    public int getChildren() {
        return children;
    }

    /**
     * Get birthday.
     *
     * @return birthday
     */
    public Calendar getBirthday() {
        return birthday;
    }
}
