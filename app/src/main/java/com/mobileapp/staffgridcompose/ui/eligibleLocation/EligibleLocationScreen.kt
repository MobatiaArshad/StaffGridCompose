package com.mobileapp.staffgridcompose.ui.eligibleLocation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mobileapp.staffgridcompose.R
import com.mobileapp.staffgridcompose.ui.eligibleLocation.model.EligibleLocModel
import com.mobileapp.staffgridcompose.ui.eligibleLocation.model.EligibleResultData
import com.mobileapp.staffgridcompose.ui.eligibleLocation.model.EligibleResultModel
import com.mobileapp.staffx.ui.mainActivity.theme.inter
import com.mobileapp.staffx.ui.mainActivity.theme.interBold
import com.mobileapp.staffx.ui.mainActivity.theme.interSemiBold

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun EligibleLocScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: EligibleLocViewModel = viewModel()
) {
    val context = LocalContext.current
    val showDialog = remember { mutableStateOf(false) }
    var mainList = remember { eligibleList().toMutableStateList() }
    val selectedNames = remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Select Region to Work at",
                fontSize = 14.sp,
                fontFamily = inter,
                fontWeight = FontWeight(700),
                color = colorResource(id = R.color.theme_yellow),
                modifier = Modifier.padding(top = 20.dp)
            )

            Card(
                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.white),),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 15.dp)
                    .width(331.dp)
                    .height(52.dp)
                    .background(
                        color = colorResource(id = R.color.outline_stroke_color),
                        shape = RoundedCornerShape(size = 5.dp)
                    )
                    .border(
                        width = 2.dp,
                        shape = RoundedCornerShape(size = 5.dp),
                        color = colorResource(id = R.color.outline_stroke_color)
                    )
                    .clickable {
                        showDialog.value = !showDialog.value
                    }) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 20.dp, end = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (selectedNames.value == "") "Select" else selectedNames.value,
                        fontSize = 15.sp,
                        fontFamily = inter,
                        fontWeight = FontWeight(600),
                        color = colorResource(id = R.color.black),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.width(230.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.drop_down_black),
                        contentDescription = "",
                        modifier = Modifier
                            .width(25.dp)
                            .height(25.dp)
                    )
                }
            }

            LazyColumn() {

                // LazyRow still able to be use, since it has different orientation with parent LazyColumn
                item {
                    LazyHorizontalStaggeredGrid(
                        modifier = Modifier
                            .height(130.dp)
                            .padding(top = 15.dp),
                        rows = StaggeredGridCells.Fixed(3),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        content = {
                            items(mainList.filter { it.isSelected.value }) { chipsNames ->
                                ChipColumns(chipsNames) {
                                    it.isSelected.value = false
                                    selectedNames.value = mainList.filter { it.isSelected.value }
                                        .joinToString { it.name }
                                }
                            }
                        },
                    )
                }

                items(getEligibleResultList()) { post ->
                    EligibleResultTitle(post)
                }
            }



            if (showDialog.value) {
                DialogWithLazyColumn(
                    showDialog.value, mainList
                ) { isOpen, updatedList ->
                    showDialog.value = isOpen
                    mainList = updatedList.toMutableStateList()
                    selectedNames.value =
                        updatedList.filter { it.isSelected.value }.joinToString { it.name }
                }
            }


        }
    }

}

