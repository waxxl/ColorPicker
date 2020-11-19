package com.story.creator.picker.util;

import com.story.creator.picker.model.bean.ColorValue;
import com.story.creator.picker.model.bean.PaletteModules;
import com.story.creator.picker.model.database.RealmData;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmResults;

public class Util {

    public static void save(boolean isFavorite, String name, RealmList<ColorValue> list) {
        RealmData realmData = new RealmData();
        realmData.insertPalette(isFavorite, name, list);
    }

    public static ArrayList<PaletteModules> queryAll() {
        RealmData realmData = new RealmData();
        RealmResults<PaletteModules> results = realmData.findAll();

        ArrayList<PaletteModules> arrList = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            arrList.add(results.get(i));
        }
        return arrList;
    }

    public static ArrayList<PaletteModules> queryAllFavorite() {
        RealmData realmData = new RealmData();
        RealmResults<PaletteModules> results = realmData.findAll();

        ArrayList<PaletteModules> arrList = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            if(results.get(i).isFavorite()) {
                arrList.add(results.get(i));
            }
        }
        return arrList;
    }

    public static RealmResults<PaletteModules> findAll() {
        RealmData realmData = new RealmData();
        RealmResults<PaletteModules> results = realmData.findAll();
        return results;
    }

    public static int isHexInt(String value) {
        boolean flag = true;
        int x = -1;
        try {
            x = Integer.parseInt(value, 16);
        } catch (Exception e) {
            x = -1;
            flag = false;
        }
//        for (int i = 0; i < value.length(); i++) {
//
//        }

        return x;
    }
}
