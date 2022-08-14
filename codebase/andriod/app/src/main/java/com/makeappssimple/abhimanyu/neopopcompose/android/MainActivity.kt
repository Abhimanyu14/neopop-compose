package com.makeappssimple.abhimanyu.neopopcompose.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.makeappssimple.abhimanyu.neopopcompose.android.ui.screen.NeoPop
import com.makeappssimple.abhimanyu.neopopcompose.android.ui.theme.NeoPopComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeoPopComposeTheme {
                NeoPop()
            }
        }
    }
}
