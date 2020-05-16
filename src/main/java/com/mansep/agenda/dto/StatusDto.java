package com.mansep.agenda.dto;

import java.io.Serializable;

import com.mansep.agenda.entity.enums.Status;

public class StatusDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Status status;

    @Override
    public String toString() {
        return "StatusDto [status=" + status + "]";
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}