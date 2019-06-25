package com.bank.abc.model;

import com.bank.abc.util.PriorityType;
import com.bank.abc.util.Status;

import java.util.Objects;

public class Token {
    private Long id;
    private PriorityType priority;
    private Status status;
    private String counterAssigned;
    private String branchId;
    private String actionItems;
    private Long custId;
    private String comments;
    private String operator;

    public Token() {
    }

    public Token(Long id, PriorityType priority, Status status, Long custId) {
        this.id = id;
        this.priority = priority;
        this.status = status;
        this.custId = custId;
    }

    public PriorityType getPriority() {
        return priority;
    }

    public void setPriority(PriorityType priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCounterAssigned() {
        return counterAssigned;
    }

    public void setCounterAssigned(String counterAssigned) {
        this.counterAssigned = counterAssigned;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getActionItems() {
        return actionItems;
    }

    public void setActionItems(String actionItems) {
        this.actionItems = actionItems;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return Objects.equals(id, token.id) &&
                Objects.equals(counterAssigned, token.counterAssigned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, priority, status, counterAssigned, branchId, actionItems, custId, comments, operator);
    }
}
