package me.panavtec.wizard;

import android.support.v4.app.FragmentActivity;

public class WizardBuilder {

  private final FragmentActivity activity;
  private final WizardPage[] pages;
  private final ActionBarResolver actionBarResolver;
  private int containerId;
  private int enterAnimation;
  private int exitAnimation;
  private int popEnterAnimation;
  private int popExitAnimation;
  private WizardPageListener pageListener;
  private WizardListener wizardListener;

  public WizardBuilder(FragmentActivity activity, ActionBarResolver actionBarResolver,
      WizardPage... pages) {
    if (activity == null) {
      throw new IllegalArgumentException("Activity must not be null.");
    }
    this.activity = activity;

    if (actionBarResolver == null) {
      throw new IllegalArgumentException(
          "ActionBarResolver must be provided, if you don't have any operations you can use the other constructor.");
    }
    this.actionBarResolver = actionBarResolver;

    if (pages == null) {
      throw new IllegalArgumentException("Pages must not be null.");
    }
    this.pages = pages;
  }

  public WizardBuilder(FragmentActivity activity, WizardPage... pages) {
    this(activity, new ReflectionActionBarResolver(activity), pages);
  }

  public WizardBuilder containerId(int containerId) {
    this.containerId = containerId;
    return this;
  }

  public WizardBuilder enterAnimation(int enterAnimation) {
    this.enterAnimation = enterAnimation;
    return this;
  }

  public WizardBuilder exitAnimation(int exitAnimation) {
    this.exitAnimation = exitAnimation;
    return this;
  }

  public WizardBuilder popEnterAnimation(int popEnterAnimation) {
    this.popEnterAnimation = popEnterAnimation;
    return this;
  }

  public WizardBuilder popExitAnimation(int popExitAnimation) {
    this.popExitAnimation = popExitAnimation;
    return this;
  }

  public WizardBuilder pageListener(WizardPageListener pageListener) {
    this.pageListener = pageListener;
    return this;
  }

  public WizardBuilder wizardListener(WizardListener wizardListener) {
    this.wizardListener = wizardListener;
    return this;
  }

  public Wizard build() {
    if (pages.length == 0) {
      throw new RuntimeException(
          "No page list configured or empty. If you don't " + "have pages why you need Merlin?");
    }

    if (containerId == 0) {
      containerId = android.R.id.content;
    }

    return new Wizard(activity, actionBarResolver, pages, containerId, pageListener, wizardListener,
        enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation);
  }
}
