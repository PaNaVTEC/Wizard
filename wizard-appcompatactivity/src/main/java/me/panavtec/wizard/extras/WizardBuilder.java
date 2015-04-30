package me.panavtec.wizard.extras;

import android.support.v7.app.AppCompatActivity;
import me.panavtec.wizard.BaseWizardBuilder;
import me.panavtec.wizard.WizardPage;
import me.panavtec.wizard.actionbar.ActionBarResolver;

public class WizardBuilder extends BaseWizardBuilder<AppCompatActivity> {
  public WizardBuilder(AppCompatActivity activity, WizardPage... pages) {
    super(activity, pages);
  }

  @Override protected ActionBarResolver createActionBarResolver(AppCompatActivity activity) {
    return new AppCompactActivityResolver(activity);
  }
}
