package com.binary.mobile.permissions

interface PermissionManager {
    suspend fun checkPermission(permission: Permission): PermissionState
    suspend fun requestPermission(permission: Permission): PermissionState
    suspend fun openAppSettings()
}

suspend fun PermissionManager.isGranted(permission: Permission): Boolean =
    checkPermission(permission) == PermissionState.Granted

suspend fun PermissionManager.requestAndCheck(permission: Permission): Boolean =
    requestPermission(permission) == PermissionState.Granted
