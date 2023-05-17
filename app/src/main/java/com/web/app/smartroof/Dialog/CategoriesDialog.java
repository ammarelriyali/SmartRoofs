package com.web.app.smartroof.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.web.app.smartroof.API.Api;
import com.web.app.smartroof.API.Model.Category;
import com.web.app.smartroof.API.RetrofitBuilder;
import com.web.app.smartroof.Adapter.AdapterRecyclerviewCycleHome;
import com.web.app.smartroof.R;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND;

public class CategoriesDialog extends BottomSheetDialogFragment {

    RecyclerView recyclerViewCycleHome;
    ImageView imageView;
    Api api;
    ArrayList<Category> categories ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.bottom_sheet_categories, container, false);
        imageView=root.findViewById(R.id.image_view_finish_dialog);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        recyclerViewCycleHome = root.findViewById(R.id.categories_list);
        api= RetrofitBuilder.getRetrofitInstance().create(Api.class);
        Call< ArrayList<Category> > call = api.getCategory();
        call.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                categories=response.body();
                AdapterRecyclerviewCycleHome adapterRecyclerviewCycleHome = new AdapterRecyclerviewCycleHome(getContext(),categories);
                recyclerViewCycleHome.setAdapter(adapterRecyclerviewCycleHome);
                recyclerViewCycleHome.setLayoutManager(new GridLayoutManager(getContext(),3));
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {


            }
        });
        return root;
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialogInterface;
                setupSheet(bottomSheetDialog);
            }
        });

        return dialog;
    }

    private void setupSheet(final BottomSheetDialog bottomSheetDialog) {
        FrameLayout bottomSheet = (FrameLayout) bottomSheetDialog.findViewById(R.id.design_bottom_sheet);
        BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
        ViewGroup.LayoutParams layoutParams = bottomSheet.getLayoutParams();

        int windowHeight = getWindowHeight();
        if (layoutParams != null) {
            layoutParams.height = windowHeight;
        }
        bottomSheet.setLayoutParams(layoutParams);
        behavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);

        behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (BottomSheetBehavior.STATE_EXPANDED == newState) {
                    bottomSheetDialog.getWindow().clearFlags(FLAG_DIM_BEHIND);

                }

                if (BottomSheetBehavior.STATE_COLLAPSED == newState) {
                    bottomSheetDialog.getWindow().addFlags(FLAG_DIM_BEHIND);
                }

                if (BottomSheetBehavior.STATE_HIDDEN == newState) {
                    dismiss();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    private int getWindowHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }
}
