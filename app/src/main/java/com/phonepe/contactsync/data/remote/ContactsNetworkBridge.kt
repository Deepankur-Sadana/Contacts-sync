package com.phonepe.contactsync.data.remote

import com.phonepe.contactsync.domain.model.DeviceContact
import kotlinx.coroutines.delay
import javax.inject.Inject


interface IContactsNetworkBridge {
    suspend fun syncContacts(changes: List<DeviceContact>, deleted: List<String>): Boolean
}

class ContactsNetworkBridge @Inject constructor(

) : IContactsNetworkBridge{

    override suspend fun syncContacts(changes: List<DeviceContact>, deleted: List<String>): Boolean {
        // Stub implementation
        delay(1000) // Simulate network delay
        return true
    }
}