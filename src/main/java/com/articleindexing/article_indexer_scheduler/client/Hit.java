package com.articleindexing.article_indexer_scheduler.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Represents a single search result or "hit" from the external API.
 */
@Data
public class Hit {

    /**
     * The unique title for the hit.
     */
    private String title;

    /**
     * The author identifier for the hit.
     */
    private String author;

    /**
     * The url for the hit.
     */
    private String url;

    /**
     * The unique identifier for the hit.
     */
    @JsonProperty("objectID")
    private String objectID;

    /**
     * The date in the hit.
     */
    @JsonProperty("created_at")
    private String createdAt;
}
