package com.rafernan.app.task;

import java.util.Date;

import com.rafernan.app.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public class Task {
  @Id
  @GeneratedValue
  private Integer id;

  private String title;

  private String description;

  @Temporal(TemporalType.DATE)
  private Date createdAt;

  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name="user_id", referencedColumnName="id")
  private User user;
}
