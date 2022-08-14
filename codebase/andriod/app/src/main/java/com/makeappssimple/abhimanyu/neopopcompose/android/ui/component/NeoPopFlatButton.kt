package com.makeappssimple.abhimanyu.neopopcompose.android.ui.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable
fun NeoPopFlatButtonSample() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth(),
    ) {
        NeoPopFlatButton(
            text = "Flat Button",
            onClick = {},
        )
    }
}

@Composable
fun NeoPopFlatButton(
    text: String,
    onClick: () -> Unit,
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val pressed = interactionSource.collectIsPressedAsState()
    val depth: Dp by animateDpAsState(
        targetValue = if (pressed.value) {
            4.dp
        } else {
            0.dp
        },
    )

    val modifier = Modifier
        .clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick,
            role = Role.Button,
        )
    NeoPopFlatButtonView(
        text = text,
        modifier = modifier,
        depth = depth,
    )
}

@Composable
private fun NeoPopFlatButtonView(
    text: String,
    modifier: Modifier = Modifier,
    width: Dp = 200.dp,
    height: Dp = 40.dp,
    depth: Dp = 4.dp,
) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier = modifier
                .wrapContentSize(
                    align = Alignment.Center,
                )
                .requiredSize(
                    width = width,
                    height = height,
                ),
        ) {
            drawRect(
                color = Color.LightGray,
                size = Size(
                    width = width.toPx(),
                    height = height.toPx(),
                ),
            )
            drawRect(
                color = Color.White,
                topLeft = Offset(
                    x = depth.toPx(),
                    y = depth.toPx(),
                ),
                size = Size(
                    width = (width - depth).toPx(),
                    height = (height - depth).toPx(),
                ),
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .requiredSize(
                    width = width,
                    height = height,
                )
                .offset {
                    IntOffset(
                        x = depth
                            .toPx()
                            .toInt(),
                        y = depth
                            .toPx()
                            .toInt(),
                    )
                },
        ) {
            Text(
                text = text,
                modifier = Modifier,
            )
        }
    }
}
