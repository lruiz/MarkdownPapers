package org.tautua.markdownpapers.cli;

import org.junit.*;

/**
 * Unit test for simple Main.
 */
public class MainTest {

    @Test
    public void usage() throws Exception {
        Main main = new Main(new String[0]);
        main.execute();
    }

    @Test
    public void specificFileAsInput() throws Exception {
        Main main = new Main(new String[]{"target/test-classes/example.md", "other"});
        main.execute();
    }

    @Test
    public void directoryAsInput() throws Exception {
        Main main = new Main(new String[]{"target/test-classes/directory"});
        main.execute();
    }

}
