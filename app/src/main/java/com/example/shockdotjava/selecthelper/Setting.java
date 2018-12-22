package com.example.shockdotjava.selecthelper;

import java.util.ArrayList;

public class Setting
{
    private ArrayList<Selection> setting;
    private ArrayList<Integer> weight;
    public final static int NUM_OF_TYPES = 3;

    public Setting()
    {
        setting = new ArrayList<Selection>(NUM_OF_TYPES);
        this.weight = new ArrayList<Integer>(NUM_OF_TYPES);
        for(int i = 0; i < NUM_OF_TYPES; i++)
        {
            Selection temp = new Selection("Unset");
            setting.add(temp);
            this.weight.add(0);
        }
    }

    public Selection getSettingByType(String Type)
    {
        for(int i = 0; i < NUM_OF_TYPES; i++)
        {
            if(this.setting.get(i).SelectionName().equals(Type))
                return this.setting.get(i);
        }
        return null;
    }

    public void setSettingByType(String type, Selection setting, int weight)
    {
        for(int i = 0; i < NUM_OF_TYPES; i++)
        {
            if(this.setting.get(i).SelectionName().equals(type))
            {
                this.setting.set(i, setting);
                this.weight.set(i, weight);
                return;
            }
        }

        for(int i = 0; i < NUM_OF_TYPES; i++)
        {
            if(this.setting.get(i).SelectionName().equals("Unset"))
            {
                this.setting.set(i, setting);
                this.weight.set(i, weight);
                return;
            }
        }
    }
}
