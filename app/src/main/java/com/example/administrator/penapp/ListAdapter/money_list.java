package com.example.administrator.penapp.ListAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.penapp.Entityclass.Money;
import com.example.administrator.penapp.R;

import java.util.ArrayList;
import java.util.List;

public class money_list extends BaseAdapter {
    List<Money> golden_list;
    Context mcontext;
    TextView text_sname;
    TextView text_symbol;
    TextView text_sg_states;
    TextView text_per_nav;
    Button bt_nav_a;


    public money_list(List<Money>money_list_item,Context context)
    {
        golden_list=money_list_item;
        mcontext=context;

    }
    @Override
    public int getCount() {
        return golden_list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view1;
        if(convertView==null)
        {
            view1=View.inflate(mcontext,R.layout.money_list_item,null);

        }
        else
        {
            view1=convertView;
        }
       text_sname=(TextView)view1.findViewById(R.id.money_item_sname);
        text_per_nav=(TextView)view1.findViewById(R.id.money_per_nav);
        text_sg_states=(TextView)view1.findViewById(R.id.money_sg_states);
        text_symbol=(TextView)view1.findViewById(R.id.money_symbol);
        bt_nav_a=(Button)view1.findViewById(R.id.money_nav_a);
        Money money=golden_list.get(position);
        text_sname.setText(money.getSname());
        text_symbol.setText(money.getSymbol());
        text_sg_states.setText(money.getSg_states());
        bt_nav_a.setText(money.getNav_a());
        return view1;
    }
}
