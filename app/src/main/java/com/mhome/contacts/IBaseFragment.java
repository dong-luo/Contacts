package com.mhome.contacts;

import android.view.View;

/**
 * Title : Fragment接口
 * Description :
 * Author : xiansenxuan   Date : 2016/11/17 14:26
 * Updater :
 * Version : 1.0.0
 * Copyright : Copyright(c) 浙江蘑菇加电子商务有限公司 2015 ~ 2016 版权所有
 */
public interface IBaseFragment {

    /**
     * 绑定渲染视图的布局文件
     *
     * @return 布局文件资源id
     */
    int bindLayout();

    /**
     * 不需要刷新的操作 （控件初始化 设置适配器 等操作）
     */
    void initView(final View view);

    /**
     * 暂停恢复刷新相关操作（onResume方法中调用）
     */
    void resume();

    /**
     * 暂停恢复刷新相关操作（onResume方法中调用）
     */
    void pause();

    /**
     * 销毁、释放资源相关操作（onDestroy方法中调用）
     */
    void destroy();


    /**
     * 需要刷新界面数据的操作 （网络请求 标题栏刷新 等操作）
     */
    void doBusiness();

    /**
     * @return 是否执行doBusiness方法
     */
    boolean isActionBusiness();

    /**
     * 是否需要订阅事件
     */
    boolean isNeedEventBus();
}
