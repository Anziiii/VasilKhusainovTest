package com.example.vasilkhusainovtest.ui.searchScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.vasilkhusainovtest.R
import com.example.vasilkhusainovtest.ui.theme.LocalCustomColorsPalette
import com.example.vasilkhusainovtest.ui.theme.VasilKhusainovTestTheme


@Preview
@Composable
fun PreviewUserCard() {

    VasilKhusainovTestTheme {
        UserCard(
            userName = "Abcd user",
            image = "url/",
            eventTransition = fun() {}
        )
    }
}

@Composable
fun UserCard(
    userName: String,
    image: String,
    eventTransition: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                eventTransition()
            }
            .padding(vertical = 4.dp, horizontal = 8.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = LocalCustomColorsPalette.current.customCard.containerColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                AsyncImage(
                    modifier = Modifier
                        .padding(6.dp)
                        .size(64.dp)
                        .clip(CircleShape),
                    model = image,
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = userName,
                    fontWeight = FontWeight.Normal,
                    color = LocalCustomColorsPalette.current.primaryTextColor
                )
            }

            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = stringResource(R.string.title_back),
                tint = LocalCustomColorsPalette.current.customCard.iconSurfaceColor
            )
        }
    }
}
