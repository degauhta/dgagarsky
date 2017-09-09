package ru.dega.dao;

import ru.dega.models.MusicType;

import java.util.List;

/**
 * MusicTypeDAO class.
 *
 * @author Denis
 * @since 31.08.2017
 */
public interface MusicTypeDAO {
    /**
     * Get all music types.
     *
     * @return list of music types
     */
    List<MusicType> getAll();

    /**
     * Create new music type.
     *
     * @param musicType musicType obj
     * @return id of new element
     */
    int createMusicType(MusicType musicType);

    /**
     * Get music type by name.
     *
     * @param name name
     * @return music type obj
     */
    MusicType getByName(String name);

    /**
     * Update music type.
     *
     * @param musicType new music type
     * @return true if update successfully
     */
    boolean update(MusicType musicType);

    /**
     * Delete music type.
     *
     * @param name music type name
     * @return true if delete successfully
     */
    boolean delete(String name);
}