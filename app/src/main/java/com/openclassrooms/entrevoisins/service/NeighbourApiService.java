package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Get all my favorite Neighbours
     * @return {@link List}
     */
    List<Neighbour> getFavoriteNeighbours();

    /**
     * Get specific neighbour using his id
     * @param neighbourId
     * @return {@link Neighbour}
     */
    Neighbour getNeighbourById(long neighbourId);

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);
}