@Preview(showBackground = true)
@Composable
fun ChipColumns(
    data: EligibleLocModel = EligibleLocModel(), delete: (EligibleLocModel) -> Unit = {}
) {
    Card(colors = CardDefaults.cardColors(
        containerColor = colorResource(id = R.color.white)
    ), modifier = Modifier
        .padding(3.dp)
        .height(30.dp)
        .background(
            color = colorResource(id = R.color.outline_stroke_color),
            shape = RoundedCornerShape(size = 5.dp)
        )
        .border(
            width = 2.dp,
            shape = RoundedCornerShape(size = 5.dp),
            color = colorResource(id = R.color.outline_stroke_color)
        )
        .clickable {
            delete.invoke(data)
        }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(4.dp)
        ) {
            Text(
                text = data.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                fontFamily = inter,
                fontWeight = FontWeight(600),
                color = colorResource(id = R.color.black),
                modifier = Modifier.padding(start = 12.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.close_chip),
                contentDescription = "",
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DialogWithLazyColumn(
    isOpen: Boolean = false,
    eligibleList: MutableList<EligibleLocModel> = mutableListOf(EligibleLocModel("name")),
    onDismissRequest: (Boolean, MutableList<EligibleLocModel>) -> Unit = { _, _ -> },
) {

    Dialog(
        onDismissRequest = { onDismissRequest.invoke(isOpen, eligibleList) },

        ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colors.surface,
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .width(1270.dp)
            ) {

                LazyColumn(
                    modifier = Modifier.height(500.dp)
                ) {
                    items(eligibleList.toMutableStateList()) { items ->
                        Row(verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp)
                                .clickable {
                                    items.isSelected.value = !items.isSelected.value
                                }) {
                            Checkbox(checked = items.isSelected.value,
                                onCheckedChange = { isChecked ->
                                    items.isSelected.value = !items.isSelected.value
                                })

                            Text(
                                text = items.name, maxLines = 1, overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }

                Button(onClick = {
                    onDismissRequest.invoke(!isOpen, eligibleList)
                }) {
                    Text(text = "Close")
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun EligibleResultTitle(
    post: EligibleResultModel = EligibleResultModel()
) {

    val isExpanded = remember {
        mutableStateOf(false)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white),
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 7.5.dp, top = 23.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                    post.isExpanded.value = !post.isExpanded.value
                }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp, end = 25.dp, top = 20.dp, bottom = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,

                ) {
                    Text(
                        text = post.title,
                        fontSize = 14.sp,
                        lineHeight = 16.87.sp,
                        fontFamily = inter,
                        fontWeight = FontWeight(700),
                        color = colorResource(id = R.color.black),
                    )
                    Image(painter = painterResource(id = R.drawable.drop_down_black), contentDescription = "")
                }

                AnimatedVisibility(visible = post.isExpanded.value) {
                    InnerListCell()
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InnerListCell() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(33.dp)
                    .background(color = Color(0xFFF3F3F3)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,

            ) {
                Image(painter = painterResource(id = R.drawable.home_ico), contentDescription = "",
                    modifier = Modifier.padding(start = 18.dp))
                Text(
                    text = "Aston Early",
                    fontSize = 14.sp,
                    lineHeight = 16.87.sp,
                    fontFamily = inter,
                    fontWeight = FontWeight(600),
                    color = colorResource(id = R.color.black),
                    modifier = Modifier.padding(start = 5.dp)
                )
            }

            Text(
                text = "Chester Township Regional",
                fontSize = 14.sp,
                lineHeight = 16.87.sp,
                fontFamily = inter,
                fontWeight = FontWeight(400),
                color = colorResource(id = R.color.black),
                modifier = Modifier.padding(top = 9.dp, start = 20.dp)
            )

            Text(
                text = "COVID special rate available",
                fontSize = 12.sp,
                fontFamily = interBold,
                fontWeight = FontWeight(700),
                color = colorResource(id = R.color.black),
                modifier = Modifier.padding(top = 15.dp, start = 20.dp)
            )

            Text(
                text = "$3-$90/hour\nLast Updated on 10/26/2021",
                fontSize = 10.sp,
                fontFamily = interSemiBold,
                fontWeight = FontWeight(500),
                color = colorResource(id = R.color.black),
                modifier = Modifier.padding(top = 10.dp, start = 20.dp)
            )

            Text(
                text = "Travel Rate Applicable\n(Stipend Applicable)",
                fontSize = 15.sp,
                fontFamily = interBold,
                fontWeight = FontWeight(700),
                color = colorResource(id = R.color.black),
                modifier = Modifier.padding(top = 15.dp, start = 20.dp)
            )

            Text(
                text = "$20-$40/hour\nLast Updated on 06/29/2022 ",
                fontSize = 10.sp,
                fontFamily = interSemiBold,
                fontWeight = FontWeight(500),
                color = colorResource(id = R.color.black),
                modifier = Modifier.padding(top = 10.dp, start = 20.dp)
            )

            Row(
                horizontalArrangement = Arrangement.Absolute.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 15.dp, start = 20.dp)
                    .width(84.dp)
                    .height(28.dp)
                    .background(
                        color = colorResource(id = R.color.theme_yellow),
                        shape = RoundedCornerShape(size = 5.dp)
                    )
            ) {
                Text(text = "Apply",
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    fontFamily = inter,
                    fontWeight = FontWeight(700),
                    color = colorResource(id = R.color.black),
                )

            }

        }
    }
}

fun eligibleList(): MutableList<EligibleLocModel> = mutableListOf(
    EligibleLocModel(
        "Haimi Zainab"
    ), EligibleLocModel(
        "Aysha Huda"
    ), EligibleLocModel(
        "Feliza"
    ), EligibleLocModel(
        "Fathima Rushda"
    ), EligibleLocModel(
        "Amna"
    ), EligibleLocModel(
        "Fathima Hiza"
    ), EligibleLocModel(
        "Pachu"
    ), EligibleLocModel(
        "Fathima Naja"
    ), EligibleLocModel(
        "Fathima Nada"
    ), EligibleLocModel(
        "Mehfi Shaji"
    ), EligibleLocModel(
        "Manha"
    )
)

fun getEligibleResultList(): MutableList<EligibleResultModel> = mutableListOf(
    EligibleResultModel(
        title = "Aston Early",
        mutableListOf(
            EligibleResultData(
                name = "Astom Early Childhood"
            ),
            EligibleResultData(
                name = "Chester Township"
            ),
        )
    ),EligibleResultModel(
        title = "Aston Early",
        mutableListOf(
            EligibleResultData(
                name = "Astom Early Childhood"
            ),
            EligibleResultData(
                name = "Chester Township"
            ),
        )
    ),EligibleResultModel(
        title = "Aston Early",
        mutableListOf(
            EligibleResultData(
                name = "Astom Early Childhood"
            ),
            EligibleResultData(
                name = "Chester Township"
            ),
        )
    ),EligibleResultModel(
        title = "Aston Early",
        mutableListOf(
            EligibleResultData(
                name = "Astom Early Childhood"
            ),
            EligibleResultData(
                name = "Chester Township"
            ),
        )
    ),
)
