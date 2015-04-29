package me.panavtec.wizardsample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment3 extends Fragment {

  private Button goNextButton;
  private Button goBackButton;

  @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment, container, false);
    view.setBackgroundColor(Color.BLACK);
    goNextButton = (Button) view.findViewById(R.id.goNextButton);
    goBackButton = (Button) view.findViewById(R.id.goBackButton);
    goNextButton.setVisibility(View.GONE);
    goBackButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        ((MainActivity) getActivity()).getWizard().navigatePrevious();
      }
    });
    return view;
  }
}
