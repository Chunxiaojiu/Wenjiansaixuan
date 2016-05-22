package com.example.zyin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.Db.Dbwenjian;
import com.example.adapter.WenjianAdapter;
import com.example.adapter.WenjianAdapter.OnItemClickLitener;
import com.example.zywenjian.wenjianmodel;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class WenjianChiose extends ActionBarActivity {
	private static String volumeName = "external";
	private FloatingActionButton wenjianconfig;
	private RecyclerView mRecyclerView;
	private List<wenjianmodel> mDatas, dblist;
	private WenjianAdapter mAdapter;
	private Dbwenjian dbwenjian = new Dbwenjian(this);
	Handler getmsg = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				mAdapter.notifyItemInserted(0);
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wenjian);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarwenjian);
		// Title
		toolbar.setTitle("选择文件");
		mDatas = new ArrayList<wenjianmodel>();
		new Thread(new Runnable() {

			@Override
			public void run() {
				String path = "/storage/emulated/0";
				File[] files = new File(path).listFiles();
				SearchFile(files, mDatas);
				Message mg = new Message();
				mg.what = 1;
				getmsg.sendMessage(mg);
			}
		}).start();
		wenjianconfig = (FloatingActionButton) findViewById(R.id.wenjianconfig);
		mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.setAdapter(mAdapter = new WenjianAdapter(this, mDatas));
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
				DividerItemDecoration.VERTICAL_LIST));
		wenjianconfig.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dblist = new ArrayList<wenjianmodel>();
				mAdapter.returntotalitem(dblist);
				SQLiteDatabase dbwrite = dbwenjian.getWritableDatabase(); 
				for (int i = 0; i < dblist.size(); i++) {
					ContentValues cv = new ContentValues();
					cv.put("DATA", dblist.get(i).getData());
					cv.put("NAME", dblist.get(i).getName());
					cv.put("TYPE", dblist.get(i).getType());
					dbwrite.insert("WENJIAN", null, cv);
					Log.i("@@@@@@@@@@@@", dblist.get(i).getData()+dblist.get(i).getName()+dblist.get(i).getType());
				}
				dbwrite.close();
				Intent intent = new Intent(WenjianChiose.this,MainActivity.class);
				intent.putExtra("Choise", 1);
				startActivity(intent);
			}
		});
		mAdapter.setOnItemClickLitener(new OnItemClickLitener() {

			@Override
			public void onItemClick(View view, int position) {
				Toast.makeText(WenjianChiose.this, position + " click",
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onItemLongClick(View view, int position) {
				Toast.makeText(WenjianChiose.this, position + " long click",
						Toast.LENGTH_SHORT).show();
			}
		});

	}

	public void SearchFile(File[] files, List<wenjianmodel> list) {
		for (File file : files) {
			if (file.isDirectory())// 若为目录则递归查找
			{
				SearchFile(file.listFiles(), list);
			} else if (file.isFile()) {
				String data = file.getPath();
				String name = file.getName();
				if (data.endsWith(".doc") | data.endsWith(".docx"))// 查找指定扩展名的文件
				{
					Log.i("doc/docs", data);
					list.add(new wenjianmodel(name, data, "application/msword"));
				} else if (data.endsWith(".ppt") | data.endsWith(".pptx")) {
					Log.i("ppt", data);
					list.add(new wenjianmodel(name, data, "ppt"));
				} else if (data.endsWith(".pdf")) {
					Log.i("pdf", data);
					list.add(new wenjianmodel(name, data, "application/pdf"));
				}
			}
		}
	}
}
