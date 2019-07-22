package com.txm.topcodes.animationpracticeapplication.activity;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.txm.topcodes.animationpracticeapplication.R;
import com.txm.topcodes.animationpracticeapplication.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Tangxianming on 2019/3/11.
 * 布局动画之LayoutAnimation动画
 */
public class LayoutAnimationActivity extends BaseActivity {
    RecyclerView rcvContent;
    Button btnAdd;
    StringAdapter stringAdapter;
    List<String> strings = new ArrayList<>();

    public static void start(Context context) {
        Intent starter = new Intent(context, LayoutAnimationActivity.class);
        context.startActivity(starter);
    }

    @Override
    public Object initContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_layout_animation;
    }

    @Override
    public void initListener() {
        rcvContent = findViewById(R.id.rcvContent);
        btnAdd = findViewById(R.id.btnAdd);
        rcvContent.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < 100; i++) {
            strings.add("ITEM_" + i);
        }
        stringAdapter = new StringAdapter(strings);
        rcvContent.setAdapter(stringAdapter);
    }

    @Override
    public void initdata() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 100; i++) {
                    strings.add("ITEM_" + i);
                }
                stringAdapter.notifyDataSetChanged();
            }
        });
    }


    private class StringAdapter extends RecyclerView.Adapter<StringAdapter.MyViewHolder> {

        List<String> strings;

        protected StringAdapter(List<String> strings) {
            this.strings = strings;
        }


        @NonNull
        @Override
        public StringAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = View.inflate(parent.getContext(), android.R.layout.simple_list_item_1, null);
            MyViewHolder myViewHolder = new MyViewHolder(v);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull StringAdapter.MyViewHolder holder, int position) {
            holder.bindTo(strings.get(position));
        }

        @Override
        public int getItemCount() {
            return strings.size();
        }


        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView textView;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(android.R.id.text1);
            }


            public void bindTo(String s) {
                textView.setText(s);
            }

        }
    }

}
