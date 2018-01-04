package io.vrinda.kotlinpermissions

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PermissionFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PermissionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
abstract class PermissionFragment : Fragment() {


    private val REQUEST_PERMISSION = 1111
    private val NEEDED_PERMISSIONS = 2222
    var pCallback: PermissionCallBack? = null;
    var permissionsNeed: MutableList<String> = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun AppCompatActivity.toast(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    fun requestPermissions(arrays: Array<String>, permissionCallback: PermissionCallBack) {
        permissionsNeed.clear()
        pCallback = permissionCallback
        for (permission in arrays) {
            if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsNeed.add(permission)
            }
        }
        if (permissionsNeed.size > 0) {
            Log.v("request", "permissions")
            reuestNeededPermission(permissionsNeed)
        } else {
            pCallback?.permissionGranted()
            // toast(activity,"Permissions Granted")
        }
    }

    fun requestPermissions(permission: String, permissionCallback: PermissionCallBack) {
        pCallback = permissionCallback
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            requestPermissions(arrayOf(permission),
                    REQUEST_PERMISSION)
        } else {
            requestPermissions(arrayOf(permission),
                    REQUEST_PERMISSION)
        }
    }

    fun reuestNeededPermission(permissionsNeed: MutableList<String>) {
        // if (ActivityCompat.shouldShowRequestPermissionRationale(this@PermissionsActivity,permissionsNeed.toTypedArray()))
        requestPermissions(permissionsNeed.toTypedArray(), NEEDED_PERMISSIONS)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
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