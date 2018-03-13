package com.qtechie.stocktrack.adapter;

import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.qtechie.stocktrack.R;
import com.qtechie.stocktrack.activity.MainActivity;
import com.qtechie.stocktrack.model.CompanyModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 06-01-2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private RecyclerView rv = null;
    private List<CompanyModel> list;
    int selection_id;

    public RecyclerViewAdapter(List<CompanyModel> list, int selection_id) {
        this.list = list;
        this.selection_id = selection_id;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        if (rv == null && parent instanceof RecyclerView)
            rv = (RecyclerView) parent;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CompanyModel model = list.get(position);
        holder.id = model.getId();
        holder.tV_name.setText(model.getCompany_name());

        String l_Price,h_Price;
        switch (selection_id) {
            case R.id.navigation_day:
                l_Price=model.getDay_lowPrice();
                h_Price=model.getDay_highPrice();
                break;
            case R.id.navigation_week:
                l_Price=model.getWeek_lowPrice();
                h_Price=model.getWeek_highPrice();
                break;
            case R.id.navigation_month:
                l_Price=model.getMonth_lowPrice();
                h_Price=model.getMonth_highPrice();
                break;
                default:
                    l_Price="NIL";
                    h_Price="NIL";
        }
        holder.tV_low_price.setText(l_Price);
        holder.tV_high_price.setText(h_Price);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open bottom sheet
                View view = LayoutInflater.from(MainActivity.context).inflate(R.layout.frag_bottom_sheet_dialog_add_company, null, false);
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.context);
                bottomSheetDialog.setContentView(view);
                TextView name = (TextView) view.findViewById(R.id.dialog_eT_company_name);
                name.setText(model.getCompany_name());
                name.setEnabled(false);

                TextView lP = (TextView) view.findViewById(R.id.dialog_eT_day_low);
                lP.setText(model.getDay_lowPrice());
                lP.setEnabled(false);

                TextView hP = (TextView) view.findViewById(R.id.dialog_eT_day_high);
                hP.setText(model.getDay_highPrice());
                hP.setEnabled(false);

                TextView wL = (TextView) view.findViewById(R.id.dialog_eT_week_low);
                wL.setText(model.getWeek_lowPrice());
                wL.setEnabled(false);

                TextView wH = (TextView) view.findViewById(R.id.dialog_eT_week_high);
                wH.setText(model.getWeek_highPrice());
                wH.setEnabled(false);

                TextView mL = (TextView) view.findViewById(R.id.dialog_eT_month_low);
                mL.setText(model.getMonth_lowPrice());
                mL.setEnabled(false);

                TextView mH = (TextView) view.findViewById(R.id.dialog_eT_month_high);
                mH.setText(model.getMonth_highPrice());
                mH.setEnabled(false);

                ((Button) view.findViewById(R.id.dialog_btn_okay)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {

        return list.size();
    }


    public void addItem(CompanyModel model) {
        for (CompanyModel cm :
                list) {
            if (cm.getId() == model.getId())
                return;
        }
        list.add(model);
        notifyItemInserted(list.size());
    }

    public void deleteItem(CompanyModel model) {
        CompanyModel cm;
        for (int i = 0; i < list.size(); i++) {
            cm = list.get(i);
            if (cm.getId() == model.getId()) {
                list.remove(cm);
                notifyItemRemoved(i);
            }
        }
    }

    public void restoreDeletedItem(CompanyModel item, int position) {
        list.add(position, item);
        // notify the item added by position
        // to perform recycler view animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemInserted(position);
        rv.scrollToPosition(position);//allow auto-scroll to item at pos
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tV_name)
        TextView tV_name;
        @BindView(R.id.tV_low_price)
        TextView tV_low_price;
        @BindView(R.id.tV_high_price)
        TextView tV_high_price;

        int id;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
