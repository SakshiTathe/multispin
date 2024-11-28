package com.example.practiceactivity;

import android.app.AlertDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class MultiSelectSpinnerAdapter {
    private Context context;
    private List<String> itemList;
    private boolean[] selectedItems;
    private MultiSelectListener listener;

    public MultiSelectSpinnerAdapter(Context context, List<String> itemList, MultiSelectListener listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener = listener;
        this.selectedItems = new boolean[itemList.size()];
    }

    public void showMultiSelectDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select Items");

        builder.setMultiChoiceItems(
                itemList.toArray(new String[0]),
                selectedItems,
                (dialog, which, isChecked) -> selectedItems[which] = isChecked
        );

        builder.setPositiveButton("OK", (dialog, which) -> {
            List<String> selected = new ArrayList<>();
            for (int i = 0; i < itemList.size(); i++) {
                if (selectedItems[i]) {
                    selected.add(itemList.get(i));
                }
            }
            listener.onItemsSelected(selected);
        });

        builder.setNegativeButton("Cancel", null);

        builder.show();
    }

    public interface MultiSelectListener {
        void onItemsSelected(List<String> selectedItems);
    }
}
