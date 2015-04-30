package me.panavtec.wizard;

import android.support.v4.app.FragmentActivity;
import me.panavtec.wizard.actionbar.ActionBarResolver;

public abstract class BaseWizardBuilder<T extends FragmentActivity> {

  private T activity;
  private WizardPage[] pages;
  private int containerId;
  private int enterAnimation;
  private int exitAnimation;
  private int popEnterAnimation;
  private int popExitAnimation;
  private WizardPageListener pageListener;
  private WizardListener wizardListener;

  public BaseWizardBuilder(T activity, WizardPage... pages) {
    if (activity == null) {
      throw new IllegalArgumentException("Activity must not be null.");
    }
    this.activity = activity;

    if (pages == null) {
      throw new IllegalArgumentException("Pages must not be null.");
    }
    this.pages = pages;
  }

  public BaseWizardBuilder containerId(int containerId) {
    this.containerId = containerId;
    return this;
  }

  public BaseWizardBuilder enterAnimation(int enterAnimation) {
    this.enterAnimation = enterAnimation;
    return this;
  }

  public BaseWizardBuilder exitAnimation(int exitAnimation) {
    this.exitAnimation = exitAnimation;
    return this;
  }

  public BaseWizardBuilder popEnterAnimation(int popEnterAnimation) {
    this.popEnterAnimation = popEnterAnimation;
    return this;
  }

  public BaseWizardBuilder popExitAnimation(int popExitAnimation) {
    this.popExitAnimation = popExitAnimation;
    return this;
  }

  public BaseWizardBuilder pageListener(WizardPageListener pageListener) {
    this.pageListener = pageListener;
    return this;
  }

  public BaseWizardBuilder wizardListener(WizardListener wizardListener) {
    this.wizardListener = wizardListener;
    return this;
  }

  protected abstract ActionBarResolver createActionBarResolver(T activity);

  public Wizard build() {

    if (pages == null || pages.length == 0) {
      throw new RuntimeException(
          "No page list configured or empty. If you don't " + "have pages why you need Merlin?");
    }

    if (activity == null) {
      throw new RuntimeException("No activity configured, Wizard needs an Activity to work. "
          + "set activity with builder.activity(activity)");
    }

    if (containerId == 0) {
      containerId = android.R.id.content;
    }

    return new Wizard(createActionBarResolver(activity), activity, pages, containerId, pageListener, wizardListener,
        enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation);
  }
}
