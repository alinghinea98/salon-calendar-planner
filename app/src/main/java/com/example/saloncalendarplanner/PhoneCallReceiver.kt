package com.example.saloncalendarplanner

import android.Manifest
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.telephony.TelephonyManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.saloncalendarplanner.dashboard.DashboardActivity

class PhoneCallReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("PhoneCallReceiver", "Received phone state changed event")
        if (intent?.action == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
                if (state == TelephonyManager.EXTRA_STATE_IDLE) {
                    val dashboardIntent = Intent(context, DashboardActivity::class.java)
                    dashboardIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(dashboardIntent)
                }
            } else {
                if (context is Activity) {
                    context.finishAffinity()
                }
            }
        }
    }
}
