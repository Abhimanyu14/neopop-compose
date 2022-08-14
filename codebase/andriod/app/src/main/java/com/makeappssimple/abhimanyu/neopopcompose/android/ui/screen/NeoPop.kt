package com.makeappssimple.abhimanyu.neopopcompose.android.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.makeappssimple.abhimanyu.neopopcompose.android.ui.component.NeoPopCheckBox
import com.makeappssimple.abhimanyu.neopopcompose.android.ui.component.NeoPopCheckBoxSample
import com.makeappssimple.abhimanyu.neopopcompose.android.ui.component.NeoPopRadioButton
import com.makeappssimple.abhimanyu.neopopcompose.android.ui.component.NeoPopRadioButtonSample

@Composable
fun NeoPop() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEEEEEE)),
    ) {
        NeoPopBody()
    }
}

@Composable
fun NeoPopBody() {
    val (selected, onSelectionChange) = remember {
        mutableStateOf(false)
    }
    val (checked, onCheckedChange) = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .verticalScroll(
                state = rememberScrollState(),
            )
            .fillMaxSize()
            .padding(
                vertical = 16.dp,
            ),
        verticalArrangement = Arrangement
            .spacedBy(
                space = 8.dp,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp,
                ),
        ) {
            Text(
                text = "NeoPop CheckBox",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(
                        weight = 1F,
                    ),
            )
            NeoPopCheckBox(
                checked = checked,
                onCheckedChange = onCheckedChange,
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp,
                ),
        ) {
            Text(
                text = "Material CheckBox",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(
                        weight = 1F,
                    ),
            )
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange,
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp,
                ),
        ) {
            Text(
                text = "NeoPop RadioButton",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(
                        weight = 1F,
                    ),
            )
            NeoPopRadioButton(
                selected = selected,
                onSelectionChange = onSelectionChange,
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp,
                ),
        ) {
            Text(
                text = "Material RadioButton",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(
                        weight = 1F,
                    ),
            )
            RadioButton(
                selected = selected,
                onClick = {
                    onSelectionChange(!selected)
                },
            )
        }
        NeoPopCheckBoxSample()
        NeoPopRadioButtonSample()
    }
}
