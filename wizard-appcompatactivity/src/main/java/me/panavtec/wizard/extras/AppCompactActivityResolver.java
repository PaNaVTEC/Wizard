package me.panavtec.wizard.extras;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import me.panavtec.wizard.actionbar.ActionBarResolver;

public class AppCompactActivityResolver implements ActionBarResolver {
  private AppCompatActivity appCompatActivity;

  public AppCompactActivityResolver(AppCompatActivity appCompatActivity) {
    this.appCompatActivity = appCompatActivity;
  }

  @Override public ActionBar getActionBar() {
    return appCompatActivity.getSupportActionBar();
  }
}
