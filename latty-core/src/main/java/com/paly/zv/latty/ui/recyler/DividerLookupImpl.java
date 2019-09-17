package com.paly.zv.latty.ui.recyler;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

public class DividerLookupImpl implements DividerItemDecoration.DividerLookup {

    private final int COLOR;
    private final int SIZE;

    public DividerLookupImpl(int color, int size) {
        COLOR = color;
        SIZE = size;
    }


    @Override
    public Divider getVerticalDivider(int position) {
        return new Divider.Builder().color(COLOR).size(SIZE).build();
    }

    @Override
    public Divider getHorizontalDivider(int position) {
        return new Divider.Builder().color(COLOR).size(SIZE).build();

    }
}
