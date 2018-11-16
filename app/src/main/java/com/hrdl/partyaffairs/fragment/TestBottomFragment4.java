package com.hrdl.partyaffairs.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.hrdl.partyaffairs.R;
import com.hrdl.partyaffairs.base.BaseLazyFragment;

/**
 * 作者：王健 on 2018/9/30
 * 邮箱：845040970@qq.com
 */
public class TestBottomFragment4 extends BaseLazyFragment {
    @BindView(R.id.users_photo_id)
    RoundedImageView usersPhotoId;
    @BindView(R.id.score_text_id)
    TextView scoreTextId;
    @BindView(R.id.ranking_text_id)
    TextView rankingTextId;
    @BindView(R.id.age_text_id)
    TextView ageTextId;
    @BindView(R.id.notify_image)
    ImageView notifyImage;
    @BindView(R.id.notify_id)
    LinearLayout notifyId;
    @BindView(R.id.hours_image)
    ImageView hoursImage;
    @BindView(R.id.hours_id)
    LinearLayout hoursId;
    @BindView(R.id.study_image)
    ImageView studyImage;
    @BindView(R.id.study_id)
    LinearLayout studyId;
    @BindView(R.id.achievement_image)
    ImageView achievementImage;
    @BindView(R.id.achievement_id)
    LinearLayout achievementId;
    @BindView(R.id.mail_image)
    ImageView mailImage;
    @BindView(R.id.mail_id)
    LinearLayout mailId;
    @BindView(R.id.party_image)
    ImageView partyImage;
    @BindView(R.id.party_id)
    LinearLayout partyId;
    Unbinder unbinder;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_testbottom4;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        isLazyLoad();
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
