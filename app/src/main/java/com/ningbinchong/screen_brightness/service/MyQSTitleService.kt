package com.ningbinchong.screen_brightness.service

import android.content.Intent
import android.os.IBinder
import android.provider.Settings
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService

/**
 * author: ningbinchong
 * created on: 2020/3/6 11:05
 * description: Tile for details about the UI of a Quick Settings Tile
 */
class MyQSTitleService : TileService() {

    override fun onTileAdded() {
        super.onTileAdded()
        settCurrentScreenBrightnessMode()
    }

    override fun onStartListening() {
        super.onStartListening()
        settCurrentScreenBrightnessMode()
    }

    override fun onClick() {
        super.onClick()
        if (isLocked || isSecure) {
            //如果设备处于锁屏状态或者安全模式，不允许更改亮度模式
            return
        }
        val qsTile = qsTile
        if (qsTile == null) {
            return
        }
        when (qsTile.state) {
            Tile.STATE_ACTIVE -> {
                //当前状态是开，设置状态为关闭
                closeAutoScreenBrightness()
                qsTile.state = Tile.STATE_INACTIVE
                qsTile.updateTile()
            }
            Tile.STATE_UNAVAILABLE -> {

            }
            Tile.STATE_INACTIVE -> {
                //当前状态是关，设置状态为开.
                openAutoScreenBrightness()
                qsTile.state = Tile.STATE_ACTIVE
                qsTile.updateTile()
            }
        }
    }

    fun getCurrentScreenBrightnessMode(): Int {
        return Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE)
    }

    fun settCurrentScreenBrightnessMode() {
        val qsTile = qsTile
        qsTile?.state =
            if (getCurrentScreenBrightnessMode() == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) Tile.STATE_ACTIVE else Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC
        qsTile?.updateTile()
    }

    fun openAutoScreenBrightness() {
        Settings.System.putInt(
            contentResolver,
            Settings.System.SCREEN_BRIGHTNESS_MODE,
            Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC
        )
    }

    fun closeAutoScreenBrightness() {
        Settings.System.putInt(
            contentResolver,
            Settings.System.SCREEN_BRIGHTNESS_MODE,
            Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
        )
    }

}