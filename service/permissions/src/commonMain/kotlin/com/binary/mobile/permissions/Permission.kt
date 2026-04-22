package com.binary.mobile.permissions

enum class Permission {
    Camera,
    Gallery,
    Microphone,
    LocationFine,
    LocationCoarse,
    Contacts,
    PushNotification,
    Bluetooth,
}

enum class PermissionState {
    Granted,
    Denied,
    DeniedAlways,
    NotDetermined,
}
