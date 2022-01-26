/*
 * Copyright (C) 2022 Kingston Dynamics Inc.
 *
 * This file is part of DragonSong
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.kdyncs.dragonsong.common.security;

import org.mindrot.jbcrypt.BCrypt;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hash and verify passwords.
 *
 * TODO: Use passay to implement password rules.
 */
public class SecurePass {

    private final MessageDigest digest;

    public SecurePass() throws SecurePassException {

        try {
            this.digest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new SecurePassException("SYSTEM ERROR");
        }
    }

    /**
     * Hash Provided Password
     *
     * Using the users provided password we will hash it using SHA-512 and then run that through JBCrypt's hash password
     * function. This allows us to take in a password of arbitrary length, hash it, and salt it, for storage within a
     * database for later with with verification.
     */
    public String hash(String password) {

        // Hash password
        String intermediate = digest(password);

        // Hash Password using BCrypt which is secure.
        // Hashes generated have a maximum length of 60
        return BCrypt.hashpw(intermediate, BCrypt.gensalt());
    }

    /**
     * Verify Hashed Password
     *
     * Using the users provided password we will hash it using SHA-512 and then run that through JBCrypt's verification
     * function. This allows us to verify whether the entered password is a match for what we have on file and also
     * allows us to use an arbitrary password length.
     */
    public Boolean verify(String password, String hashed) {

        // Hash password
        String intermediate = digest(password);

        // Check password using BCrypt
        return BCrypt.checkpw(intermediate, hashed);
    }

    /**
     * Hash password using SHA-512.
     *
     * By hashing the password first with SHA-512 we essentially provide a method for which we can allow passwords of
     * arbitrary length. The reason to do this is that the limit for BCrypt is somewhere in the neighbourhood of
     * 60 characters which puts an upper limit on how long a password may be. This allows us to avoid that.
     */
    protected String digest(String password) {

        // Reset Digest if Necessary
        digest.reset();

        // Push password into Digest for hashing
        digest.update(password.getBytes(StandardCharsets.UTF_8));

        // Hash password
        return String.format("%0128x", new BigInteger(1, digest.digest()));
    }
}
