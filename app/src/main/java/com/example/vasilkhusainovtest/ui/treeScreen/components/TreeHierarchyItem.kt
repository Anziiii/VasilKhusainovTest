package com.example.vasilkhusainovtest.ui.treeScreen.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.vasilkhusainovtest.domain.models.Type

@Preview
@Composable
fun PreviewTreeHierarchyFileItem() {
    TreeHierarchyItem(
        type = Type.File,
        titleName = "12321",
        titleSize = "10 KB",
        eventDirectory = fun() {},
        eventFile = fun() {}
    )
}

@Preview
@Composable
fun PreviewTreeHierarchyFolderItem() {
    TreeHierarchyItem(
        type = Type.Folder,
        titleName = "12321",
        titleSize = "10 KB",
        eventDirectory = fun() {},
        eventFile = fun() {}
    )
}

@Composable
fun TreeHierarchyItem(
    type: Type,
    titleName: String?,
    titleSize: String,
    eventDirectory:()->Unit,
    eventFile:()->Unit,
){
    when(type){
        Type.Folder -> {
            FolderItem(titleName, eventDirectory)
        }
        Type.File -> {
            FileItem(titleName, titleSize, eventFile)
        }

        Type.Unknown -> {

        }
    }
}
