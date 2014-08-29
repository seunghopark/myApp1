package psh.testapp.pshfont;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;

public class MainActivity extends Activity {

	public static final int REQUEST_CODE_ANOTHER = 1001;

	EditText edittxtContent;

	EditText edittxtSize;

	Button edittxtBtn;

	Button fontTypeA;

	Resources myr;

	Spinner spinner1;

	Spinner spinner2;

	// 텍스트뷰 객체 참조
	TextView text1;

	List<String> list = new ArrayList<String>();

	String[] filelist;

	String[] linesArr;

	List<Integer> sizeVal = new ArrayList<Integer>();

	ActionBar shbar;

	// String fontName = "fonts/NanumGothicBold.ttf";

	EasyTracker easyTracker = EasyTracker.getInstance(this);

	String selItem;
	Integer selItemSize;

	private static final String TYPEFACE_NAME = "fonts/NanumGothicBold.ttf";

	private Typeface typeface = null;

	private void loadTypeface() {
		if (typeface == null)
			typeface = Typeface.createFromAsset(getAssets(), TYPEFACE_NAME);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		loadTypeface();

		setContentView(R.layout.activity_main);

		View myButtonLayout = getLayoutInflater().inflate(
				R.layout.main_sctionbar, null);

		ActionBar barbar = getActionBar();

		barbar.setCustomView(myButtonLayout);
		barbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

		// 이전 파트에서와 마찬가지로 일단 calendar를 하나 가져온다.
		// final String CONTENT_URI = "content://com.android.calendar";
		//
		// Uri uri = Uri.parse(CONTENT_URI + "/calendars");
		//
		// Cursor c = getContentResolver().query(
		// uri, new String[] { "_id" },
		// "selected=?", new String[] { "1" }, null);
		//
		//
		// if(c == null || !c.moveToFirst()) {
		// // 시스템에 캘린더가 존재하지 않음(계정이 등록되어 있지 않음) 오류 처리 필요
		// }
		//
		//
		// final int id = c.getInt(c.getColumnIndex("_id"));
		//
		// c.close();

		// uri = Uri.parse(CONTENT_URI + "/events");
		// // 이전 파트에서 집어 넣었던 일정을 검색한다.
		// c = getContentResolver().query(
		// uri, new String[] { "_id", "dtstart" }, // 가져올 필드
		// "calendar_id=? AND title=?",
		// new String[] { String.valueOf(id), "birthday" }, null);
		// if(c == null || !c.moveToFirst()) {
		// // 실패했거나 검색 결과가 없음. 오류처리 필요
		// }
		// // 일정의 시작 시간을 Date타입인 dtstart에 저장한다.
		// final Date dtstart = new
		// Date(c.getLong(c.getColumnIndex("dtstart")));
		// Log.i("xx", "+dtstart+");
		// c.close();

		final AssetManager assetManager = getAssets();

		// 텍스트뷰 객체 참조
		text1 = (TextView) findViewById(R.id.Txt1);

		try {

			filelist = assetManager.list("fonts");

			if (filelist == null) {
				// Log.v("assets", "폰트가 없다");
			} else {
				for (int i = 0; i < filelist.length; i++) {
					// Get filename of file or directory
					String filename = filelist[i];
					list.add(filename);
				}

				System.out.println(list);

				// 리스트를 다시 배열로
				linesArr = list.toArray(new String[list.size()]);

				for (String line : linesArr) {
					System.out.println(line);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// 콤보박스 설정

		// 입력한내용의 폰트설정

		spinner1 = (Spinner) findViewById(R.id.fontSpinner);

		spinner1.setPrompt("폰트를 선택하세요");

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, linesArr);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner1.setAdapter(dataAdapter);

		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				selItem = (String) spinner1.getSelectedItem();

				Toast.makeText(getBaseContext(), selItem + "을 선택 했습니다.",
						Toast.LENGTH_LONG).show();
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		// 스나이퍼 폰트 사이즈

		for (int j = 0; j < 150; j++) {
			// sizeVal[j] = j + 1;
			sizeVal.add(j + 1);

		}

		spinner2 = (Spinner) findViewById(R.id.sizeSpinner);

		ArrayAdapter<Integer> dataAdapterSize = new ArrayAdapter<Integer>(this,
				android.R.layout.simple_spinner_item, sizeVal);

		dataAdapterSize
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner2.setAdapter(dataAdapterSize);

		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				selItemSize = (Integer) spinner2.getSelectedItem();

				Toast.makeText(getBaseContext(), selItemSize + "을 선택 했습니다.",
						Toast.LENGTH_LONG).show();
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		// 폰트명

		edittxtContent = (EditText) findViewById(R.id.inputTxt1);

		// edittxtSize = (EditText) findViewById(R.id.inputTxt2);

		edittxtBtn = (Button) findViewById(R.id.btn1); // 보기버튼

		edittxtBtn.setOnClickListener(new ClickHandler());

		fontTypeA = (Button) findViewById(R.id.btn2);

		fontTypeA.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 자동 생성된 메소드 스텁
				Intent intent = new Intent(getApplicationContext(),
						SubActivityN.class);
				// May return null if a EasyTracker has not yet been initialized
				// with a
				// property ID.

				// MapBuilder.createEvent().build() returns a Map of event
				// fields and values
				// that are set and sent with the hit.
				easyTracker.send(MapBuilder.createEvent("메인-기본나눔", // Event
																	// category
																	// (required)
						"button_press", // Event action (required)
						"play_button", // Event label
						null) // Event value
						.build());

				// 액티비티를 띄워주도록 startActivityForResult() 메소드를 호출합니다.
				startActivity(intent);

			}

		});

