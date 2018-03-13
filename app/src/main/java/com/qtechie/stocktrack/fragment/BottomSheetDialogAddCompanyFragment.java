package com.qtechie.stocktrack.fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.qtechie.stocktrack.R;
import com.qtechie.stocktrack.model.CompanyModel;
import com.qtechie.stocktrack.utils.PrefUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BottomSheetDialogAddCompanyFragment extends BottomSheetDialogFragment{
    @BindView(R.id.dialog_eT_company_name)
    TextInputEditText eT_company_name;
    @BindView(R.id.dialog_eT_day_low)
    TextInputEditText eT_day_low;
    @BindView(R.id.dialog_eT_day_high)
    TextInputEditText eT_day_high;
    @BindView(R.id.dialog_eT_week_low)
    TextInputEditText eT_week_low;
    @BindView(R.id.dialog_eT_week_high)
    TextInputEditText eT_week_high;
    @BindView(R.id.dialog_eT_month_low)
    TextInputEditText eT_month_low;
    @BindView(R.id.dialog_eT_month_high)
    TextInputEditText eT_month_high;
    @BindView(R.id.dialog_btn_okay)
    Button btn_okay;

    OnDataAddedListener listener;
    PrefUtils prefUtils = PrefUtils.getInstance();

    public BottomSheetDialogAddCompanyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_bottom_sheet_dialog_add_company, container, false);
        ButterKnife.bind(this, view);
        listener= (OnDataAddedListener) getActivity();
        return view;
    }

    @OnClick(R.id.dialog_btn_okay)
    public void save_to_prefs() {
        //TODO:save to shared pref
        CompanyModel model = new CompanyModel(eT_company_name.getText().toString(),
                eT_day_low.getText().toString(), eT_day_high.getText().toString(),
                eT_week_low.getText().toString(), eT_week_high.getText().toString(),
                eT_month_low.getText().toString(), eT_month_high.getText().toString());

        if (prefUtils.add_PrefData(model) != 0) {
            Toast.makeText(getContext(), "Saved Successfully. " + prefUtils.getList_AllPrefData().size(), Toast.LENGTH_SHORT).show();
            this.dismiss();
            listener.onDataAdditionCompleted(model);
        } else
            Toast.makeText(getContext(), "Company already exists", Toast.LENGTH_SHORT).show();

    }
    public interface OnDataAddedListener{
        public void onDataAdditionCompleted(CompanyModel model);
    }
}
