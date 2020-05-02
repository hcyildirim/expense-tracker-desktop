package models;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;

public class Transaction {
    private String id;

    private String description;

    private Type type;

    private BigDecimal amount;

    private Timestamp createdAt;

    private String userId;

    public static enum Type {
        INCOME, OUTCOME
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        if(getType() == Type.INCOME) {
            return amount.multiply(BigDecimal.valueOf(-1));
        }

        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String toWriteable() {
        return String.format("%s,%s,%s,%.2f,%d,%s", this.id, this.description, this.type.toString(), this.amount, this.createdAt.getTime(), this.userId);
    }
}