		Button fontTypeB = (Button) findViewById(R.id.btn3);

		fontTypeB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 자동 생성된 메소드 스텁
				Intent intent = new Intent(getApplicationContext(),
						SubActivityNL.class);
				// 액티비티를 띄워주도록 startActivityForResult() 메소드를 호출합니다.
				startActivity(intent);
			}

		});

		Button fontTypeC = (Button) findViewById(R.id.btn4);

		fontTypeC.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO 자동 생성된 메소드 스텁
				Intent intent = new Intent(getApplicationContext(),
						SubActivityNB.class);
				// 액티비티를 띄워주도록 startActivityForResult() 메소드를 호출합니다.

				easyTracker.send(MapBuilder.createEvent("메인-나눔볼드", // Event
																	// category
																	// (required)
						"button_press", // Event action (required)
						"play_button", // Event label
						null) // Event value
						.build());

				startActivity(intent);
			}
		});
		Button fontTypeD = (Button) findViewById(R.id.btn5);

		fontTypeD.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 자동 생성된 메소드 스텁
				Intent intent = new Intent(getApplicationContext(),
						SubActivityNEB.class);

				easyTracker.send(MapBuilder.createEvent("메인-나눔익스트라볼드", // Event
																		// category
																		// (required)
						"button_press", // Event action (required)
						"play_button", // Event label
						null) // Event value
						.build());
				// 액티비티를 띄워주도록 startActivityForResult() 메소드를 호출합니다.
				startActivity(intent);
			}

		});

		Button fontTypeR = (Button) findViewById(R.id.btn11); // 보기버튼

		fontTypeR.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 자동 생성된 메소드 스텁
				Intent intent = new Intent(getApplicationContext(),
						SubActivityR.class);

				easyTracker.send(MapBuilder.createEvent("메인-로보트", // Event
																	// category
																	// (required)
						"button_press", // Event action (required)
						"play_button", // Event label
						null) // Event value
						.build());
				// 액티비티를 띄워주도록 startActivityForResult() 메소드를 호출합니다.
				startActivity(intent);
			}

		});

	}

	private class ClickHandler implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO 자동 생성된 메소드 스텁
			try {
				String myFontContent = edittxtContent.getText().toString();// 입력한내용

				Intent myFontActivity = new Intent(getApplicationContext(),
						CsFontActivity.class);
				
				myFontActivity.putExtra("fontContent", myFontContent); // 내용
				myFontActivity.putExtra("fontName", selItem); // 폰트명
				myFontActivity.putExtra("fontSize", selItemSize); // 폰트사이즈

				// 액티비티를 띄워주도록 startActivityForResult() 메소드를 호출합니다.
				startActivity(myFontActivity);

				/*Toast.makeText(
						getBaseContext(),
						myFontContent + "내용으로" + selItem + "선택했고" + selItemSize
								+ "사이즈로 선택했다고 어렵구나", Toast.LENGTH_LONG).show();*/
				
/*				 팝업으로 띄우기 부분*/
				
				
				

			} catch (Exception ex) {
				easyTracker.send(MapBuilder.createEvent("메인-직접",
						"button_press", // Event action (required)
						"play_button", // Event label
						null) // Event value
						.build());

				Toast.makeText(getBaseContext(), ex.getMessage(),
						Toast.LENGTH_LONG).show();
			}

		}
	}

	/**
	 * 새로운 액티비티에서 돌아올 때 자동 호출되는 메소드
	 */
	// protected void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// super.onActivityResult(requestCode, resultCode, data);
	//
	// if (requestCode == REQUEST_CODE_ANOTHER) {
	// Toast toast = Toast.makeText(getBaseContext(),
	// "onActivityResult() 메소드가 호출됨. 요청코드 : " + requestCode
	// + ", 결과코드 : " + resultCode, Toast.LENGTH_LONG);
	// toast.show();
	// }
	//
	// }
	//
	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }

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
		easyTracker.set(Fields.SCREEN_NAME, "메인페이지");
		easyTracker.send(MapBuilder.createAppView().build());
	}

	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this); // Add this method.
	}

}
