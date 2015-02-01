package me.panavtec.wizard;

import android.support.v7.app.ActionBarActivity;

public class WizardBuilder {

    private ActionBarActivity activity;
    private WizardPage[] pages;
    private int containerId;
    private int enterAnimation;
    private int exitAnimation;
    private int popEnterAnimation;
    private int popExitAnimation;
    private WizardPageListener pageListener;

    public WizardBuilder() { }

    public WizardBuilder activity(ActionBarActivity activity) {
        this.activity = activity;
        return this;
    }

    public WizardBuilder pageList(WizardPage[] pages) {
        this.pages = pages;
        return this;
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

    public Wizard build() {

        if (pages == null || pages.length == 0) {
            throw new RuntimeException("No page list configured or empty. If you dont " +
                    "have pages why you need Merlin?");
        }

        if (activity == null) {
            throw new RuntimeException("No activity configured, Wizard needs an Acitivty to work. " +
                    "set activity with builder.activity(activity)");
        }

        if (containerId == 0) {
            throw new RuntimeException("No container id configured. Sample config: " +
                    "builder.setContainerId(android.R.id.content)");
        }

        return new Wizard(activity, pages, containerId, pageListener,
                enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation);
    }

}
