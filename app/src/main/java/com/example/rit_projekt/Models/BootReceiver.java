package com.example.rit_projekt.Models;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Booting completed",Toast.LENGTH_LONG).show();
    }
}
