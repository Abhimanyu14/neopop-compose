package com.makeappssimple.abhimanyu.neopopcompose.android.ui.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun NeoPopElevatedButtonSample() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth(),
    ) {
        NeoPopElevatedButton(
            text = "Elevated Button",
            onClick = {},
        )
    }
}

@Composable
fun NeoPopElevatedButton(
    text: String,
    onClick: () -> Unit,
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val pressed = interactionSource.collectIsPressedAsState()
    val elevation: Dp by animateDpAsState(
        targetValue = if (pressed.value) {
            0.dp
        } else {
            4.dp
        },
    )

    val modifier = Modifier
        .clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick,
            role = Role.Button,
        )
    NeoPopElevatedButtonView(
        text = text,
        modifier = modifier,
        elevation = elevation,
        maxElevation = 4.dp,
    )
}

@OptIn(ExperimentalTextApi::class)
@Composable
private fun NeoPopElevatedButtonView(
    text: String,
    modifier: Modifier = Modifier,
    width: Dp = 200.dp,
    height: Dp = 40.dp,
    elevation: Dp = 4.dp,
    maxElevation: Dp = 4.dp,
) {
    val textMeasurer = rememberTextMeasurer()
    val textLayoutResult: TextLayoutResult = textMeasurer.measure(
        text = AnnotatedString(
            text = text,
        ),
    )
    val textSize = textLayoutResult.size

    Canvas(
        modifier = modifier
            .wrapContentSize(
                align = Alignment.Center,
            )
            .requiredSize(
                width = width + maxElevation,
                height = height + maxElevation,
            ),
    ) {

        val bottom = Path().let {
            it.moveTo(
                x = maxElevation.toPx(),
                y = (height + maxElevation).toPx(),
            )
            it.lineTo(
                x = (maxElevation + width).toPx(),
                y = (height + maxElevation).toPx(),
            )
            it.lineTo(
                x = (maxElevation + width - elevation).toPx(),
                y = (height + maxElevation - elevation).toPx(),
            )
            it.lineTo(
                x = (maxElevation - elevation).toPx(),
                y = (height + maxElevation - elevation).toPx(),
            )
            it.close()
            it
        }
        val right = Path().let {
            it.moveTo(
                x = (maxElevation + width).toPx(),
                y = (height + maxElevation).toPx(),
            )
            it.lineTo(
                x = (maxElevation + width - elevation).toPx(),
                y = (height + maxElevation - elevation).toPx(),
            )
            it.lineTo(
                x = (maxElevation + width - elevation).toPx(),
                y = (maxElevation - elevation).toPx(),
            )
            it.lineTo(
                x = (maxElevation + width).toPx(),
                y = (maxElevation).toPx(),
            )
            it.close()
            it
        }

        drawRect(
            color = Color.White,
            topLeft = Offset(
                x = (maxElevation - elevation).toPx(),
                y = (maxElevation - elevation).toPx(),
            ),
            size = Size(
                width = (width).toPx(),
                height = (height).toPx(),
            ),
        )
        drawPath(
            path = bottom,
            color = Color.LightGray,
        )
        drawPath(
            path = right,
            color = Color.LightGray,
        )
        drawText(
            textMeasurer = textMeasurer,
            text = text,
            topLeft = Offset(
                x = ((width.toPx() - textSize.width) / 2) + (maxElevation - elevation).toPx(),
                y = ((height.toPx() - textSize.height) / 2) + (maxElevation - elevation).toPx(),
            ),
        )
    }
}
