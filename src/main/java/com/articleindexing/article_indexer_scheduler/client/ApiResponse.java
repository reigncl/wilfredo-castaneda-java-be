package com.articleindexing.article_indexer_scheduler.client;

import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
@Setter
@NoArgsConstructor(force = true)
public class ApiResponse {

    /**
     * The list of hits retrieved from the external API.
     */
    private final List<Hit> hits;

    /**
     * Constructs an instance of ApiResponse with the provided hits.
     * Creates a defensive copy of the list to prevent external modification.
     *
     * @param hitList the list of hits. Cannot be null.
     */
    public ApiResponse(final List<Hit> hitList) {
        if (hitList == null) {
            throw new IllegalArgumentException("Hits cannot be null");
        }
        // Defensive copy to protect internal state
        this.hits = new ArrayList<>(hitList);
    }

    /**
     * Retrieves a copy of the list of hits.
     * This method returns a defensive copy
     * of the internal list to prevent
     * external modification of the original data.
     *
     * @return a new list containing the hits.
     */
    public List<Hit> getHits() {
        assert hits != null;
        return new ArrayList<>(hits);
    }
}
