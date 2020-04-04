package com.mansep.agenda.dto.abstrct;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.mansep.agenda.entity.enums.Status;


@MappedSuperclass
public abstract class AbstractBaseDto {
    private long id;
    private Date createdAt;
    private Date updatedAt;
    private Status status = Status.ACTIVE;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}