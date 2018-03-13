package com.qtechie.stocktrack.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qtechie.stocktrack.activity.MainActivity;
import com.qtechie.stocktrack.model.CompanyModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 06-01-2018.
 */

public class PrefUtils {

    private static final String PREF_NAME = "Stock data";
    private static final String PREF_LIST_KEY = "Company list";
    private static final String PREF_LAST_ID_KEY = "last id";

    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor;

    private Gson gson = new Gson();
    private Type type = new TypeToken<List<CompanyModel>>() {
    }
            .getType();


    //    private List<CompanyModel> companyModelList = null;
//    private String companyModelList_as_str = null;
    private static PrefUtils _instance = null;
    private int last_id = 0;

    public static PrefUtils getInstance() {
        if (_instance == null)
            _instance = new PrefUtils();
        return _instance;
    }

    private PrefUtils() {
        if(MainActivity.context==null)
            return;
        sharedPreferences = MainActivity.context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        last_id = sharedPreferences.getInt("PREF_LAST_ID_KEY", 0);
    }

    public List<CompanyModel> getList_AllPrefData() {
        String companyModelList_as_str = sharedPreferences.getString(PREF_LIST_KEY, null);
        if (companyModelList_as_str == null)
            return null;

        return gson.fromJson(companyModelList_as_str, type);
    }

    public int add_PrefData(CompanyModel companyModel) {//one data at a time
        List<CompanyModel> companyModelList = _instance.getList_AllPrefData();
        if (companyModelList == null)
            companyModelList = new ArrayList<>();

        //skip insert if duplicate found
        for (CompanyModel model :
                companyModelList) {
            if (companyModel.getCompany_name().equalsIgnoreCase(model.getCompany_name()))
                return 0;
        }

        companyModel.setId(last_id + 1);
        companyModelList.add(companyModel);
        String companyModelList_as_str = gson.toJson(companyModelList);

        editor = sharedPreferences.edit();
        editor.putString(PREF_LIST_KEY, companyModelList_as_str);
        editor.putInt(PREF_LAST_ID_KEY, ++last_id);
        editor.apply();
        return companyModel.getId();
    }

    public int updatePrefData_List(CompanyModel companyModel) {//on the basis of id
        List<CompanyModel> companyModelList = _instance.getList_AllPrefData();
        if (companyModelList == null) return 0;
        for (CompanyModel model :
                companyModelList) {
            if (companyModel.getId() == model.getId()) {
                companyModelList.remove(model);
                companyModelList.add(companyModel);
                String companyModelList_as_str = gson.toJson(companyModelList);
                editor = sharedPreferences.edit();
                editor.putString(PREF_LIST_KEY, companyModelList_as_str);
                editor.apply();
                return model.getId();
            }
        }
        return 0;
    }

    public int deletePrefData_List(CompanyModel companyModel) {//on the basis of id
        List<CompanyModel> companyModelList = _instance.getList_AllPrefData();

        if (companyModelList == null) return 0;
        for (CompanyModel model :
                companyModelList) {
            if (companyModel.getId() == model.getId()) {
                companyModelList.remove(model);
                String companyModelList_as_str = gson.toJson(companyModelList);
                editor = sharedPreferences.edit();
                editor.putString(PREF_LIST_KEY, companyModelList_as_str);
                editor.apply();
                return model.getId();
            }
        }
        return 0;
    }

    public void deleteList_AllPrefData() {
        editor = sharedPreferences.edit();
        editor.putString(PREF_LIST_KEY, null);
        editor.putInt(PREF_LAST_ID_KEY, 0);
        editor.apply();
    }
}
