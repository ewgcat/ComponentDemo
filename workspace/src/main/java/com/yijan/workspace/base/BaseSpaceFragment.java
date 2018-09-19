package com.yijan.workspace.base;

import com.yijan.commonlib.mvp.base.mvc.MvcBaseFragment;
import com.yijan.workspace.observe.Observer;


public class BaseSpaceFragment extends MvcBaseFragment implements Observer {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
    }

    @Override
    public void update(Object data) {

    }
}
