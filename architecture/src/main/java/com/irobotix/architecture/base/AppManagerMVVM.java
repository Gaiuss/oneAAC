package com.irobotix.architecture.base;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import java.util.Stack;

public class AppManagerMVVM {

    private static Stack<Activity> activityStack;
    private static Stack<Fragment> fragmentStack;
    private static AppManagerMVVM sInstance;

    private AppManagerMVVM() {
    }

    public static AppManagerMVVM getAppManager() {
        if (null == sInstance) {
            sInstance = new AppManagerMVVM();
        }
        return sInstance;
    }

    public static Stack<Activity> getActivityStack() {
        return activityStack;
    }

    public static Stack<Fragment> getFragmentStack() {
        return fragmentStack;
    }

    public void addActivity(Activity activity) {
        if (null == activityStack) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (null != activity) {
            activityStack.remove(activity);
        }
    }

    public boolean isActivity() {
        if (null != activityStack) {
            return !activityStack.isEmpty();
        }
        return false;
    }

    public Activity currentActivity() {
        return activityStack.lastElement();
    }

    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                finishActivity(activityStack.get(i));
            }
        }
        activityStack.clear();
    }

    public Activity getActivity(Class<?> cls) {
        if (activityStack != null)
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        return null;
    }

    public void addFragment(Fragment fragment) {
        if (fragmentStack == null) {
            fragmentStack = new Stack<Fragment>();
        }
        fragmentStack.add(fragment);
    }

    public void removeFragment(Fragment fragment) {
        if (fragment != null) {
            fragmentStack.remove(fragment);
        }
    }

    public boolean isFragment() {
        if (fragmentStack != null) {
            return !fragmentStack.isEmpty();
        }
        return false;
    }

    public Fragment currentFragment() {
        if (fragmentStack != null) {
            return fragmentStack.lastElement();
        }
        return null;
    }

    public void AppExit() {
        try {
            finishAllActivity();
            // 杀死该应用进程
//          android.os.Process.killProcess(android.os.Process.myPid());
//            调用 System.exit(n) 实际上等效于调用：
//            Runtime.getRuntime().exit(n)
//            finish()是Activity的类方法，仅仅针对Activity，当调用finish()时，
//            只是将活动推向后台，并没有立即释放内存，活动的资源并没有被清理；当调用System.exit(0)时，
//            退出当前Activity并释放资源（内存），但是该方法不可以结束整个App如有多个Activity或者有其他组件service等不会结束。
//            其实android的机制决定了用户无法完全退出应用，当你的application最长时间没有被用过的时候，android自身会决定将application关闭了。
            //System.exit(0);
        } catch (Exception e) {
            activityStack.clear();
            e.printStackTrace();
        }
    }
}
