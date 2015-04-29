package me.panavtec.wizard;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;

public class WizardBuilder {

  private FragmentActivity activity;
  private WizardPage[] pages;
  private int containerId;
  private int enterAnimation;
  private int exitAnimation;
  private int popEnterAnimation;
  private int popExitAnimation;
  private WizardPageListener pageListener;
  private WizardListener wizardListener;
  private ActionBarResolver actionBarResolver;

  public WizardBuilder(FragmentActivity activity, WizardPage... pages) {
    if (activity == null) {
      throw new IllegalArgumentException("Activity must not be null.");
    }
    this.activity = activity;

    if (activity instanceof ActionBarActivity) {
      this.actionBarResolver = new ActionBarActivityResolver(((ActionBarActivity) activity));
    } else if (activity instanceof AppCompatActivity) {
      this.actionBarResolver = new AppCompactActivityResolver(((AppCompatActivity) activity));
    }

    if (pages == null) {
      throw new IllegalArgumentException("Pages must not be null.");
    }
    this.pages = pages;
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

    return new Wizard(actionBarResolver, activity, pages, containerId, pageListener, wizardListener,
        enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation);
  }
}
