package com.mobileapp.staffgridcompose.ui.eligibleLocation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.mobileapp.staffx.ui.mainActivity.theme.inter

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

            Card(colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.white),
            ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
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

            LazyHorizontalStaggeredGrid(
                modifier = Modifier
                    .height(130.dp)
                    .padding(top = 30.dp),
                rows = StaggeredGridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                content = {
                    items(mainList.filter { it.isSelected.value }) { chipsNames ->
                        ChipColumns(chipsNames) {

                            it.isSelected.value = false
                            selectedNames.value =
                                mainList.filter { it.isSelected.value }.joinToString { it.name }

                        }

                    }
                },
            )


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
