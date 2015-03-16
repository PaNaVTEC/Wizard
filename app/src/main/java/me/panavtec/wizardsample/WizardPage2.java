package me.panavtec.wizardsample;

import me.panavtec.wizard.WizardPage;

public class WizardPage2 extends WizardPage<Fragment2> {

  @Override public Fragment2 createFragment() {
    return new Fragment2();
  }
}
