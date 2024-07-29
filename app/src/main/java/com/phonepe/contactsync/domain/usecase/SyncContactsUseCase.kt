package com.phonepe.contactsync.domain.usecase

import com.phonepe.contactsync.data.device.IDeviceContactRepository
import com.phonepe.contactsync.data.local.ContactDao
import com.phonepe.contactsync.data.local.ContactEntity
import com.phonepe.contactsync.data.remote.SyncDataWithServerRepo
import com.phonepe.contactsync.domain.model.DeviceContact
import javax.inject.Inject


class SyncContactsUseCase @Inject constructor(
    private val syncWithServerRepo: SyncDataWithServerRepo,
    private val fetchDataFromOS: IDeviceContactRepository,
    private val diffCalculator: IContactsDiffCalculator,
    private val contactDao: ContactDao,
) {
    suspend operator fun invoke() {
        val contactsFromOS = fetchDataFromOS.getAllDeviceContacts()
        val localDbContacts = contactDao.getAllContacts()
        val calculatedDiff = diffCalculator.calculateDiff(contactsFromOS, localDbContacts)
        val serverResponse = syncWithServerRepo.syncContactsWithServer(
            changes = calculatedDiff.changes,
            deleted = calculatedDiff.deleted
        )
        if (serverResponse) {
            markContactsAsSynced(calculatedDiff)
        } else {
            TODO()
        }
    }

    private suspend fun markContactsAsSynced(contactsDiff: ContactsDiff) {
        val entityList  = ArrayList<ContactEntity>()
        contactsDiff.changes.forEach { editedContact ->
            entityList.add(toContactEntity(editedContact))
        }
        contactDao.insertContacts(entityList)

        for (i in contactsDiff.deleted.indices) {
            contactDao.deleteContact(contactsDiff.deleted[i])
        }
    }

    private fun toContactEntity(deviceContact: DeviceContact) : ContactEntity {
        TODO()
    }
}

data class ContactsDiff(
    val changes: List<DeviceContact>, val deleted: List<String>
)

interface IContactsDiffCalculator {
    fun calculateDiff(
        contactsFromOS: List<DeviceContact>,
        localDbContacts: List<ContactEntity>
    ): ContactsDiff
}