package com.example.tacademy.mydialogpractice;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAlertDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Alert Dialog");
        builder.setMessage("Dialog test...");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Yes clicked...", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Cancel clicked...", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "No clicked...", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }

    String[] items = {"item1", "item2", "item3", "item4"};

    public void onListDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("List Dialog");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "item : " + items[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    int selectPosition;

    public void onSingleDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Single Dialog");
        selectPosition = 1;
        builder.setSingleChoiceItems(items, selectPosition, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectPosition = which;
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Choice : " + items[selectPosition], Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    boolean[] selectionArray = new boolean[items.length];

    public void onMultipleDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Multiple Dialog");
        selectionArray[0] = true;
        selectionArray[3] = true;
        builder.setMultiChoiceItems(items, selectionArray, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                selectionArray[which] = isChecked;
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < selectionArray.length; i++) {
                    if (selectionArray[i]) {
                        sb.append(items[i]).append(", ");
                    }
                }
                Toast.makeText(MainActivity.this, "Choice : " + sb, Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    Handler mHandler = new Handler(Looper.getMainLooper());

    public void onProgressDialog(View view) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setIcon(android.R.drawable.ic_dialog_info);
        dialog.setTitle("Downloading...");
        dialog.setMessage("File...");
        dialog.show();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 2000);
    }

    public void onHorizontalProgress(View view) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setIcon(android.R.drawable.ic_dialog_info);
        dialog.setTitle("Downloading...");
        dialog.setMessage("File...");
        dialog.setMax(150);
        dialog.show();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int progress = dialog.getProgress();
                progress += 10;
                dialog.setProgress(progress);

                if (progress < dialog.getMax()) {
                    dialog.setSecondaryProgress(progress + 10);
                } else {
                    dialog.setSecondaryProgress(dialog.getMax());
                }

                if (progress < dialog.getMax()) {
                    mHandler.postDelayed(this, 200);
                } else {
                    dialog.dismiss();
                }
            }
        }, 200);
    }
}
