package com.example.toptativa2.Dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;

public class InfoDialog {
    private AlertDialog dialog;
    private DialogInterface.OnClickListener DialogListenerClick;

    public InfoDialog(Context c, String title, String message, int ico){
        dialog = new AlertDialog.Builder(c).create();
        dialog.setIcon(ico);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }

    public InfoDialog(Context c,String title,String message,Drawable ico){
        dialog = new AlertDialog.Builder(c).create();
        dialog.setIcon(ico);
        dialog.setTitle(title);
        dialog.setMessage(message);
    }

    public InfoDialog(Context c,String title,String message){
        dialog = new AlertDialog.Builder(c).create();
        dialog.setTitle(title);
        dialog.setMessage(message);
    }

    public void OkButton(String txt, DialogInterface.OnClickListener listener){
        DialogListenerClick = listener;
        dialog.setButton(txt,DialogListenerClick);
    }

    public void make(){
        dialog.show();
    }

}
