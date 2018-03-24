package org.wjc.maven.util;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class LoginUtilTest {

    @Test
    public void testGenerateHash() {
        assertNotEquals("admin", LoginUtil.generateHash("admin"));
        assertTrue("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918".equals(LoginUtil.generateHash("admin")));
    }

}
