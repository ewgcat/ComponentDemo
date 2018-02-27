package com.example.commonlibrary.mvp.model;


import com.example.commonlibrary.data.DataManager;

public class BaseModel<R extends DataManager> implements IModel<R> {


    protected R baseRepositoryManager;


    public BaseModel(R repositoryManager) {
        this.baseRepositoryManager = repositoryManager;
    }

    @Override
    public void onDestroy() {
        baseRepositoryManager.clearAllCache();
    }

    @Override
    public R getDataManager() {
        return baseRepositoryManager;
    }
}
