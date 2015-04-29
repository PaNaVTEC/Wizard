package me.panavtec.wizard;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class ActionBarActivityResolver implements ActionBarResolver {
  private final ActionBarActivity actionBarActivity;

  public ActionBarActivityResolver(ActionBarActivity actionBarActivity) {
    this.actionBarActivity = actionBarActivity;
  }

  @Override public ActionBar getActionBar() {
    return actionBarActivity.getSupportActionBar();
  }
}
