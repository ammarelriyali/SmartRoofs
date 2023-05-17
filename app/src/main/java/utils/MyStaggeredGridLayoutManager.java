package utils;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class MyStaggeredGridLayoutManager extends StaggeredGridLayoutManager {
    public MyStaggeredGridLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    @Override
    public boolean canScrollHorizontally() {
        return false;
    }

}
