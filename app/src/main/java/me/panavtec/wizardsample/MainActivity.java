package me.panavtec.wizardsample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import me.panavtec.wizard.Wizard;
import me.panavtec.wizard.WizardPage;

public class MainActivity extends ActionBarActivity {

    private Wizard wizard;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Wizard.Builder()
                .activity(this)
                .containerId(android.R.id.content)
                .enterAnimation(R.anim.card_slide_right_in)
                .exitAnimation(R.anim.card_slide_left_out)
                .popEnterAnimation(R.anim.card_slide_left_in)
                .popExitAnimation(R.anim.card_slide_right_out)
                .pageList(new WizardPage[]{
                        new WizardPage1(),
                        new WizardPage2(),
                        new WizardPage3()
                })
                .build();
        wizard.init();
    }

    public Wizard getWizard() {
        return wizard;
    }

}
