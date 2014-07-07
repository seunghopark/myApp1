package psh.testapp.pshfont;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.analytics.tracking.android.EasyTracker;

public class IntroActivity extends Activity {

	Handler h;
	
	private static final String TYPEFACE_NAME2 = "fonts/NanumGothic.otf";
	private Typeface typeface2 = null;
	
	private void loadTypeface() {
		if (typeface2 == null)
			typeface2 = Typeface.createFromAsset(getAssets(), TYPEFACE_NAME2);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadTypeface();
		setContentView(R.layout.activity_intro);

		TextView test = (TextView) findViewById(R.id.textView1);		
		test.setTypeface(typeface2);
		
		ImageView image = (ImageView) findViewById(R.id.img_app_mark2);
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.intro_ani);
		image.startAnimation(anim);

		// 다음 화면으로 넘어가는 딜레이
		h = new Handler();
		h.postDelayed(irun, 2000);

	}

	Runnable irun = new Runnable() {

		@Override
		public void run() {
			// TODO 자동 생성된 메소드 스텁
			Intent i = new Intent(IntroActivity.this, MainActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(i);
			finish();
		}
	};

	public void onBackPreessed() {
		super.onBackPressed();
		h.removeCallbacks(irun);
	}

	@Override
	public void onStart() {
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this); // Add this method.
	}

	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this); // Add this method.
	}

}
