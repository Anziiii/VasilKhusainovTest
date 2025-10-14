package com.example.vasilkhusainovtest.ui.searchScreen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.vasilkhusainovtest.R
import com.example.vasilkhusainovtest.ui.theme.LocalCustomColorsPalette
import com.example.vasilkhusainovtest.ui.theme.VasilKhusainovTestTheme


@Preview
@Composable
fun PreviewRepositoryCard() {

    VasilKhusainovTestTheme {
        RepositoryCard(
            id = 1,
            name = "ABCDassssssssss",
            companyName = "Abcd company",
            description = "Тут краткое описание",
            image = "url/",
            textUpdate = "13.10.2025 09:41:23",
            textCreate = "13.10.2025 09:41:23",
            starMetric = "340.k",
            viewMetric = "340.k",
            branchMetric = "340.k",
            visible = true,
            eventDescription = fun(_: Int) {},
            eventTransitionCompany = fun() {},
            eventTransitionRepository = fun() {},
        )
    }
}

@Composable
fun RepositoryCard(
    id: Int,
    name: String,
    companyName: String,
    description: String,
    image: String,
    textUpdate: String,
    textCreate: String,
    starMetric: String,
    viewMetric: String,
    branchMetric: String,
    visible: Boolean,
    eventDescription: (id: Int) -> Unit,
    eventTransitionCompany: () -> Unit,
    eventTransitionRepository: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable { eventTransitionRepository() },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = LocalCustomColorsPalette.current.customCard.containerColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {

        val rotationArrow = animateFloatAsState(
            targetValue = if (visible) 90f else 0f
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.weight(0.8f),
                    text = name,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = LocalCustomColorsPalette.current.primaryTextColor
                )

                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    MetricsRow(title = starMetric, painterResource = R.drawable.ic_star)
                    MetricsRow(title = viewMetric, painterResource = R.drawable.ic_eye)
                    MetricsRow(title = branchMetric, painterResource = R.drawable.ic_branch)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        eventDescription(id)
                    }, verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    stringResource(R.string.description_card),
                    fontWeight = FontWeight.Normal,
                    color = LocalCustomColorsPalette.current.primaryTextColor
                )
                Icon(
                    modifier = Modifier.rotate(rotationArrow.value),
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = stringResource(R.string.title_back),
                    tint = LocalCustomColorsPalette.current.customCard.iconSurfaceColor
                )
            }

            AnimatedVisibility(visible) {

                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 6.dp)
                            .clickable { eventTransitionCompany() }
                            .clip(
                                RoundedCornerShape(6.dp)
                            )
                            .background(LocalCustomColorsPalette.current.customCard.secondContainerColor),
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
                                text = companyName,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
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

                    Text(
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(
                                LocalCustomColorsPalette.current.customCard.secondContainerColor
                            )
                            .padding(start = 6.dp, end = 6.dp),
                        text = buildAnnotatedString {

                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Normal,
                                    color = LocalCustomColorsPalette.current.secondaryTextColor
                                )
                            ) {
                                append(stringResource(id = R.string.created_date_format))
                                append(" ")
                            }

                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = LocalCustomColorsPalette.current.secondaryTextColor
                                )
                            ) {
                                append(textCreate)
                            }
                        },
                        fontWeight = FontWeight.Normal,
                        color = LocalCustomColorsPalette.current.secondaryTextColor
                    )

                    Text(
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(LocalCustomColorsPalette.current.customCard.secondContainerColor)
                            .padding(start = 6.dp, end = 6.dp),
                        text =
                            buildAnnotatedString {

                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Normal,
                                        color = LocalCustomColorsPalette.current.secondaryTextColor
                                    )
                                ) {
                                    append(stringResource(id = R.string.update_date_format))
                                    append(" ")
                                }

                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        color = LocalCustomColorsPalette.current.secondaryTextColor
                                    )
                                ) {
                                    append(textUpdate)
                                }
                            },
                        color = LocalCustomColorsPalette.current.secondaryTextColor
                    )

                    Text(
                        stringResource(R.string.description_title),
                        fontWeight = FontWeight.Normal,
                        color = LocalCustomColorsPalette.current.primaryTextColor
                    )
                    Text(
                        text = description,
                        fontWeight = FontWeight.Normal,
                        color = LocalCustomColorsPalette.current.secondaryTextColor
                    )
                }

            }

        }
    }
}

@Composable
private fun MetricsRow(title: String, painterResource: Int) {
    Row(
        modifier = Modifier
            .padding(2.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(LocalCustomColorsPalette.current.customCard.iconSurfaceColor),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 4.dp),
            color = LocalCustomColorsPalette.current.customCard.iconColor
        )
        Icon(
            modifier = Modifier
                .size(18.dp)
                .padding(4.dp),
            painter = painterResource(painterResource),
            contentDescription = null,
            tint = LocalCustomColorsPalette.current.customCard.iconColor
        )
    }
}

