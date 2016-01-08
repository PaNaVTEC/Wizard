package me.panavtec.wizard;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import java.lang.reflect.Method;

class ReflectionActionBarResolver implements ActionBarResolver {
  private final FragmentActivity activity;
  private ActionBar actionBar;

  public ReflectionActionBarResolver(FragmentActivity activity) {
    this.activity = activity;
  }

  @Override public ActionBar getSupportActionBar() {
    if (actionBar == null) resolveActionBar();
    return actionBar;
  }

  private void resolveActionBar() {
    try {
      Method getSupportActionBar = activity.getClass().getMethod("getSupportActionBar", null);
      this.actionBar = (ActionBar) getSupportActionBar.invoke(activity, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
