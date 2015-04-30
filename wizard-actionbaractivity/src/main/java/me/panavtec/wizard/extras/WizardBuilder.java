package me.panavtec.wizard.extras;

import android.support.v7.app.ActionBarActivity;
import me.panavtec.wizard.BaseWizardBuilder;
import me.panavtec.wizard.WizardPage;
import me.panavtec.wizard.actionbar.ActionBarResolver;

public class WizardBuilder extends BaseWizardBuilder<ActionBarActivity> {
  public WizardBuilder(ActionBarActivity activity, WizardPage... pages) {
    super(activity, pages);
  }

  @Override protected ActionBarResolver createActionBarResolver(ActionBarActivity activity) {
    return new ActionBarActivityResolver(activity);
  }
}
