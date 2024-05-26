package com.jpa_n_plus_one.test.domain_lazy;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    private String projectName;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Project(String projectName, Department department) {
        this.projectName = projectName;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectName='" + projectName + '\'' +
                ", projectId=" + projectId +
                '}';
    }
}
