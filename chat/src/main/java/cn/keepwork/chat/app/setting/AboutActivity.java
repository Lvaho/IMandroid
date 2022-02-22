/*
 * Copyright (c) 2020 WildFireChat. All rights reserved.
 */

package cn.keepwork.chat.app.setting;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.keepwork.chat.app.AppService;
import cn.wildfire.chat.kit.Config;
import cn.wildfire.chat.kit.WfcBaseActivity;
import cn.wildfire.chat.kit.WfcWebViewActivity;
import cn.wildfirechat.chat.R;

public class AboutActivity extends WfcBaseActivity {

    @BindView(R.id.infoTextView)
    TextView infoTextView;

    @Override
    protected int contentLayout() {
        return R.layout.activity_about;
    }

    @Override
    protected void afterViews() {
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), PackageManager.GET_CONFIGURATIONS);
            String info =packageInfo.versionCode + " " + packageInfo.versionName + "\n"
                + Config.IM_SERVER_HOST + "\n"
                + AppService.APP_SERVER_ADDRESS + "\n";

            for (String[] ice : Config.ICE_SERVERS) {
                info += ice[0] + " " + ice[1] + " " + ice[2] + "\n";
            }
            infoTextView.setText(info);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.introOptionItemView)
    public void intro() {
        WfcWebViewActivity.loadUrl(this, "之讯功能介绍", "http://web.keepwork.xyz:8090/functionzhiim");
    }

    @OnClick(R.id.agreementOptionItemView)
    public void agreement() {
        WfcWebViewActivity.loadUrl(this, "之讯相关", "http://web.keepwork.xyz:8090/aboutzhiimmob");
    }

    @OnClick(R.id.privacyOptionItemView)
    public void privacy() {
        WfcWebViewActivity.loadUrl(this, "之讯隐私政策", "http://web.keepwork.xyz:8090/privicyzhiim");
    }
}
