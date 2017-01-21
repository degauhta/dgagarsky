package ru.job4j;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ChatTest class.
 *
 * @author Denis
 * @since 21.01.2017
 */
public class ChatTest {
    /**
     * Test chat.
     *
     * @throws IOException possible exception.
     */
    @Test
    public void whenThen() throws IOException {
        String[] answers = new String[]{"q", "pause", "a", "b", "continue", "z", "quit"};
        Chat chat = new Chat(new StubInput(answers));
        File chatFile = new File(getClass().getClassLoader().getResource("EnglishProverbs.txt").getFile());
        File logFile = new File(chatFile.getParent() + "\\ChatLog.txt");
        File expectedLog = new File(getClass().getClassLoader().getResource("ExpectedLog.txt").getFile());
        chat.startChat(chatFile, logFile);
        assertThat(FileUtils.contentEquals(expectedLog, logFile), is(true));
    }
}