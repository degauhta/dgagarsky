package ru.job4j;

import java.util.Calendar;

/**
 * UserHashCode class.
 *
 * @author Denis
 * @since 07.03.2017
 */
public class UserHashCode extends User {
    /**
     * Main constructor.
     *
     * @param name     name
     * @param children children
     * @param birthday birthday
     */
    public UserHashCode(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    /**
     * Hashcode.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getChildren();
        result = 31 * result + (getBirthday() != null ? getBirthday().hashCode() : 0);
        return result;
    }
}
