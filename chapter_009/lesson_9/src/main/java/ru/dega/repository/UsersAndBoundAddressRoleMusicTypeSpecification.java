package ru.dega.repository;

/**
 * UsersAndAllBoundObjectsSpecification class.
 *
 * @author Denis
 * @since 04.09.2017
 */
public class UsersAndBoundAddressRoleMusicTypeSpecification implements Specification {
    @Override
    public String toSqlQuery() {
        return "SELECT u.id AS userid, u.login AS userlogin, "
                + "r.id AS roleid, r.name AS rolename, "
                + "coalesce(adr.id, 0) AS adrid, "
                + "coalesce(adr.REPRESENTATION, '') AS adrrepr, "
                + "array_remove(array_agg(mt.id), NULL) AS musicid, "
                + "array_remove(array_agg(mt.name), NULL) AS music "
                + "FROM USERS AS u "
                + "LEFT JOIN ROLES AS r ON u.role_id=r.id "
                + "LEFT JOIN ADDRESS AS adr ON u.id=adr.USER_ID "
                + "LEFT JOIN MUSICTYPE_USERS mu ON u.id = mu.USER_ID "
                + "LEFT JOIN MUSIC_TYPE mt ON mu.MUSICTYPE_ID = mt.id "
                + "GROUP BY u.id, u.login, r.id, r.name, adr.id, adr.REPRESENTATION";
    }
}