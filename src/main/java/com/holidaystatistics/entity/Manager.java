package com.holidaystatistics.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author nitmali@126.com
 * @date 2018/5/7 20:37
 */

@Entity
public class Manager {
    @Id
    @Column(nullable = false)
    private String managerId;

    @Column(nullable = false)
    private String managerName;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    private ProfessionalClass professionalClass;

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ProfessionalClass getProfessionalClass() {
        return professionalClass;
    }

    public void setProfessionalClass(ProfessionalClass professionalClass) {
        this.professionalClass = professionalClass;
    }
}
