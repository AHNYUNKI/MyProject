package com.api.TopicTraverse.domain;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTime {

    @CreationTimestamp
    @Column(insertable = false)
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(updatable = false)
    private LocalDateTime updateTime;

}
