package psh.testapp.pshfont;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;

public class CsFontActivity extends Activity implements View.OnClickListener {

	//private static final String TYPEFACE_NAME = "fonts/NanumGothic.otf";
	private Typeface typeface = null;
	
	private static final String TYPEFACE_NAME2 = "fonts/NanumGothic.otf";
	private Typeface typeface2 = null;
	
	public String textName;

	private void loadTypeface() {
		if (typeface == null)
			typeface = Typeface.createFromAsset(getAssets(), "fonts/"+textName);
		
		if (typeface2 == null)
			typeface2 = Typeface.createFromAsset(getAssets(), TYPEFACE_NAME2);
	}
		
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		Intent myintent = getIntent(); // 값을 받기 위한 설정
		String fontCnt = myintent.getStringExtra("fontContent");
		textName = myintent.getStringExtra("fontName");
		int fontSize = myintent.getIntExtra("fontSize", 0);
		
		Toast.makeText(getBaseContext(),fontCnt+"을 선택 했습니다."+fontSize+textName	,
				Toast.LENGTH_LONG).show();		
		loadTypeface();
		
		setContentView(R.layout.activity_sub_n);
		
		
		View myButtonLayout = getLayoutInflater().inflate(R.layout.psh_sctionbar, null);
		myButtonLayout.findViewById(R.id.backbutton1).setOnClickListener(this);
		
		ActionBar barbar = getActionBar();
		barbar.setCustomView(myButtonLayout);				
		barbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

		LinearLayout linerLO = new LinearLayout(this);
		
		linerLO.setOrientation(LinearLayout.VERTICAL);
		TextView txt = new TextView(this);
		txt.setText(fontCnt);
		txt.setTypeface(typeface);
		txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
		linerLO.addView(txt);
		setContentView(linerLO);
		
		System.out.println("뿌려지나");

	}
	
	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this); // Add this method.
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		Log.d("클릭", "클릭됨");
		onBackPressed();
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backbutton1:
			finish();
			break;
		}
		// TODO 자동 생성된 메소드 스텁

	}
	

	@Override
	public void onStart() {
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this); // Add this method.
		// May return null if EasyTracker has not yet been initialized with a
		// property
		// ID.
		Tracker easyTracker = EasyTracker.getInstance(this);
		// This screen name value will remain set on the tracker and sent with
		// hits until it is set to a new value or to null.
		easyTracker.set(Fields.SCREEN_NAME, "Home XXXn");

		easyTracker.send(MapBuilder.createAppView().build());
	}

}
