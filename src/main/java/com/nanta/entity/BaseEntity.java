package com.nanta.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {
  public static final String COLUMN_ID = "ID";
  public static final String COLUMN_MARK_FOR_DELETE = "MARK_FOR_DELETE";
  public static final String COLUMN_VERSION = "OPTLOCK";
  public static final String COLUMN_CREATED_BY = "CREATED_BY";
  public static final String COLUMN_CREATED_DATE = "CREATED_DATE";
  public static final String COLUMN_UPDATED_BY = "UPDATED_BY";
  public static final String COLUMN_UPDATED_DATE = "UPDATED_DATE";

  @Id
  @Column(name = BaseEntity.COLUMN_ID)
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  @org.springframework.data.annotation.Id
  private String id;

  @Column(name = BaseEntity.COLUMN_MARK_FOR_DELETE)
  private boolean markForDelete = false;

  @Version
  @Column(name = BaseEntity.COLUMN_VERSION)
  private Long version = 0L;

  @CreatedBy
  @Column(name = BaseEntity.COLUMN_CREATED_BY, nullable = false)
  private String createdBy;

  @CreatedDate
  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(name = BaseEntity.COLUMN_CREATED_DATE, nullable = false)
  private Date createdDate;

  @LastModifiedBy
  @Column(name = BaseEntity.COLUMN_UPDATED_BY, nullable = false)
  private String updatedBy;

  @LastModifiedDate
  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(name = BaseEntity.COLUMN_UPDATED_DATE, nullable = false)
  private Date updatedDate;

}
