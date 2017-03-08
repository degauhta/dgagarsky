package ru.job4j;

import java.util.Calendar;

/**
 * UserEquals class.
 *
 * @author Denis
 * @since 08.03.2017
 */
public class UserEquals extends User {
    /**
     * Main constructor.
     *
     * @param name     name
     * @param children children
     * @param birthday birthday
     */
    public UserEquals(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    /**
     * Equals.
     *
     * @param o the reference object with which to compare
     * @return @code true} if this object is the same as the obj
     * argument; {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        if (getChildren() != user.getChildren()) {
            return false;
        }
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) {
            return false;
        }
        return getBirthday() != null ? getBirthday().equals(user.getBirthday()) : user.getBirthday() == null;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
