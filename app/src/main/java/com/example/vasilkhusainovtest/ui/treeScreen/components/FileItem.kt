package com.example.vasilkhusainovtest.ui.treeScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.vasilkhusainovtest.R
import com.example.vasilkhusainovtest.ui.theme.LocalCustomColorsPalette


@Composable
fun FileItem(
    titleName: String?,
    titleSize: String,
    eventFile: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clickable { eventFile() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Icon(
                painter = painterResource(R.drawable.ic_file),
                contentDescription = stringResource(R.string.title_back),
                tint = LocalCustomColorsPalette.current.hierarchyThree.fileColor
            )

            Text(
                modifier = Modifier.padding(start = 6.dp),
                text = titleName ?: "",
                color = LocalCustomColorsPalette.current.primaryTextColor
            )
        }

        Text(titleSize, color = LocalCustomColorsPalette.current.secondaryTextColor)
    }
}