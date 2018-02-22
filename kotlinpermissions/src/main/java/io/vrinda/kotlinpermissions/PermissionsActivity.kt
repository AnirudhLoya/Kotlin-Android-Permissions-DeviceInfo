package io.vrinda.kotlinpermissions

import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast

abstract class PermissionsActivity : AppCompatActivity() {

    private val REQUEST_PERMISSION = 1111
    private val NEEDED_PERMISSIONS = 2222
    var pCallback: PermissionCallBack? = null
    var permissionsNeed: MutableList<String> = mutableListOf()

    fun requestPermissions(arrays: Array<String>, permissionCallback: PermissionCallBack) {
        permissionsNeed.clear()
        pCallback = permissionCallback
        for (permission in arrays) {
            if (ActivityCompat.checkSelfPermission(applicationContext, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsNeed.add(permission)
            }
        }
        if (permissionsNeed.size > 0) {
            Log.v("request", "permissions")
            reuestNeededPermission(permissionsNeed)
        } else {
            toast("Permissions Granted")
            pCallback?.permissionGranted()
        }
    }

    fun requestPermissions(permission: String, permissionCallback: PermissionCallBack) {
        pCallback = permissionCallback
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(permission),
                    REQUEST_PERMISSION)
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(permission),
                    REQUEST_PERMISSION)
        }
    }

    private fun reuestNeededPermission(permissionsNeed: MutableList<String>) {
        // if (ActivityCompat.shouldShowRequestPermissionRationale(this@PermissionsActivity,permissionsNeed.toTypedArray()))
        ActivityCompat.requestPermissions(this@PermissionsActivity, permissionsNeed.toTypedArray(), NEEDED_PERMISSIONS)
    }

    fun AppCompatActivity.toast(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        if (grantResults.isNotEmpty()) {
            Log.v("resultss", "" + grantResults[0] + grantResults.toString())
            if (requestCode == REQUEST_PERMISSION) {
                if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Camera permission has been granted, preview can be displayed
                    pCallback?.permissionGranted()
                } else {
                    pCallback?.permissionDenied()
                }
            } else if (requestCode == NEEDED_PERMISSIONS) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pCallback?.permissionGranted()
                } else {
                    pCallback?.permissionDenied()
                }

            } else {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }
    }
}
