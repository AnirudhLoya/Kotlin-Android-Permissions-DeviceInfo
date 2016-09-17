package io.vrinda.kotlinpermissions

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

abstract class PermissionsActivity : AppCompatActivity(), PermissionCallBack {


    private val REQUEST_CAMERA = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_permissions)
    }


     fun requestCameraPermission(permissionCallback: PermissionCallBack) {
        toast("CAMERA permission has NOT been granted. Requesting permission.")

        // BEGIN_INCLUDE(camera_permission_request)
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA)) {
            toast("Displaying camera permission rationale to provide additional context.")
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.CAMERA),
                    REQUEST_CAMERA)

        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),
                    REQUEST_CAMERA)
        }
    }

    fun AppCompatActivity.toast(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        if (requestCode == REQUEST_CAMERA) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Camera permission has been granted, preview can be displayed
                toast("CAMERA permission has now been granted. Showing preview.")
                permissionGranted()
            } else {
                toast("CAMERA permission was NOT granted.")

                permissionDenied()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}
