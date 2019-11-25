package com.elegion.test.behancer.ui.projects;

import com.elegion.data.model.project.Project;
import com.elegion.test.behancer.common.BaseView;

import java.util.List;

public interface ProjectsView extends BaseView {

    void showProjects(List<Project> projects);

    void showProfileFragment(String username);
}
