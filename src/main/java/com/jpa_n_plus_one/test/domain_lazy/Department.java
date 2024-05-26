package com.jpa_n_plus_one.test.domain_lazy;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;
    private String departmentName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    List<Employee> employees = new ArrayList<>();

    @org.hibernate.annotations.BatchSize(size = 5)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    List<Project> projects = new ArrayList<>();

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentName='" + departmentName + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }
}
