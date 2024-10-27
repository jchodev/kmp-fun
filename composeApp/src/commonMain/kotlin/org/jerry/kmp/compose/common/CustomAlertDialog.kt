package org.jerry.kmp.compose.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


@Composable
fun CustomAlertDialog(
    icon: @Composable (() -> Unit)? = null,

    title: String? = "this is title",
    message: String = "this is message",

    leftBtnStr: String = "Dismiss",
    onLeftClick: () -> Unit = {},

    rightBtnStr: String? = "OK",
    onRightClick: () -> Unit = {},
) {
    Dialog(
        onDismissRequest = { onLeftClick() },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        AlertDialogContent(
            icon = icon,
            title = title,
            message = message,
            leftBtnStr = leftBtnStr,
            onLeftClick = onLeftClick,
            rightBtnStr = rightBtnStr,
            onRightClick = onRightClick,
        )
    }
}


@Composable
private fun AlertDialogContent(
    modifier: Modifier = Modifier,

    icon: @Composable (() -> Unit)? = null,

    title: String? = "this is title",
    message: String = "this is message",

    leftBtnStr: String = "Dismiss",
    onLeftClick: () -> Unit = {},

    rightBtnStr: String? = "OK",
    onRightClick: () -> Unit = {}
){
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(8.dp),

    ) {
        Column(
            modifier= modifier.background(MaterialTheme.colorScheme.background).fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            icon?.let {
                it.invoke()
            }

            Column(modifier = Modifier.padding(16.dp)) {
                title?.let {
                    Text(
                        text = it,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Text(
                    text = message,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(
                            top = 8.dp,
                            start = 24.dp,
                            end = 24.dp
                        )
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            //.......................................................................
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                horizontalArrangement = Arrangement.SpaceAround) {

                TextButton(
                    modifier = Modifier.weight(1f),
                    onClick = onLeftClick
                ) {
                    Text(

                        text = leftBtnStr,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp)
                    )
                }
                rightBtnStr?.let {
                    TextButton(
                        modifier = Modifier.weight(1f),
                        onClick = onRightClick
                    ) {
                        Text(
                            text = it,
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp)
                        )
                    }
                }
            }
        }
    }
}


//@DevicePreviews
//@Composable
//fun AlertDialogPreview(){
//    AssessmentprojectTheme {
//        CustomAlertDialog(
//            icon = {
//                Icon(
//                    modifier = Modifier.size(160.dp).padding(top = 32.dp),
//                    tint = MaterialTheme.colorScheme.error,
//                    imageVector = Icons.Outlined.Error,
//                    contentDescription = null
//                )
//            },
//            title = null,
//        )
//    }
//}
