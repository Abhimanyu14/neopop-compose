package com.makeappssimple.abhimanyu.neopopcompose.android.ui.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun NeoPopCheckBoxSample() {
    val (checked, onCheckedChange) = remember {
        mutableStateOf(false)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth(),
    ) {
        NeoPopCheckBox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            size = 300.dp,
            borderWidth = 30.dp,
            checkedImageVector = Icons.Rounded.Done,
            animationDurationInMillis = 3000,
        )
    }
}

@Composable
fun NeoPopCheckBox(
    checked: Boolean,
    onCheckedChange: (checked: Boolean) -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 20.dp,
    borderWidth: Dp = 2.dp,
    checkedImageVector: ImageVector = Icons.Rounded.Done,
    animationDurationInMillis: Int = 300,
) {
    val checkedIndicatorPainter = rememberVectorPainter(
        image = checkedImageVector,
    )
    val borderWidthValue = if (borderWidth < (size / 2)) {
        borderWidth
    } else if ((borderWidth / 10) > 2.dp) {
        borderWidth / 10
    } else {
        2.dp
    }
    val checkBoxModifier = modifier
        .clickable(
            interactionSource = remember {
                MutableInteractionSource()
            },
            indication = null,
            role = Role.Checkbox,
            onClick = {
                onCheckedChange(!checked)
            },
        )
    val strokeWidthDefaultValue = borderWidthValue.value
    val strokeWidthCheckedValue = (size / 2).value
    val checkedIndicatorAlphaDefaultValue = 0.0F
    val checkedIndicatorAlphaCheckedValue = 1.0F
    val easing = LinearEasing

    var strokeWidth by remember {
        mutableStateOf(strokeWidthDefaultValue)
    }
    var checkedIndicatorAlpha by remember {
        mutableStateOf(checkedIndicatorAlphaDefaultValue)
    }

    LaunchedEffect(
        key1 = checked,
    ) {
        if (checked) {
            this.launch {
                animate(
                    initialValue = strokeWidth,
                    targetValue = strokeWidthCheckedValue,
                    animationSpec = tween(
                        durationMillis = (animationDurationInMillis * ((strokeWidthCheckedValue - strokeWidth) / (strokeWidthCheckedValue - strokeWidthDefaultValue))).toInt(),
                        easing = easing,
                    ),
                ) { value, _ ->
                    strokeWidth = value
                }
                animate(
                    initialValue = checkedIndicatorAlpha,
                    targetValue = checkedIndicatorAlphaCheckedValue,
                    animationSpec = tween(
                        durationMillis = (animationDurationInMillis * ((checkedIndicatorAlphaCheckedValue - checkedIndicatorAlpha) / (checkedIndicatorAlphaCheckedValue - checkedIndicatorAlphaDefaultValue))).toInt(),
                        easing = easing,
                    ),
                ) { value, _ ->
                    checkedIndicatorAlpha = value
                }
            }
        } else {
            this.launch {
                animate(
                    initialValue = checkedIndicatorAlpha,
                    targetValue = checkedIndicatorAlphaDefaultValue,
                    animationSpec = tween(
                        durationMillis = (animationDurationInMillis * ((checkedIndicatorAlpha - checkedIndicatorAlphaDefaultValue) / ((checkedIndicatorAlphaCheckedValue - checkedIndicatorAlphaDefaultValue)))).toInt(),
                        easing = easing,
                    ),
                ) { value, _ ->
                    checkedIndicatorAlpha = value
                }
                animate(
                    initialValue = strokeWidth,
                    targetValue = strokeWidthDefaultValue,
                    animationSpec = tween(
                        durationMillis = (animationDurationInMillis * ((strokeWidth - strokeWidthDefaultValue) / (strokeWidthCheckedValue - strokeWidthDefaultValue))).toInt(),
                        easing = easing,
                    ),
                ) { value, _ ->
                    strokeWidth = value
                }
            }
        }
    }

    NeoPopCheckBoxView(
        checkboxSize = size,
        strokeWidth = strokeWidth.dp,
        checkedIndicatorSize = size,
        checkedIndicatorAlpha = checkedIndicatorAlpha,
        checkedIndicatorPainter = checkedIndicatorPainter,
        modifier = checkBoxModifier,
    )
}

@Composable
private fun NeoPopCheckBoxView(
    checkboxSize: Dp,
    strokeWidth: Dp,
    checkedIndicatorSize: Dp,
    checkedIndicatorAlpha: Float,
    checkedIndicatorPainter: VectorPainter,
    modifier: Modifier = Modifier,
) {
    Canvas(
        modifier = modifier
            .wrapContentSize(
                align = Alignment.Center,
            )
            .requiredSize(
                size = checkboxSize,
            ),
    ) {
        drawRect(
            color = Color.Black,
            topLeft = Offset(
                x = (strokeWidth / 2).toPx(),
                y = (strokeWidth / 2).toPx(),
            ),
            size = Size(
                width = (checkboxSize - strokeWidth).toPx(),
                height = (checkboxSize - strokeWidth).toPx(),
            ),
            style = Stroke(
                width = strokeWidth.toPx(),
            ),
        )
        drawRect(
            color = Color.White,
            topLeft = Offset(
                x = strokeWidth.toPx(),
                y = strokeWidth.toPx(),
            ),
            size = Size(
                width = (checkboxSize - strokeWidth * 2).toPx(),
                height = (checkboxSize - strokeWidth * 2).toPx(),
            ),
        )
        with(
            receiver = checkedIndicatorPainter,
        ) {
            draw(
                size = Size(
                    width = checkedIndicatorSize.toPx(),
                    height = checkedIndicatorSize.toPx(),
                ),
                alpha = checkedIndicatorAlpha,
                colorFilter = ColorFilter.tint(
                    color = Color.White,
                ),
            )
        }
    }
}
