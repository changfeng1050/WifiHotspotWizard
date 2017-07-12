package com.zjl.wifihotspotwizard

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.tongda.settingitemview.SettingItemView
import org.jetbrains.anko.*
import tonda.tdledwizard.util.Preference

class MainActivity : AppCompatActivity() {

    private val TAG: String = "MainActivity"
    val mContent = this

    var mWifiAdmin: WifiAdmin? = null

    var hotspotName: String by Preference(this, "hotspot_name", "TDLED")
    var hotspotPassword: String by Preference(this, "hotspot_password", "tongdaled")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val hotSpotNameTextView = find<SettingItemView>(R.id.setting_item_view_hotspot_name)
        val hotSpotPasswordTextView = find<SettingItemView>(R.id.setting_item_view_hotspot_password)
        val createButton = find<Button>(R.id.button_create)

        hotSpotNameTextView.content = hotspotName
        hotSpotPasswordTextView.content = hotspotPassword

        hotSpotNameTextView.onClick {
            alert {
                title("热点名称")
                customView {
                    val et = editText {
                        setText(hotspotName)
                    }

                    yesButton {
                        hotspotName = et.text.toString().trim()
                        hotSpotNameTextView.content = hotspotName
                    }

                    noButton {

                    }
                }
            }.show()
        }

        hotSpotPasswordTextView.onClick {
            alert {
                title("密码")
                customView {
                    val et = editText {
                        setText(hotspotPassword)
                    }

                    yesButton {
                        hotspotPassword = et.text.toString().trim()
                        hotSpotPasswordTextView.content = hotspotPassword
                    }

                    noButton {

                    }
                }
            }.show()

        }


        createButton.onClick {
            val wifiApp = WifiApAdmin(mContent)
            wifiApp.startWifiAp(hotspotName, hotspotPassword)
        }

    }
}
