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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SecurePassTest {

    @Test
    void verifyBasicPassword() throws SecurePassException {
        String password = "Password";
        SecurePass sc = new SecurePass();
        String hashed = sc.hash(password);
        assertTrue(sc.verify(password, hashed));
    }

    @Test
    void verifyLongPassword() throws SecurePassException {
        String password = "g-ö>«Þ¡´8MqM42YÄ<,$üjã9ÖÜ5ã[É[òïêÐHJãÀÏfü¹W]hÂ\"³maÕ6Íµ¯JÍñ_Y-L:Úç2-ÁU÷=»_::ã=«æð,`âÁÄãKÇ46";
        SecurePass sc = new SecurePass();
        String hashed = sc.hash(password);
        assertTrue(sc.verify(password, hashed));
    }

    @Test
    void verifyLongerPassword() throws SecurePassException {
        String password = "ÎDuöÛ÷ÁiáUYÒHÓ´ßâ¦¼gE,3>&°ÉZYmAí5êM3eµ\"]öÅX`&ý~ðÀ_+pAs*íÐúÖâ@fPÆj\"ª@èqÕÄÀ¶,M3?=wJ$\\`üe¢Cj¦¤ÃzW×ÝêràpTªí«fS:ÄðÒ½¦Wak>ûwoæ}«Úg]ÙÈL%ãh÷ò'74§â{èîÎvª÷ká¸»ví%ç¥¬aÝ¾ÄZÛ_þv®ãQ¦ßYãDåÒÈ»&4L¸Ã4f]õ5Ä¼ium×VQÀcòò«®yAÕW><âPA:Ãó$Ö:(vÝÚ]sx\"ëV¨EíßÃ]È{âÖÉ5'È7¦ÝNgVL¢^Qc'¬Kßê";
        SecurePass sc = new SecurePass();
        String hashed = sc.hash(password);
        assertTrue(sc.verify(password, hashed));
    }
}
