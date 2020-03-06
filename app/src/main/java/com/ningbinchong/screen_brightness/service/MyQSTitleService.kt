package com.ningbinchong.screen_brightness.service

import android.content.Intent
import android.os.IBinder
import android.service.quicksettings.TileService

/**
 * author: ningbinchong
 * created on: 2020/3/6 11:05
 * description: Tile for details about the UI of a Quick Settings Tile
 */
class MyQSTitleService : TileService() {
    override fun onTileRemoved() {
        super.onTileRemoved()
    }

    override fun onTileAdded() {
        super.onTileAdded()
    }

    override fun onStartListening() {
        super.onStartListening()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return super.onBind(intent)
    }

    override fun onStopListening() {
        super.onStopListening()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}