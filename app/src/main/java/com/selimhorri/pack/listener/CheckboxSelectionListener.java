package com.selimhorri.pack.listener;

import java.util.List;

@FunctionalInterface
public interface CheckboxSelectionListener<T> {

    void onSelect(final List<T> list);

}
