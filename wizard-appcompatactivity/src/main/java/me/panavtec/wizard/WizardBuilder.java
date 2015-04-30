package me.panavtec.wizard;

import android.support.v7.app.AppCompatActivity;
import me.panavtec.wizard.actionbar.ActionBarResolver;
import me.panavtec.wizard.actionbar.AppCompactActivityResolver;

public class WizardBuilder extends BaseWizardBuilder<AppCompatActivity> {
  public WizardBuilder(AppCompatActivity activity, WizardPage... pages) {
    super(activity, pages);
  }

  @Override protected ActionBarResolver createActionBarResolver(AppCompatActivity activity) {
    return new AppCompactActivityResolver(activity);
  }
}
