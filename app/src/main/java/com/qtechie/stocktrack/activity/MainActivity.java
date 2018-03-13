package com.qtechie.stocktrack.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qtechie.stocktrack.R;
import com.qtechie.stocktrack.adapter.RecyclerViewAdapter;
import com.qtechie.stocktrack.fragment.BottomSheetDialogAddCompanyFragment;
import com.qtechie.stocktrack.model.CompanyModel;
import com.qtechie.stocktrack.utils.PrefUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements BottomSheetDialogAddCompanyFragment.OnDataAddedListener{

    //header
    @BindView(R.id.header_recyclerView)
    LinearLayout header_recyclerView;
    @BindView(R.id.tV_name)
    TextView tV_name;
    @BindView(R.id.tV_high_price)
    TextView tV_high_price;
    @BindView(R.id.tV_low_price)
    TextView tV_low_price;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    RecyclerViewAdapter adapter;
    PrefUtils prefUtils;
    public static Context context;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_day:
                case R.id.navigation_week:
                case R.id.navigation_month:
                    loadData(item.getItemId());
                    return true;
            }
            return false;
        }
    };

    private void loadData(int itemId) {

        prefUtils = PrefUtils.getInstance();
        List<CompanyModel> list = prefUtils.getList_AllPrefData();
        if (list == null) return;
        adapter = new RecyclerViewAdapter(list,itemId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        ButterKnife.bind(this);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadData(R.id.navigation_day);
    }

    @OnClick(R.id.fab)
    public void addItemData() {
        BottomSheetDialogAddCompanyFragment bottomSheetFragment = new BottomSheetDialogAddCompanyFragment();
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }

    @Override
    public void onDataAdditionCompleted(CompanyModel model) {
        adapter.addItem(model);
    }
}
