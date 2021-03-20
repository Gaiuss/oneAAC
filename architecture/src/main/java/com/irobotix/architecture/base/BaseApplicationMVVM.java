package com.irobotix.architecture.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.irobotix.architecture.utils.Utils;

public class BaseApplicationMVVM extends Application {
    private static Application sInstance;

    public static Application getInstance() {
        if (null == sInstance) {
            throw new NullPointerException("please inherit BaseApplicationMVVM or call setApplication.");
        }
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setApplication(this);
    }

    /**
     * 当主工程没有继承BaseApplication时，此方法初始化BaseApplication
     */
    public static synchronized void setApplication(@NonNull Application application) {
        sInstance = application;
        Utils.init(application);
        application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                AppManagerMVVM.getAppManager().addActivity(activity);
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                AppManagerMVVM.getAppManager().removeActivity(activity);
            }
        });
    }
}
