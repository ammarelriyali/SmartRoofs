package utils;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

public class MyLinearLayoutManager extends LinearLayoutManager {
    public MyLinearLayoutManager(Context context) {
        super(context);
    }

    @Override
    public boolean canScrollVertically() {
        return false;

    }

    @Override
    public boolean canScrollHorizontally() {
        return false;
    }
}
