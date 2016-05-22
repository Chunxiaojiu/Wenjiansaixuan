package com.example.zyin;

import java.util.ArrayList;
import java.util.List;

import com.example.Db.Dbwenjian;
import com.example.adapter.WenjianChioseAdapter;
import com.example.zywenjian.WenjianChioseModel;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class leftFragment extends Fragment {
	FloatingActionButton actionButton;
	private boolean wenjian = false;
	private RecyclerView Recyclerlist;
	private WenjianChioseAdapter adapter;
	private List<WenjianChioseModel> Chioselist;
	private Dbwenjian dbwenjian;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.dayin, container, false);
		Chioselist = new ArrayList<WenjianChioseModel>();
		actionButton = (FloatingActionButton) root.findViewById(R.id.flbton);
		Recyclerlist = (RecyclerView) root.findViewById(R.id.Recyclerlist);

		Recyclerlist.setLayoutManager(new LinearLayoutManager(getActivity()));
		Recyclerlist.setAdapter(adapter = new WenjianChioseAdapter(
				getActivity(), Chioselist));
		Recyclerlist.setItemAnimator(new DefaultItemAnimator());
		Recyclerlist.addItemDecoration(new DividerItemDecoration(getActivity(),
				DividerItemDecoration.VERTICAL_LIST));

		Bundle bundle = getArguments();
		if (bundle != null) {
			wenjian = getArguments().getBoolean("addwenjian");
			if (wenjian) {
				dbwenjian = new Dbwenjian(this.getActivity()
						.getApplicationContext());
				SQLiteDatabase dbqure = dbwenjian.getReadableDatabase();
				Cursor c = dbqure.query("WENJIAN", null, null, null, null,
						null, null);
				while (c.moveToNext()) {
					Log.i("new fra",
							c.getString(c.getColumnIndex("DATA"))
									+ c.getString(c.getColumnIndex("NAME"))
									+ c.getString(c.getColumnIndex("TYPE")));
					Chioselist.add(new WenjianChioseModel(c.getString(c
							.getColumnIndex("NAME")), c.getString(c
							.getColumnIndex("DATA")), c.getString(c
							.getColumnIndex("TYPE")), true, 1, false, 1));
				}
				adapter.notifyItemInserted(0);
			}
		}
		actionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), WenjianChiose.class);
				startActivity(intent);
			}
		});

		return root;
	}
}