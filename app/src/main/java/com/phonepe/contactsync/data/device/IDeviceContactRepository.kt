package com.phonepe.contactsync.data.device

import com.phonepe.contactsync.domain.model.DeviceContact
import javax.inject.Inject

interface IDeviceContactRepository{
    fun getAllDeviceContacts(): List<DeviceContact>
}

class DeviceContactRepositoryImpl @Inject constructor(
    private val deviceContactProvider : IDeviceContactProvider
) : IDeviceContactRepository {
    override fun getAllDeviceContacts(): List<DeviceContact> {
        return deviceContactProvider.getDeviceContacts(DeviceContactProvider.OrderBy.PHONE_NUMBER, Int.MAX_VALUE, 0)
    }

}