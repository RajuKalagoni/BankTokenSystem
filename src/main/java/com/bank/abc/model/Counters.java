package com.bank.abc.model;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Objects;

@Component
public class Counters<Token> extends LinkedList<Token> {
    String counterId;
    String operatorName;

    public Counters() {
    }

    public Counters(String counterId, String operatorName) {
        this.counterId = counterId;
        this.operatorName = operatorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Counters that = (Counters) o;
        return counterId.equals(that.counterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), counterId);
    }

    public String getCounterId() {
        return counterId;
    }

    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

}
