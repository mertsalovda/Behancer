package com.elegion.test.behancer.ui.projects;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elegion.domain.model.project.Project;
import com.elegion.test.behancer.AppDelegate;
import com.elegion.test.behancer.R;
import com.elegion.test.behancer.common.PresenterFragment;
import com.elegion.test.behancer.ui.profile.ProfileActivity;
import com.elegion.test.behancer.ui.profile.ProfileFragment;
import com.elegion.test.behancer.common.RefreshOwner;
import com.elegion.test.behancer.common.Refreshable;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Vladislav Falzan.
 */

public class ProjectsFragment extends PresenterFragment<ProjectsPresenter>
        implements ProjectsView, Refreshable, ProjectsAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private RefreshOwner mRefreshOwner;
    private View mErrorView;
    @Inject
    ProjectsAdapter mProjectsAdapter;
    @Inject
    ProjectsPresenter mPresenter;

    public static ProjectsFragment newInstance() {
        return new ProjectsFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RefreshOwner) {
            mRefreshOwner = ((RefreshOwner) context);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppDelegate.getAppComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_projects, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = view.findViewById(R.id.recycler);
        mErrorView = view.findViewById(R.id.errorView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            getActivity().setTitle(R.string.projects);
        }

        mPresenter.setView(this);
        mProjectsAdapter.setOnItemClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mProjectsAdapter);

        onRefreshData();
    }

    @Override
    public void onItemClick(String username) {
        mPresenter.openProfileFragment(username);
    }

    @Override
    public void onDetach() {
        mRefreshOwner = null;
        super.onDetach();
    }

    @Override
    public void onRefreshData() {
        mPresenter.getProjects();
    }

    @Override
    public ProjectsPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void showProjects(List<Project> projects) {
        mErrorView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mProjectsAdapter.addData(projects, true);
    }

    @Override
    public void showProfileFragment(String username) {
        Intent intent = new Intent(getActivity(), ProfileActivity.class);
        Bundle args = new Bundle();
        args.putString(ProfileFragment.PROFILE_KEY, username);
        intent.putExtra(ProfileActivity.USERNAME_KEY, args);
        startActivity(intent);
    }

    @Override
    public void showRefresh() {
        mRefreshOwner.setRefreshState(true);
    }

    @Override
    public void hideRefresh() {
        mRefreshOwner.setRefreshState(false);
    }

    @Override
    public void showError() {
        mErrorView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

}