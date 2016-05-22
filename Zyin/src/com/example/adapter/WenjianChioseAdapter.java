package com.example.adapter;

import java.util.List;

import com.example.zyin.R;
import com.example.zywenjian.WenjianChioseModel;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class WenjianChioseAdapter extends Adapter<WenjianChioseAdapter.MyChioseHolder> {
	private Context context;
	private List<WenjianChioseModel> list;

	public WenjianChioseAdapter(Context context, List<WenjianChioseModel> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	public void onBindViewHolder(WenjianChioseAdapter arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public MyChioseHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		MyChioseHolder mcholder = new MyChioseHolder(LayoutInflater.from(
				context).inflate(R.layout.dayin_item, arg0, false));
		return mcholder;
	}

	class MyChioseHolder extends ViewHolder {
		ImageView img;
		TextView text;
		Switch switch1;
		Switch switch2;
		Button btndet;

		public MyChioseHolder(View view) {
			super(view);
			img = (ImageView) view.findViewById(R.id.tupiao);
			text = (TextView) view.findViewById(R.id.dayin_name);
			switch1 = (Switch) view.findViewById(R.id.switch1);
			switch2 = (Switch) view.findViewById(R.id.switch2);
			btndet = (Button) view.findViewById(R.id.shanchu);
		}
	}

	@Override
	public void onBindViewHolder(MyChioseHolder viewholder, int pos) {
		switch (list.get(pos).getType()) {
		case "application/msword":
			viewholder.img.setImageResource(R.drawable.word);
			break;
		case "application/pdf":
			viewholder.img.setImageResource(R.drawable.pdf);
			break;
		case "ppt":
			viewholder.img.setImageResource(R.drawable.ppt);
			break;
		default:
			break;
		}
		viewholder.text.setText(list.get(pos).getName());
		
	
	}
}
