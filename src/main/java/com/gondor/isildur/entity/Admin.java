package com.gondor.isildur.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.gondor.isildur.DTO.AdminDTO;
import com.gondor.isildur.util.DTOProperty;
import com.gondor.isildur.util.ToDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "admin")
@ToDTO(DTOClass = AdminDTO.class)
public class Admin extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long adminId;

    @DTOProperty(ignore = true)
    private String adminPassword;

    private String adminName;
    private Integer adminRole;
    private java.sql.Timestamp createTime;
    private java.sql.Timestamp updateTime;

    public void setAdminName(String adminName) throws IllegalArgumentException, JSONException {
        if (adminName != null && !adminName.equals("")) {
            this.adminName = adminName;
        }
    }

    public void setAdminPassword(String adminPassword) {
        if (adminPassword != null && !adminPassword.equals("")) {
            this.adminPassword = adminPassword;
        }
    }


    public void setAdminRole(Integer adminRole) {
        if (adminRole != null) {
            if (adminRole == 1 || adminRole == 2) {
                this.adminRole = adminRole;
            }
        }
    }

    public static void main(String[] args) {
        Admin admin = new Admin();
        admin.setAdminId(1);
        admin.setAdminName("123");
        admin.setAdminPassword("admin");
        admin.setAdminRole(1);
        admin.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
        admin.setUpdateTime(new java.sql.Timestamp(System.currentTimeMillis()));
        AdminDTO adminDTO = admin.toDTO();
        System.out.println(JSON.toJSONString(adminDTO));

    }

}
