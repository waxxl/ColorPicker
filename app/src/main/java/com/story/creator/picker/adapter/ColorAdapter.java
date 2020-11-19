package com.story.creator.picker.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.story.creator.picker.BR;
import com.story.creator.picker.R;
import com.story.creator.picker.constants.Cons;
import com.story.creator.picker.model.bean.ColorItem;
import com.story.creator.picker.model.bean.ColorValue;
import com.story.creator.picker.view.ColorView;

import java.util.ArrayList;

import io.realm.RealmList;

import static com.story.creator.picker.constants.ConsKt.getRandomColorItem;

public class ColorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_CUSTOM = 1;
    private static final int TYPE_HAIL = 2;

    private int mSelectPosition = -1;

    public void setSelectColorItem(ColorItem colorItem) throws Exception {
        if(colorData.size() > 0) {
            if(mSelectPosition == -1) {
                mSelectPosition = 0;
            }
            colorData.get(mSelectPosition).setColorInt(colorItem.getColor());
            notifyDataSetChanged();
        } else {
            throw new Exception("setSelectColorItem colorData.size() <= 0");
        }
    }

    public interface OnAddClickListener {
        void addClick();

        void onItemClick(ColorItem colorItem);
    }

    private Context context;
    private OnAddClickListener onAddClickListener;
    private ArrayList<ColorItem> colorData;

    public ColorAdapter(Context context) {
        this.context = context;
    }

    public void setColorData(ArrayList<ColorItem> data) {

        this.colorData =  data;
    }

    public void addColorData(ColorItem data) {
        if (colorData != null) {
            colorData.add(data);
        }
    }

    public void setOnAddClickListener(OnAddClickListener listener) {
        onAddClickListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HAIL) {
            ViewDataBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.color_hail, parent, false);
            //View view = View.inflate(parent.getContext(), R.layout.color_hail, null);
            return new BaseViewHolder<>(dataBinding);
        }

        ViewDataBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.color_item, parent, false);
        return new ColorViewHolder<>(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_CUSTOM) {
            ColorViewHolder colorViewHolder = (ColorViewHolder) holder;
            ViewDataBinding binding = colorViewHolder.getDataBinding();
            binding.setVariable(BR.is_select, colorData.get(position).isSelect());
            binding.setVariable(BR.color, colorData.get(position).getColor());
            binding.setVariable(BR.adapter, this);
            binding.setVariable(BR.position, position);
            binding.setVariable(BR.colorItem, colorData.get(position));
            binding.executePendingBindings();

            ColorItem colorItem = colorData.get(position);
            colorViewHolder.colorView.setSelect(colorItem.isSelect());
            colorViewHolder.colorView.setArbg(0xff, colorItem.getColorR(), colorItem.getColorG(), colorItem.getColorB());
            colorViewHolder.colorValue.setText("#" + Integer.toHexString(colorData.get(position).getColor()));
        } else if (holder.getItemViewType() == TYPE_HAIL) {
            BaseViewHolder baseViewHolder = (BaseViewHolder) holder;
            ViewDataBinding binding = baseViewHolder.getDataBinding();
            binding.setVariable(BR.position, position);
            binding.setVariable(BR.adapter, this);
            binding.executePendingBindings();

//            hailViewHolder.hailImg.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(onAddClickListener != null) {
//                        onAddClickListener.addClick();
//                    }
//                }
//            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position >= colorData.size()) {
            return TYPE_HAIL;
        }
        return TYPE_CUSTOM;
    }

    @Override
    public int getItemCount() {
        return colorData.size() + 1;
    }

    public RealmList<ColorValue> getColorList() {
        RealmList<ColorValue> list = new RealmList<>();
        ColorValue colorValue;
        for (int i = 0; i < colorData.size(); i++) {
            colorValue = new ColorValue();
            colorValue.setValue(colorData.get(i).getColor());
            list.add(colorValue);
        }
        return list;
    }

    public class ColorViewHolder<B extends ViewDataBinding> extends BaseViewHolder {
        private B binding;
        public TextView colorValue;
        public ColorView colorView;

        public ColorViewHolder(B binding) {
            super(binding);
            this.binding = binding;
            colorValue = binding.getRoot().findViewById(R.id.color_value);
            colorView = binding.getRoot().findViewById(R.id.color_view);
        }
    }

    public class HailViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {
        public ImageView hailImg;

        public HailViewHolder(@NonNull View itemView) {
            super(itemView);
            hailImg = itemView.findViewById(R.id.hail_img);
        }
    }

    public class BaseViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {
        private B binding;

        public BaseViewHolder(B binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ViewDataBinding getDataBinding() {
            return binding;
        }
    }

    public void onItemClick(Object obj, int position) {
        if (obj != null) {
            if (obj instanceof ColorItem) {
                setAllSelect(false);
                ColorItem colorItem = (ColorItem) obj;
                colorItem.setSelect(true);
                mSelectPosition = position;

                if (onAddClickListener != null) {
                    onAddClickListener.onItemClick(colorData.get(position));
                }
            }
            notifyDataSetChanged();
        } else {
//            if (onAddClickListener != null) {
//                onAddClickListener.addClick();
//            }
            colorData.add(getRandomColorItem());
            notifyItemInserted(position);
            Handler ha = new Handler(Looper.getMainLooper());
            ha.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (onAddClickListener != null) {
                        onAddClickListener.addClick();
                    }
                    notifyDataSetChanged();
                }
            }, 200);
        }
    }

    private void setAllSelect(boolean isSelect) {
        for (ColorItem colorItem : colorData) {
            colorItem.setSelect(isSelect);
        }
    }
}
