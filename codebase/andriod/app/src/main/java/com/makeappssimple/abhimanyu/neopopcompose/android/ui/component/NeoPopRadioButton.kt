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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun NeoPopRadioButtonSample() {
    val (selected, onSelectionChange) = remember {
        mutableStateOf(false)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth(),
    ) {
        NeoPopRadioButton(
            selected = selected,
            onSelectionChange = onSelectionChange,
            size = 300.dp,
            borderWidth = 15.dp,
            selectionWidth = 75.dp,
            animationDurationInMillis = 3000,
        )
    }
}

@Composable
fun NeoPopRadioButton(
    selected: Boolean,
    onSelectionChange: (selected: Boolean) -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 20.dp,
    borderWidth: Dp = 2.dp,
    selectionWidth: Dp = 5.dp,
    animationDurationInMillis: Int = 300,
) {
    val radius = size / 2
    val borderWidthValue = if (borderWidth < radius) {
        borderWidth
    } else if ((borderWidth / 10) > 2.dp) {
        borderWidth / 10
    } else {
        2.dp
    }
    val selectionWidthValue = if (selectionWidth > (radius - borderWidthValue)) {
        radius - borderWidthValue
    } else {
        selectionWidth
    }

    val innerRadiusDefaultValue = (radius - borderWidthValue).value
    val innerRadiusSelectedValue = (radius - borderWidthValue - selectionWidthValue).value
    val outerWidthDefaultValue = (borderWidthValue).value
    val outerWidthSelectedValue = (borderWidthValue + selectionWidthValue).value
    val easing = LinearEasing

    var innerRadius by remember {
        mutableStateOf(innerRadiusDefaultValue)
    }
    var outerWidth by remember {
        mutableStateOf(outerWidthDefaultValue)
    }

    LaunchedEffect(
        key1 = selected,
    ) {
        if (selected) {
            this.launch {
                launch {
                    animate(
                        initialValue = innerRadius,
                        targetValue = innerRadiusSelectedValue,
                        animationSpec = tween(
                            durationMillis = (animationDurationInMillis * ((innerRadius - innerRadiusSelectedValue) / (innerRadiusDefaultValue - innerRadiusSelectedValue))).toInt(),
                            easing = easing,
                        ),
                    ) { value, _ ->
                        innerRadius = value
                    }
                }
                launch {
                    animate(
                        initialValue = outerWidth,
                        targetValue = outerWidthSelectedValue,
                        animationSpec = tween(
                            durationMillis = (animationDurationInMillis * ((outerWidthSelectedValue - outerWidth) / (outerWidthSelectedValue - outerWidthDefaultValue))).toInt(),
                            easing = easing,
                        ),
                    ) { value, _ ->
                        outerWidth = value
                    }
                }
            }
        } else {
            this.launch {
                launch {
                    animate(
                        initialValue = innerRadius,
                        targetValue = innerRadiusDefaultValue,
                        animationSpec = tween(
                            durationMillis = (animationDurationInMillis * ((innerRadiusDefaultValue - innerRadius) / (innerRadiusDefaultValue - innerRadiusSelectedValue))).toInt(),
                            easing = easing,
                        ),
                    ) { value, _ ->
                        innerRadius = value
                    }
                }
                launch {
                    animate(
                        initialValue = outerWidth,
                        targetValue = outerWidthDefaultValue,
                        animationSpec = tween(
                            durationMillis = (animationDurationInMillis * ((outerWidth - outerWidthDefaultValue) / (outerWidthSelectedValue - outerWidthDefaultValue))).toInt(),
                            easing = easing,
                        ),
                    ) { value, _ ->
                        outerWidth = value
                    }
                }
            }
        }
    }

    val radioButtonModifier = modifier
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            role = Role.RadioButton,
            onClick = {
                onSelectionChange(!selected)
            },
        )

    NeoPopRadioButtonView(
        innerRadius = innerRadius.dp,
        outerWidth = outerWidth.dp,
        modifier = radioButtonModifier,
    )
}

@Composable
private fun NeoPopRadioButtonView(
    innerRadius: Dp,
    outerWidth: Dp,
    modifier: Modifier = Modifier,
) {
    Canvas(
        modifier = modifier
            .wrapContentSize(
                align = Alignment.Center,
            )
            .requiredSize(
                size = (innerRadius + outerWidth) * 2,
            ),
    ) {
        drawCircle(
            color = Color.White,
            radius = innerRadius.toPx(),
        )
        drawCircle(
            color = Color.Black,
            radius = (innerRadius + (outerWidth / 2)).toPx(),
            style = Stroke(
                width = outerWidth.toPx(),
            ),
        )
    }
}
