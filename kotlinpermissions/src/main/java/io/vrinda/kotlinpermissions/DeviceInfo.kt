package io.vrinda.kotlinpermissions

import android.Manifest
import android.accounts.AccountManager
import android.annotation.TargetApi
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.view.MotionEvent
import android.webkit.WebView
import java.net.Inet4Address
import java.net.NetworkInterface
import java.util.*


public class DeviceInfo {
    companion object {
        @JvmStatic fun getAndroidID(context: Context): String? {
            var result: String? = null
            try {
                result = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            } catch (exception: Exception) {
                exception.printStackTrace();
            }
            return result
        }

        /**
         * Gets model.

         * @return the model
         */
        @JvmStatic fun getModel(): String {
            var result: String? = null
            try {
                result = Build.MODEL
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return handleIllegalCharacterInResult(result.toString())
        }

        @JvmStatic fun handleIllegalCharacterInResult(result: String): String {
            var result = result
            if (result.indexOf(" ") > 0) {
                result = result.replace(" ".toRegex(), "_")
            }
            return result
        }

        /**
         * Gets build brand.

         * @return the build brand
         */
        @JvmStatic fun getBuildBrand(): String {
            var result: String? = null
            try {
                result = Build.BRAND
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return handleIllegalCharacterInResult(result.toString())
        }

        /**
         * Gets build host.

         * @return the build host
         */
        @JvmStatic fun getBuildHost(): String {
            var result: String? = null
            try {
                result = Build.HOST
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets build tags.

         * @return the build tags
         */
        @JvmStatic fun getBuildTags(): String {
            var result: String? = null
            try {
                result = Build.TAGS
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets build time.

         * @return the build time
         */
        @JvmStatic fun getBuildTime(): Long {
            var result: Long = 0
            try {
                result = Build.TIME
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return result.toLong()
        }

        /**
         * Gets build user.

         * @return the build user
         */
        @JvmStatic fun getBuildUser(): String {
            var result: String? = null
            try {
                result = Build.USER
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        ///////////////////////
        @JvmStatic fun getBuildVersionRelease(): String {
            var result: String? = null
            try {
                result = Build.VERSION.RELEASE
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets screen display id.

         * @return the screen display id
         */
        //TODO getScreenDisplayID
        @JvmStatic fun getScreenDisplayID(context: Context): String {
            var result: String? = null
            try {
                val display = context.getSystemService(Context.WINDOW_SERVICE) as Display
                result = display.displayId.toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets build version codename.

         * @return the build version codename
         */
        @JvmStatic fun getBuildVersionCodename(): String {
            var result: String? = null
            try {
                result = Build.VERSION.CODENAME
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets build version incremental.

         * @return the build version incremental
         */
        @JvmStatic fun getBuildVersionIncremental(): String {
            var result: String? = null
            try {
                result = Build.VERSION.INCREMENTAL
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets build version sdk.

         * @return the build version sdk
         */
        @JvmStatic fun getBuildVersionSDK(): Int {
            var result = 0
            try {
                result = Build.VERSION.SDK_INT
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return result
        }

        /**
         * Gets build id.

         * @return the build id
         */
        @JvmStatic fun getBuildID(): String {
            var result: String? = null
            try {
                result = Build.ID
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Get supported abis string [ ].

         * @return the string [ ]
         */
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @JvmStatic fun getSupportedABIS(): Array<String> {
            var result: Array<String>? = arrayOf("-")
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    result = Build.SUPPORTED_ABIS
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.size == 0) {
                result = arrayOf("-")
            }
            return result
        }

        /**
         * Gets string supported abis.

         * @return the string supported abis
         */
        //TODO
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @JvmStatic fun getStringSupportedABIS(): String {
            var result: String? = null
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val supportedABIS = Build.SUPPORTED_ABIS

                    val supportedABIString = StringBuilder()
                    if (supportedABIS.size > 0) {
                        for (abis in supportedABIS) {
                            supportedABIString.append(abis).append("_")
                        }
                        supportedABIString.deleteCharAt(supportedABIString.lastIndexOf("_"))
                    } else {
                        supportedABIString.append("")
                    }
                    result = supportedABIString.toString()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return handleIllegalCharacterInResult(result.toString())
        }

        /**
         * Gets string supported 32 bit abis.

         * @return the string supported 32 bit abis
         */
        @JvmStatic fun getStringSupported32bitABIS(): String {
            var result: String? = null
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val supportedABIS = Build.SUPPORTED_32_BIT_ABIS

                    val supportedABIString = StringBuilder()
                    if (supportedABIS.size > 0) {
                        for (abis in supportedABIS) {
                            supportedABIString.append(abis).append("_")
                        }
                        supportedABIString.deleteCharAt(supportedABIString.lastIndexOf("_"))
                    } else {
                        supportedABIString.append("")
                    }

                    result = supportedABIString.toString()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }

            return handleIllegalCharacterInResult(result.toString())
        }

        /**
         * Gets string supported 64 bit abis.

         * @return the string supported 64 bit abis
         */
        @JvmStatic @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        fun getStringSupported64bitABIS(): String {
            var result: String? = null
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val supportedABIS = Build.SUPPORTED_64_BIT_ABIS

                    val supportedABIString = StringBuilder()
                    if (supportedABIS.size > 0) {
                        for (abis in supportedABIS) {
                            supportedABIString.append(abis).append("_")
                        }
                        supportedABIString.deleteCharAt(supportedABIString.lastIndexOf("_"))
                    } else {
                        supportedABIString.append("")
                    }
                    result = supportedABIString.toString()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return handleIllegalCharacterInResult("")
        }

        /**
         * Get supported 32 bit abis string [ ].

         * @return the string [ ]
         */
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @JvmStatic fun getSupported32bitABIS(): Array<String> {
            var result: Array<String>? = arrayOf("-")
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    result = Build.SUPPORTED_32_BIT_ABIS
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.size == 0) {
                result = arrayOf("-")
            }
            return result
        }

        /**
         * Get supported 64 bit abis string [ ].

         * @return the string [ ]
         */
        @JvmStatic @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        fun getSupported64bitABIS(): Array<String> {
            var result: Array<String>? = arrayOf("-")
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    result = Build.SUPPORTED_64_BIT_ABIS
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.size == 0) {
                result = arrayOf("-")
            }
            return result
        }

        /**
         * Gets manufacturer.

         * @return the manufacturer
         */
        @JvmStatic fun getManufacturer(): String {
            var result: String? = null
            try {
                result = Build.MANUFACTURER
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return handleIllegalCharacterInResult(result.toString())
        }

        /**
         * Gets resolution.

         * @return the resolution
         */
        //TODO
        @JvmStatic fun getResolution(context: Context): String {
            var result: String = ""
            try {

                val display = context.getSystemService(Context.WINDOW_SERVICE) as Display

                val metrics = DisplayMetrics()
                display.getMetrics(metrics)
                result = "${metrics.heightPixels} x ${metrics.widthPixels}"
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result.length == 0) {
                result = ""
            }
            return result
        }

        /**
         * Gets carrier.

         * @return the carrier
         */
        //TODO
        @JvmStatic fun getCarrier(context: Context): String {
            var result: String = ""
            try {
                val tm: TelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                if (tm != null && tm.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) {
                    result = tm.getNetworkOperatorName().toLowerCase(Locale.getDefault())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result.length == 0) {
                result = ""
            }
            return handleIllegalCharacterInResult(result.toString())
        }

        /**
         * Gets device.

         * @return the device
         */
        @JvmStatic fun getDevice(): String {
            var result: String? = null
            try {
                result = Build.DEVICE
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets bootloader.

         * @return the bootloader
         */
        @JvmStatic fun getBootloader(): String {
            var result: String? = null
            try {
                result = Build.BOOTLOADER
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets board.

         * @return the board
         */
        @JvmStatic fun getBoard(): String {
            var result: String? = ""
            try {
                result = Build.BOARD
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets display version.

         * @return the display version
         */
        @JvmStatic fun getDisplayVersion(): String {
            var result: String? = null
            try {
                result = Build.DISPLAY
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets language.

         * @return the language
         */
        @JvmStatic fun getLanguage(): String {
            var result: String? = null
            try {
                result = Locale.getDefault().language
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets country.

         * @return the country
         */
        @JvmStatic fun getCountry(context: Context): String {
            var result: String? = ""
            try {
                val tm: TelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                if (tm.getSimState() == TelephonyManager.SIM_STATE_READY) {
                    result = tm.getSimCountryIso().toLowerCase(Locale.getDefault())
                } else {
                    val locale = Locale.getDefault()
                    result = locale.country.toLowerCase(locale)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result?.length == 0) {
                result = ""
            }
            return handleIllegalCharacterInResult(result.toString())
        }

        /**
         * Gets network type.

         * @return the network type
         */
        //TODO
        @JvmStatic fun getNetworkType(context: Context): String {
            val networkStatePermission = context.checkCallingOrSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE)

            var result: String? = null

            if (networkStatePermission == PackageManager.PERMISSION_GRANTED) {
                try {

                    val activeNetwork = context.getSystemService(Context.CONNECTIVITY_SERVICE) as NetworkInfo
                    if (activeNetwork == null) {
                        result = "Unknown"
                    } else if (activeNetwork.type == ConnectivityManager.TYPE_WIFI || activeNetwork.type == ConnectivityManager.TYPE_WIMAX) {
                        result = "Wifi/WifiMax"
                    } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                        val tm: TelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                        if (tm.simState == TelephonyManager.SIM_STATE_READY) {
                            when (tm.networkType) {
                            // Unknown
                                TelephonyManager.NETWORK_TYPE_UNKNOWN -> result = "Cellular - Unknown"
                            // Cellular Data–2G
                                TelephonyManager.NETWORK_TYPE_EDGE, TelephonyManager.NETWORK_TYPE_GPRS, TelephonyManager.NETWORK_TYPE_CDMA, TelephonyManager.NETWORK_TYPE_IDEN, TelephonyManager.NETWORK_TYPE_1xRTT -> result = "Cellular - 2G"
                            // Cellular Data–3G
                                TelephonyManager.NETWORK_TYPE_UMTS, TelephonyManager.NETWORK_TYPE_HSDPA, TelephonyManager.NETWORK_TYPE_HSPA, TelephonyManager.NETWORK_TYPE_HSPAP, TelephonyManager.NETWORK_TYPE_HSUPA, TelephonyManager.NETWORK_TYPE_EVDO_0, TelephonyManager.NETWORK_TYPE_EVDO_A, TelephonyManager.NETWORK_TYPE_EVDO_B -> result = "Cellular - 3G"
                            // Cellular Data–4G
                                TelephonyManager.NETWORK_TYPE_LTE -> result = "Cellular - 4G"
                            // Cellular Data–Unknown Generation
                                else -> result = "Cellular - Unknown Generation"
                            }
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
            if (result?.length == 0) {
                result = null
            }
            return handleIllegalCharacterInResult(result.toString())
        }

        /**
         * Gets os codename.

         * @return the os codename
         */
        @JvmStatic fun getOSCodename(): String {
            var codename: String? = null
            when (Build.VERSION.SDK_INT) {
                Build.VERSION_CODES.BASE -> codename = "First Android Version. Yay !"
                Build.VERSION_CODES.BASE_1_1 -> codename = "Base Android 1.1"
                Build.VERSION_CODES.CUPCAKE -> codename = "Cupcake"
                Build.VERSION_CODES.DONUT -> codename = "Donut"
                Build.VERSION_CODES.ECLAIR, Build.VERSION_CODES.ECLAIR_0_1, Build.VERSION_CODES.ECLAIR_MR1 ->

                    codename = "Eclair"
                Build.VERSION_CODES.FROYO -> codename = "Froyo"
                Build.VERSION_CODES.GINGERBREAD, Build.VERSION_CODES.GINGERBREAD_MR1 -> codename = "Gingerbread"
                Build.VERSION_CODES.HONEYCOMB, Build.VERSION_CODES.HONEYCOMB_MR1, Build.VERSION_CODES.HONEYCOMB_MR2 -> codename = "Honeycomb"
                Build.VERSION_CODES.ICE_CREAM_SANDWICH, Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1 -> codename = "Ice Cream Sandwich"
                Build.VERSION_CODES.JELLY_BEAN, Build.VERSION_CODES.JELLY_BEAN_MR1, Build.VERSION_CODES.JELLY_BEAN_MR2 -> codename = "Jelly Bean"
                Build.VERSION_CODES.KITKAT -> codename = "Kitkat"
                Build.VERSION_CODES.KITKAT_WATCH -> codename = "Kitkat Watch"
                Build.VERSION_CODES.LOLLIPOP, Build.VERSION_CODES.LOLLIPOP_MR1 -> codename = "Lollipop"
                Build.VERSION_CODES.M -> codename = "Marshmallow"
            }
            return codename.toString()
        }

        /**
         * Gets os version.

         * @return the os version
         */
        @JvmStatic fun getOSVersion(): String {
            var result: String? = null
            try {
                result = Build.VERSION.RELEASE
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets wifi mac.

         * @return the wifi mac
         */
        @JvmStatic @SuppressWarnings("MissingPermission") fun getWifiMAC(context: Context): String {
            var result: String? = null
            try {

                if (context.checkCallingOrSelfPermission(Manifest.permission.ACCESS_WIFI_STATE) == PackageManager.PERMISSION_GRANTED) {
                    val wifiManager: WifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
                    result = wifiManager.connectionInfo.macAddress
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets imei.

         * @return the imei
         */
        @JvmStatic fun getIMEI(context: Context): String {
            var result: String? = null
            val hasReadPhoneStatePermission = context.checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
            val tm: TelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            try {
                if (hasReadPhoneStatePermission) result = tm.getDeviceId()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets imsi.

         * @return the imsi
         */
        @JvmStatic fun getIMSI(context: Context): String {
            var result: String? = null
            val hasReadPhoneStatePermission = context.checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
            val tm: TelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            try {
                if (hasReadPhoneStatePermission) result = tm.getSubscriberId()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets serial.

         * @return the serial
         */
        @JvmStatic fun getSerial(): String {
            var result: String? = null
            try {
                result = Build.SERIAL
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets sim serial.

         * @return the sim serial
         */
        @JvmStatic fun getSIMSerial(context: Context): String {
            var result: String? = null
            try {
                val tm: TelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                result = tm.getSimSerialNumber()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets gsfid.

         * @return the gsfid
         */
        @JvmStatic fun getGSFID(context: Context): String? {
            val URI = Uri.parse("content://com.google.android.gsf.gservices")
            val ID_KEY = "android_id"

            val params = arrayOf(ID_KEY)
            val c = context.getContentResolver().query(URI, null, null, params, null)

            if (!c.moveToFirst() || c!!.getColumnCount() < 2) {
                c.close()
                return null
            }
            try {
                val gsfID = java.lang.Long.toHexString(java.lang.Long.parseLong(c.getString(1)))
                c.close()
                return gsfID
            } catch (e: NumberFormatException) {
                c.close()
                return null
            }

        }

        /**
         * Gets bluetooth mac.

         * @return the bluetooth mac
         */
        @JvmStatic @SuppressWarnings("MissingPermission") fun getBluetoothMAC(context: Context): String {
            var result: String? = null
            try {
                if (context.checkCallingOrSelfPermission(Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED) {
                    val bta = BluetoothAdapter.getDefaultAdapter()
                    result = bta.address
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets psuedo unique id.

         * @return the psuedo unique id
         */
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @JvmStatic fun getPsuedoUniqueID(): String {
            // If all else fails, if the user does have lower than API 9 (lower
            // than Gingerbread), has reset their phone or 'Secure.ANDROID_ID'
            // returns 'null', then simply the ID returned will be solely based
            // off their Android device information. This is where the collisions
            // can happen.
            // Try not to use DISPLAY, HOST or ID - these items could change.
            // If there are collisions, there will be overlapping data
            var devIDShort = "35" +
                    Build.BOARD.length % 10 + Build.BRAND.length % 10

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                devIDShort += Build.SUPPORTED_ABIS[0].length % 10
            } else {
                devIDShort += Build.CPU_ABI.length % 10
            }

            devIDShort += Build.DEVICE.length % 10 + Build.MANUFACTURER.length % 10 + Build.MODEL.length % 10 + Build.PRODUCT.length % 10

            // Only devices with API >= 9 have android.os.Build.SERIAL
            // http://developer.android.com/reference/android/os/Build.html#SERIAL
            // If a user upgrades software or roots their phone, there will be a duplicate entry
            val serial: String
            try {
                serial = Build::class.java.getField("SERIAL").get(null).toString()

                // Go ahead and return the serial for api => 9
                return UUID(devIDShort.hashCode().toLong(), serial.hashCode().toLong()).toString()
            } catch (e: Exception) {
                // String needs to be initialized
                serial = "ESYDV000" // some value
            }

            // Finally, combine the values we have found by using the UUID class to create a unique identifier
            return UUID(devIDShort.hashCode().toLong(), serial.hashCode().toLong()).toString()
        }

        /**
         * Gets phone no.

         * @return the phone no
         */
        @JvmStatic fun getPhoneNo(context: Context): String {
            var result: String? = null
            try {
                val tm: TelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                if (tm.getLine1Number() != null) {
                    result = tm.getLine1Number()
                    if (result == "") {
                        result = null
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result?.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets product.

         * @return the product
         */
        @JvmStatic fun getProduct(): String {
            var result: String? = null
            try {
                result = Build.PRODUCT
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets fingerprint.

         * @return the fingerprint
         */
        @JvmStatic fun getFingerprint(): String {
            var result: String? = null
            try {
                result = Build.FINGERPRINT
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets hardware.

         * @return the hardware
         */
        @JvmStatic fun getHardware(): String {
            var result: String? = null
            try {
                result = Build.HARDWARE
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets radio ver.

         * @return the radio ver
         */
        @JvmStatic fun getRadioVer(): String {
            var result: String? = null
            try {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                    result = Build.getRadioVersion()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets ip address.

         * @param useIPv4 the use i pv 4
         * *
         * @return the ip address
         */
        @JvmStatic fun getIPAddress(useIPv4: Boolean): String {
            var result: String? = null
            try {
                val interfaces = Collections.list(NetworkInterface.getNetworkInterfaces())
                for (intf in interfaces) {
                    val addrs = Collections.list(intf.inetAddresses)
                    for (addr in addrs) {
                        if (!addr.isLoopbackAddress) {
                            val sAddr = addr.hostAddress.toUpperCase(Locale.getDefault())
                            val isIPv4 = addr is Inet4Address
                            if (useIPv4) {
                                if (isIPv4) result = sAddr
                            } else {
                                if (!isIPv4) {
                                    val delim = sAddr.indexOf('%') // drop ip6 port suffix
                                    result = if (delim < 0) sAddr else sAddr.substring(0, delim)
                                }
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets ua.

         * @return the ua
         */
        //TODO
        @JvmStatic fun getUA(context: Context): String {
            val system_ua = System.getProperty("http.agent")
            var result: String? = system_ua
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    result = WebView(context).settings.userAgentString +
                            "__" + system_ua
                } else {
                    result = WebView(context).settings.userAgentString +
                            "__" + system_ua
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result!!.length < 0 || result == null) {
                result = system_ua
            }
            return result.toString()
        }

        /**
         * Get lat long double [ ].

         * @return the double [ ]
         */
        //TODO
        @SuppressWarnings("MissingPermission")
        @TargetApi(Build.VERSION_CODES.M)
        @JvmStatic fun getLatLong(context: Context): DoubleArray {
            val hasFineLocationPermission = if (context.checkCallingOrSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                true
            else
                false
            val isGPSEnabled: Boolean
            val isNetworkEnabled: Boolean

            val gps = DoubleArray(2)
            gps[0] = 0.0
            gps[1] = 0.0
            if (hasFineLocationPermission) {
                try {
                    val locationManger: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                    isGPSEnabled = locationManger.isProviderEnabled(LocationManager.GPS_PROVIDER)
                    isNetworkEnabled = locationManger.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

                    var net_loc: Location? = null
                    var gps_loc: Location? = null
                    var final_loc: Location? = null

                    if (isGPSEnabled) {
                        gps_loc = locationManger.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    }
                    if (isNetworkEnabled) {
                        net_loc = locationManger.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                    }

                    if (gps_loc != null && net_loc != null) {
                        if (gps_loc.accuracy >= net_loc.accuracy) {
                            final_loc = gps_loc
                        } else {
                            final_loc = net_loc
                        }
                    } else {
                        if (gps_loc != null) {
                            final_loc = gps_loc
                        } else if (net_loc != null) {
                            final_loc = net_loc
                        } else {
                            // GPS and Network both are null so try passive
                            final_loc = locationManger.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)
                        }
                    }

                    if (final_loc != null) {
                        gps[0] = final_loc.latitude
                        gps[1] = final_loc.longitude
                    }

                    return gps
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
            return gps
        }

        /**
         * Get display xy coordinates int [ ].

         * @param event the event
         * *
         * @return the int [ ]
         */
        @JvmStatic fun getDisplayXYCoordinates(event: MotionEvent): IntArray {
            val coordinates = IntArray(2)
            coordinates[0] = 0
            coordinates[1] = 0
            try {
                if (event.action == MotionEvent.ACTION_DOWN) {
                    coordinates[0] = event.x.toInt()     // X Coordinates
                    coordinates[1] = event.y.toInt()     // Y Coordinates
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return coordinates
        }

        /**
         * Gets time.

         * @return the time
         */
        @JvmStatic fun getTime(): Long {
            return System.currentTimeMillis()
        }

        /**
         * Gets formated time.

         * @return the formated time
         */
        @JvmStatic fun getFormatedTime(): String {
            val cal = Calendar.getInstance()
            cal.timeInMillis = System.currentTimeMillis()
            return "${cal.get(Calendar.HOUR_OF_DAY)} :  ${cal.get(Calendar.MINUTE)} :${cal.get(Calendar.SECOND)}"
            /* return cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(
                     Calendar.SECOND)*/
        }

        /**
         * Gets app name.

         * @return the app name
         */
        @JvmStatic fun getAppName(context: Context): String {
            val result: String
            val pm = context.getPackageManager()
            val ai: ApplicationInfo?
            try {
                ai = pm.getApplicationInfo(context.getPackageName(), 0)
            } catch (e: PackageManager.NameNotFoundException) {
                ai = null
                e.printStackTrace()
            }

            result = (if (ai != null) pm.getApplicationLabel(ai) else null) as String
            return result
        }

        /**
         * Gets app version.

         * @return the app version
         */
        @JvmStatic fun getAppVersion(context: Context): String {
            var result: String? = null
            try {
                result = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets app version code.

         * @return the app version code
         */
        @JvmStatic fun getAppVersionCode(context: Context): String {
            var result: String? = null
            try {
                result = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode.toString()
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            if (result?.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets activity name.

         * @return the activity name
         */
        @JvmStatic fun getActivityName(context: Context): String {
            var result: String? = null
            try {
                result = context.javaClass.getSimpleName()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result?.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets package name.

         * @return the package name
         */
        @JvmStatic fun getPackageName(context: Context): String {
            var result: String? = null
            try {
                result = context.getPackageName()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets store.

         * @return the store
         */
        @JvmStatic fun getStore(context: Context): String {
            var result: String? = null
            if (Build.VERSION.SDK_INT >= 3) {
                try {
                    result = context.getPackageManager().getInstallerPackageName(context.getPackageName())
                } catch (e: Exception) {
                    Log.i("TAG", "Can't get Installer package")
                }

            }
            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }

        /**
         * Gets density.

         * @return the density
         */
        @JvmStatic fun getDensity(context: Context): String {
            var densityStr: String? = null
            val density = context.getResources().getDisplayMetrics().densityDpi
            when (density) {
                DisplayMetrics.DENSITY_LOW -> densityStr = "LDPI"
                DisplayMetrics.DENSITY_MEDIUM -> densityStr = "MDPI"
                DisplayMetrics.DENSITY_TV -> densityStr = "TVDPI"
                DisplayMetrics.DENSITY_HIGH -> densityStr = "HDPI"
                DisplayMetrics.DENSITY_XHIGH -> densityStr = "XHDPI"
                DisplayMetrics.DENSITY_400 -> densityStr = "XMHDPI"
                DisplayMetrics.DENSITY_XXHIGH -> densityStr = "XXHDPI"
                DisplayMetrics.DENSITY_XXXHIGH -> densityStr = "XXXHDPI"
            }
            return densityStr.toString()
        }

        /**
         * Get accounts string [ ].

         * @return the string [ ]
         */
        @JvmStatic @SuppressWarnings("MissingPermission") fun getAccounts(context: Context): Array<String?>? {
            try {

                if (context.checkCallingOrSelfPermission(Manifest.permission.GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED) {
                    val accounts = AccountManager.get(context).getAccountsByType("com.google")
                    val result = arrayOfNulls<String>(accounts.size)
                    for (i in accounts.indices) {
                        result[i] = accounts[i].name
                    }
                    return result
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return null
        }

        /**
         * Is network available boolean.

         * @return the boolean
         */
        //TODO
        @JvmStatic fun isNetworkAvailable(context: Context): Boolean {
            if (context.checkCallingOrSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED && context.checkCallingOrSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED) {
                val netInfo: NetworkInfo = context.getSystemService(Context.CONNECTIVITY_SERVICE) as NetworkInfo
                return netInfo != null && netInfo.isConnected
            }
            return false
        }

        /**
         * Is running on emulator boolean.

         * @return the boolean
         */

        @JvmStatic fun isRunningOnEmulator(): Boolean {
            return Build.BRAND.contains("generic")
                    || Build.DEVICE.contains("generic")
                    || Build.PRODUCT.contains("sdk")
                    || Build.HARDWARE.contains("goldfish")
                    || Build.MANUFACTURER.contains("Genymotion")
                    || Build.PRODUCT.contains("vbox86p")
                    || Build.DEVICE.contains("vbox86p")
                    || Build.HARDWARE.contains("vbox86")
        }
    }

    /**
     * Gets android ad id.
     *
     * @param callback the callback
     */
    /*public void getAndroidAdId(final AdIdentifierCallback callback) {
    new Thread(new Runnable() {
        @Override public void run() {
            AdvertisingIdClient.Info adInfo;
            try {
                adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
                String androidAdId = adInfo.getId();
                boolean adDoNotTrack = adInfo.isLimitAdTrackingEnabled();
                if (androidAdId == null) {
                    androidAdId = "NA";
                }

                //Send Data to callback
                callback.onSuccess(androidAdId, (adDoNotTrack && adDoNotTrack));
            } catch (IOException e) {
                // Unrecoverable error connecting to Google Play services (e.g.,
                // the old version of the service doesn't support getting AdvertisingId).

            } catch (GooglePlayServicesNotAvailableException e) {
                // Google Play services is not available entirely.
            } catch (GooglePlayServicesRepairableException e) {
                e.printStackTrace();
            }
        }
    }).start();
}*/

    /**
     * The interface Ad identifier callback.
     */
    interface AdIdentifierCallback {
        /**
         * On success.

         * @param adIdentifier the ad identifier
         * *
         * @param adDonotTrack the ad donot track
         */
        fun onSuccess(adIdentifier: String, adDonotTrack: Boolean)
    }

}