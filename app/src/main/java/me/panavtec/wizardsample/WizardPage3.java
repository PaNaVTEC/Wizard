package me.panavtec.wizardsample;

import me.panavtec.wizard.WizardPage;

public class WizardPage3 extends WizardPage<Fragment3> {

  @Override public Fragment3 createFragment() {
    return new Fragment3();
  }
}