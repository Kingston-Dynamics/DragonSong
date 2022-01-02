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
import java.security.NoSuchAlgorithmException;

public class SecurePass {

    private final SHA512 SHA;

    public SecurePass() throws SecurePassException {

        try {
            SHA = new SHA512();
        } catch (NoSuchAlgorithmException e) {
            throw new SecurePassException("SYSTEM ERROR");
        }
    }

    public String hash(String password) {

        // Hash Using SHA512 to handle super long passwords
        String intermediate = SHA.hash(password);

        // Hash Password using BCrypt which is secure.
        // Hashes generated have a maximum length of 60
        return BCrypt.hashpw(intermediate, BCrypt.gensalt());
    }

    public Boolean verify(String password, String hashed) {

        // Hash Using SHA512 to handle super long passwords
        String intermediate = SHA.hash(password);

        // Check password using BCrypt
        return BCrypt.checkpw(intermediate, hashed);
    }
}
