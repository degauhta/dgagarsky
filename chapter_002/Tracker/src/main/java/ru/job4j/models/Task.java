package ru.job4j.models;

/**
 * Created by Denis on 18.12.2016.
 */
public class Task extends Item {
    /**
     * Class constructor.
     *
     * @param name        name bug.
     * @param description short description.
     * @param create      data of creation.
     */
    public Task(String name, String description, long create) {
        super(name, description, create);
    }
}
