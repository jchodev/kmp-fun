package org.jerry.kmp.compose.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jerry.kmp.contants.TEST_TAG_TITLE


@Composable
fun ErrorText(
    modifier: Modifier = Modifier,
    text: String = ""
){
    Row (
        modifier = modifier.testTag(TEST_TAG_TITLE),
        verticalAlignment = Alignment.CenterVertically,
    ){
        Icon(
            imageVector = Icons.Rounded.Info,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = MaterialTheme.colorScheme.error,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text (
            text = text,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.error,
            lineHeight = 16.sp,
            fontWeight = FontWeight(400)
        )
    }
}