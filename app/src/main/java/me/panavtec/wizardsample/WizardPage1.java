package me.panavtec.wizardsample;

import me.panavtec.wizard.WizardPage;

public class WizardPage1 extends WizardPage<Fragment1> {
  @Override public Fragment1 createFragment() {
    return new Fragment1();
  }
}
