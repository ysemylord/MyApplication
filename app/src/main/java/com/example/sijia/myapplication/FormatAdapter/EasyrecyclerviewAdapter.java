package com.example.sijia.myapplication.FormatAdapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sijia.myapplication.R;
import com.example.sijia.myapplication.model.Person;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by Administrator on 2016/4/6.
 */
public class EasyrecyclerviewAdapter extends RecyclerArrayAdapter<Person> {
    public EasyrecyclerviewAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup viewGroup, int i) {
        return new PersonViewHolder(viewGroup);
    }
}

class PersonViewHolder extends BaseViewHolder<Person> {
    private TextView mTv_name;

    public PersonViewHolder(ViewGroup parent) {
        super(parent, R.layout.recycler_item);
        mTv_name = $(R.id.text_view);

    }


    @Override
    public void setData( Person person) {
        mTv_name.setText(person.getName());

    }

}

