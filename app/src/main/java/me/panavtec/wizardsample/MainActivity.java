package me.panavtec.wizardsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import me.panavtec.wizard.Wizard;
import me.panavtec.wizard.WizardBuilder;
import me.panavtec.wizard.WizardListener;
import me.panavtec.wizard.WizardPage;
import me.panavtec.wizard.WizardPageListener;

public class MainActivity extends AppCompatActivity implements WizardPageListener, WizardListener {

  private Wizard wizard;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    WizardPage[] wizardPages = { new WizardPage1(), new WizardPage2(), new WizardPage3() };
    wizard = new WizardBuilder(this, wizardPages).containerId(android.R.id.content)
        .enterAnimation(R.anim.card_slide_right_in)
        .exitAnimation(R.anim.card_slide_left_out)
        .popEnterAnimation(R.anim.card_slide_left_in)
        .popExitAnimation(R.anim.card_slide_right_out)
        .pageListener(this)
        .wizardListener(this)
        .build();
    wizard.init();
  }

  public Wizard getWizard() {
    return wizard;
  }

  @Override public void onPageChanged(int currentPageIndex, WizardPage page) {
    Toast.makeText(this, "Page selected: " + currentPageIndex, Toast.LENGTH_SHORT).show();
  }

  @Override public void onWizardFinished() {
    Toast.makeText(this, "Wizard finished", Toast.LENGTH_SHORT).show();
  }
}
