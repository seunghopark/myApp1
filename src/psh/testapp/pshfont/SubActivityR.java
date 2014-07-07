package psh.testapp.pshfont;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class SubActivityR extends Activity implements View.OnClickListener{

	private static final String TYPEFACE_NAME = "fonts/Roboto-Thin.ttf";
	
	private Typeface typeface = null;
	
	private static final String TYPEFACE_NAME2 = "fonts/NanumGothic.otf";
	private Typeface typeface2 = null;
	
	private void loadTypeface() {
		if (typeface == null)
			typeface = Typeface.createFromAsset(getAssets(), TYPEFACE_NAME);
		
		if (typeface2 == null)
			typeface2 = Typeface.createFromAsset(getAssets(), TYPEFACE_NAME2);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.backbutton1:			
			finish();
			break;
		}
		// TODO 자동 생성된 메소드 스텁

	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		loadTypeface();
		
		setContentView(R.layout.activity_sub_n);	
		
		View myButtonLayout = getLayoutInflater().inflate(R.layout.psh_sctionbar, null);		
		myButtonLayout.findViewById(R.id.backbutton1).setOnClickListener(this);		
		TextView test = (TextView) myButtonLayout.findViewById(R.id.titleView1);
		test.setText("로보트틴");
		test.setTypeface(typeface2);
		
		ActionBar barbar = getActionBar();
		barbar.setCustomView(myButtonLayout);				
		barbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

		LinearLayout linerLO = new LinearLayout(this);

		linerLO.setOrientation(LinearLayout.VERTICAL);

		TextView txt = new TextView(this);

		txt.setText("승호가 만든 폰트 비교하기 Roboto-Thin");

		linerLO.addView(txt);

		TextView[] tvr = new TextView[140];
		ScrollView scc = new ScrollView(this);

		for (int i = 0; i < tvr.length; i++) {

			tvr[i] = new TextView(this);
			tvr[i].setTypeface(typeface);
			tvr[i].setText((i + 10) + "SmileDay스마일데이2014");
			tvr[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, i + 10);
			linerLO.addView(tvr[i]);
		}

		scc.addView(linerLO);

		setContentView(scc);

	}	

}
