package me.panavtec.wizard;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

public abstract class WizardPage<T extends Fragment> {

    public abstract T createFragment();

    public void setupActionBar(ActionBar supportActionBar) {
    }

    public boolean hasOptionMenu() {
        return false;
    }

    public boolean allowsBackNavigation() {
        return true;
    }

}
