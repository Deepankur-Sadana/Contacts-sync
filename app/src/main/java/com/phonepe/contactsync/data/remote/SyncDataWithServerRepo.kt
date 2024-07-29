package com.phonepe.contactsync.data.remote

import com.phonepe.contactsync.domain.model.DeviceContact
import javax.inject.Inject

interface SyncDataWithServerRepo {
    suspend fun syncContactsWithServer(changes: List<DeviceContact>, deleted: List<String>) : Boolean
}

class SyncDataWithServerRepoImpl
@Inject constructor(
    private val contactsNetworkBridge: IContactsNetworkBridge
) : SyncDataWithServerRepo {
    override suspend fun syncContactsWithServer(
        changes: List<DeviceContact>,
        deleted: List<String>
    ): Boolean {
        return contactsNetworkBridge.syncContacts(changes, deleted)
    }
}