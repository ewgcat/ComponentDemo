package com.yijian.staff.mvp.vip.edit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.yijian.staff.R;
import com.yijian.staff.mvp.vip.bean.VipDetailBean;
import com.yijian.staff.widget.NavigationBar2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 会员信息编辑页
 */
public class VipInfoEditActivity extends AppCompatActivity {

    @BindView(R.id.tv_source)
    TextView tv_source;
    @BindView(R.id.et_fitnessGoal)
    EditText et_fitnessGoal;
    @BindView(R.id.tv_onceJoinedClub)
    TextView tv_onceJoinedClub;
    @BindView(R.id.et_clubBrand)
    EditText et_clubBrand;
    @BindView(R.id.tv_yearIncome)
    TextView tv_yearIncome;
    @BindView(R.id.et_carPrice)
    EditText et_carPrice;
    @BindView(R.id.et_hobby)
    EditText et_hobby;
    @BindView(R.id.et_nationality)
    EditText et_nationality;
    @BindView(R.id.et_nation)
    EditText et_nation;
    @BindView(R.id.et_occupation)
    EditText et_occupation;
    @BindView(R.id.tv_marriageStatus)
    TextView tv_marriageStatus;
    @BindView(R.id.tv_hasChildren)
    TextView tv_hasChildren;
    @BindView(R.id.et_address)
    EditText et_address;

    VipDetailBean.DetailBean detailBean;
    String resource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_info_edit);
        ButterKnife.bind(this);
        initTitle();
        initData();

    }

    private void initData() {
        detailBean = (VipDetailBean.DetailBean) getIntent().getSerializableExtra("detail");
        resource = getIntent().getStringExtra("source");
        updateUi();
    }

    private void initTitle() {
        NavigationBar2 navigationBar2 = findViewById(R.id.vip_edit_navigation_bar);
        navigationBar2.setTitle("张三详细信息");
        navigationBar2.hideLeftSecondIv();
        TextView rightTv = navigationBar2.getmRightTv();
        rightTv.setText("保存");
        navigationBar2.setBackClickListener(this);
    }

    @OnClick({R.id.right_tv,R.id.tv_source,R.id.tv_onceJoinedClub,R.id.tv_yearIncome})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_tv: //保存
                submitData();
                break;
            case R.id.tv_source: //用户渠道
                break;
            case R.id.tv_onceJoinedClub: // 是否参加过俱乐部
                break;
            case R.id.tv_yearIncome: // 年收入
                break;
        }
    }


    /**
     * 提交数据
     */
    private void submitData() {

    }

    /**
     * 更新数据
     */
    private void updateUi() {
        tv_source.setText(resource);
        et_fitnessGoal.setText(detailBean.getFitnessGoal());
        tv_onceJoinedClub.setText((detailBean.isOnceJoinedClub()) ? "是" : "否");
        et_clubBrand.setText(detailBean.getClubBrand());
        tv_yearIncome.setText(detailBean.getYearIncome());
        et_carPrice.setText(detailBean.getCarPrice());
        et_hobby.setText(detailBean.getHobby());
        et_nationality.setText(detailBean.getNationality());
        et_nation.setText(detailBean.getNation());
        et_occupation.setText(detailBean.getOccupation());
        tv_marriageStatus.setText(detailBean.getMarriageStatus());
        tv_hasChildren.setText(detailBean.getChildrenStatus());
        et_address.setText(detailBean.getAddress());

    }

    /**
     * 选项弹出框
     *
     * @param opts
     * @param defaultValue
     * @param name
     */
    private void manualPickedView(List<String> opts, String defaultValue, TextView name) {
        OptionsPickerView pvNoLinkOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                name.setText(opts.get(options1));
            }
        }).build();

        pvNoLinkOptions.setNPicker(opts, null, null);
        pvNoLinkOptions.setSelectOptions(opts.indexOf(defaultValue));
        pvNoLinkOptions.show();
    }


}
