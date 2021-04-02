package com.example.demo.service;

import com.example.demo.model.Project;
import com.example.demo.repository.ProjectRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private ProjectRepository repository;

    @Override
    public List<Project> findAll() {

        List<Project> projects = (List<Project>) repository.findAll();

        return projects;
    }
}