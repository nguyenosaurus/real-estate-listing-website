package com.example.demo.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import java.util.HashSet;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "project")
public class Project implements java.io.Serializable {

    private Integer project_id;
    private String project;
    private String project_size;
    private Set<Post> posts = new HashSet<Post>(0);

    public Project() {
    }

    public Project(Integer project_id, String project, String project_size) {

        this.project_id = project_id;
        this.project = project;
        this.project_size =project_size;
    }

    @Id
	@GeneratedValue(strategy = IDENTITY)
    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProject_size() {
        return project_size;
    }

    public void setProject_size(String project_size) {
        this.project_size = project_size;
    }

    @JsonBackReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	public Set<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
}