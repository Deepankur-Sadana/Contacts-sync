package com.phonepe.contactsync.di

import com.phonepe.contactsync.data.remote.SyncDataWithServerRepo
import com.phonepe.contactsync.data.remote.SyncDataWithServerRepoImpl
import com.phonepe.contactsync.data.remote.ContactsNetworkBridge
import com.phonepe.contactsync.data.remote.IContactsNetworkBridge
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideIContactsNetworkBridge(
        contactsNetworkBridge: IContactsNetworkBridge
    ): IContactsNetworkBridge {
        return ContactsNetworkBridge()
    }

    @Provides
    @Singleton
    fun provideSyncDataWithServerRepo(
        contactsNetworkBridge: IContactsNetworkBridge
    ): SyncDataWithServerRepo {
        return SyncDataWithServerRepoImpl(contactsNetworkBridge)
    }

}