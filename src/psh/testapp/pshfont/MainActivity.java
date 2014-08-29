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

	// �ؽ�Ʈ�� ��ü ����
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

		// ���� ��Ʈ������ ���������� �ϴ� calendar�� �ϳ� �����´�.
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
		// // �ý��ۿ� Ķ������ �������� ����(������ ��ϵǾ� ���� ����) ���� ó�� �ʿ�
		// }
		//
		//
		// final int id = c.getInt(c.getColumnIndex("_id"));
		//
		// c.close();

		// uri = Uri.parse(CONTENT_URI + "/events");
		// // ���� ��Ʈ���� ���� �־��� ������ �˻��Ѵ�.
		// c = getContentResolver().query(
		// uri, new String[] { "_id", "dtstart" }, // ������ �ʵ�
		// "calendar_id=? AND title=?",
		// new String[] { String.valueOf(id), "birthday" }, null);
		// if(c == null || !c.moveToFirst()) {
		// // �����߰ų� �˻� ����� ����. ����ó�� �ʿ�
		// }
		// // ������ ���� �ð��� DateŸ���� dtstart�� �����Ѵ�.
		// final Date dtstart = new
		// Date(c.getLong(c.getColumnIndex("dtstart")));
		// Log.i("xx", "+dtstart+");
		// c.close();

		final AssetManager assetManager = getAssets();

		// �ؽ�Ʈ�� ��ü ����
		text1 = (TextView) findViewById(R.id.Txt1);

		try {

			filelist = assetManager.list("fonts");

			if (filelist == null) {
				// Log.v("assets", "��Ʈ�� ����");
			} else {
				for (int i = 0; i < filelist.length; i++) {
					// Get filename of file or directory
					String filename = filelist[i];
					list.add(filename);
				}

				System.out.println(list);

				// ����Ʈ�� �ٽ� �迭��
				linesArr = list.toArray(new String[list.size()]);

				for (String line : linesArr) {
					System.out.println(line);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// �޺��ڽ� ����

		// �Է��ѳ����� ��Ʈ����

		spinner1 = (Spinner) findViewById(R.id.fontSpinner);

		spinner1.setPrompt("��Ʈ�� �����ϼ���");

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, linesArr);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner1.setAdapter(dataAdapter);

		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				selItem = (String) spinner1.getSelectedItem();

				Toast.makeText(getBaseContext(), selItem + "�� ���� �߽��ϴ�.",
						Toast.LENGTH_LONG).show();
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		// �������� ��Ʈ ������

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

				Toast.makeText(getBaseContext(), selItemSize + "�� ���� �߽��ϴ�.",
						Toast.LENGTH_LONG).show();
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		// ��Ʈ��

		edittxtContent = (EditText) findViewById(R.id.inputTxt1);

		// edittxtSize = (EditText) findViewById(R.id.inputTxt2);

		edittxtBtn = (Button) findViewById(R.id.btn1); // �����ư

		edittxtBtn.setOnClickListener(new ClickHandler());

		fontTypeA = (Button) findViewById(R.id.btn2);

		fontTypeA.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO �ڵ� ������ �޼ҵ� ����
				Intent intent = new Intent(getApplicationContext(),
						SubActivityN.class);
				// May return null if a EasyTracker has not yet been initialized
				// with a
				// property ID.

				// MapBuilder.createEvent().build() returns a Map of event
				// fields and values
				// that are set and sent with the hit.
				easyTracker.send(MapBuilder.createEvent("����-�⺻����", // Event
																	// category
																	// (required)
						"button_press", // Event action (required)
						"play_button", // Event label
						null) // Event value
						.build());

				// ��Ƽ��Ƽ�� ����ֵ��� startActivityForResult() �޼ҵ带 ȣ���մϴ�.
				startActivity(intent);

			}

		});

		Button fontTypeB = (Button) findViewById(R.id.btn3);

		fontTypeB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO �ڵ� ������ �޼ҵ� ����
				Intent intent = new Intent(getApplicationContext(),
						SubActivityNL.class);
				// ��Ƽ��Ƽ�� ����ֵ��� startActivityForResult() �޼ҵ带 ȣ���մϴ�.
				startActivity(intent);
			}

		});

		Button fontTypeC = (Button) findViewById(R.id.btn4);

		fontTypeC.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO �ڵ� ������ �޼ҵ� ����
				Intent intent = new Intent(getApplicationContext(),
						SubActivityNB.class);
				// ��Ƽ��Ƽ�� ����ֵ��� startActivityForResult() �޼ҵ带 ȣ���մϴ�.

				easyTracker.send(MapBuilder.createEvent("����-��������", // Event
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
				// TODO �ڵ� ������ �޼ҵ� ����
				Intent intent = new Intent(getApplicationContext(),
						SubActivityNEB.class);

				easyTracker.send(MapBuilder.createEvent("����-�����ͽ�Ʈ�󺼵�", // Event
																		// category
																		// (required)
						"button_press", // Event action (required)
						"play_button", // Event label
						null) // Event value
						.build());
				// ��Ƽ��Ƽ�� ����ֵ��� startActivityForResult() �޼ҵ带 ȣ���մϴ�.
				startActivity(intent);
			}

		});

		Button fontTypeR = (Button) findViewById(R.id.btn11); // �����ư

		fontTypeR.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO �ڵ� ������ �޼ҵ� ����
				Intent intent = new Intent(getApplicationContext(),
						SubActivityR.class);

				easyTracker.send(MapBuilder.createEvent("����-�κ�Ʈ", // Event
																	// category
																	// (required)
						"button_press", // Event action (required)
						"play_button", // Event label
						null) // Event value
						.build());
				// ��Ƽ��Ƽ�� ����ֵ��� startActivityForResult() �޼ҵ带 ȣ���մϴ�.
				startActivity(intent);
			}

		});

	}

	private class ClickHandler implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO �ڵ� ������ �޼ҵ� ����
			try {
				String myFontContent = edittxtContent.getText().toString();// �Է��ѳ���

				Intent myFontActivity = new Intent(getApplicationContext(),
						CsFontActivity.class);
				
				myFontActivity.putExtra("fontContent", myFontContent); // ����
				myFontActivity.putExtra("fontName", selItem); // ��Ʈ��
				myFontActivity.putExtra("fontSize", selItemSize); // ��Ʈ������

				// ��Ƽ��Ƽ�� ����ֵ��� startActivityForResult() �޼ҵ带 ȣ���մϴ�.
				startActivity(myFontActivity);

				/*Toast.makeText(
						getBaseContext(),
						myFontContent + "��������" + selItem + "�����߰�" + selItemSize
								+ "������� �����ߴٰ� ��Ʊ���", Toast.LENGTH_LONG).show();*/
				
/*				 �˾����� ���� �κ�*/
				
				
				

			} catch (Exception ex) {
				easyTracker.send(MapBuilder.createEvent("����-����",
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
	 * ���ο� ��Ƽ��Ƽ���� ���ƿ� �� �ڵ� ȣ��Ǵ� �޼ҵ�
	 */
	// protected void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// super.onActivityResult(requestCode, resultCode, data);
	//
	// if (requestCode == REQUEST_CODE_ANOTHER) {
	// Toast toast = Toast.makeText(getBaseContext(),
	// "onActivityResult() �޼ҵ尡 ȣ���. ��û�ڵ� : " + requestCode
	// + ", ����ڵ� : " + resultCode, Toast.LENGTH_LONG);
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
		easyTracker.set(Fields.SCREEN_NAME, "����������");
		easyTracker.send(MapBuilder.createAppView().build());
	}

	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this); // Add this method.
	}

}
