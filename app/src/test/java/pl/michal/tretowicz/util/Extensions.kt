package pl.michal.tretowicz.util

import org.mockito.Mockito

/**
 * Wrapper for Mockito.any(), used to avoid 'any() must not be null' exception
 */
fun <T> any(): T = Mockito.any<T>()