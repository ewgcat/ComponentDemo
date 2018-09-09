package com.yijian.staff.mvp.workspace.base;

import com.yijian.staff.mvp.base.mvc.MvcBaseFragment;
import com.yijian.staff.mvp.workspace.observe.Observer;

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
