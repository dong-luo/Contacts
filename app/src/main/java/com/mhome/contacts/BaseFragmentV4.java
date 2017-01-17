package com.mhome.contacts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Title : Fragment基类(V4包)
 * Description :
 * Author : xiansenxuan   Date : 2016/11/17 14:26
 * Updater :
 * Version : 1.0.0
 * Copyright : Copyright(c) 浙江蘑菇加电子商务有限公司 2015 ~ 2016 版权所有
 */
public abstract class BaseFragmentV4 extends Fragment implements IBaseFragment {

    /**
     * fragment视图是否显示
     */
    protected boolean isVisible      = false;
    /**
     * 是否初始化完成，即onCreateView过，才可以doBusiness，否则会nullpoint
     */
    protected boolean isPrepared     = false;
    /**
     * 当前Fragment渲染的视图View
     **/
    private View mContextView   = null;
    private   boolean actionBusiness = false;
    private   boolean isNeedEventBus = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // 渲染视图View(防止切换时重绘View)
//        if (null != mContextView) {
//            ViewGroup parent = (ViewGroup) mContextView.getParent();
//            if (null != parent) {
//                parent.removeView(mContextView);
//            }
//        } else {
//            MyLogger.xuanLog().i("onCreateView    " + this.getClass().getSimpleName());
//
//            mContextView = inflater.inflate(bindLayout(), container, false);
//            // 控件初始化
//            ButterKnife.bind(this, mContextView);
//            initView(mContextView);
//        }

        if (null == mContextView) {
            mContextView = inflater.inflate(bindLayout(), container, false);
            // 控件初始化
            initView(mContextView);
        }
//        else {
//            // 控件初始化
//            ButterKnife.bind(this, mContextView);
//        }


        isPrepared = true;

        if (isActionBusiness() && isPrepared) {
            doBusiness();
        }
        if (isActionBusiness() && !isPrepared) {
            setActionBusiness(true);
        }

        //取消事件注册
        if (isNeedEventBus) {
        }

        return mContextView;
    }

    /**
     * 在onCreateView之前调用
     * 在这里实现Fragment数据的缓加载
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    public void setActionBusiness(boolean actionBusiness) {
        this.actionBusiness = actionBusiness;
    }

    protected void onVisible() {
        // 业务处理
        if (isPrepared && isVisible) {
            doBusiness();
        }
    }

    protected void onInvisible() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        resume();


        super.onResume();
    }

    @Override
    public void onPause() {
        pause();

        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        destroy();
        //取消事件注册
        if (isNeedEventBus) {
        }

        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解绑ButterKnife
    }

}
