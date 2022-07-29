package com.suryodaybank.jyotiassisted.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;

import com.suryodaybank.jyotiassisted.R;

public class ProgressDialog {

    private Dialog dialog;
    private ProgressBar progressBar;

    public ProgressDialog(Context context) {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progress_layout);
        dialog.setCancelable(false);
        if (dialog.getWindow() != null) {
            dialog.getWindow()
                    .setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressBar = dialog.findViewById(R.id.progressBar);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.getIndeterminateDrawable().setColorFilter(context.getResources().getColor(R.color.primaryColor), PorterDuff.Mode.MULTIPLY);
                new Handler().postDelayed(this, 300);
            }
        }, 0);

    }

    public void showDialog() {
        if (dialog != null && !dialog.isShowing())
            dialog.show();
    }

    public void hideDialog() {
        if (dialog != null && dialog.isShowing())
            dialog.cancel();
    }
}

