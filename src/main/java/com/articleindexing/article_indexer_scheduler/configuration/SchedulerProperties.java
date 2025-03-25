package com.articleindexing.article_indexer_scheduler.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix = "scheduler")
@Validated
public final class SchedulerProperties {

    /**
     * Default fixed rate in milliseconds for the scheduled task.
     */
    public static final long DEFAULT_FIXED_RATE = 9000;

    /**
     * Fixed rate in milliseconds for the scheduled task.
     * This value is configurable via application.properties.
     */
    private long fixedRate = DEFAULT_FIXED_RATE;

    /**
     * Retrieves the fixed rate for the scheduled task.
     *
     * @return the fixed rate in milliseconds.
     */
    public long getFixedRate() {
        return fixedRate;
    }

    /**
     * Sets the fixed rate for the scheduled task.
     * This value should be positive to avoid misconfigurations.
     *
     * @param rate the fixed rate in milliseconds.
     */
    public void setFixedRate(final long rate) {
        this.fixedRate = rate;
    }
}

