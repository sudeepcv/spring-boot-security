package com.sudeepcv.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserInfo {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String password;
    private String roles;


}
