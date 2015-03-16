package me.panavtec.wizard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

/**
 * Encapsulates navigation operations between fragments of an activity using BackStack
 * The first fragment on PageList will act like a root fragment
 */
public class Wizard implements FragmentManager.OnBackStackChangedListener {

  private final ActionBarActivity activity;
  private final WizardPage[] pages;
  private final int containerId;
  private final int enterAnimation;
  private final int exitAnimation;
  private final int popEnterAnimation;
  private final int popExitAnimation;
  private final WizardPageListener pageListener;
  private final WizardListener wizardListener;

  protected Wizard(ActionBarActivity activity, WizardPage[] pages, int containerId,
      WizardPageListener pageListener, WizardListener wizardListener, int enterAnimation,
      int exitAnimation, int popEnterAnimation, int popExitAnimation) {
    this.activity = activity;
    this.pages = pages;
    this.containerId = containerId;
    this.pageListener = pageListener;
    this.wizardListener = wizardListener;
    this.enterAnimation = enterAnimation;
    this.exitAnimation = exitAnimation;
    this.popEnterAnimation = popEnterAnimation;
    this.popExitAnimation = popExitAnimation;
  }

  public void init() {
    if (!activity.isFinishing()) {
      FragmentManager fragmentManager = activity.getSupportFragmentManager();
      fragmentManager.addOnBackStackChangedListener(this);
      WizardPage firstPage = pages[0];
      fragmentManager.beginTransaction().replace(containerId, firstPage.createFragment()).commit();
      fragmentManager.executePendingTransactions();
      if (firstPage.hasOptionMenu()) {
        activity.supportInvalidateOptionsMenu();
      }
      firstPage.setupActionBar(activity.getSupportActionBar());
    }
  }

  public boolean returnToFirst() {
    if (!activity.isFinishing()) {
      FragmentManager fragmentManager = activity.getSupportFragmentManager();
      if (fragmentManager.getBackStackEntryCount() > 0) {
        String name = fragmentManager.getBackStackEntryAt(0).getName();
        fragmentManager.popBackStackImmediate(name, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        return true;
      }
    }
    return false;
  }

  public boolean navigatePrevious() {
    if (!activity.isFinishing()) {
      FragmentManager fragmentManager = activity.getSupportFragmentManager();
      if (fragmentManager.getBackStackEntryCount() > 0) {
        fragmentManager.popBackStackImmediate();
        fragmentManager.executePendingTransactions();
        return true;
      }
    }
    return false;
  }

  public boolean onBackPressed() {
    return getCurrentPage().allowsBackNavigation();
  }

  public boolean navigateNext() {
    if (!activity.isFinishing()) {
      FragmentManager fragmentManager = activity.getSupportFragmentManager();
      int nextStep = fragmentManager.getBackStackEntryCount() + 1;
      if (nextStep < pages.length) {
        WizardPage WizardPage = pages[nextStep];
        Fragment fragment = WizardPage.createFragment();
        fragmentManager.beginTransaction()
            .addToBackStack(fragment.getClass().getName())
            .setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
            .replace(containerId, fragment)
            .commit();
        fragmentManager.executePendingTransactions();
        return true;
      } else if (wizardListener != null) {
        wizardListener.onWizardFinished();
      }
    }
    return false;
  }

  private WizardPage getCurrentPage() {
    return pages[activity.getSupportFragmentManager().getBackStackEntryCount()];
  }

  public Fragment getCurrent() {
    return activity.getSupportFragmentManager().findFragmentById(containerId);
  }

  public int getCurrentIndex() {
    return activity.getSupportFragmentManager().getBackStackEntryCount();
  }

  @Override public void onBackStackChanged() {
    int currentPageIndex = activity.getSupportFragmentManager().getBackStackEntryCount();
    WizardPage currentPage = pages[currentPageIndex];
    currentPage.setupActionBar(activity.getSupportActionBar());
    if (currentPage.hasOptionMenu()) {
      activity.supportInvalidateOptionsMenu();
    }
    if (pageListener != null) {
      pageListener.onPageChanged(currentPageIndex, currentPage);
    }
  }

  public static class Builder extends WizardBuilder {
    public Builder(ActionBarActivity activity, WizardPage... pages) {
      super(activity, pages);
    }
  }
}
