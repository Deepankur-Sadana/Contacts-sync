package com.phonepe.contactsync.presentation


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.phonepe.contactsync.domain.usecase.SyncContactsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*
import org.junit.Assert.*

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: MainViewModel
    private lateinit var syncContactsUseCase: SyncContactsUseCase

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        syncContactsUseCase = mock()
        viewModel = MainViewModel(syncContactsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `syncContacts success updates status to Success`() = runTest {
        // Given
        whenever(syncContactsUseCase.invoke()).thenReturn(Unit)

        // When
        viewModel.syncContacts()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(SyncStatus.Success, viewModel.syncStatus.value)
        verify(syncContactsUseCase).invoke()
    }

    @Test
    fun `syncContacts failure updates status to Error`() = runTest {
        // Given
        val errorMessage = "Sync failed"
        whenever(syncContactsUseCase.invoke()).thenThrow(RuntimeException(errorMessage))

        // When
        viewModel.syncContacts()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val status = viewModel.syncStatus.value
        assertTrue(status is SyncStatus.Error)
        assertEquals(errorMessage, (status as SyncStatus.Error).message)
        verify(syncContactsUseCase).invoke()
    }
}