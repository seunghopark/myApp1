package psh.testapp.pshfont;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.analytics.tracking.android.EasyTracker;

public class MyFontActivity extends Activity {

	private static final String TYPEFACE_NAME = "fonts/NanumGothic.otf";
	private Typeface typeface = null;
	
	private void loadTypeface(){
		if(typeface==null)
			typeface = Typeface.createFromAsset(getAssets(), TYPEFACE_NAME);		
	}
	
	 @Override
	    public void onStart() {
	      super.onStart();
	      EasyTracker.getInstance(this).activityStart(this);  // Add this method.
	    }

	    @Override
	    public void onStop() {
	      super.onStop();     
	      EasyTracker.getInstance(this).activityStop(this);  // Add this method.
	    }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		loadTypeface();
		
		setContentView(R.layout.activity_sub_n);		

		LinearLayout linerLO = new LinearLayout(this);

		linerLO.setOrientation(LinearLayout.VERTICAL);

		TextView txt = new TextView(this);

		txt.setText("승호가 만든 폰트 비교하기 NanumGothic");

		linerLO.addView(txt);

		TextView[] tv = new TextView[140];

		ScrollView scc = new ScrollView(this);

		for (int i = 0; i < tv.length; i++) {
			tv[i] = new TextView(this);
			tv[i].setTypeface(typeface);
			tv[i].setText((i + 10) + "SmileDay스마일데이2014");			
			tv[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, i + 10);
			linerLO.addView(tv[i]);
		}

		scc.addView(linerLO);

		setContentView(scc);

	}	

}
