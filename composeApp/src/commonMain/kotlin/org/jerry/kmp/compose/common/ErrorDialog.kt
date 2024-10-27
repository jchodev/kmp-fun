package org.jerry.kmp.compose.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kmp_fun.composeapp.generated.resources.Res
import kmp_fun.composeapp.generated.resources.close
import kmp_fun.composeapp.generated.resources.error
import kmp_fun.composeapp.generated.resources.retry
import org.jetbrains.compose.resources.stringResource


@Composable
fun ErrorDialog(
    text: String = "this is text",
    onRetryRequest: () -> Unit = {},
    onDismissRequest: () -> Unit = {},
) {
    CustomAlertDialog(
        icon = {
            Icon(
                modifier = Modifier
                    .size(160.dp)
                    .padding(top = 32.dp),
                tint = MaterialTheme.colorScheme.error,
                imageVector = Icons.Outlined.Warning,
                contentDescription = stringResource(Res.string.error)
            )
        },
        title = null,
        message = text,
        leftBtnStr= stringResource(Res.string.close),
        onLeftClick = onDismissRequest,
        rightBtnStr = stringResource(Res.string.retry),
        onRightClick = onRetryRequest
    )
}
//
//@DevicePreviews
//@Composable
//private fun ErrorDialogPreview(){
//    AssessmentprojectTheme {
//        ErrorDialog()
//    }
//}