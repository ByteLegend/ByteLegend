package com.bytelegend.app.serverapi.data;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@DynamoDbBean
public class Session extends Mvcc {
    // A token expires after 14 days
    // If token is older than 7 days, renew the token
    public static final Duration DEFAULT_SESSION_AGE = Duration.ofDays(14);
    public static final Duration DEFAULT_SESSION_RENEW = Duration.ofDays(7);
    public static final Session NULL = new Session();
    private String id;
    private String playerId;
    private Instant createdAt;
    private Map<String, String> data = new HashMap<>();

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public Long getTtl() {
        return getExpireTime().getEpochSecond();
    }

    public boolean isExpired() {
        return getExpireTime().compareTo(Instant.now()) < 0;
    }

    private Instant getExpireTime() {
        return createdAt.plus(DEFAULT_SESSION_AGE);
    }

    public boolean isRenewable() {
        return createdAt.plus(DEFAULT_SESSION_RENEW).compareTo(Instant.now()) < 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
