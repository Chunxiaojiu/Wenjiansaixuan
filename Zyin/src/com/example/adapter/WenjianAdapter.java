package com.example.adapter;

import java.util.List;

import com.example.zyin.R;
import com.example.zywenjian.wenjianmodel;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

public class WenjianAdapter extends Adapter<WenjianAdapter.MyViewHolder> {
	private Context context;
	List<wenjianmodel> list;
	private OnItemClickLitener mOnItemClickLitener;
	private SparseBooleanArray mCheckStates = new SparseBooleanArray();

	public WenjianAdapter(Context context, List<wenjianmodel> list) {
		this.context = context;
		this.list = list;
	}


	public interface OnItemClickLitener {
		void onItemClick(View view, int position);

		void onItemLongClick(View view, int position);
	}

	public void returntotalitem(List<wenjianmodel> data) {
		for (int i = 0; i < mCheckStates.size(); i++) {
			if (mCheckStates.valueAt(i)) {
				data.add(list.get(mCheckStates.keyAt(i)));
			}
		}
	}

	public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
		this.mOnItemClickLitener = mOnItemClickLitener;
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context)
				.inflate(R.layout.wenjian_item, parent, false));
		return holder;
	}

	@Override
	public void onBindViewHolder(final MyViewHolder holder, int position) {
		holder.cbx.setTag(position);
		holder.cbx
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						int pos = (int) buttonView.getTag();
						if (isChecked) {
							mCheckStates.put(pos, true);
							// do something
						} else {
							mCheckStates.delete(pos);
							// do something else
						}
					}
				});
		holder.cbx.setChecked(mCheckStates.get(position, false));
		holder.tv.setText(list.get(position).getName());
		switch (list.get(position).getType()) {
		case "application/msword":
			holder.img.setImageResource(R.drawable.word);
			break;
		case "application/pdf":
			holder.img.setImageResource(R.drawable.pdf);
			break;
		case "ppt":
			holder.img.setImageResource(R.drawable.ppt);
			break;
		default:
			break;
		}
		if (mOnItemClickLitener != null) {
			holder.itemView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					int pos = holder.getLayoutPosition();
					mOnItemClickLitener.onItemClick(holder.itemView, pos);
				}
			});

			holder.itemView.setOnLongClickListener(new OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					int pos = holder.getLayoutPosition();
					mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
					return false;
				}
			});
		}

	}

	@Override
	public int getItemCount() {
		return list.size();
	}

	class MyViewHolder extends ViewHolder {
		ImageView img;
		TextView tv;
		CheckBox cbx;

		public MyViewHolder(View view) {
			super(view);
			img = (ImageView) view.findViewById(R.id.img);
			tv = (TextView) view.findViewById(R.id.wenjian);
			cbx = (CheckBox) view.findViewById(R.id.checkBox1);
		}
	}

}
