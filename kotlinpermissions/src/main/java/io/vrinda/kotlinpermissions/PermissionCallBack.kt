package io.vrinda.kotlinpermissions


interface PermissionCallBack {

    fun permissionGranted() {
    }

    fun permissionDenied() {
    }
}