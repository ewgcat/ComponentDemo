package com.yijian.workspace.workspace.base;

import com.yijian.workspace.base.mvc.MvcBaseFragment;
import com.yijian.workspace.workspace.observe.Observer;

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
