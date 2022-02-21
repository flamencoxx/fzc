package com.example.testjpa.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author flamenco.xxx
 * @date 2022/1/30 9:38
 */
@Entity
@Table(name = "jpatest")
public class JpaTest {

    private Long id;

    private Date instructed;

    private String name;

    private int age;

    private long treeid;


    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "instructed")
    public Date getInstructed() {
        return instructed;
    }

    public void setInstructed(Date instructed) {
        this.instructed = instructed;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Basic
    @Column(name = "treeid")
    public long getTreeid() {
        return treeid;
    }

    public void setTreeid(long treeid) {
        this.treeid = treeid;
    }


    @Override
    public String toString() {
        return "JpaTest{" +
                "id=" + id +
                ", instructed=" + instructed +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", treeid=" + treeid +
                '}';
    }
}
