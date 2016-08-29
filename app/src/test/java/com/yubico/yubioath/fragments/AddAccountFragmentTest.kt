/*
 * Copyright (c) 2013, Yubico AB.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 *  Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following
 *   disclaimer in the documentation and/or other materials provided
 *   with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 * TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF
 * THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 */

package com.yubico.yubioath.fragments

import org.junit.Test
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config

import org.junit.Assert.assertEquals
import org.junit.Assert.fail

import android.net.Uri

import com.yubico.yubioath.BuildConfig
import com.yubico.yubioath.model.CredentialData

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21))
class AddAccountFragmentTest {

    @Test
    fun testParseUriBad() {
        val failingUris = listOf("http://example.com/", "otpauth://foobar?secret=kaka", "foobar", "otpauth://totp/Example:alice@google.com?secret=balhonga1&issuer=Example", "otpauth:///foo:mallory@example.com?secret=kaka")
        for (uri in failingUris) {
            try {
                CredentialData(uri)
                fail("URL $uri did not fail")
            } catch (e: IllegalArgumentException) {
                // Should fail.
            }

        }
    }

    @Test
    fun testParseUriGood() {
        val goodUris = listOf("otpauth://totp/Example:alice@google.com?secret=JBSWY3DPEHPK3PXP&issuer=Example", "otpauth://hotp/foobar:bob@example.com?secret=blahonga2")
        for (uri in goodUris) {
            try {
                CredentialData(uri)
            } catch (e: IllegalArgumentException) {
                System.err.println("Failed at uri: " + uri)
                throw e
            }
        }
    }
}
